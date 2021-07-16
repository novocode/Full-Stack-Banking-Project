package com.revature.controller;

import io.javalin.http.Context;

public interface TransactionsController {
	public void getAllTransactions(Context ctx);
}
