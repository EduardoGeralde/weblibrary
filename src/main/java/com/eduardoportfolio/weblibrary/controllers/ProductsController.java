package com.eduardoportfolio.weblibrary.controllers;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.eduardoportfolio.weblibrary.dao.ProductDao;
import com.eduardoportfolio.weblibrary.models.BookType;
import com.eduardoportfolio.weblibrary.models.Product;

@Controller
@Transactional
@RequestMapping("products")
public class ProductsController {
	
	@Autowired
	private ProductDao productDao;
	
	@RequestMapping("form")
	public ModelAndView form(){
		ModelAndView modelAndView = new ModelAndView("products/book-form");
		modelAndView.addObject("types", BookType.values());
		return modelAndView;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView save(Product product){
		ModelAndView modelAndView = new ModelAndView("redirect:products");
		modelAndView.addObject("sucess", "product successfully registered");
		productDao.save(product);
		return modelAndView;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView list(){
		ModelAndView modelAndView = new ModelAndView("product/list");
		modelAndView.addObject("products", productDao.list());
		return modelAndView;
	}

}
