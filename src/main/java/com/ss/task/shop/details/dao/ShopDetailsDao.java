/**
 * 
 */
package com.ss.task.shop.details.dao;

import com.ss.task.shop.details.request.vo.ShopDetailsVo;

/**
 * @author saurav singh
 *
 */
public interface ShopDetailsDao {

	/**
	 * Method is use to save shop information to in memory H2 database.
	 * @param shopDetailsVo
	 * @return
	 * @throws Exception 
	 */
	int saveShopDetails(final ShopDetailsVo shopDetailsVo) throws Exception;
}
