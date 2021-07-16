package com.revature.service;

import java.util.List;

import com.revature.models.Account;

public interface AccountManager {

	List<Account> getAllBankAccounts(String user);
}
