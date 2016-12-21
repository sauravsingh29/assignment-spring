/**
 * 
 */
package com.ss.task.shop.details.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ss.task.shop.details.dao.ShopDetailsDao;
import com.ss.task.shop.details.request.ShopAddress;
import com.ss.task.shop.details.request.ShopDetails;

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

	/**
	 * Service to save shop details
	 * @param shopDetails
	 * @return
	 * @throws Exception
	 */
	public int saveShopDetails(final ShopDetails shopDetails) throws Exception {
		LOGGER.debug("Started method {} with params - {}", "saveShopDetails", shopDetails.toString());
		final ShopAddress address = shopDetails.getShopAddress();
		return shopDetailsDao.saveShopDetails(shopDetails.getShopName(), address.getAddressNumber(),
				address.getAddressPostCode());
	}
}
