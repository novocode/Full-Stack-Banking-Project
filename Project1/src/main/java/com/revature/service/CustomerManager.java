package com.revature.service;

public interface CustomerManager {
	
	int createCustomer(String username, String password, String firstname, String lastname, int ssn);
	
	boolean withdrawAmount(int accountNumber, double amount);

	boolean depositAmount(int accountNumber, double amount);

	boolean transferAmount(int accountNumber, int transferToNumber, double amount);

	boolean accountApplication(String username, double amount, String string);

	boolean createBankAccount(String type, double balance, int bank_membership_number);
	
	boolean authenticateCustomer(String username, String password);
	

/* ************************************************Refactored methods, don't need these****************************************************
	double displayBalance(int accountNumber);
	
	boolean getCustomerAccount(int accountNumber);

	Customer getCustomer();

	boolean getBankAccount(int accountNumber) throws CustomerIsNullException;
	*/
}
