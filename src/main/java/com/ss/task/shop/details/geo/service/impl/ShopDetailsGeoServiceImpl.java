package com.ss.task.shop.details.geo.service.impl;

import static com.ss.task.dao.shop.details.constants.ShopDetailsConstants.LATITUDE_KEY;
import static com.ss.task.dao.shop.details.constants.ShopDetailsConstants.LONGITUDE_KEY;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.ss.task.geo.response.GeoCodingResponse;
import com.ss.task.geo.response.Results;
import com.ss.task.shop.details.geo.service.ShopDetailsGeoService;

/**
 * @author Saurav Singh
 *
 */
@Service("shopDetailsGeoService")
public class ShopDetailsGeoServiceImpl implements ShopDetailsGeoService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ShopDetailsGeoServiceImpl.class);

	@Autowired
	@Qualifier("restTemplate")
	private RestTemplate restTemplate;

	@Value("${google.lnglat.url}")
	private String geoLatngUrl;

	@Value("${google.location.url}")
	private String geoLocationUrl;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ss.task.shop.details.geo.service.ShopDetailsGeoService#getLngLatByAddress(java.lang.String)
	 */
	@Override
	public Map<String, String> getLngLatByAddress(final String address) throws RuntimeException {
		LOGGER.debug("Started method {} - params {}", "getLngLatByAddress", address);
		final String url = geoLatngUrl + address;
		try {
			final GeoCodingResponse geocodingResult = restTemplate.getForObject(url, GeoCodingResponse.class);
			final Map<String, String> returnMap = new HashMap<>();
			Results[] results = geocodingResult.getResults();
			for (Results res : results) {
				returnMap.put(LATITUDE_KEY, Double.toString(res.getGeometry().getLocation().getLat()));
				returnMap.put(LONGITUDE_KEY, Double.toString(res.getGeometry().getLocation().getLng()));
			}
			return returnMap;
		} catch (RestClientException e) {
			LOGGER.error("Failed to find location lng lat. Exception {}", e);
			throw new RuntimeException("Failed to find latitude/longitude of address", e.getCause());
		}
	}

	@Override
	public GeoCodingResponse getNearestShopDetails(final String latlngParam) throws RuntimeException {
		LOGGER.debug("Started method {} - params {}", "getNearestShopDetails", latlngParam);
		final String url = geoLocationUrl + latlngParam;
		try {
			return restTemplate.getForObject(url, GeoCodingResponse.class);
		} catch (RestClientException e) {
			LOGGER.error("Failed to find location lng lat. Exception {}", e);
			throw new RuntimeException("Failed to find nearest locations of latitude/longitude", e.getCause());
		}
	}
}
