package com.cursomc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.cursomc.services.DBService;

@Configuration
@Profile("heroku")
public class HerokuConfig {

	@Autowired
	private DBService dbService;
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategyDB;
	
	@Bean
	public boolean instantiateDatabase() throws ParseException {
		
		if(!"create".equals(strategyDB) && !"create-drop".equals(strategyDB)) {
			return false;
		}
		
		dbService.instantiateTestDatabase();
		
		return true;
	}
	
}
