package com.topas.air.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
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
@MapperScan(
        basePackages = "com.topas.air.mapper.oracle",
        sqlSessionFactoryRef = "sqlSessionFactory"
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

		Map<String, Object> properties = new HashMap<>();
		properties.put("hibernate.dialect", "org.hibernate.dialect.OracleDialect");
//		properties.put("hibernate.hbm2ddl.auto", "create");
		properties.put("hibernate.hbm2ddl.auto", "update");
		
        properties.put("hibernate.format_sql", true);
        properties.put("hibernate.show_sql", true);  // sql은 log4j로 출력 org.hibernate.SQL=DEBUG

		return builder.dataSource(dataSource).packages("com.topas.air.repository.oracle")
				.persistenceUnit("oracleEntityManager").properties(properties).build();
	}

	@Primary
	@Bean
	public PlatformTransactionManager oracleTransactionManager(
			@Qualifier("oracleEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}
	
	

    @Bean(name= "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("oracle") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sessionFactoryBean.setMapperLocations(resolver.getResources("classpath:/mapper/oracle/*.xml")); //mapper path
        sessionFactoryBean.setTypeAliasesPackage("com.topas.air.repository.oracle, com.topas.air.dto.oracle");
        sessionFactoryBean.setTypeHandlersPackage("com.topas.air.typehandler.oracle");
        Objects.requireNonNull(sessionFactoryBean.getObject()).getConfiguration().setMapUnderscoreToCamelCase(true); //camelCase
        return sessionFactoryBean.getObject();
    }
    
    @Bean(name= "sqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
	
	

//    @Bean(name= "txManager")
//    public PlatformTransactionManager txManager(@Qualifier("oracle") DataSource dataSource) {
//        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager(dataSource);
//        dataSourceTransactionManager.setNestedTransactionAllowed(true); // nested
//
//        return dataSourceTransactionManager;
//    }
//    JpaTransactionManager transactionManager() {
//        return new JpaTransactionManager();
//    }
	
}
