package com.revature.controller;

import io.javalin.http.Context;

public interface EmployeeController {

	public void createEmployee(Context ctx);
	public void getAllApplications(Context ctx);
	public void getAllCustomers(Context ctx);
	public void getAllRequests(Context ctx);
}
