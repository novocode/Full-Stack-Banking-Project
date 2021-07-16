package com.revature.controller;

import java.util.List;

import com.revature.models.Transactions;
import com.revature.service.TransactionManager;
import com.revature.service.TransactionManagerImpl;

import io.javalin.http.Context;

public class TransactionsControllerImpl implements TransactionsController {

	private TransactionManager transactionService = new TransactionManagerImpl();
	
	@Override
	public void getAllTransactions(Context ctx) { //GET
		
		List<Transactions> getList = transactionService.getAllTransactions();
		if(getList == null) {
			ctx.status(); //Extreme failure, unable to create list
		}else if(getList.isEmpty()) {
			ctx.status(); //Failure, no transactions
		}else {
			ctx.json(getList);
			ctx.status(); //Success
		}		
	}
}
