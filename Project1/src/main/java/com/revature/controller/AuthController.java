package com.revature.controller;

import io.javalin.http.Context;

public interface AuthController {

	public void loginCustomer(Context ctx);
	public void logoutCustomer(Context ctx);
	public void loginEmployee(Context ctx);
	public void logoutEmployee(Context ctx);
	public boolean checkCustomer(Context ctx);
	public boolean checkEmployee(Context ctx);
}
