package com.eduardoportfolio.weblibrary.controllers;

import java.math.BigDecimal;
import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.eduardoportfolio.weblibrary.models.PaymentData;
import com.eduardoportfolio.weblibrary.models.ShoppingCart;
import com.eduardoportfolio.weblibrary.models.User;

@Controller
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	ShoppingCart shoppingCart;
	@Autowired
	RestTemplate restTemplate;
	@Autowired
	private MailSender mailSender;
	
	@RequestMapping(value="checkout", method=RequestMethod.POST)
	//Analog to Runnable, but allow us give a return. @AuthenticationPrincipal, allow receive the
	//user logged as a parameter.
	public Callable<ModelAndView> checkout(@AuthenticationPrincipal User user){
		
		return() -> {
		
			BigDecimal total = shoppingCart.getTotal();
			
			String uriToPay = "http://book-payment.herokuapp.com/payment";
			try{
				String response = restTemplate.postForObject(uriToPay, new PaymentData(total), String.class);
				//sendNewPurchaseMail(user);
				return new ModelAndView("payment/payment-success");
			} catch (HttpClientErrorException exception){
				return new ModelAndView("payment/payment-error");
			}
		};
	}
	
	private void sendNewPurchaseMail(User user){
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setFrom("edardogeralde83@gmail.com");
		mail.setTo(user.getLogin());
		mail.setSubject("New Purchase");
		mail.setText("Mail body");
		mailSender.send(mail);
	}
	
}