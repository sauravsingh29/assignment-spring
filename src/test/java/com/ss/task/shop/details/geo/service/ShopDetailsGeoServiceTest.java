package com.ss.task.shop.details.geo.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.ss.task.geo.response.AddressComponent;
import com.ss.task.geo.response.GeoCodingResponse;
import com.ss.task.geo.response.Geometry;
import com.ss.task.geo.response.LatLng;
import com.ss.task.geo.response.Results;
import com.ss.task.shop.details.geo.service.impl.ShopDetailsGeoServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class ShopDetailsGeoServiceTest {

	@InjectMocks
	private ShopDetailsGeoService geoService = new ShopDetailsGeoServiceImpl();

	@Mock
	private RestTemplate restTemplate;

	@Before
	public void setUp() throws Exception {
		ReflectionTestUtils.setField(geoService, "geoLatngUrl", "https://test.com");
		ReflectionTestUtils.setField(geoService, "geoLocationUrl", "https://test.com");
	}

	@Test
	public void test_getLngLatByAddress_Success() {
		GeoCodingResponse codingResponse = testGeoCodingResponse();
		when(restTemplate.getForObject(anyString(), eq(GeoCodingResponse.class))).thenReturn(codingResponse);
		Map<String, String> map = geoService.getLngLatByAddress("khaadi");
		assertEquals(map.get("lat"), Double.toString(11.1212));
		assertEquals(map.get("lng"), Double.toString(12.12));
	}

	@Test(expected = RuntimeException.class)
	public void test_getLngLatByAddress_Failure() {
		when(restTemplate.getForObject(anyString(), eq(GeoCodingResponse.class)))
				.thenThrow(new HttpClientErrorException(HttpStatus.BAD_REQUEST));
		geoService.getLngLatByAddress("khaadi");
	}

	@Test
	public void test_getNearestShopDetails_Success() {
		GeoCodingResponse codingResponse = testGeoCodingResponse();
		when(restTemplate.getForObject(anyString(), eq(GeoCodingResponse.class))).thenReturn(codingResponse);
		GeoCodingResponse response = geoService.getNearestShopDetails("11.1212,2.12");
		assertNotNull(response);
	}

	@Test(expected = RuntimeException.class)
	public void test_getNearestShopDetails_Failure() {
		when(restTemplate.getForObject(anyString(), eq(GeoCodingResponse.class)))
				.thenThrow(new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR));
		geoService.getNearestShopDetails("11.1212,2.12");
	}

	private GeoCodingResponse testGeoCodingResponse() {
		GeoCodingResponse geoCodingResponse = new GeoCodingResponse();
		AddressComponent addressComponent = new AddressComponent();
		Results result = new Results();
		Geometry geometry = new Geometry();
		LatLng latLng = new LatLng();
		latLng.setLat(11.1212);
		latLng.setLng(12.12);
		geometry.setLocation(latLng);
		String[] types = { "type1", "type2" };
		addressComponent.setLongName("LongName");
		addressComponent.setShortName("shortName");
		addressComponent.setTypes(types);
		AddressComponent addressComponents[] = { addressComponent };
		result.setAddressComponents(addressComponents);
		result.setFormattedAddress("aaddress");
		result.setGeometry(geometry);
		result.setTypes(types);
		result.setPartialMatch(true);
		Results[] resultsArr = { result };
		geoCodingResponse.setResults(resultsArr);
		geoCodingResponse.setStatus("testStatus");
		return geoCodingResponse;
	}

}
