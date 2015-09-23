/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rob
 */
public class Employee {

	private int id;
	private String firstName;
	private String lastName;
	private String number;

	private Database db;

	public Employee() {
		if (db == null) {
			db = new Database();
		}
	}

	public Employee(String number, String firstName, String lastName) {
		this.number = number;
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
		this.number = rs.getString(number);
	}

	public boolean insert() throws SQLException, ClassNotFoundException {
		// Insert employee into the EMPLOYEES table
		boolean result = false;
		this.db.connect();

		Statement stmt = null;
		String sql = "INSERT INTO EMPLOYEES (id, first_name, last_name) "
				+ "VALUES (" + this.number + ", '" + this.firstName + "', '" + this.lastName + "');";

		stmt = db.con.createStatement();
		stmt.executeUpdate(sql);

		if (stmt != null) {
			stmt.close();
		}

		return result;
	}

	public static Employee getById(int id) throws SQLException, ClassNotFoundException {
		// Retrieve Employee with id
		Database db = new Database();
		Employee employee = null;

		Connection c = null;
		Statement stmt = null;

		db.connect();
		c = DriverManager.getConnection("jdbc:sqlite:dairy.db");
		c.setAutoCommit(false);

		stmt = c.createStatement();
		String sql = "SELECT * FROM EMPLOYEES WHERE id = id;";

		ResultSet rs = stmt.executeQuery(sql);

		while (rs.next()) {
			employee = new Employee(rs.getString("id"), rs.getString("first_name"), rs.getString("last_name"));
		}

		stmt.close();
		c.commit();
		c.close();

		return employee;
	}

	public static boolean importData(ResultSet rs) throws SQLException, ClassNotFoundException {
		/*
		 Import process:
		 ***** ResultSet is from the DBF database *****
		 1 - Create Employee object from the ResultSet rs
		 */
		while (rs.next()) {
			Employee emp = Employee.getEmployeeFromDbfResultSet(rs);

			emp.insert();
		}

		return true;
	}

	public static Employee getEmployeeFromDbfResultSet(ResultSet rs) throws SQLException {
		// rs is from a DBF record
		Employee emp = new Employee();

		String[] name = rs.getString("name").split(" ");
		emp.firstName = name[0];
		emp.lastName = name[1];
		emp.number = rs.getString("num");

		return emp;
	}

}
