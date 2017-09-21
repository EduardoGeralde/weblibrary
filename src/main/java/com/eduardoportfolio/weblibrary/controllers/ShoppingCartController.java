package com.eduardoportfolio.weblibrary.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.eduardoportfolio.weblibrary.dao.ProductDao;
import com.eduardoportfolio.weblibrary.models.BookType;
import com.eduardoportfolio.weblibrary.models.Product;
import com.eduardoportfolio.weblibrary.models.ShoppingCart;
import com.eduardoportfolio.weblibrary.models.ShoppingItem;

@Controller
@RequestMapping("/shopping")
public class ShoppingCartController {

	private ProductDao productDao;
	
	private ShoppingCart shoppingCart;
	
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView add(Integer prodId, BookType bookType){
		ShoppingItem item = createItem(prodId, bookType);
		shoppingCart.add(item);
		return new ModelAndView("redirect:/products");
	}
	
	private ShoppingItem createItem(Integer productId, BookType bookType){
		Product product = productDao.find(productId);
		ShoppingItem item = new ShoppingItem(product, bookType);
		return item;
	}
	
	
}
