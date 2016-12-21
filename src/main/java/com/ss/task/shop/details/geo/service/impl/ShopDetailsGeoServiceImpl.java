package com.ss.task.shop.details.geo.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.ss.task.shop.details.geo.response.GeoLngLatResponse;
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
			final GeoLngLatResponse geoLngLatResponse = restTemplate.getForObject(url, GeoLngLatResponse.class);
			final Map<String, String> returnMap = new HashMap<>();
			buildReturnMapFromResponse(geoLngLatResponse.get(), returnMap);
			return returnMap;
		} catch (RestClientException e) {
			LOGGER.error("Failed to find location lng lat. Exception {}", e);
			throw new RuntimeException("Failed to find location latitude/longitude", e.getCause());
		}
	}

	@SuppressWarnings("unchecked")
	private void buildReturnMapFromResponse(final Map<String, Object> geoLngLatResponseMap, final Map<String, String> returnMap) {
		for (Entry<String, Object> entry : geoLngLatResponseMap.entrySet()) {
			if (entry.getValue() instanceof Map) {
				buildReturnMapFromResponse((Map<String, Object>) entry.getValue(), returnMap);
			}
			if (entry.getKey().equalsIgnoreCase("location") && entry.getValue() instanceof Map) {
				Map<String, String> latLngMap = (Map<String, String>) entry.getValue();
				returnMap.putAll(latLngMap);
				break;
			}
		}
	}

}
