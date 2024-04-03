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
        entityManagerFactoryRef = "mysqlEntityManagerFactory",
        transactionManagerRef = "mysqlTransactionManager",
        basePackages = { "com.topas.air.repository.mysql" }
)
public class DBMysqlConfig {

	@Bean(name = "mysql")
	@ConfigurationProperties("mysql.datasource")
	public DataSource mysql() {
		return DataSourceBuilder.create().build();
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean mysqlEntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("mysql") DataSource dataSource) {
		
		Map<String, String> properties = new HashMap<String, String>();
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
		properties.put("hibernate.hbm2ddl.auto", "create");
		
		
		return builder.dataSource(dataSource).packages("com.topas.air.repository.mysql")
				.persistenceUnit("mariadbEntityManager").properties(properties).build();
	}

	@Bean
	public PlatformTransactionManager mysqlTransactionManager(
			@Qualifier("mysqlEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}
}
