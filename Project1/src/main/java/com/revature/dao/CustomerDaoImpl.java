package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Customer;
import com.revature.util.ConnectionFactory;

public class CustomerDaoImpl implements CustomerDao {

	@Override
	public int createCustomer(Customer c, String password) {
		String sql1 = "INSERT INTO customers "
				+ "(customer_username, customer_password, customer_first_name, customer_last_name, customer_ssn) "
				+ "VALUES (?, ?, ?, ?, ?);";

		String sql2 = "SELECT customer_id FROM customers WHERE customer_username = ?;";
		int customerId = -1;
		
		try(Connection conn = ConnectionFactory.getConnection()){
			PreparedStatement ps = conn.prepareStatement(sql1);
			ps.setString(1, c.getUsername());
			ps.setString(2, password);
			ps.setString(3, c.getFirst_name());
			ps.setString(4, c.getLast_name());
			ps.setInt(5, c.getSsn());
			
			ps.execute();

			ps = conn.prepareStatement(sql2);
			ps.setString(1, c.getUsername());
			
			ResultSet rs = ps.executeQuery();
			while(rs.next())
				customerId = rs.getInt("customer_id");
		}
		catch(SQLException e) {
			
		}
		return customerId;
	}

	@Override
	public Customer selectCustomerById(int id) {
		String sql = "SELECT * FROM customers WHERE customer_id = ?;";
		
		Customer getCustomer = null;
		
		try(Connection conn = ConnectionFactory.getConnection()){
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next())
				getCustomer = new Customer(rs.getString("customer_username"),
										rs.getString("customer_first_name"), 
										rs.getString("customer_last_name"),
										rs.getInt("customer_id"),
										rs.getInt("customer_ssn"));
		}
		catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return getCustomer;
	}

	@Override
	public Customer selectCustomerByName(String first_name, String last_name) {
		String sql = "SELECT * FROM customers WHERE customer_first_name = ? AND customer_last_name = ?;";
		
		Customer getCustomer = null;
		
		try(Connection conn = ConnectionFactory.getConnection()){
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, first_name);
			ps.setString(2, last_name);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next())
				getCustomer = new Customer(rs.getString("customer_username"),
										rs.getString("customer_first_name"), 
										rs.getString("customer_last_name"),
										rs.getInt("customer_id"),
										rs.getInt("customer_ssn"));
		}
		catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return getCustomer;
	}

	@Override
	public Customer selectCustomerByUsername(String username) {
		String sql = "SELECT * FROM customers WHERE customer_username = ?;";
		
		Customer getCustomer = null;
		
		try(Connection conn = ConnectionFactory.getConnection()){
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next())
				getCustomer = new Customer(rs.getString("customer_username"),
										rs.getString("customer_first_name"), 
										rs.getString("customer_last_name"),
										rs.getInt("customer_id"),
										rs.getInt("customer_ssn"));
		}
		catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return getCustomer;
	}

	@Override
	public List<Customer> getAllCustomers() {
		String sql = "SELECT * FROM customers;";
		
		List<Customer> getCustomerList = new ArrayList<Customer>();
		
		try(Connection conn = ConnectionFactory.getConnection()){
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next())
				getCustomerList.add(new Customer(rs.getString("customer_username"),
										rs.getString("customer_first_name"), 
										rs.getString("customer_last_name"),
										rs.getInt("customer_id"),
										-1));
		}
		catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return getCustomerList;
	}

	@Override
	public boolean authenticateCustomer(String username, String password) {
		String sql = "SELECT customer_password FROM customers WHERE customer_username = ?;";
		
		String checkPassword = null;
		try(Connection conn = ConnectionFactory.getConnection()){
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next())
				checkPassword = rs.getString("customer_password");
		}
		catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		if(password.equals(checkPassword)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean changePassword(String username, String password) {
		String sql = "UPDATE customers "
				+ "SET customer_password = ? WHERE customer_username = ?;";
		
		try(Connection conn = ConnectionFactory.getConnection()){
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, password);
			ps.setString(2, username);
			
			ps.execute();
		}
		catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
