/**
 * 
 */
package com.ss.task.shop.details.dao;

/**
 * @author saurav singh
 *
 */
public interface ShopDetailsDao {

	/**
	 * Method is use to save shop information to in memory H2 database.
	 * 
	 * @param shopName  Name of Shop
	 * @param shopAddNum Shop Address Number
	 * @param shopPostCode Shop Postal Code
	 * @return
	 * @throws Exception 
	 */
	int saveShopDetails(final String shopName, final String shopAddNum, final int shopPostCode) throws Exception;
}
