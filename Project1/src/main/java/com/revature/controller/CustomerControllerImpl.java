package com.revature.controller;

import java.util.HashMap;
import java.util.List;

import org.eclipse.jetty.util.ajax.JSON;

import com.revature.models.Account;
import com.revature.service.AccountManager;
import com.revature.service.AccountManagerImpl;
import com.revature.service.CustomerManager;
import com.revature.service.CustomerManagerImpl;

import io.javalin.http.Context;

public class CustomerControllerImpl implements CustomerController {

	private CustomerManager customerService = new CustomerManagerImpl();
	private AccountManager accountService = new AccountManagerImpl();
	private AuthController authControl = new AuthControllerImpl();
	

	@Override
	public void createCustomer(Context ctx) { //POST
		HashMap<String, String> hMap = (HashMap<String, String>) JSON.parse(ctx.body());
		int value = customerService.createCustomer(
				hMap.get("username"),
				hMap.get("password"),
				hMap.get("firstname"),
				hMap.get("lastname"),
				Integer.valueOf(hMap.get("ssn")).intValue()	
				);
		if(value == -1)
		{
			ctx.status(401); //Failure	
			//ctx.json("Account creation failed"); //Not sure how to display this
			//ctx.redirect("customerInitial.html");
			//ctx.html("<h6>Account creation failed</h6>"); 
		}
		else {
			ctx.status(200); //Success
			//ctx.json("Account #"+value+" created"); //Not sure how to display this
			//ctx.html("<h6>Account #"+value+" created\"</h6>"); 
			//ctx.redirect("customerInitial.html");
		}
		ctx.json(value);
	}
	
	@Override
	public void getAllBankAccounts(Context ctx) { //GET
		if(authControl.checkCustomer(ctx)) {
			String user = (String) ctx.cookieStore("cust");
			List<Account> listStorage = accountService.getAllBankAccounts(user);
			if(listStorage == null)
			{
				ctx.status(401);
				ctx.json("No bank accounts found");
			}else if(listStorage.isEmpty()) {
				ctx.status(401);
				ctx.json("No bank accounts found");				
			}else {
				ctx.status(200);
				ctx.json(listStorage);				
			}	
		}
	}

	@Override
	public void withdrawAmount(Context ctx) { //PUT
		if(authControl.checkCustomer(ctx)) {
		HashMap<String, String> hMap = (HashMap<String, String>) JSON.parse(ctx.body());
		ctx.json(customerService.withdrawAmount(
				Integer.valueOf(hMap.get("baseBankAccID")).intValue(),
				Double.valueOf(hMap.get("amount")).doubleValue()
				));
		}
		
	}

	@Override
	public void depositAmount(Context ctx) { //PUT
		if(authControl.checkCustomer(ctx)) {
		HashMap<String, String> hMap = (HashMap<String, String>) JSON.parse(ctx.body());
		ctx.json(customerService.depositAmount(
				Integer.valueOf(hMap.get("baseBankAccID")).intValue(),
				Double.valueOf(hMap.get("amount")).doubleValue()
				));
		}
	}

	@Override
	public void transferAmount(Context ctx) { //PUT
		if(authControl.checkCustomer(ctx)) {
		HashMap<String, String> hMap = (HashMap<String, String>) JSON.parse(ctx.body());
		ctx.json(customerService.transferAmount(
				Integer.valueOf(hMap.get("baseBankAccID")).intValue(),
				Integer.valueOf(hMap.get("transferBankAccID")).intValue(),
				Double.valueOf(hMap.get("amount")).doubleValue()
				));
		}
		
	}

	@Override
	public void accountApplication(Context ctx) { //POST
		if(authControl.checkCustomer(ctx)) {
			HashMap<String, String> hMap = (HashMap<String, String>) JSON.parse(ctx.body());

			String user = (String) ctx.cookieStore("cust");
			if(customerService.accountApplication(user,
				Double.valueOf(hMap.get("amount")).doubleValue(),
				hMap.get("accountType")
			)) {
				ctx.status(200); //Success
				ctx.json("Application submitted.");
			}
			else {
				ctx.status(401); //Failure
				ctx.json("Application failed to submit.");
			}
		}
	}

	@Override
	public void manageAmounts(Context ctx) {
		switch(ctx.formParam("manageType")) {
		case "withdraw":
			withdrawAmount(ctx);
			break;
		case "deposit":
			depositAmount(ctx);
			break;
		case "transfer":
			transferAmount(ctx);
			break;
		}
		
	}

	/*
	 *********************************** REFACTORED OUT OF CODE ****************************************
	@Override
	public void createBankAccount(Context ctx) {
		ctx.json(customerService.createBankAccount(
				ctx.attribute("amount"),
				ctx.attribute("accountType"),
				ctx.attribute("customerMemberNumber")
				));
		
	}

	@Override
	public void getCustomerAccount(Context ctx) { //Might not need this
		ctx.json(customerService.getCustomerAccount(
				));
		
	}

	@Override
	public void getCustomer(Context ctx) {
		ctx.json(customerService.getCustomer());
		
	}

	@Override
	public void getBankAccount(Context ctx) {
		try {
			ctx.json(customerService.getBankAccount(ctx.attribute("username")));
			ctx.status(); //Success
		} catch (CustomerIsNullException e) {
			ctx.status(); //failure
		}
		
	}
	
	@Override
	public void displayBalance(Context ctx) {
		ctx.json(customerService.displayBalance(
				ctx.attribute("baseBankAccID")));
		
	}
	*/
}
