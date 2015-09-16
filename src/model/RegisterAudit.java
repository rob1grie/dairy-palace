/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import utils.Utils;

/**
 *
 * @author Rob
 */
public class RegisterAudit {

	private int id;
	private String dateTime;
	private int shift;
	private float tapeRead;
	private float cashCount;
	private boolean audit;
	private String register;
	private String managerId;
	private List<Employee> employees;

	private Database db;

	public RegisterAudit(String time, int shift, float tapeRead,
			float cashCount, boolean audit, String register, String managerId) {
		this.dateTime = time;
		this.shift = shift;
		this.tapeRead = tapeRead;
		this.cashCount = cashCount;
		this.audit = audit;
		this.register = register;
		this.managerId = managerId;
		this.employees = RegisterAuditEmployee.getAuditEmployees(id);

		if (this.db == null) {
			db = new Database();
		}
	}

	public RegisterAudit(int id) {
		this.id = id;

		if (this.db == null) {
			db = new Database();
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTime() {
		return dateTime;
	}

	public void setTime(String time) {
		this.dateTime = time;
	}

	public int getShift() {
		return shift;
	}

	public void setShift(int shift) {
		this.shift = shift;
	}

	public float getTapeRead() {
		return tapeRead;
	}

	public void setTapeRead(float tapeRead) {
		this.tapeRead = tapeRead;
	}

	public float getCashCount() {
		return cashCount;
	}

	public void setCashCount(float cashCount) {
		this.cashCount = cashCount;
	}

	public boolean getAudit() {
		return audit;
	}

	public void setAudit(boolean audit) {
		this.audit = audit;
	}

	public String getRegister() {
		return register;
	}

	public void setRegister(String register) {
		this.register = register;
	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	public void loadEmployees() {
		// Loads Employees from RegisterAuditAmployee

	}

	public static boolean importData(ResultSet rs) throws Exception {

		
//		// file is a CSV file obtained from a CSV filtered file chooser
//		BufferedReader br = null;
//		String line = "";
//		String cvsSplitBy = ",";
//		String[] reg = null;
//		String dateTime = null;
//		String time = null;
//		int shift = -1;
//		float tapeRead = 0;
//		float cashCount = 0;
//		boolean audit = false;
//		String register = null;
//		String managerId = null;
//		int auditId = -1;
//
//		try {
//			br = new BufferedReader(new FileReader(file.toString()));
//			// First line is field names
//			line = br.readLine();
//
//			while ((line = br.readLine()) != null) {
//				// use comma as separator
//				reg = line.split(cvsSplitBy);
//				dateTime = reg[5] + " " + reg[6] + " " + reg[7];
//				time = Utils.getLocalDateTime(dateTime);
//				shift = Integer.parseInt(reg[8]);
//				tapeRead = Float.parseFloat(reg[9]);
//				cashCount = Float.parseFloat(reg[10]);
//				audit = Boolean.parseBoolean(reg[11]);
//				register = reg[12];
//
//				// Some imported records have no manager ID
//				if (reg.length == 14) {
//					managerId = reg[13];
//				} else {
//					managerId = "";
//				}
//
//				RegisterAudit regAudit = new RegisterAudit(time, shift, tapeRead, cashCount, audit, register, managerId);
//
//				// Register import data has five columns of Employee numbers
//				// Get each column and if it is not empty get the Employee with that ID
//				auditId = regAudit.insert();
//
//				int emp = -1;
//				for (int i = 0; i < 5; i++) {
//					if (!reg[i].isEmpty()) {
//						int test = Integer.parseInt(reg[i]);
//						emp = Integer.parseInt(reg[i]);
//						RegisterAuditEmployee.insert(auditId, emp);
//					}
//				}
//
//			}
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//			return false;
//		} catch (IOException ex) {
//			ex.printStackTrace();
//			return false;
//		} finally {
//			if (br != null) {
//				try {
//					br.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//					return false;
//				}
//			}
//		}
//
		return true;
	}

	public int insert() throws Exception {
		// Insert employee into the EMPLOYEES table
		db.connect();

		Connection c = null;
		Statement stmt = null;
		String sql = null;
		int auditId = -1;

		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:dairy.db");
			c.setAutoCommit(false);

			stmt = c.createStatement();
			sql = "INSERT INTO REGISTER_AUDITS (date_time, shift, tape_read, cash_count, audit, register, manager_id) "
					+ "VALUES ('" + this.dateTime + "', " + this.shift + ", " + this.tapeRead + ", " + this.cashCount + ", "
					+ (this.audit ? 1 : 0) + ", '" + this.register + "', '" + this.managerId + "');";

			stmt.executeUpdate(sql);

			// Get the ID of this RegisterAudit
			auditId = Database.getLastRowId(stmt, "REGISTER_AUDITS");

			// Get all non-empty Employee IDs and insert them into RegisterAuditEmployee
			stmt.close();
			c.commit();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}

		db.disconnect();
		
		return auditId;
	}

}
