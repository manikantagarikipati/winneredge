package com.winneredge.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.winneredge.dao.Dao;
import com.winneredge.model.User;

@Controller
public class RegisterController {

	@RequestMapping(value="/register" , method = RequestMethod.GET )
	public String registerpage()
	{
		return "register";
	}
	
	@RequestMapping(value="/signup" , method=RequestMethod.POST)
	public ModelAndView signupUser(@ModelAttribute User user, @RequestParam("username")String username)
	{
		Dao dao = new Dao();
		user.setUserName(username);
		boolean result = dao.registerUser(user);
		// if successfully inserted result == true then if statement executes
		if(result)
		{
			ModelAndView model = new ModelAndView("success");
			model.addObject("Username",user.getUserName());
			return model;
		}
		else
		{
			ModelAndView model = new ModelAndView("register");
			model.addObject("ErrorMessage","Registration Unsuccessfull");
			return model;
		}
	}
}
