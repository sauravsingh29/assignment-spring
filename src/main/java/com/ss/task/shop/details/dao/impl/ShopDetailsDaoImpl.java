/**
 * 
 */
package com.ss.task.shop.details.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ss.task.shop.details.dao.ShopDetailsDao;
import com.ss.task.shop.details.request.vo.ShopDetailsVo;

/**
 * @author Saurav Singh
 *
 */
@Repository("shopDetailsDao")
public class ShopDetailsDaoImpl implements ShopDetailsDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(ShopDetailsDaoImpl.class);
	public static final String SAVE_SHOP_DETAILS = "insert into shop_details values(?, ?, ?, ?, ?)";

	@Autowired
	@Qualifier("jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	@Override
	public int saveShopDetails(final ShopDetailsVo shopDetailsVo) throws Exception {
		LOGGER.debug("Starting method {} with params - {} ", "saveShopDetails", shopDetailsVo.toString());
		Object[] params = { shopDetailsVo.getShopName(), shopDetailsVo.getShopAddress(), shopDetailsVo.getShopPostalCode(),
				shopDetailsVo.getShopLat(), shopDetailsVo.getShopLng() };
		try{
			return jdbcTemplate.update(SAVE_SHOP_DETAILS, params);
		} catch (RuntimeException e){
			throw new RuntimeException("Unable to add Shop address at this moment! Please check request or try later.", e.getCause());
		}
	}

}
