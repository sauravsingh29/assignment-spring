package com.ss.task.shop.details.request;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

public class ShopAddress implements Serializable {

	private static final long serialVersionUID = -8816046099625445967L;

	@JsonProperty(value = "address", required = true)
	@ApiModelProperty(notes = "The sub-locality, locality, area, city, states where shop is situataed", required = true)
	private String address;

	@JsonProperty(value = "postCode", required = true)
	@ApiModelProperty(notes = "Shop location zip code/postal code", required = true)
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