package com.revature.service;

import com.revature.dao.AccountDao;
import com.revature.dao.AccountDaoImpl;
import com.revature.dao.ApplicationDao;
import com.revature.dao.ApplicationDaoImpl;
import com.revature.dao.CustomerDao;
import com.revature.dao.CustomerDaoImpl;
import com.revature.exceptions.CustomerIsNullException;
import com.revature.models.Account;
import com.revature.models.Customer;

public class CustomerManagerImpl implements CustomerManager {

	CustomerDao cDao;
	AccountDao aDao;
	ApplicationDao appDao;
	
	public CustomerManagerImpl() {
		this(new CustomerDaoImpl(), new AccountDaoImpl());
	}

	public CustomerManagerImpl(CustomerDao cDao, AccountDao aDao) {
		super();
		this.cDao = cDao;
		this.aDao = aDao;
		this.appDao = new ApplicationDaoImpl();
	}

	@Override
	public int createCustomer(String username, String password, String firstname,
			String lastname, int ssn) {
		Customer currentCustomerInstance = new Customer(username, firstname, lastname, 0, ssn);
		return cDao.createCustomer(currentCustomerInstance, password);
	}

	@Override
	public boolean withdrawAmount(int accountNumber, double amount) {
		Account currentAccountInstance = aDao.getCustomerBankAccount(accountNumber);
		if(currentAccountInstance != null) {
			if(currentAccountInstance.getBalance() >= amount) {
				currentAccountInstance.withdrawBalance(amount);
				if(aDao.updateBalance(accountNumber, currentAccountInstance.getBalance()))
					return true;
				else
					return false;
			}
		}
		return false;
	}

	@Override
	public boolean depositAmount(int accountNumber, double amount) {
		Account currentAccountInstance = aDao.getCustomerBankAccount(accountNumber);
		if(currentAccountInstance != null) {
			currentAccountInstance.depositBalance(amount);
			if(aDao.updateBalance(accountNumber, currentAccountInstance.getBalance())) {
				return true;
			}
			else {
				return false;
			}
		}
		return false;
	}

	@Override
	public boolean transferAmount(int accountNumber, int transferToNumber, double amount) {
		if(withdrawAmount(accountNumber, amount)){
			if(depositAmount(transferToNumber, amount)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean accountApplication(String username, double amount, String string) {
		int customerMemNumber = cDao.selectCustomerByUsername(username).getBank_membership_number();
		return appDao.createAccount(customerMemNumber, string, amount);		
	}

	@Override
	public boolean createBankAccount(String type, double balance, int bank_membership_number) {
		return aDao.createAccount(bank_membership_number, type, balance);		
	}

	@Override
	public boolean authenticateCustomer(String username, String password) {
		return cDao.authenticateCustomer(username, password);
	}
}
