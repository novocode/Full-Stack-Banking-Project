package com.revature.service;

import java.util.List;

import com.revature.models.Account;
import com.revature.models.Customer;

public interface EmployeeManager {
	
	int createEmployee(String username, String firstname, String lastname, String password);

	List<Account> reviewNewAccounts();

	List<Account> viewCustomerAccounts(int accountNumber);

	void removeApplication(int accountID);
	
	boolean authenticateEmployee(String username, String password);

	List<Customer> getAllCustomers();
}
