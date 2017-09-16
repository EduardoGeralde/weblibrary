package com.eduardoportfolio.weblibrary.controllers;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.eduardoportfolio.weblibrary.dao.ProductDao;
import com.eduardoportfolio.weblibrary.models.BookType;
import com.eduardoportfolio.weblibrary.models.Product;

@Controller
@Transactional
public class ProductsController {
	
	@Autowired
	private ProductDao productDao;
	
	@RequestMapping("form")
	public ModelAndView form(){
		ModelAndView modelAndView = new ModelAndView("products/book-form");
		modelAndView.addObject("types", BookType.values());
		return modelAndView;
	}
	
	@RequestMapping("products")
	public String save(Product product){
		System.out.println("Saving product "+ product);
		productDao.save(product);
		return "products/ok";
	}
	

}
