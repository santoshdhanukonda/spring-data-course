package com.dataGlacier.spring.data.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories("com.dataGlacier.spring.data.dao.repositories")
@EnableTransactionManagement
@ComponentScan(basePackages="com.dataGlacier.spring.data")
public class DataGlacierAppConfig {
	
	@Bean
	public DataSource dataSource() {
		return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).build();
	}
	
	@Bean
	public EntityManagerFactory entityManagerFactory() {
		HibernateJpaVendorAdapter jpaVendorAdaptor = new HibernateJpaVendorAdapter();
		jpaVendorAdaptor.setGenerateDdl(true);
		
		Properties jpaProperties = new Properties();
		jpaProperties.put("hibernate.hbm2ddl.auto", "create-drop");
		jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
		jpaProperties.put("org.hibernate.FlushMode" , "AUTO");
		jpaProperties.put("hibernate.hbm2ddl.import_files" , "init.sql");
		
		LocalContainerEntityManagerFactoryBean entityFactory = new LocalContainerEntityManagerFactoryBean();
		entityFactory.setDataSource(dataSource());
		entityFactory.setJpaVendorAdapter(jpaVendorAdaptor);
		entityFactory.setJpaProperties(jpaProperties);
		entityFactory.setPackagesToScan("com.dataGlacier.spring.data.dao.entities");
		entityFactory.afterPropertiesSet();
		return entityFactory.getObject();
	}
	
	@Bean
	public PlatformTransactionManager transactionManager() {
		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(entityManagerFactory());
		return txManager;
	}
}
