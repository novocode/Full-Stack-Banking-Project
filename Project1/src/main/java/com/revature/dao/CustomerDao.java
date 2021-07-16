package com.revature.dao;

import java.util.List;

import com.revature.models.Customer;

public interface CustomerDao {

	//CRUD
	
	//CREATE
	int createCustomer(Customer c, String password);
	//READ
	Customer selectCustomerById(int id);
	Customer selectCustomerByName(String first_name, String last_name);
	Customer selectCustomerByUsername(String username);
	List<Customer> getAllCustomers();
	boolean authenticateCustomer(String username, String password);
	//UPDATE
	boolean changePassword(String username, String password);
	//DELETE
		//There is no deletion of customers
}
