package com.winneredge.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.winneredge.dao.Dao;

@Controller
public class LoginController {

	@RequestMapping(value ="/login", method = RequestMethod.GET)
	public String loginPage()
	{
		return "login";
	}
	
	@RequestMapping( value="/userlogin" , method = RequestMethod.POST)
	public ModelAndView userloginPage(@RequestParam("username")String username, @RequestParam("password") String password)
	{
		Dao dao = new Dao();

		boolean result = dao.validateUser(username,password);
		
		if(result)
		{
			ModelAndView model= new ModelAndView("success");
			model.addObject("Username", username);
			return model;
		}
		else
		{
			ModelAndView model = new ModelAndView("login");
			model.addObject("ErrorMessage", "Please provide valid credentials");
			return model;
		}
	}
}
