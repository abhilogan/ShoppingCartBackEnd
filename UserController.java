package com.niit.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.niit.shoppingcart.dao.UserDAO;
import com.niit.shoppingcart.domain.User;

@Controller
public class UserController {
	
	//UserDAO - backend project
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private User user;
	
	@Autowired
	HttpSession httpsession;
	

	@GetMapping("validate")
	public ModelAndView validate(@RequestParam("uname") String username, @RequestParam("psw") String password)
	{
		ModelAndView mv = new ModelAndView("home");
		
		user = userDAO.validate(username, password);

		if (user==null )
		{
			mv.addObject("errorMessage", "invalid credentials , please try again");
		}
		else
		{
			//mv.addObject("WelcomeMessage", "welcome Mr./Ms."+user.getName());
			httpsession.setAttribute("WelcomeMessage", "welcome Mr./Ms."+user.getName());
		}
		if(user.getRole()=='A')
		{
			httpsession.setAttribute("isAdmin" , true);
		}
		
		return mv;
		
		
		
	}
	
}
