/**
 * 
 */
package com.ss.task.shop.details.geo.service;

import java.util.Map;

import com.ss.task.geo.response.GeoCodingResponse;

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
	
	/**
	 * This method will call Google GeoCoding location details using longitude and latitude of location/address.
	 * @param latlngParam
	 * @return
	 * @throws RuntimeException
	 */
	GeoCodingResponse getNearestShopDetails(final String latlngParam) throws RuntimeException;
}
