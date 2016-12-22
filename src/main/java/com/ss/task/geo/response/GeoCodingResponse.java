package com.ss.task.geo.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GeoCodingResponse {

	@JsonProperty("results")
	private Results[] results;
	
	@JsonProperty("status")
	private String status;

	/**
	 * @return the results
	 */
	public Results[] getResults() {
		return results;
	}

	/**
	 * @param results the results to set
	 */
	public void setResults(Results[] results) {
		this.results = results;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	

}
