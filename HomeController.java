package com.niit.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.niit.shoppingcart.dao.CategoryDAO;
import com.niit.shoppingcart.domain.Category;

@Controller
public class HomeController {
	@Autowired
	private CategoryDAO categoryDAO;

	@Autowired
	private Category category;

	@Autowired
	private HttpSession httpSession;

	@GetMapping("/")
	public ModelAndView home() {
		ModelAndView mv = new ModelAndView("home");
		// we need to fetch all the categories
		// Autowired CategoryDAO and categories

		List<Category> categories = categoryDAO.list();

		// mv.addObject("categories", categories);
		httpSession.setAttribute("categories", categories);

		return mv;

	}

	@GetMapping("/login")
	public ModelAndView test1() {
		ModelAndView mv = new ModelAndView("home");

		mv.addObject("isUserClickedLogin", true);

		return mv;

	}

	@GetMapping("/register")
	public ModelAndView test3() {
		ModelAndView mv = new ModelAndView("home");
		mv.addObject("isUserClickedRegister", true);

		return mv;

	}

}
