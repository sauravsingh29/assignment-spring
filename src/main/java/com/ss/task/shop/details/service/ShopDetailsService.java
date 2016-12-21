/**
 * 
 */
package com.ss.task.shop.details.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import static com.ss.task.dao.shop.details.constants.ShopDetailsConstants.*;
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
		final ShopDetailsVo shopDetailsVo = new ShopDetailsVo(shopDetails.getShopName(), address.getAddress(), address.getAddressPostCode(),
				latLngMap.get(LATITUDE_KEY), latLngMap.get(LONGITUDE_KEY));
		return shopDetailsDao.saveShopDetails(shopDetailsVo);
	}
}
