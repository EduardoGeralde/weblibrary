package com.eduardoportfolio.weblibrary.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eduardoportfolio.weblibrary.dao.ProductDao;
import com.eduardoportfolio.weblibrary.models.Product;

@Controller
public class ProductsController {
	
	@Autowired
	private ProductDao productDao;
	
	@RequestMapping("products/form")
	public String form(){
		return "products/book-form";
	}
	
	@RequestMapping("/products")
	public String save(Product product){
		System.out.println("Saving product "+ product);
		productDao.save(product);
		return "products/ok";
	}
	

}
