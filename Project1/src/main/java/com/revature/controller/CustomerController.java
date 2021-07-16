package com.revature.controller;

import io.javalin.http.Context;

public interface CustomerController {	

	public void createCustomer(Context ctx); //POST
	public void getAllBankAccounts(Context ctx); //GET
	public void withdrawAmount(Context ctx); //PUT
	public void depositAmount(Context ctx); //PUT
	public void transferAmount(Context ctx); //PUT
	public void accountApplication(Context ctx); //POST
	public void manageAmounts(Context ctx);
	
	/*
	 * The following methods aren't really needed because everything else is covered
	public void getCustomerAccount(Context ctx);
	public void getCustomer(Context ctx);
	public void getBankAccount(Context ctx);
	public void displayBalance(Context ctx);
	public void createBankAccount(Context ctx);
	*/
}
