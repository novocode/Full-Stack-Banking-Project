package com.revature.service;

import java.util.List;

import com.revature.dao.AccountDao;
import com.revature.dao.AccountDaoImpl;
import com.revature.dao.CustomerDao;
import com.revature.dao.CustomerDaoImpl;
import com.revature.models.Account;

public class AccountManagerImpl implements AccountManager {

	AccountDao aDao = new AccountDaoImpl();
	CustomerDao cDao = new CustomerDaoImpl();
	
	@Override
	public List<Account> getAllBankAccounts(String username) {
		int customerMemberNumber = cDao.selectCustomerByUsername(username).getBank_membership_number();
		return aDao.getAllCustomerAccounts(customerMemberNumber);
	}

}
