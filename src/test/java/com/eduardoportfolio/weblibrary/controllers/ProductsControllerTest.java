package com.eduardoportfolio.weblibrary.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import javax.servlet.Filter;
import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import com.eduardoportfolio.weblibrary.builder.ProductBuilder;
import com.eduardoportfolio.weblibrary.conf.DataSourceConfigurationTest;
import com.eduardoportfolio.weblibrary.configuration.AppWebConfiguration;
import com.eduardoportfolio.weblibrary.configuration.JpaConfiguration;
import com.eduardoportfolio.weblibrary.configuration.SecurityConfiguration;
import com.eduardoportfolio.weblibrary.dao.ProductDao;
import com.eduardoportfolio.weblibrary.models.Product;

@RunWith(SpringJUnit4ClassRunner.class)
//Responsible to do the Runner of the Spring Test load necessary objects for a web application. 
//Ex: HttpServletRequest and HttpServletResponse
@WebAppConfiguration
@ContextConfiguration(classes = {AppWebConfiguration.class, JpaConfiguration.class, 
																SecurityConfiguration.class,
																DataSourceConfigurationTest.class})
@ActiveProfiles("test")
public class ProductsControllerTest {
	
	@Autowired
	ProductDao productDao;
	
	@Autowired
	WebApplicationContext wac;
	
	@Autowired
	private Filter springSecurityFilterChain;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup(){
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac)
					.addFilters(springSecurityFilterChain).build();
	}
	
	@Test
	public void onlyAdminShoudAccessProductsForm()
			throws Exception {
		// Could use the isFound()
		this.mockMvc.perform(
				get("/products/form").with(SecurityMockMvcRequestPostProcessors
						.user("bbb")
						.password("bbb")
						.roles("USER"))).andExpect(
						status().is(403));
	}
	
	@Test
	@Transactional
	public void shouldListAllBooksInTheHome()
			throws Exception {
		productDao.save(ProductBuilder.newProduct().buildOne());

		ResultActions action = this.mockMvc.perform(get("/produtos"));
		ResultMatcher modelAndViewMatcher = new ResultMatcher() {

			@Override
			public void match(MvcResult result) throws Exception {
				ModelAndView mv = result.getModelAndView();
				@SuppressWarnings("unchecked")
				List<Product> products = (List<Product>) mv.getModel().get("products");
				Assert.assertEquals(1, products.size());
			}
		};
		action.andExpect(modelAndViewMatcher).andExpect(
				MockMvcResultMatchers
						.forwardedUrl("/WEB-INF/views/products/list.jsp"));
	}

}
