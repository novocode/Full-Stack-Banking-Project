package com.revature.models;

import java.util.Objects;

public class Employee {
	String username;
	String first_name;
	String last_name;
	int employee_number;
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Employee(String username, String first_name, String last_name, int employee_number) {
		super();
		this.username = username;
		this.first_name = first_name;
		this.last_name = last_name;
		this.employee_number = employee_number;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public int getEmployee_number() {
		return employee_number;
	}
	public void setEmployee_number(int employee_number) {
		this.employee_number = employee_number;
	}
	@Override
	public String toString() {
		return "Employee [username=" + username + ", first_name=" + first_name + ", last_name=" + last_name
				+ ", employee_number=" + employee_number + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(employee_number, first_name, last_name, username);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return employee_number == other.employee_number && Objects.equals(first_name, other.first_name)
				&& Objects.equals(last_name, other.last_name) && Objects.equals(username, other.username);
	}
}
