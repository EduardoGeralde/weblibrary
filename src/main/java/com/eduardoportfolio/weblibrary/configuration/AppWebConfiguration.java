package com.eduardoportfolio.weblibrary.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.eduardoportfolio.weblibrary.controllers.HomeController;
import com.eduardoportfolio.weblibrary.dao.ProductDao;

@EnableWebMvc
@ComponentScan(basePackageClasses = {HomeController.class, ProductDao.class})
public class AppWebConfiguration {
	
	@Bean
	public InternalResourceViewResolver internalResourceViewResolver(){
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

	
}
