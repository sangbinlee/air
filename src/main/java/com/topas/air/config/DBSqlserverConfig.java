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
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import jakarta.persistence.EntityManagerFactory;


@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef = "sqlserverEntityManagerFactory",
        transactionManagerRef = "sqlserverTransactionManager",
        basePackages = { "com.topas.air.repository.sqlserver" }
)
public class DBSqlserverConfig {

	@Bean(name = "sqlserver")
	@ConfigurationProperties("sqlserver.datasource")
	public DataSource sqlserver() {
		return DataSourceBuilder.create().build();
	}
	

	@Bean
	public LocalContainerEntityManagerFactoryBean sqlserverEntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("sqlserver") DataSource dataSource) {
		
		Map<String, String> properties = new HashMap<String, String>();
		properties.put("hibernate.dialect", "org.hibernate.dialect.SQLServerDialect");
		properties.put("hibernate.hbm2ddl.auto", "create");
		
		return builder.dataSource(dataSource).packages("com.topas.air.repository.sqlserver")
				.persistenceUnit("sqlserverEntityManager").properties(properties).build();
	}

	@Bean
	public PlatformTransactionManager sqlserverTransactionManager(
			@Qualifier("sqlserverEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}
}
