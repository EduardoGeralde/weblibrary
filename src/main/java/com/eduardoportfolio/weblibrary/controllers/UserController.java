package com.eduardoportfolio.weblibrary.controllers;


import javax.transaction.Transactional;
import javax.validation.Valid;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.amazonaws.services.identitymanagement.model.Role;
import com.eduardoportfolio.weblibrary.dao.RoleDao;
import com.eduardoportfolio.weblibrary.dao.UserDao;
import com.eduardoportfolio.weblibrary.models.User;

@Controller
@Transactional
@RequestMapping("/register")
//@Scope(value=WebApplicationContext.SCOPE_REQUEST)
public class UserController {
	
	@Autowired
	UserDao userDao;
	@Autowired
	RoleDao roleDao;

	@RequestMapping("userForm")
	public ModelAndView userForm(User user){
		ModelAndView modelAndView = new ModelAndView("user/userForm");
		modelAndView.addObject("roleList", roleDao.list());
		return modelAndView;
	}
	
	
	@RequestMapping (value="saveUser", method=RequestMethod.POST, name="saveUser")
	public ModelAndView saveUser(@Valid User user, BindingResult bindingResult, 
															RedirectAttributes redirectAttributes){
		
		if (bindingResult.hasErrors()){
			return userForm(user);
		}
		userDao.save(user);
		
		redirectAttributes.addFlashAttribute("success", "User successfully registered");
		return new ModelAndView("redirect:/register/userForm");
	}
	
}
