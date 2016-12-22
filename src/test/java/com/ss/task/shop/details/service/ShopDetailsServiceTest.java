/**
 * 
 */
package com.ss.task.shop.details.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.ss.task.shop.details.dao.ShopDetailsDao;
import com.ss.task.shop.details.geo.service.ShopDetailsGeoService;
import com.ss.task.shop.details.request.ShopAddress;
import com.ss.task.shop.details.request.ShopDetails;
import com.ss.task.shop.details.request.vo.ShopDetailsVo;

/**
 * @author Saurav Singh
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class ShopDetailsServiceTest {

	@InjectMocks
	private ShopDetailsService shopDetailsService = new ShopDetailsService();

	@Mock
	private ShopDetailsGeoService shopDetailsGeoService;

	@Mock
	private ShopDetailsDao shopDetailsDao;

	private ShopDetails shopDetails;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		shopDetails = new ShopDetails();

		final ShopAddress address = new ShopAddress();
		address.setAddress("Pune");
		address.setAddressPostCode(411028);

		shopDetails.setShopName("ABC");
		shopDetails.setShopAddress(address);
	}

	@Test
	public void test_saveShopDetails_sccuess() throws Exception {
		Map<String, String> latlngMap = new HashMap<>();
		latlngMap.put("lat", "18.2547845");
		latlngMap.put("lng", "23.2547845");
		when(shopDetailsGeoService.getLngLatByAddress(anyString())).thenReturn(latlngMap);
		when(shopDetailsDao.saveShopDetails(any(ShopDetailsVo.class))).thenReturn(1);
		int actual = shopDetailsService.saveShopDetails(shopDetails);
		assertEquals(1, actual);
	}

	@Test(expected = Exception.class)
	public void test_saveShopDetails_failure() throws Exception {
		Map<String, String> latlngMap = new HashMap<>();
		latlngMap.put("lat", "18.2547845");
		latlngMap.put("lng", "23.2547845");
		when(shopDetailsGeoService.getLngLatByAddress(anyString())).thenReturn(latlngMap);
		when(shopDetailsDao.saveShopDetails(any(ShopDetailsVo.class))).thenThrow(new Exception());
		shopDetailsService.saveShopDetails(shopDetails);
	}

}
