package com.revature.service;

import java.util.List;

import com.revature.models.Transactions;

public interface TransactionManager {

	void createTransactionRecord(int accountNumber, double amount, String string);

	void createTransactionRecord(int accountNumber, double amount, String string, int transferToNumber);

	void createTransactionRecord(double amount, String transactionType, String accountType);

	List<Transactions> getAllTransactions();
}
