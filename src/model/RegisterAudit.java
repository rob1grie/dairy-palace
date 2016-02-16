/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import utils.Utils;

/**
 *
 * @author Rob
 */
public class RegisterAudit {

	private int id;
	private LocalDate auditDate;
	private LocalTime auditTime;
	private int shift;
	private float tapeRead;
	private float cashCount;
	private boolean audit;
	private String register;
	private String managerId;
	private List<Employee> employees;

	private Database db;

	public RegisterAudit() {
		// Default empty constructor
		if (this.db == null) {
			this.db = new Database();
		}
	}

	public RegisterAudit(LocalDate auditDate, LocalTime auditTime, int shift, float tapeRead,
			float cashCount, boolean audit, String register, String managerId) throws ParseException {
		this.auditDate = auditDate;
		this.auditTime = auditTime;
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

	public RegisterAudit(ResultSet rs) throws SQLException, ParseException {
		// Constructor from a ResultSet
		// Calling method must place ResultSet row pointer
		this.getRegisterAuditFromResultSet(rs);
	}

	private void getRegisterAuditFromResultSet(ResultSet rs) throws SQLException, ParseException {
		this.auditDate = Utils.getDateFromString(rs.getDate("audit_date").toString());
		this.auditTime = Utils.getTimeFromString(rs.getTime("audit_time").toString());
		this.shift = rs.getInt("shift");
		this.tapeRead = rs.getFloat("tape_read");
		this.cashCount = rs.getFloat("cash_count");
		this.audit = rs.getInt("is_audit") == 1;
		this.register = rs.getString("register");
		this.managerId = rs.getString("manager_id");
		this.employees = this.loadEmployees();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getAuditDate() {
		return auditDate;
	}

	public void setAuditDate(LocalDate auditDate) {
		this.auditDate = auditDate;
	}

	public LocalTime getAuditTime() {
		return this.auditTime;
	}

	public void setAuditTime(LocalTime auditTime) {
		this.auditTime = auditTime;
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

	public List<Employee> loadEmployees() {
		// Loads Employees from RegisterAuditAmployee
		ArrayList<Employee> employees = new ArrayList<>();

		return employees;
	}

	public static boolean importData(ResultSet rs) throws SQLException, ClassNotFoundException, ParseException {
		// rs is from a DBF record
		boolean result = true;

		Database db = new Database();
		db.connect();
		PreparedStatement pStmt = null;
		String sql = "";

		while (rs.next() && result) {
			RegisterAudit audit = RegisterAudit.getRegisterAuditFromDbfResultSet(rs);

			sql = "INSERT INTO REGISTER_AUDITS (audit_date, audit_time, shift, tape_read, cash_count, audit, register, manager_id) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			pStmt = db.con.prepareStatement(sql, new String[]{"ID"});

			pStmt.setString(1, audit.auditDate.toString());
			pStmt.setString(2, audit.auditTime.toString());
			pStmt.setInt(3, audit.shift);
			pStmt.setFloat(4, audit.tapeRead);
			pStmt.setFloat(5, audit.cashCount);
			pStmt.setInt(6, audit.audit ? 1 : 0);
			pStmt.setString(7, audit.register);

			// managerID is blank on some entries
			if (audit.managerId != null) {
				pStmt.setString(8, audit.managerId);
			} else {
				pStmt.setString(8, "");
			}

			result = Database.insert(pStmt) == 1;
			ResultSet gk = pStmt.getGeneratedKeys(); // will return the ID in id);

			while (gk.next()) {
				audit.id = gk.getInt("1");
			}

			if (result) {
				ArrayList<RegisterAuditEmployee> employees = RegisterAuditEmployee.importData(rs, audit.id);

				if (employees.size() > 0) {
					try (Statement stmt = db.con.createStatement()) {
						for (RegisterAuditEmployee employee : employees) {
							sql = "INSERT INTO REGISTER_AUDIT_EMPLOYEES (audit_id, employee_id) "
									+ "VALUES (" + employee.getAuditId() + ", " + employee.getId() + ")";
							stmt.executeUpdate(sql);
						}
					}
				}
			}
		}

		pStmt.close();
		db.con.commit();
		db.disconnect();

		return result;
	}

	public static RegisterAudit getRegisterAuditFromDbfResultSet(ResultSet rs)
			throws SQLException, ClassNotFoundException, ParseException {
		// Loads fields from the current row of rs, so do NOT move the row pointer!
		RegisterAudit data = new RegisterAudit();

		data.shift = rs.getInt("shift");

		LocalDate auditDate = Utils.getDateFromString(rs.getString("this_date"));
		LocalTime auditTime = Utils.getTimeFromString(rs.getString("time") + " " + rs.getString("ampm"));
		data.auditDate = auditDate;
		data.auditTime = auditTime;

		data.tapeRead = rs.getFloat("tape_read");
		data.cashCount = rs.getFloat("cash_count");
		data.audit = rs.getBoolean("audit");
		data.register = rs.getString("register");
		data.managerId = rs.getString("mgr");

		return data;
	}

	public int insert() throws SQLException, ClassNotFoundException, ParseException {
		// Insert employee into the EMPLOYEES table
		db.connect();

		String sql = null;
		int auditId = -1;

		sql = "INSERT INTO REGISTER_AUDITS (audit_date, audit_time, shift, tape_read, cash_count, audit, register, manager_id) "
				+ "VALUES ('" + this.auditDate + "', '"
				+ this.auditTime + "', "
				+ this.shift + ", "
				+ this.tapeRead + ", "
				+ this.cashCount + ", "
				+ (this.audit ? 1 : 0) + ", '"
				+ this.register + "', '"
				+ this.managerId + "')";

		PreparedStatement ps = db.con.prepareStatement(sql,
				Statement.RETURN_GENERATED_KEYS);

		ps.execute();

		// Get the ID of this RegisterAudit
		ResultSet rs = ps.getGeneratedKeys();
		if (rs.next()) {
			auditId = rs.getInt(1);
		}

		db.disconnect();

		return auditId;
	}

}
