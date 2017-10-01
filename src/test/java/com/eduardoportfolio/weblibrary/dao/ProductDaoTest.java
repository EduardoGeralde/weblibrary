package com.eduardoportfolio.weblibrary.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.eduardoportfolio.weblibrary.builder.ProductBuilder;
import com.eduardoportfolio.weblibrary.conf.DataSourceConfigurationTest;
import com.eduardoportfolio.weblibrary.configuration.JpaConfiguration;
import com.eduardoportfolio.weblibrary.models.BookType;
import com.eduardoportfolio.weblibrary.models.Product;

//Its provided so that frameworks can be notified of the phases of the test execution
@RunWith(SpringJUnit4ClassRunner.class)
//Tells which classes has to be load
@ContextConfiguration(classes = {ProductDao.class, JpaConfiguration.class, DataSourceConfigurationTest.class})
@ActiveProfiles("test")
public class ProductDaoTest {

	@Autowired
	private ProductDao productDao;
	
	@Transactional
	@Test
	public void shouldSumAllPricesOfEachBookPerType(){
		
		//Save a list of printed books
		List<Product> printedBooks = ProductBuilder.newProduct(BookType.PRINTED, BigDecimal.TEN).more(4)
				.buildAll();
		//Java8 forEach to save
		printedBooks.stream().forEach(productDao::save);
		
		//Save a list of printed books
		List<Product> ebooks = ProductBuilder.newProduct(BookType.EBOOK, BigDecimal.TEN).more(4)
				.buildAll();
		//Java8 forEach to save
		ebooks.stream().forEach(productDao::save);
		
		BigDecimal value = productDao.sumPricesPerType(BookType.PRINTED);
		Assert.assertEquals(new BigDecimal(50).setScale(2), value);
	}

}
