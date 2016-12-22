package com.ss.task.shop.details.dao;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;

import com.ss.task.shop.details.dao.impl.ShopDetailsDaoImpl;
import com.ss.task.shop.details.request.vo.ShopDetailsVo;

@RunWith(MockitoJUnitRunner.class)
public class ShopDetailsDaoTest {

	@InjectMocks
	private ShopDetailsDao shopDetailsDao = new ShopDetailsDaoImpl();

	@Mock
	private JdbcTemplate jdbcTemplate;
	
	private ShopDetailsVo shopDetailsVo; 
	
	@Before
	public void setUp(){
		shopDetailsVo = new ShopDetailsVo("ABC", "London", 12345, "18.264797", "-23.9497979");
	}

	@Test
	public void test_saveShopDetails_sccuess() throws Exception {
		when(jdbcTemplate.update(anyString(), (Object[]) anyObject())).thenReturn(0);
		assertEquals(0, shopDetailsDao.saveShopDetails(shopDetailsVo));
	}
	
	@Test(expected = Exception.class)
	public void test_saveShopDetails_Failed() throws Exception {
		when(jdbcTemplate.update(anyString(), (Object[]) anyObject())).thenThrow(new Exception());
		shopDetailsDao.saveShopDetails(shopDetailsVo);
	}

}
