package com.ss.task.dao.shop.details.controller;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.ss.task.shop.details.request.ShopDetails;
import com.ss.task.shop.details.request.vo.ShopDetailsVo;
import com.ss.task.shop.details.service.ShopDetailsService;

@RunWith(MockitoJUnitRunner.class)
public class ShopDetailsControllerTest {

	@InjectMocks
	private ShopDetailsController shopDetailsController = new ShopDetailsController();

	@Mock
	private ShopDetailsService shopDetailsService;

	private MockMvc mockMvc;


	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(shopDetailsController).build();
	}

	@Test
	public void testSaveShopDetails() throws Exception {
		when(shopDetailsService.saveShopDetails((ShopDetails)anyObject())).thenReturn(1);
		mockMvc.perform(post("/shopdetails").contentType(MediaType.APPLICATION_JSON_VALUE).content("{\"shopName\" : \"Huma Bakery\",\"shopAddress\" : {\"address\" : \"Sahyog Nagar\",\"postCode\" : 412114}}")).andExpect(status().isOk());
	}

	@Test
	public void testSaveShopDetailsBadRequest() throws Exception {
		when(shopDetailsService.saveShopDetails((ShopDetails)anyObject())).thenReturn(0);
		mockMvc.perform(post("/shopdetails").contentType(MediaType.APPLICATION_JSON_VALUE).content("{\"shopName\" : \"Huma Bakery\",\"shopAddress\" : {\"address\" : \"Sahyog Nagar\",\"postCode\" : 412114}}")).andExpect(status().isBadRequest());
	}

	@Test
	public void testSaveShopDetailsInternalServer() throws Exception {
		when(shopDetailsService.saveShopDetails((ShopDetails)anyObject())).thenThrow(new RuntimeException());
		mockMvc.perform(post("/shopdetails").contentType(MediaType.APPLICATION_JSON_VALUE).content("{\"shopName\" : \"Huma Bakery\",\"shopAddress\" : {\"address\" : \"Sahyog Nagar\",\"postCode\" : 412114}}")).andExpect(status().isInternalServerError());
	}

	@Test
	public void testgetNearByShops() throws Exception {
		when(shopDetailsService.findShopNearByLatLng(anyString(), anyString())).thenReturn(new ArrayList<ShopDetailsVo>());
		mockMvc.perform(get("/shopdetails").param("customerLatitude", "12").param("customerLongitude", "13")).andExpect(status().isOk());
	}

	@Test
	public void testgetNearByShopsException() throws Exception {
		when(shopDetailsService.findShopNearByLatLng(anyString(), anyString())).thenThrow(new RuntimeException());
		mockMvc.perform(get("/shopdetails").param("customerLatitude", "12").param("customerLongitude", "13")).andExpect(status().isInternalServerError());
	}

}
