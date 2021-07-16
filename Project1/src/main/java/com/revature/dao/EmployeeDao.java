package com.revature.dao;

import com.revature.models.Employee;

public interface EmployeeDao {

	//CRUD
	
	//CREATE
	int createEmployee(Employee e, String password);
	//READ
	Employee selectEmployeeById(int id);
	Employee selectEmployeeByName(String first_name, String last_name);
	Employee selectEmployeeByUsername(String username);
	boolean authenticateEmployee(String username, String password);
	//UPDATE
	boolean changePassword(String username, String password);
	//DELETE
		//There is no deletion of employees
}
