package com.ss.task.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.web.client.RestTemplate;

/**
 * @author Saurav Singh
 *
 */
@Configuration
@PropertySource(value = {"classpath:geo-config.properties"})
public class ShopConfig {

	@Bean
	public DataSource dataSource() {
		return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).addScripts("sql/create-shop-details.sql").build();
	}

	@Bean(name = "jdbcTemplate")
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(dataSource());
	}
	
	@Bean(name = "restTemplate")
	public RestTemplate restTemplate(@Value("${geo.read.timeout}") int readTimeOut, @Value("${geo.connect.timeout}") int connectTimeOut){
		return new RestTemplate(clientHttpRequestFactory(readTimeOut, connectTimeOut));
	}
	
	private ClientHttpRequestFactory clientHttpRequestFactory(int readTimeOut,int connectTimeOut) {
        final HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setReadTimeout(readTimeOut);
        factory.setConnectTimeout(connectTimeOut);
        return factory;
    }
}
