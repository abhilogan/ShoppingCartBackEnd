package com.niit.shoppingcart.dao;

import com.niit.shoppingcart.domain.User;


//DAO-> Data Access Object
public interface UserDAO 
{
	//declare the methods
	
	//create the new user
	
	public boolean save(User user);
	
	//update the existing user
	
	public boolean upadte(User user);
	
	//get the user details
	
	public User get(String emailID);
	
	//delete the user
	
	public boolean delete(String emailID);
	
	
	//we may required more methods .... will discuss

}
