/**
 * 
 */
package com.ss.task.shop.details.geo.response;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

/**
 * @author Saurav Singh
 *
 */
public class GeoLngLatResponse implements Serializable {

	private static final long serialVersionUID = -8345331400017438593L;
	
	private Map<String, Object> geocodeResponse = new TreeMap<String, Object>();

	@JsonAnyGetter
	public Map<String, Object> get() {
		return geocodeResponse;
	}

	@JsonAnySetter
	public void set(String name, Object value) {
		geocodeResponse.put(name, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Response [responseMap=").append(geocodeResponse).append("]");
		return builder.toString();
	}
}
