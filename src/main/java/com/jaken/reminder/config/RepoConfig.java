package com.jaken.reminder.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@PropertySource("classpath:basic-config.properties")
@EnableJpaRepositories(basePackages="com.jaken.reminder.dao")
public class RepoConfig {

	@Autowired
	private Environment env;
	@Bean
	public DataSource getDataSource() throws Exception {
		BasicDataSource bds=new BasicDataSource();
		bds.setUrl(env.getProperty("db.url"));
		bds.setDriver(new com.mysql.jdbc.Driver());
		bds.setUsername(env.getProperty("db.user"));
		bds.setPassword(env.getProperty("db.password"));
		return bds;
	}
	
	@Bean
	public JpaVendorAdapter getJpaVendor() {
		HibernateJpaVendorAdapter jpaVendorAdaptor=new HibernateJpaVendorAdapter();
		jpaVendorAdaptor.setShowSql(true);
		jpaVendorAdaptor.setDatabasePlatform("org.hibernate.dialect.MySQLDialect");
		return jpaVendorAdaptor;
	}
	
	@Bean(name="entityManagerFactory")
	@Autowired
	public LocalContainerEntityManagerFactoryBean getEntityManagerFactory(DataSource dataSource,JpaVendorAdapter vendorAdapter) {
		LocalContainerEntityManagerFactoryBean factoryBean=new LocalContainerEntityManagerFactoryBean();
		factoryBean.setDataSource(dataSource);
		factoryBean.setJpaVendorAdapter(vendorAdapter);
		factoryBean.setPackagesToScan("com.jaken.reminder.model");
		return factoryBean;
	}
	
	@Bean(name="jdbcTemplate")
	@Autowired
	public JdbcTemplate getJdbcTemplate(DataSource dataSource) {
		JdbcTemplate jdbcTemplate=new JdbcTemplate();
		jdbcTemplate.setDataSource(dataSource);
		return jdbcTemplate;
	}
}
