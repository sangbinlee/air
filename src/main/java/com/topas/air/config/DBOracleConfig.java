package com.topas.air.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef = "oracleEntityManagerFactory",
        transactionManagerRef = "oracleTransactionManager",
        basePackages = { "com.topas.air.repository.oracle" }
)
public class DBOracleConfig {

	@Primary
	@Bean(name = "oracle")
	@ConfigurationProperties("oracle.datasource")
	public DataSource oracle() {
		return DataSourceBuilder.create().build();
	}

	@Primary
	@Bean
	public LocalContainerEntityManagerFactoryBean oracleEntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("oracle") DataSource dataSource) {

		Map<String, String> properties = new HashMap<String, String>();
		properties.put("hibernate.dialect", "org.hibernate.dialect.OracleDialect");
//		properties.put("hibernate.hbm2ddl.auto", "create");
		properties.put("hibernate.hbm2ddl.auto", "update");

		return builder.dataSource(dataSource).packages("com.topas.air.repository.oracle")
				.persistenceUnit("oracleEntityManager").properties(properties).build();
	}

	@Primary
	@Bean
	public PlatformTransactionManager oracleTransactionManager(
			@Qualifier("oracleEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}
}
