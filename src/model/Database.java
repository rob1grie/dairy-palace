/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rob
 */

/*
 Add throws Exception clause to methods so that the calling method can check for exceptions
 Use try/catch in the calling methods
 NEVER use literal values in SQL, always use a prepared statement so special characters or anything else can be caught
 */
public class Database {

	public Connection con = null;
	private int lastRowId = 0;

	public Database() {

	}

	public void connect() throws SQLException, ClassNotFoundException {
		if (this.con != null) {
			return;
		}
		System.setProperty("derby.system.home", "./");
		this.con = DriverManager.getConnection("jdbc:derby:dairydb;create=true;user=dairy");
		this.con.setAutoCommit(false);
	}

	public void disconnect() throws SQLException {
		if (!this.con.isClosed()) {
			try {
				this.con.close();
			} catch (SQLException ex) {
			}
		}
	}

	public int getLastRowId() {
		return lastRowId;
	}

	private static void readDBTable(Set<String> set, DatabaseMetaData dbmeta, String searchCriteria, String schema)
			throws SQLException {
		ResultSet rs = dbmeta.getTables(null, schema, null, new String[]{searchCriteria});
		while (rs.next()) {
			set.add(rs.getString("TABLE_NAME").toUpperCase());
		}
	}

	public static boolean init() throws SQLException, ClassNotFoundException {
		// Initialize database by creating tables if they don't exist
		Database db = new Database();
		db.connect();
		Statement stmt = db.con.createStatement();

		String sql = "";

		DatabaseMetaData md = db.con.getMetaData();
		Set<String> names = new HashSet<>();
		readDBTable(names, md, "TABLE", "DAIRY");

		// Users table
		if (!names.contains("USERS")) {
			sql = "CREATE TABLE USERS "
					+ "(id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), "
					+ "username VARCHAR(30), "
					+ "password VARCHAR(30) NOT NULL, "
					+ "first_name VARCHAR(30) NOT NULL, "
					+ "last_name VARCHAR(30) NOT NULL, "
					+ "initials VARCHAR(4) NOT NULL, "
					+ "position_id INT NOT NULL)";
			stmt.executeUpdate(sql);
		}
		// Positions table
		if (!names.contains("POSITIONS")) {
			sql = "CREATE TABLE POSITIONS "
					+ "(id INT PRIMARY KEY NOT NULL, "
					+ "position VARCHAR(30) NOT NULL)";
			stmt.executeUpdate(sql);

			// Initialize POSITIONS table
			sql = "INSERT INTO POSITIONS (id, position) VALUES (1, 'Manager')";
			stmt.executeUpdate(sql);
			sql = "INSERT INTO POSITIONS (id, position) VALUES (2, 'Employee')";
			stmt.executeUpdate(sql);
		}

		if (!names.contains("PERMISSIONS")) {
			sql = "CREATE TABLE PERMISSIONS "
					+ "(id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), "
					+ "user_id INT NOT NULL, "
					+ "view_change BOOLEAN NOT NULL, "
					+ "reports BOOLEAN NOT NULL, "
					+ "orders BOOLEAN NOT NULL, "
					+ "food_expense BOOLEAN NOT NULL, "
					+ "utilities BOOLEAN NOT NULL)";
			stmt.executeUpdate(sql);
		}

		if (!names.contains("PREFERENCES")) {
			sql = "CREATE TABLE PREFERENCES "
					+ "(id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), "
					+ "main_frame_width INT NOT NULL, "
					+ "main_frame_height INT NOT NULL, "
					+ "import_month INT NOT NULL, "
					+ "import_year INT NOT NULL, "
					+ "import_directory VARCHAR(128) NOT NULL)";
			stmt.executeUpdate(sql);
		}

		if (!names.contains("SHIFT_DATAS")) {
			sql = "CREATE TABLE SHIFT_DATAS "
					+ "(id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), "
					+ "shift INT NOT NULL, "
					+ "shift_date DATE NOT NULL, "
					+ "user_id INT NOT NULL, "
					+ "food REAL NOT NULL DEFAULT 0.0, "
					+ "rest_supp REAL NOT NULL DEFAULT 0.0, "
					+ "off_supp REAL NOT NULL DEFAULT 0.0, "
					+ "rep_maint REAL NOT NULL DEFAULT 0.0, "
					+ "freight REAL NOT NULL DEFAULT 0.0, "
					+ "cred_cards REAL NOT NULL DEFAULT 0.0, "
					+ "store_cash REAL NOT NULL DEFAULT 0.0, "
					+ "z_dept_tl REAL NOT NULL DEFAULT 0.0, "
					+ "overrings REAL NOT NULL DEFAULT 0.0, "
					+ "beg_cash REAL NOT NULL DEFAULT 0.0, "
					+ "z_tx REAL NOT NULL DEFAULT 0.0, "
					+ "z_coupon REAL NOT NULL DEFAULT 0.0, "
					+ "school_charges REAL NOT NULL DEFAULT 0.0, "
					+ "tax_exempt_sales REAL NOT NULL DEFAULT 0.0, "
					+ "donations REAL NOT NULL DEFAULT 0.0, "
					+ "gift_certs REAL NOT NULL DEFAULT 0.0, "
					+ "ecards REAL NOT NULL DEFAULT 0.0, "
					+ "discounts REAL NOT NULL DEFAULT 0.0, "
					+ "mgr_on_duty VARCHAR(4) NOT NULL DEFAULT '')";
			stmt.executeUpdate(sql);
		}

		sql = "CREATE INDEX SHIFTDATESHIFT "
				+ "ON SHIFT_DATAS (SHIFT_DATE, SHIFT)";
		stmt.executeUpdate(sql);

		if (!names.contains("OTHER_PAID_OUTS")) {
			sql = "CREATE TABLE OTHER_PAID_OUTS "
					+ "(id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), "
					+ "shift_data_id INT NOT NULL, "
					+ "label VARCHAR(128) NOT NULL DEFAULT '', "
					+ "cost REAL NOT NULL DEFAULT 0.0)";
			stmt.executeUpdate(sql);
		}

		if (!names.contains("EMPLOYEES")) {
			sql = "CREATE TABLE EMPLOYEES "
					+ "(id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), "
					+ "emp_id VARCHAR(16) NOT NULL, "
					+ "first_name VARCHAR(30) NOT NULL, "
					+ "last_name VARCHAR(30) NOT NULL)";
			stmt.executeUpdate(sql);
		}

		if (!names.contains("REGISTER_AUDITS")) {
			sql = "CREATE TABLE REGISTER_AUDITS "
					+ "(id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), "
					+ "audit_date DATE NOT NULL, "
					+ "audit_time TIME NOT NULL, "
					+ "shift INT NOT NULL, "
					+ "tape_read REAL NOT NULL, "
					+ "cash_count REAL NOT NULL, "
					+ "audit INT NOT NULL, "
					+ "register VARCHAR(5) NOT NULL, "
					+ "manager_id VARCHAR(4) NOT NULL)";
			stmt.executeUpdate(sql);
		}

		if (!names.contains("REGISTER_AUDIT_EMPLOYEES")) {
			sql = "CREATE TABLE REGISTER_AUDIT_EMPLOYEES "
					+ "(id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), "
					+ "audit_id INT NOT NULL, "
					+ "employee_id INT NOT NULL)";
			stmt.executeUpdate(sql);
		}

		if (!names.contains("VENDORS")) {
			sql = "CREATE TABLE VENDORS "
					+ "(id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), "
					+ "name VARCHAR(256) NOT NULL)";
			stmt.executeUpdate(sql);
		}

		if (!names.contains("VENDOR_INVOICES")) {
			sql = "CREATE TABLE VENDOR_INVOICES "
					+ "(id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), "
					+ "invoice_date DATE NOT NULL, "
					+ "manager_id INT NOT NULL, "
					+ "vendor_id INT NOT NULL, "
					+ "invoice_num VARCHAR(64) NOT NULL, "
					+ "amount REAL NOT NULL, "
					+ "include_in_food BOOLEAN NOT NULL DEFAULT TRUE)";
			stmt.executeUpdate(sql);
		}
		db.con.commit();

		stmt.close();
		db.con.close();

		return true;
	}

	public ResultSet getResultSet(String sql) throws SQLException {
		ResultSet rs = null;
		Statement stmt = null;

		stmt = this.con.createStatement();
		rs = stmt.executeQuery(sql);

		this.con.commit();

		stmt.close();
		this.con.close();

		return rs;
	}

	public static ResultSet load(String sql) throws SQLException, ClassNotFoundException {
		// Returns a result tableNames using an SQL string
		ResultSet rs = null;
		Database db = new Database();
		db.connect();
		Statement stmt = db.con.createStatement();

		try {
			rs = stmt.executeQuery(sql);
		} catch (Exception ex) {
			Logger.getLogger(ShiftData.class.getName()).log(Level.SEVERE, null, ex);
		}

		db.disconnect();

		return rs;
	}

	public static ResultSet loadFromPrepared(String sql) throws SQLException, ClassNotFoundException {
		Database db = new Database();
		db.connect();
		PreparedStatement pStmt;
		pStmt = db.con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet rs = pStmt.executeQuery();
		return rs;
	}

	public static int insert(String sql) throws SQLException, ClassNotFoundException {
		Database db = new Database();
		db.connect();

		Statement stmt = null;

		stmt = db.con.createStatement();
		int result = stmt.executeUpdate(sql);
		db.con.commit();

		stmt.close();

		db.disconnect();
		return result;
	}

	public static int insert(PreparedStatement stmt) throws SQLException {
		// Overridden insert method that receives a Statement created by an already open connection
		int result = stmt.executeUpdate();

		return result;
	}

	public static int update(String sql) throws SQLException, ClassNotFoundException {
		// Simply implements the Database.insert() method
		int result = Database.insert(sql);

		return result;
	}

	public static void execute(String sql) throws SQLException, ClassNotFoundException {
		Database db = new Database();
		db.connect();
		Statement stmt = db.con.createStatement();

		stmt.executeUpdate(sql);
	}

	public static int getRowCount(String table) throws SQLException, ClassNotFoundException {
		Database db = new Database();
		db.connect();

		String sql = "SELECT COUNT(*) AS C FROM " + table;
		Statement stmt = db.con.createStatement();

		int c = 0;
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			c = rs.getInt("C");
		}
		db.con.commit();

		stmt.close();

		db.disconnect();
		return c;
	}
}
