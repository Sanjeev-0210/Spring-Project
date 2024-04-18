package org.jsp.springempapp;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("org.jsp.springempapp")
public class EmployeeConfig {

	@Bean
	public EntityManager getentityManager() {
		return Persistence.createEntityManagerFactory("development").createEntityManager();
	}
}
