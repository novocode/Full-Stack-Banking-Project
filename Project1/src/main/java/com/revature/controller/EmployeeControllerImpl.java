package com.revature.controller;

import java.util.HashMap;
import java.util.List;

import org.eclipse.jetty.util.ajax.JSON;

import com.revature.models.Account;
import com.revature.models.Customer;
import com.revature.service.EmployeeManager;
import com.revature.service.EmployeeManagerImpl;

import io.javalin.http.Context;

public class EmployeeControllerImpl implements EmployeeController {

	private EmployeeManager employeeManager = new EmployeeManagerImpl();
	private CustomerController cC = new CustomerControllerImpl();
	private TransactionsController tC = new TransactionsControllerImpl();
	
	@Override
	public void createEmployee(Context ctx) { //POST
		HashMap<String, String> hMap = (HashMap<String, String>) JSON.parse(ctx.body());
		int value = employeeManager.createEmployee(
				hMap.get("username"),
				hMap.get("password"),
				hMap.get("firstname"),
				hMap.get("lastname"));
		if(value == -1)
			ctx.status(401);
		else
			ctx.status(200);
		
		ctx.json(value);
	}

	@Override
	public void getAllApplications(Context ctx) { //GET
		List<Account> getList = employeeManager.reviewNewAccounts();
		if(getList == null) {
			ctx.status(401); //Massive Failure
		} else if(getList.isEmpty()) {
			ctx.status(401); //Failure
		}else {
			ctx.json(getList);
			ctx.status(200);
		}
	}

	@Override
	public void getAllCustomers(Context ctx) { //GET
		List<Customer> getList = employeeManager.getAllCustomers();
		if(getList == null) {
			ctx.status(401); //Massive Failure
		} else if(getList.isEmpty()) {
			ctx.status(401); //Failure
		}else {
			ctx.json(getList);
			ctx.status(200);
		}
	}

	@Override
	public void getAllRequests(Context ctx) {
		HashMap<String, String> hMap = (HashMap<String, String>) JSON.parse(ctx.body());
		switch(hMap.get("manageType")) {
		case "allApplications":
			getAllApplications(ctx);
			break;
		case "allCustomers":
			getAllCustomers(ctx);
			break;
		case "allAccounts":
			cC.getAllBankAccounts(ctx);
			break;
		case "allTransactions":
			tC.getAllTransactions(ctx);
			break;
		}
	}

}
