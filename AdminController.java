package com.niit.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.niit.shoppingcart.dao.CategoryDAO;
import com.niit.shoppingcart.dao.SupplierDAO;
import com.niit.shoppingcart.domain.Category;
import com.niit.shoppingcart.domain.Supplier;

@Controller
public class AdminController {

	@Autowired
	private CategoryDAO categoryDAO;

	@Autowired
	private SupplierDAO supplierDAO;

	@Autowired
	private Category category;

	@Autowired
	HttpSession httpSession;

	@GetMapping("/managecategories")
	public ModelAndView adminClickedCategories() {
		ModelAndView mv = new ModelAndView("home");
		mv.addObject("isAdminClickedManageCategories", true);
		// fetch all the categories again
		List<Category> categories = categoryDAO.list();
		// and set to http session.
		httpSession.setAttribute("categories", categories);

		return mv;

	}

	@GetMapping("/managesupplier")
	public ModelAndView adminClickedSupllier() {
		ModelAndView mv = new ModelAndView("home");
		mv.addObject("isAdminClickedManageSuppliers", true);
		List<Supplier> suppliers = supplierDAO.list();
		httpSession.setAttribute("suppliers", suppliers);
		
		return mv;

	}

	@GetMapping("/manageproducts")
	public ModelAndView adminClickedProducts() {
		ModelAndView mv = new ModelAndView("home");
		mv.addObject("isAdminClickedManageProducts", true);
		return mv;

	}

}
