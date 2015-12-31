/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Rob
 */
public class Employee {

	private int id;
	private String firstName;
	private String lastName;
	private String empId;

	private Database db;

	public Employee() {
		if (db == null) {
			db = new Database();
		}
	}

	public Employee(String number, String firstName, String lastName) {
		this.empId = number;
		this.firstName = firstName;
		this.lastName = lastName;

		if (db == null) {
			db = new Database();
		}
	}

	public Employee(ResultSet rs) throws SQLException {
		this.getEmployeeFromResultSet(rs);

		if (db == null) {
			db = new Database();
		}
	}

	public void getEmployeeFromResultSet(ResultSet rs) throws SQLException {
		// rs is already at current row, just load values
		this.firstName = rs.getString("first_name");
		this.lastName = rs.getString("last_name");
		this.empId = rs.getString(empId);
	}

	public static Employee getById(int id) throws SQLException, ClassNotFoundException {
		// Retrieve Employee with id
		Employee employee = null;

		String sql = "SELECT * FROM EMPLOYEES WHERE id = id;";

		ResultSet rs = Database.load(sql);

		while (rs.next()) {
			employee = new Employee(
					rs.getString("id"), 
					rs.getString("first_name"), 
					rs.getString("last_name"));
		}

		return employee;
	}

	public static boolean importData(ResultSet rs) throws SQLException, ClassNotFoundException {
		// rs is from a DBF record
		boolean result = true;

		Database db = new Database();
		db.connect();
		PreparedStatement stmt = null; 
		String sql = "";

		while (rs.next() && result) {
			Employee emp = Employee.getEmployeeFromDbfResultSet(rs);

			sql = "INSERT INTO EMPLOYEES (emp_id, first_name, last_name) "
				+ "VALUES (?, ?, ?)";
			
			stmt = db.con.prepareStatement(sql);
			stmt.setString(1, emp.empId);
			stmt.setString(2, emp.firstName);
			stmt.setString(3, emp.lastName);
			
			result = Database.insert(stmt) == 1;
		}

		stmt.close();
		db.con.commit();
		db.disconnect();

		return result;
	}

	public static Employee getEmployeeFromDbfResultSet(ResultSet rs) throws SQLException {
		// rs is from a DBF record
		Employee emp = new Employee();

		String[] name = rs.getString("name").split(" ");
		emp.firstName = name[0];
		emp.lastName = name[1];
		emp.empId = rs.getString("num");

		return emp;
	}

}
