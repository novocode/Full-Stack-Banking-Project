package com.revature.service;

import java.util.List;

import com.revature.dao.TransactionsDao;
import com.revature.dao.TransactionsDaoImpl;
import com.revature.models.Transactions;

public class TransactionManagerImpl implements TransactionManager {
	
	TransactionsDao tDao;

	public TransactionManagerImpl() {
		super();
		this.tDao = new TransactionsDaoImpl();
	}

	//The create transaction record is only used within Service, but can't set to protected 
	//Used for Withdraw and Deposit
	@Override
	public void createTransactionRecord(int accountNumber, double amount, String transactionType) {
		tDao.createTranRecord(accountNumber, amount, transactionType);

	}

	//The create transaction record is only used within Service, but can't set to protected 
	//Used for Transfer
	@Override
	public void createTransactionRecord(int accountNumber, double amount, String transactionType, int transferToNumber) {
		tDao.createTransferTranRecord(accountNumber, amount, transactionType, transferToNumber);

	}

	//The create transaction record is only used within Service, but can't set to protected 
	//Used for New Application
	@Override
	public void createTransactionRecord(double amount, String transactionType, String accountType) {
		tDao.createAppTranRecord(amount, transactionType, accountType);

	}

	@Override
	public List<Transactions> getAllTransactions() {
		
		return tDao.getAllTransactions();
	}

}
