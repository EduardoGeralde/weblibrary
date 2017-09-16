package com.eduardoportfolio.weblibrary.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eduardoportfolio.weblibrary.models.Product;

@Controller
public class ProductsController {
	
	@RequestMapping("products/form")
	public String form(){
		return "products/book-form";
	}
	
	@RequestMapping("/products")
	public String save(Product product){
		System.out.println("Saving product "+ product);
		return "products/ok";
	}
	

}
