/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rob
 */
public class RegisterAuditEmployee {

	private int id;
	private int auditId;

	private Database db;

	public RegisterAuditEmployee(int id) {
		this.id = id;

		if (this.db == null) {
			this.db = new Database();
		}
	}

	public RegisterAuditEmployee(int id, int auditId) {
		this.id = id;
		this.auditId = auditId;

		if (this.db == null) {
			this.db = new Database();
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAuditId() {
		return auditId;
	}

	public void setAuditId(int auditId) {
		this.auditId = auditId;
	}

	public static List<Employee> getAuditEmployees(int auditId) {
		List<Employee> employees = new ArrayList<Employee>();
		Database db = new Database();

		Connection c = null;
		Statement stmt = null;
		Employee employee = null;

		try {
			db.connect();

			String sql = "SELECT * FROM REGISTER_AUDIT_EMPLOYEE WHERE audit_id = auditId;";

			ResultSet rs = db.getResultSet(sql);

			while (rs.next()) {
				employee = new Employee(rs.getString("id"), rs.getString("first_name"), rs.getString("last_name"));
				employees.add(employee);
			}

			db.disconnect();

		} catch (Exception e) {
		}

		return employees;
	}

	public static ArrayList<RegisterAuditEmployee> importData(ResultSet rs, int id) throws SQLException {
		// Receives a ResultSet from a Register DBF record and returns an ArrayList of RegisterAuditEmployees
		
		ArrayList<RegisterAuditEmployee> al = new ArrayList<>();
		
		// Test each EmpnNum before adding to al
		if (!rs.getString("EMP1NUM").isEmpty()) {
			RegisterAuditEmployee employee = 
					new RegisterAuditEmployee(Integer.parseInt(rs.getString("EMP1NUM")), id);
			al.add(employee);
		}
		
		if (!rs.getString("EMP2NUM").isEmpty()) {
			RegisterAuditEmployee employee = 
					new RegisterAuditEmployee(Integer.parseInt(rs.getString("EMP2NUM")), id);
			al.add(employee);
		}
		
		if (!rs.getString("EMP3NUM").isEmpty()) {
			RegisterAuditEmployee employee = 
					new RegisterAuditEmployee(Integer.parseInt(rs.getString("EMP3NUM")), id);
			al.add(employee);
		}
		
		if (!rs.getString("EMP4NUM").isEmpty()) {
			RegisterAuditEmployee employee = 
					new RegisterAuditEmployee(Integer.parseInt(rs.getString("EMP4NUM")), id);
			al.add(employee);
		}
		
		if (!rs.getString("EMP5NUM").isEmpty()) {
			RegisterAuditEmployee employee = 
					new RegisterAuditEmployee(Integer.parseInt(rs.getString("EMP5NUM")), id);
			al.add(employee);
		}
		
		return al;
	}
}
