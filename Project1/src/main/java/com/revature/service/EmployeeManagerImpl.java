package com.revature.service;

import java.util.List;

import com.revature.dao.AccountDao;
import com.revature.dao.AccountDaoImpl;
import com.revature.dao.ApplicationDao;
import com.revature.dao.ApplicationDaoImpl;
import com.revature.dao.CustomerDao;
import com.revature.dao.CustomerDaoImpl;
import com.revature.dao.EmployeeDao;
import com.revature.dao.EmployeeDaoImpl;
import com.revature.models.Account;
import com.revature.models.Customer;
import com.revature.models.Employee;

public class EmployeeManagerImpl implements EmployeeManager {

	CustomerDao cDao;
	AccountDao aDao;
	ApplicationDao appDao;
	EmployeeDao eDao;
	
	Customer currentCustomerInstance;
	Account currentAccountInstance;
	
	public EmployeeManagerImpl() {
		this(new CustomerDaoImpl(), new AccountDaoImpl(), new ApplicationDaoImpl(), new EmployeeDaoImpl());
	}

	public EmployeeManagerImpl(CustomerDao cDao, AccountDao aDao, ApplicationDao appDao, EmployeeDao eDao) {
		super();
		this.cDao = cDao;
		this.aDao = aDao;
		this.appDao = appDao;
		this.eDao = eDao;
	}

	@Override
	public int createEmployee(String username, String firstname, String lastname, String password) {
		Employee emp = new Employee(username, firstname, lastname, -1);
		return eDao.createEmployee(emp, password);		
	}

	@Override
	public List<Account> viewCustomerAccounts(int accountNumber) {
		return aDao.getAllCustomerAccounts(accountNumber);
	}

	@Override
	public List<Account> reviewNewAccounts() {
		return appDao.selectAllApplications();
	}

	@Override
	public void removeApplication(int accountID) {
		appDao.removeApplication(accountID);		
	}

	@Override
	public boolean authenticateEmployee(String username, String password) {
		return eDao.authenticateEmployee(username, password);
	}

	@Override
	public List<Customer> getAllCustomers() {
		return cDao.getAllCustomers();
	}

}
