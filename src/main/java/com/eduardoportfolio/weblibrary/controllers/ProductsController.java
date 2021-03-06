package com.eduardoportfolio.weblibrary.controllers;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eduardoportfolio.weblibrary.dao.ProductDao;
import com.eduardoportfolio.weblibrary.infra.AmazonFileSaver;
import com.eduardoportfolio.weblibrary.models.BookType;
import com.eduardoportfolio.weblibrary.models.Product;

@Controller
@Transactional
@RequestMapping("/products")
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class ProductsController {
	
	@Autowired
	private ProductDao productDao;
	@Autowired
	private AmazonFileSaver fileSaver;
	
	@RequestMapping("form")
	public ModelAndView form(Product product){
		ModelAndView modelAndView = new ModelAndView("products/book-form");
		modelAndView.addObject("types", BookType.values());
		return modelAndView;
	}
	
	@RequestMapping(method=RequestMethod.POST, name="saveProduct")
	//Invalid a certain cache region when a new book is registered, forcing actualization
	//We can pass an array in the value attribute, and we can actualize an specific or all values setting in
	//the allEntries attribute
	@CacheEvict (value="books", allEntries=true)
	public ModelAndView save(@Valid Product product,
								MultipartFile summary,
								BindingResult bindingResult, 
								RedirectAttributes redirectAttributes){
		if(bindingResult.hasErrors()){
			return form(product);
		}

		String webPath = fileSaver.write("uploaded-images", summary);
		product.setSummaryPath(webPath);
		productDao.save(product);
		//add an object just for the next request, can be accessed with EL ${sucess}
		//Ex.${sucess} in the view
		redirectAttributes.addFlashAttribute("success", "product successfully registered");
		return new ModelAndView("redirect:/products");
	}
	
	@RequestMapping(method=RequestMethod.GET)
	//Make the cache of the action result and give its a region name
	@Cacheable (value="books")
	public ModelAndView list(){
		ModelAndView modelAndView = new ModelAndView("products/list");
		modelAndView.addObject("products", productDao.list());
		return modelAndView;
	}
	
	/*
	//Returns Json/XML file when products/json is accessed
	@RequestMapping(method = RequestMethod.GET, value="json")
	//Informs that the method return is to be used directly in the response body
	@ResponseBody
	public List<Product> listJSon(){
		return productDao.list();
	}
	*/
	
	@RequestMapping(value="/{id}", name="productShow") //We can pass primitive types, String, Date, Calendar
	//@RequestMapping("/{categoryId}/{productId}")
	//public ModelAndView show(@PathVariable("categoryId") Integer categoryId, @PathVariable("productId") Integer id){
	public ModelAndView show(@PathVariable("id") Integer id){
		ModelAndView modelAndView = new ModelAndView("products/showBook");
		Product product = productDao.find(id);
		modelAndView.addObject("product", product);
		return modelAndView;
	}
}
