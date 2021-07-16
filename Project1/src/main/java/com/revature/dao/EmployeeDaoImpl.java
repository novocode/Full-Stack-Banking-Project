package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.models.Employee;
import com.revature.util.ConnectionFactory;

public class EmployeeDaoImpl implements EmployeeDao {

	@Override
	public int createEmployee(Employee emp, String password) {
		String sql1 = "INSERT INTO employee "
				+ "(employee_username, employee_password, employee_first_name, employee_last_name) "
				+ "VALUES (?, ?, ?, ?)";

		String sql2 = "SELECT employee_id FROM employee WHERE employee_username = ?;";
		int employeeId = -1;
		
		try(Connection conn = ConnectionFactory.getConnection()){
			PreparedStatement ps = conn.prepareStatement(sql1);
			ps.setString(1, emp.getUsername());
			ps.setString(2, password);
			ps.setString(3, emp.getFirst_name());
			ps.setString(4, emp.getLast_name());
			
			ps.execute();

			ps = conn.prepareStatement(sql2);
			ps.setString(1, emp.getUsername());
			
			ResultSet rs = ps.executeQuery();
			while(rs.next())
				employeeId = rs.getInt("employee_id");
		}
		catch(SQLException e) {
			e.printStackTrace();
			
		}
		return employeeId;
	}

	@Override
	public Employee selectEmployeeById(int id) {
		String sql = "SELECT * FROM employee WHERE employee_id = ?;";
		
		Employee getEmployee = null;
		
		try(Connection conn = ConnectionFactory.getConnection()){
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next())
				getEmployee = new Employee(rs.getString("employee_username"),
										rs.getString("employee_first_name"), 
										rs.getString("employee_last_name"),
										rs.getInt("employee_id"));
		}
		catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return getEmployee;
	}

	@Override
	public Employee selectEmployeeByName(String first_name, String last_name) {
		String sql = "SELECT * FROM employee WHERE employee_first_name = ? AND employee_last_name = ?;";
		
		Employee getEmployee = null;
		
		try(Connection conn = ConnectionFactory.getConnection()){
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, first_name);
			ps.setString(2, last_name);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next())
				getEmployee = new Employee(rs.getString("employee_username"),
										rs.getString("employee_first_name"), 
										rs.getString("employee_last_name"),
										rs.getInt("employee_id"));
		}
		catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return getEmployee;
	}

	@Override
	public Employee selectEmployeeByUsername(String username) {
		String sql = "SELECT * FROM employee WHERE employee_username = ?;";
		
		Employee getEmployee = null;
		
		try(Connection conn = ConnectionFactory.getConnection()){
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			
			ResultSet rs = ps.executeQuery();
			getEmployee = new Employee(rs.getString("employee_username"),
					rs.getString("employee_first_name"), 
					rs.getString("employee_last_name"),
					rs.getInt("employee_id"));
		}
		catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return getEmployee;
	}

	@Override
	public boolean authenticateEmployee(String username, String password) {
		String sql = "SELECT employee_password FROM employee WHERE employee_username = ?;";
		
		String checkPassword = null;
		try(Connection conn = ConnectionFactory.getConnection()){
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next())
				checkPassword = rs.getString("employee_password");
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
		String sql = "UPDATE employee "
				+ "SET employee_password = ? WHERE employee_username = ?;";
		
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
