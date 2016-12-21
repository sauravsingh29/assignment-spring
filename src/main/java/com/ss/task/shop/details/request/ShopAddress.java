package com.ss.task.shop.details.request;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ShopAddress implements Serializable{

	private static final long serialVersionUID = -8816046099625445967L;

	@JsonProperty("number")
	private String addressNumber;

	@JsonProperty("postCode")
	private Integer addressPostCode;

	/**
	 * @return the addressNumber
	 */
	public String getAddressNumber() {
		return addressNumber;
	}

	/**
	 * @param addressNumber the addressNumber to set
	 */
	public void setAddressNumber(String addressNumber) {
		this.addressNumber = addressNumber;
	}

	/**
	 * @return the addressPostCode
	 */
	public Integer getAddressPostCode() {
		return addressPostCode;
	}

	/**
	 * @param addressPostCode the addressPostCode to set
	 */
	public void setAddressPostCode(Integer addressPostCode) {
		this.addressPostCode = addressPostCode;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ShopAddress [addressNumber=").append(addressNumber).append(", addressPostCode=")
				.append(addressPostCode).append("]");
		return builder.toString();
	}
	
}