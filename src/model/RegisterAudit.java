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
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import utils.Utils;

/**
 *
 * @author Rob
 */
public class RegisterAudit {

	private int id;
	private Date dateTime;
	private int shift;
	private float tapeRead;
	private float cashCount;
	private boolean audit;
	private int register;
	private String managerId;
	private List<Employee> employees;

	private Database db;

	public RegisterAudit() {
		// Default empty constructor
		if (this.db == null) {
			this.db = new Database();
		}
	}

	public RegisterAudit(String dateTime, int shift, float tapeRead,
			float cashCount, boolean audit, int register, String managerId) throws ParseException {
		this.dateTime = Utils.getDateTimeFromString(dateTime);
		this.shift = shift;
		this.tapeRead = tapeRead;
		this.cashCount = cashCount;
		this.audit = audit;
		this.register = register;
		this.managerId = managerId;
		this.employees = new ArrayList<>();
//		this.employees = RegisterAuditEmployee.getAuditEmployees(id);

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

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
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

	public int getRegister() {
		return register;
	}

	public void setRegister(int register) {
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

	public static void importData(ResultSet rs) throws SQLException, ClassNotFoundException, ParseException {
		/*
		 ***** ResultSet is from the DBF database *****
		 */

		while (rs.next()) {
			// ShiftData constructor takes a ResultSet with the row pointer at the desired location
			RegisterAudit registerAuditData = RegisterAudit.getRegisterAuditFromDbfResultSet(rs);

			int auditId = registerAuditData.insert();
			
			RegisterAuditEmployee.insertRegisterAuditEmployeeFromDbf(rs, auditId);
			
		}
	}

	public static RegisterAudit getRegisterAuditFromDbfResultSet(ResultSet rs)
			throws SQLException, ClassNotFoundException, ParseException {
		// Loads fields from the current row of rs, so do NOT move the row pointer!
		RegisterAudit data = new RegisterAudit();

		data.shift = rs.getInt("shift");

		String dateTime = rs.getString("this_date") + " " + rs.getString("time") + " " + rs.getString("ampm");
		data.dateTime = Utils.getDateTimeFromString(dateTime);
		
		data.tapeRead = rs.getFloat("tape_read");
		data.cashCount = rs.getFloat("cash_count");
		data.audit = rs.getBoolean("audit");
		data.register = rs.getInt("register");
		data.managerId = rs.getString("mgr");

		return data;
	}
	
	public int insert() throws SQLException, ClassNotFoundException, ParseException {
		// Insert employee into the EMPLOYEES table
		db.connect();

		String sql = null;
		int auditId = -1;

		Statement stmt = db.con.createStatement();
		sql = "INSERT INTO REGISTER_AUDITS (date_time, shift, tape_read, cash_count, audit, register, manager_id) "
				+ "VALUES ('" + Utils.parseDateTimeToString(this.dateTime) + "', " + 
				this.shift + ", " + 
				this.tapeRead + ", " + 
				this.cashCount + ", " + 
				(this.audit ? 1 : 0) + ", '" + 
				this.register + "', '" + 
				this.managerId + "');";

		int test = stmt.executeUpdate(sql);

		// Get the ID of this RegisterAudit
		auditId = Database.getLastRowId(stmt, "REGISTER_AUDITS");

		stmt.close();

		db.disconnect();

		return auditId;
	}

}
