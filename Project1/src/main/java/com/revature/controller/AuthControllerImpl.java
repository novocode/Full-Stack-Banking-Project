package com.revature.controller;

import java.util.HashMap;

import org.eclipse.jetty.util.ajax.JSON;

import com.revature.service.CustomerManager;
import com.revature.service.CustomerManagerImpl;
import com.revature.service.EmployeeManager;
import com.revature.service.EmployeeManagerImpl;

import io.javalin.http.Context;

public class AuthControllerImpl implements AuthController {

	CustomerManager cM = new CustomerManagerImpl();
	EmployeeManager eM = new EmployeeManagerImpl();
	
	@Override
	public void loginCustomer(Context ctx) {
		HashMap<String, String> hMap = (HashMap<String, String>) JSON.parse(ctx.body());
		
		String username = hMap.get("username");				
		String password = hMap.get("password");
		
		if(cM.authenticateCustomer(username, password)) {
			ctx.status(200);
			ctx.cookieStore("cust", username);
			ctx.json("customerMenu.html");
		}else {
			ctx.status(401); //failure
			ctx.json("customerLogin.html");
		}		
	}

	@Override
	public void logoutCustomer(Context ctx) {
		ctx.clearCookieStore();
		ctx.status(200);
		ctx.json("index.html");
	}

	@Override
	public void loginEmployee(Context ctx) {
		HashMap<String, String> hMap = (HashMap<String, String>) JSON.parse(ctx.body());
		
		String username = hMap.get("username");				
		String password = hMap.get("password");
		if(eM.authenticateEmployee(username, password)) {
			ctx.status(200);
			ctx.cookieStore("emp", username);
			ctx.json("employeeMenu.html");
		}else {
			ctx.status(401); //failure
			ctx.json("employeeLogin.html");
		}		
	}

	@Override
	public void logoutEmployee(Context ctx) {
		ctx.clearCookieStore();
		ctx.status(200);
		ctx.json("index.html");		
	}

	@Override
	public boolean checkCustomer(Context ctx) {
		String user = (String) ctx.cookieStore("cust");
		
		if(user != null)
			return true;
		else
			return false;
		
	}

	@Override
	public boolean checkEmployee(Context ctx) {
		String user = (String) ctx.cookieStore("emp");
		
		if(user != null)
			return true;
		else
			return false;		
	}

}
