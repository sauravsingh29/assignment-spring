/**
 * 
 */
package com.ss.task.shop.details.service;

import static com.ss.task.dao.shop.details.constants.ShopDetailsConstants.LATITUDE_KEY;
import static com.ss.task.dao.shop.details.constants.ShopDetailsConstants.LONGITUDE_KEY;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ss.task.geo.response.GeoCodingResponse;
import com.ss.task.geo.response.Results;
import com.ss.task.shop.details.dao.ShopDetailsDao;
import com.ss.task.shop.details.geo.service.ShopDetailsGeoService;
import com.ss.task.shop.details.request.ShopAddress;
import com.ss.task.shop.details.request.ShopDetails;
import com.ss.task.shop.details.request.vo.ShopDetailsVo;

/**
 * @author Saurav Singh
 *
 */
@Service("shopDetailsService")
public class ShopDetailsService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ShopDetailsService.class);

	@Autowired
	@Qualifier("shopDetailsDao")
	private ShopDetailsDao shopDetailsDao;

	@Autowired
	@Qualifier("shopDetailsGeoService")
	private ShopDetailsGeoService shopDetailsGeoService;

	private static final List<ShopDetailsVo> SHOP_LIST = new CopyOnWriteArrayList<>();

	/**
	 * Service to find latitude and longitude of location and save shop details
	 * 
	 * @param shopDetails
	 * @return
	 * @throws Exception
	 */
	public int saveShopDetails(final ShopDetails shopDetails) throws Exception {
		LOGGER.debug("Started method {} with params - {}", "saveShopDetails", shopDetails.toString());
		final ShopAddress address = shopDetails.getShopAddress();
		final Map<String, String> latLngMap = shopDetailsGeoService.getLngLatByAddress(address.getAddress());
		if (!latLngMap.isEmpty()) {
			final ShopDetailsVo shopDetailsVo = new ShopDetailsVo(shopDetails.getShopName(), address.getAddress(), address.getAddressPostCode(),
					latLngMap.get(LATITUDE_KEY), latLngMap.get(LONGITUDE_KEY));
			SHOP_LIST.add(shopDetailsVo);
			return shopDetailsDao.saveShopDetails(shopDetailsVo);
		}
		return 0;
	}

	public List<ShopDetailsVo> findShopNearByLatLng(final String custLat, final String custLng) throws Exception {
		LOGGER.debug("Started method {} with params - {}", "findShopNearByLatLng", new Object[] { custLat, custLng });
		final String latlngParam = custLat + "," + custLng;
		final GeoCodingResponse codingResponse = shopDetailsGeoService.getNearestShopDetails(latlngParam);
		Results[] results = codingResponse.getResults();
		double inpLat = Double.valueOf(custLat);
		double inpLng = Double.valueOf(custLng);
		final List<ShopDetailsVo> output = new ArrayList<>();
		for (int i = 0; i < results.length; i++) {
			Results result = results[i];
			String formatedAddress = result.getFormattedAddress();
			double lat = result.getGeometry().getLocation().getLat();
			double lng = result.getGeometry().getLocation().getLng();
			for (ShopDetailsVo shopDetailsVo : SHOP_LIST) {
				if (lat == inpLat && lng == inpLng) {
					output.add(shopDetailsVo);
				} else if (formatedAddress.contains(shopDetailsVo.getShopAddress())) {
					output.add(shopDetailsVo);
				}
			}
		}
		return output;
	}
}
