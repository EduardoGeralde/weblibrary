package com.eduardoportfolio.weblibrary.controllers;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eduardoportfolio.weblibrary.dao.ProductDao;
import com.eduardoportfolio.weblibrary.models.BookType;
import com.eduardoportfolio.weblibrary.models.Product;

@Controller
@Transactional
@RequestMapping("/products")
public class ProductsController {
	
	@Autowired
	private ProductDao productDao;
	
	@RequestMapping("form")
	public ModelAndView form(Product product){
		ModelAndView modelAndView = new ModelAndView("products/book-form");
		modelAndView.addObject("types", BookType.values());
		return modelAndView;
	}
	
	@RequestMapping(method=RequestMethod.POST, name="saveProduct")
	public ModelAndView save(@Valid Product product, 
								BindingResult bindingResult, 
								RedirectAttributes redirectAttributes){
		if(bindingResult.hasErrors()){
			return form(product);
		}
		productDao.save(product);
		//add an object just for the next request, can be accessed with EL ${sucess}
		//Ex.${sucess} in the view
		redirectAttributes.addFlashAttribute("success", "product successfully registered");
		return new ModelAndView("redirect:/products");
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView list(){
		ModelAndView modelAndView = new ModelAndView("products/list");
		modelAndView.addObject("products", productDao.list());
		return modelAndView;
	}

}
