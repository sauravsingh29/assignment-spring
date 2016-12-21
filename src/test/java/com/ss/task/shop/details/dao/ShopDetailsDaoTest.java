package com.ss.task.shop.details.dao;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import com.ss.task.dao.shop.details.impl.ShopDetailsDaoImpl;

@RunWith(MockitoJUnitRunner.class)
public class ShopDetailsDaoTest {

	@InjectMocks
	private ShopDetailsDao shopDetailsDao = new ShopDetailsDaoImpl();

	@Mock
	JdbcTemplate jdbcTemplate;

	@Mock
	DataSource dataSource;


	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test_saveShopDetails_sccuess() throws Exception {
		int expected = 1;
		when(jdbcTemplate.update(anyString(), (Object[]) anyObject())).thenReturn(expected);
		int actual = shopDetailsDao.saveShopDetails("Abc", "119", 60169);
		assertEquals(expected, actual);
	}

}
