/**
 * 
 */
package com.ss.task.dao.shop.details.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ss.task.shop.details.dao.ShopDetailsDao;

/**
 * @author Saurav Singh
 *
 */
@Repository("shopDetailsDao")
public class ShopDetailsDaoImpl implements ShopDetailsDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(ShopDetailsDaoImpl.class);

	@Autowired
	@Qualifier("jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ss.task.shop.details.dao.ShopDetailsDao#saveShopDetails(java.lang.String,
	 * java.lang.String, int)
	 */
	@Override
	public int saveShopDetails(final String shopName, final String shopAddNum, final int shopPostCode)
			throws Exception {
		Object[] params = { shopName, shopAddNum, shopPostCode };
		LOGGER.debug("Starting method {} with params - {} ", "saveShopDetails", params);
		String saveShopDetailsQuery = "insert into shop_details values(?, ?, ?)";
		return jdbcTemplate.update(saveShopDetailsQuery, params);
	}

}
