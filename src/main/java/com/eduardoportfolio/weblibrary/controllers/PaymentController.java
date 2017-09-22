package com.eduardoportfolio.weblibrary.controllers;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.eduardoportfolio.weblibrary.models.PaymentData;
import com.eduardoportfolio.weblibrary.models.ShoppingCart;

@Controller
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	ShoppingCart shoppingCart;
	@Autowired
	RestTemplate restTemplate;
	
	@RequestMapping(value="checkout", method=RequestMethod.POST)
	public String checkout(){
		
		BigDecimal total = shoppingCart.getTotal();
		
		String uriToPay = "http://book-payment.herokuapp.com/payment";
		try{
			String response = restTemplate.postForObject(uriToPay, new PaymentData(total), String.class);
			return "payment/payment-success";
		} catch (HttpClientErrorException exception){
			return "payment/payment-error";
		}
	}
}
