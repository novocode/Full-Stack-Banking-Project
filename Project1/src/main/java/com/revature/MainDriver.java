package com.revature;

import com.revature.controller.AuthController;
import com.revature.controller.AuthControllerImpl;
import com.revature.controller.CustomerController;
import com.revature.controller.CustomerControllerImpl;
import com.revature.controller.EmployeeController;
import com.revature.controller.EmployeeControllerImpl;
import com.revature.controller.TransactionsController;
import com.revature.controller.TransactionsControllerImpl;

import io.javalin.Javalin;

public class MainDriver {
	private static final String CUSTOMER_LOGIN_PATH = "/customerLogin";
	private static final String EMPLOYEE_LOGIN_PATH = "/employeeLogin";

	private static final String CUSTOMER_MAIN_MENU = "/customerMenu";
	private static final String EMPLOYEE_MAIN_MENU = "/employeeMenu";

	private static final String CUSTOMER_NEW_MENU = "/customerInitial";
	private static final String EMPLOYEE_NEW_MENU = "/employeeInitial";
	
	public static void main(String[] args)
	{
		TransactionsController tC = new TransactionsControllerImpl();
		AuthController aC = new AuthControllerImpl();
		CustomerController cC = new CustomerControllerImpl();
		EmployeeController eC = new EmployeeControllerImpl();
		
		Javalin app = Javalin.create(
			config -> {
				config.addStaticFiles("/HTMLFiles");
			}
		).start(9000);	
		
		//Login
		app.post(CUSTOMER_LOGIN_PATH, ctx -> aC.loginCustomer(ctx)); //Login Customer
		app.post(EMPLOYEE_LOGIN_PATH, ctx -> aC.loginEmployee(ctx)); //Login Employee
		//GET
		app.get(CUSTOMER_LOGIN_PATH, ctx -> aC.logoutCustomer(ctx)); //Logout Customer
		app.get(EMPLOYEE_LOGIN_PATH, ctx -> aC.logoutEmployee(ctx)); //Logout Employee
		app.get(CUSTOMER_MAIN_MENU, ctx -> cC.getAllBankAccounts(ctx)); // Get all of Customer bank account
		app.post(EMPLOYEE_MAIN_MENU, ctx -> eC.getAllRequests(ctx)); //Sending ctx to single function for distribution
		//POST
		app.post(CUSTOMER_NEW_MENU, ctx -> cC.createCustomer(ctx)); // Create new Customer
		app.post(EMPLOYEE_NEW_MENU, ctx -> eC.createEmployee(ctx)); // Create new Employee
		app.post(CUSTOMER_MAIN_MENU, ctx -> cC.accountApplication(ctx)); // Customer apply
		//PUT
		app.put(CUSTOMER_MAIN_MENU, ctx -> cC.manageAmounts(ctx)); //Sending ctx to single function for distribution
		//DELETE
	}
	
	/*
	 * FYI: The following GET and PUT calls are managed by a central function stated:
	 * 
	 ****************** Handled by getAllRequests:
		app.get(EMPLOYEE_MAIN_MENU, ctx -> eC.getAllApplications(ctx)); // Get all applications from Employee Menu
		app.get(EMPLOYEE_MAIN_MENU, ctx -> eC.getAllCustomers(ctx)); // Get all customers from Employee Menu
		app.get(EMPLOYEE_MAIN_MENU, ctx -> cC.getAllBankAccounts(ctx)); // Get all the Customer's bank accounts from Employee
		app.get(EMPLOYEE_MAIN_MENU, ctx -> tC.getAllTransactions(ctx)); //Employee Menu, get all transactions
	 * 
	 ****************** Handled by manageAmounts: 
		app.put(CUSTOMER_MAIN_MENU, ctx -> cC.withdrawAmount(ctx)); //Update Customer Bank Account Withdraw
		app.put(CUSTOMER_MAIN_MENU, ctx -> cC.depositAmount(ctx)); //Update Customer Bank Account Deposit
		app.put(CUSTOMER_MAIN_MENU, ctx -> cC.transferAmount(ctx)); //Update Customer Bank Account Transfer
	 * 
	 */
}
