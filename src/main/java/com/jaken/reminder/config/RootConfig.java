package com.jaken.reminder.config;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@Import({RepoConfig.class})
@ComponentScan(basePackages= {"com.jaken.reminder.service","com.jaken.reminder.dao"})
@EnableTransactionManagement(proxyTargetClass=true)
public class RootConfig {

	@Bean(name="transactionManager")
	@Autowired
	public JpaTransactionManager getJpaTransactionManger(EntityManagerFactory emf) {
		JpaTransactionManager jtm=new JpaTransactionManager();
		jtm.setEntityManagerFactory(emf);
		return jtm;
	}
	
}
