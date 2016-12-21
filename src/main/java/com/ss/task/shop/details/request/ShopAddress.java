package com.ss.task.shop.details.request;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ShopAddress implements Serializable {

	private static final long serialVersionUID = -8816046099625445967L;

	@JsonProperty("address")
	private String address;

	@JsonProperty("postCode")
	private Integer addressPostCode;

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the addressPostCode
	 */
	public Integer getAddressPostCode() {
		return addressPostCode;
	}

	/**
	 * @param addressPostCode
	 *            the addressPostCode to set
	 */
	public void setAddressPostCode(Integer addressPostCode) {
		this.addressPostCode = addressPostCode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ShopAddress [address=").append(address).append(", addressPostCode=").append(addressPostCode).append("]");
		return builder.toString();
	}

}