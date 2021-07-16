package com.revature.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class ConnectionFactory {
	private static final String URL = System.getenv("PROJECT_TRAINING_URL");
	private static final String USERNAME = System.getenv("PROJECT_TRAINING_USERNAME");
	private static final String PASSWORD = System.getenv("PROJECT_TRAINING_PASSWORD");
	
	private static Connection conn;
	
	public static Connection getConnection() {
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}