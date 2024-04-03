package com.topas.air.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DBConfig {
	
	@Primary
	@Bean(name = "datasource1")
	@ConfigurationProperties("database1.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "datasource2")
	@ConfigurationProperties("database2.datasource")
	public DataSource dataSource2() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "datasource3")
	@ConfigurationProperties("database3.datasource")
	public DataSource dataSource3() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "datasource4")
	@ConfigurationProperties("datasource4.datasource")
	public DataSource datasource4() {
		return DataSourceBuilder.create().build();
	}

//	@Bean(name = "datasource5")
//	@ConfigurationProperties("datasource5.datasource")
//	public DataSource datasource5() {
//		return DataSourceBuilder.create().build();
//	}
}
