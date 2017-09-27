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

import com.eduardoportfolio.weblibrary.dao.RoleDao;
import com.eduardoportfolio.weblibrary.models.Role;




@Transactional
@Controller
@RequestMapping("/register")
//@Scope(value=WebApplicationContext.SCOPE_REQUEST)
public class RoleController {
	
	@Autowired
	RoleDao roleDao;
	
	@RequestMapping("roleForm")
	public ModelAndView roleForm(Role role){
		ModelAndView modelAndView = new ModelAndView("user/roleForm");
		modelAndView.addObject("roleList", roleDao.list());
		return modelAndView;
	}
	

	@RequestMapping(value="saveRole", method=RequestMethod.POST, name="saveRole")
	public ModelAndView saveRole(@Valid Role role, BindingResult bindingResult, 
															RedirectAttributes redirectAttributes){
		if (bindingResult.hasErrors()){
			return roleForm(role);
		}
		roleDao.save(role);
		
		redirectAttributes.addFlashAttribute("success", "Role successfully registered");
		return new ModelAndView("redirect:/register/roleForm");
	}

}