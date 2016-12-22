/**
 * 
 */
package com.ss.task.shop.details.request.vo;

import java.io.Serializable;

/**
 * @author saurav singh
 *
 */
public class ShopDetailsVo implements Serializable {

	private static final long serialVersionUID = -7168237230932282167L;

	private String shopName;
	private String shopAddress;
	private Integer shopPostalCode;
	private String shopLng;
	private String shopLat;

	public ShopDetailsVo(String shopName, String shopAddress, Integer shopPostalCode, String shopLat, String shopLng) {
		this.shopName = shopName;
		this.shopAddress = shopAddress;
		this.shopPostalCode = shopPostalCode;
		this.shopLat = shopLat;
		this.shopLng = shopLng;
	}

	/**
	 * @return the shopName
	 */
	public String getShopName() {
		return shopName;
	}

	/**
	 * @return the shopAddress
	 */
	public String getShopAddress() {
		return shopAddress;
	}

	/**
	 * @return the shopPostalCode
	 */
	public Integer getShopPostalCode() {
		return shopPostalCode;
	}

	/**
	 * @return the shopLng
	 */
	public String getShopLng() {
		return shopLng;
	}

	/**
	 * @return the shopLat
	 */
	public String getShopLat() {
		return shopLat;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("shopName=").append(shopName).append(", shopAddress=").append(shopAddress).append(", shopPostalCode=")
				.append(shopPostalCode).append(", shopLng=").append(shopLng).append(", shopLat=").append(shopLat);
		return builder.toString();
	}
}
