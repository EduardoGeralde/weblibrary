package com.eduardoportfolio.weblibrary.configuration;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.eduardoportfolio.weblibrary.controllers.HomeController;
import com.eduardoportfolio.weblibrary.dao.ProductDao;
import com.eduardoportfolio.weblibrary.infra.AmazonFileSaver;
import com.eduardoportfolio.weblibrary.models.ShoppingCart;

@EnableWebMvc
@ComponentScan(basePackageClasses = {HomeController.class, ProductDao.class, 
														AmazonFileSaver.class, ShoppingCart.class})
public class AppWebConfiguration {
	
	@Bean
	public InternalResourceViewResolver internalResourceViewResolver(){
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		//Exposes all managed beans through EL
		//resolver.setExposeContextBeansAsAttributes(true);
		//Expose only a specific managed bean through EL
		resolver.setExposedContextBeanNames("shoppingCart");
		return resolver;
	}
	
	@Bean (name="messageSource")
	//Indicate to Spring the location of the file with the messages that is created. The method
	//has to have messageSource name. We can annotate this name in the Bean.
	public MessageSource messageSource(){
		ReloadableResourceBundleMessageSource bundle = new ReloadableResourceBundleMessageSource();
		bundle.setBasename("/WEB-INF/messages");
		bundle.setDefaultEncoding("UTF-8");
		//Typical development environment configuration, because we don't want refresh the server
		//always when this file change
		bundle.setCacheSeconds(1);
		return bundle;
	}
	
	@Bean
	//In this Bean, we tell Spring always use this date format. This method has to have this name
	public FormattingConversionService mvcConversionService(){
		DefaultFormattingConversionService conversionService = 
												new DefaultFormattingConversionService(true);
		//Some conversions of this class Calendar>Long/Long>Calendar/Date>Calendar/Calendar>Date
		DateFormatterRegistrar registrar = new DateFormatterRegistrar();
		//Here we inform which format that we want
		registrar.setFormatter(new DateFormatter("yyyy-MM-dd"));
		//Finally, we register all converters in the FormattingConversionService object type
		registrar.registerFormatters(conversionService);
		return conversionService;
	}
	
	@Bean
	//The interface MultipartResolver is where define methods necessary for the initial treatment 
	//of a request with multipart/form-data send mode, could be also CommonsMultipartResolver interface 
	public MultipartResolver multipartResolver(){
		return new StandardServletMultipartResolver();
	}

	@Bean
	//This object type offers many methods, that we can use to realize many types of request
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}
	
}
