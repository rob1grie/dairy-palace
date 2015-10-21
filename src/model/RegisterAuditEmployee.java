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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Utils;

/**
 *
 * @author Rob
 */
public class RegisterAuditEmployee {

	private int id;
	private int auditId;
	private int employeeId;

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

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
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

	public static boolean insert(int auditId, int employeeId) {
		Connection c = null;
		Statement stmt = null;

		try {
			c = DriverManager.getConnection("jdbc:sqlite:dairy.db");
			c.setAutoCommit(false);

			stmt = c.createStatement();
			String sql = "INSERT INTO REGISTER_AUDIT_EMPLOYEE (audit_id, employee_id) VALUES (" + auditId
					+ ", " + employeeId + ");";
			stmt.executeUpdate(sql);

			stmt.close();
			c.commit();
			c.close();
		} catch (SQLException ex) {
			Logger.getLogger(RegisterAuditEmployee.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		}
		return true;
	}

	public static void insertRegisterAuditEmployeeFromDbf(ResultSet rs, int auditId) throws SQLException {
		// Receives a DBF record in rs, tests for each Employee field 1 through 5 and inserts any non-empty
		// Test each Employee field
		
//		Utils.listResultSetFields(rs);
		
		if (rs.getString("emp1num") != null) {
			int id = Integer.parseInt(rs.getString("emp1num"));
			RegisterAuditEmployee.insert(auditId, id);
		}
		if (rs.getString("emp2num") != null) {
			int id = Integer.parseInt(rs.getString("emp2num"));
			RegisterAuditEmployee.insert(auditId, id);
		}
		if (rs.getString("emp3num") != null) {
			int id = Integer.parseInt(rs.getString("emp3num"));
			RegisterAuditEmployee.insert(auditId, id);
		}
		if (rs.getString("emp4num") != null) {
			int id = Integer.parseInt(rs.getString("emp4num"));
			RegisterAuditEmployee.insert(auditId, id);
		}
		if (rs.getString("emp5num") != null) {
			int id = Integer.parseInt(rs.getString("emp5num"));
			RegisterAuditEmployee.insert(auditId, id);
		}

	}

}
