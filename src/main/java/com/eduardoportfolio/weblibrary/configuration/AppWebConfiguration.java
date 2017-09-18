package com.eduardoportfolio.weblibrary.configuration;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
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
	
	@Bean (name="messageSource")
	//Indicate to Spring the location of the file with the messages that is created. The method
	//has to have messageSource name. We can annotate this name in the Bean.
	public MessageSource messageSource(){
		ReloadableResourceBundleMessageSource bundle = new ReloadableResourceBundleMessageSource();
		bundle.setBasename("/WEB-INF/messages");
		bundle.setDefaultEncoding("UTF-8");
		//Tipic development environment configuration, because we don't want refresh the server
		//always when this file change
		bundle.setCacheSeconds(1);
		return bundle;
	}

	
}
