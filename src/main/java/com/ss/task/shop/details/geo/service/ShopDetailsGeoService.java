/**
 * 
 */
package com.ss.task.shop.details.geo.service;

import java.util.Map;

/**
 * All Google methods related to Google GeoCoding service
 * @author Saurav Singh
 *
 */
public interface ShopDetailsGeoService {

	/**
	 * This method will call Google GeoCoding location api to find longitude and latitude of location/address.
	 * @param address
	 * @return
	 * @throws RuntimeException
	 */
	Map<String, String> getLngLatByAddress(final String address) throws RuntimeException;
}
