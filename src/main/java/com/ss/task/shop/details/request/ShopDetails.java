/**
 * 
 */
package com.ss.task.shop.details.request;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Saurav
 *
 */
@JsonIgnoreProperties(ignoreUnknown = false)
public class ShopDetails implements Serializable {

	private static final long serialVersionUID = -4333559486797401949L;

	@JsonProperty("shopName")
	private String shopName;

	@JsonProperty("shopAddress")
	private ShopAddress shopAddress;

	/**
	 * @return the shopName
	 */
	public String getShopName() {
		return shopName;
	}

	/**
	 * @param shopName
	 *            the shopName to set
	 */
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	/**
	 * @return the shopAddress
	 */
	public ShopAddress getShopAddress() {
		return shopAddress;
	}

	/**
	 * @param shopAddress
	 *            the shopAddress to set
	 */
	public void setShopAddress(ShopAddress shopAddress) {
		this.shopAddress = shopAddress;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ShopDetails [shopName=").append(shopName).append(", shopAddress=").append(shopAddress)
				.append("]");
		return builder.toString();
	}

}
