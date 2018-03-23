package com.niit.shoppingcart.daoimpl;

import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.shoppingcart.dao.UserDAO;
import com.niit.shoppingcart.domain.User;

//another annotations..

@Transactional
@Repository("userDAO") // will create instance of UserDAOImpl and the name will userDAOImpl
public class UserDAOImpl implements UserDAO {

	// first - inject hibernate session .


	@Autowired // session factory is automatically injected in this class
	private SessionFactory sessionFactory;

	public boolean save(User user) {
		// STORE IN THE DATA BASE

		try {
			user.setRole('C');
			user.setRegisteredDate(new java.util.Date());
			sessionFactory.getCurrentSession().save(user);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	public boolean upadte(User user) {
		try {
			sessionFactory.getCurrentSession().update(user);
			return true;

		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			e.printStackTrace();
			return false;
		}
	}

	public User get(String emailID) {
		// it will set the record based onemailID and store in User class
		return sessionFactory.getCurrentSession().load(User.class, emailID);

	}

	public boolean delete(String emailID) {
		try {
			sessionFactory.getCurrentSession().delete(get(emailID));
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

}
