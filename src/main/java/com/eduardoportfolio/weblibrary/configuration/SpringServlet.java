package com.eduardoportfolio.weblibrary.configuration;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;


public class SpringServlet extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	//Load classes before Spring MVC Servlet, inside a Listener, that is read when the server goes up, is the
	//ContextLoadListener
	protected Class<?>[] getRootConfigClasses() {
	//We put the security filter here, because it runs before the Spring MVC Servlet
		return new Class [] {SecurityConfiguration.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return new Class [] {AppWebConfiguration.class, 
												JpaConfiguration.class, 
																AmazonConfiguration.class};
	}

	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return new String [] {"/"};
	}
	
	@Override
	//Upload treatment. We can choose the temporary storage location (while receiving file), 
	//maximum size file, maximum request size, and so on. It receives a Dynamic Object that allow 
	//us register our configuration object of MultipartConfigElement type. We use in parallel with 
	//MultipartRsolver in the AppWebConfiguration class, to work with files.
	//With the parameter "", indicates that the web server will decide which location to use
	protected void customizeRegistration(Dynamic registration){
		registration.setMultipartConfig(new MultipartConfigElement(""));
	}

}
