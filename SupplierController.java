package com.niit.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.niit.shoppingcart.dao.SupplierDAO;
import com.niit.shoppingcart.domain.Supplier;

@Controller
public class SupplierController {
	// we need to call CategoryDAO methods
	// get , save , update , delete , list

	// 1.inject the CategoryDAO and Category
	@Autowired
	private SupplierDAO supplierDAO;

	@Autowired
	private Supplier supplier;

	@Autowired
	private HttpSession httpSession;

	// @GetMapping("/category/get/{id}")
	@RequestMapping(name = "/supplier/get/{id}", method = RequestMethod.GET)
	public ModelAndView getSupplier(@RequestParam("id") String id) {
		// based on id fetch the details from categoryDAO
		supplier = supplierDAO.get(id);

		// navigate to home page
		ModelAndView mv = new ModelAndView("home");
		mv.addObject("supplier", supplier);

		return mv;

	}

	@PostMapping("/supplier/save/")
	public ModelAndView saveSupplier(@RequestParam("id") String id, @RequestParam("name") String name,
			@RequestParam("address") String address) {
		System.out.println("saveSupplier Method ");
		ModelAndView mv = new ModelAndView("redirect:/managesuppliers");
		// set the values to category
		supplier.setId(id);
		supplier.setName(name);
		supplier.setAddress(address);

		// save into db
		if (supplierDAO.save(supplier)) {
			mv.addObject("supplierSuccessMessage", "This supplier saved successfully");
		} else {
			mv.addObject("supplierErrorMessage", "Could able to create supplier.Please contact admin");
			// fetch all the categories again
			List<Supplier> suppliers = supplierDAO.list();
			// and set to http session
			httpSession.setAttribute("suppliers", suppliers);

		}
		return mv;
	}

	@PutMapping("/supplier/update/")
	public ModelAndView updateSupplier(@ModelAttribute Supplier supplier) {

		ModelAndView mv = new ModelAndView("home");

		if (supplierDAO.upadte(supplier) == true) {

			mv.addObject("successMessage", "This supplier updated successfully");
		} else {

			mv.addObject("errorMessage", "Could not update the supplier");
		}
		return mv;

	}

	@GetMapping("/supplier/delete")
	public ModelAndView deleteSupplier(@RequestParam("id") String id) {
		System.out.println("Going to delete supplier : " + id);
		// navigate to home page
		ModelAndView mv = new ModelAndView("redirect:/managesuppliers");
		// we supposed to fetch the latest categories
		// and add to httpSession
		// based on id fetch the details from categoryDAO
		if (supplierDAO.delete(id) == true) {

			// add success method
			mv.addObject("supplierSuccessMessage", "This supplier deleted successfully");
		} else {

			// add failure method
			mv.addObject("supplierErrorMessage", "Could not delete the supplier");
		}

		return mv;

	}

	@GetMapping("/supplier/edit")
	public ModelAndView editSupplier(@RequestParam String id) {
		ModelAndView mv = new ModelAndView("redirect:/managesuppliers");

		// based on category id fetch category details
		supplier = supplierDAO.get(id);
		// mv.addObject("selectedCategory", category);
		httpSession.setAttribute("selectedSupplier", supplier);
		return mv;
	}

	@GetMapping("/suppliers")
	public ModelAndView getAllSuppliers() {
		ModelAndView mv = new ModelAndView("home");
		List<Supplier> suppliers = supplierDAO.list();
		mv.addObject("suppliers", suppliers);
		return mv;

	}

}
