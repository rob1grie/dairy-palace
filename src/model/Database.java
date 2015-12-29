/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.*;

/**
 *
 * @author Rob
 */

/*
 TODO: Improve form of using JDBC:
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
		if (con != null) {
			return;
		}

		con = DriverManager.getConnection("jdbc:derby:dairydb;create=true");
		con.setAutoCommit(true);
	}

	public void disconnect() {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException ex) {
				System.out.println("Can't close connection");
			}
		}
	}

	public int getLastRowId() {
		return lastRowId;
	}

	public static boolean init() throws SQLException, ClassNotFoundException {
		// Initialize database by creating tables if they don't exist
		Database db = new Database();
		db.connect();
		Statement stmt = null;

		stmt = db.con.createStatement();
		// Users table
		String sql = "CREATE TABLE USERS "
				+ "(id INT PRIMARY KEY, "
				+ "username VARCHAR(30), "
				+ "password VARCHAR(30) NOT NULL, "
				+ "first_name VARCHAR(30) NOT NULL, "
				+ "last_name VARCHAR(30) NOT NULL, "
				+ "initials VARCHAR(4) NOT NULL, "
				+ "position_id INT NOT NULL)";
		stmt.executeUpdate(sql);

//		// Positions table
//		sql = "CREATE TABLE IF NOT EXISTS POSITIONS "
//				+ "(id INTEGER PRIMARY KEY, "
//				+ "position TEXT NOT NULL);";
//		stmt.executeUpdate(sql);
//
//		sql = "CREATE TABLE IF NOT EXISTS PERMISSIONS "
//				+ "(id INTEGER PRIMARY KEY, "
//				+ "user_id INT NOT NULL, "
//				+ "view_change BOOL NOT NULL, "
//				+ "reports BOOL NOT NULL, "
//				+ "orders BOOL NOT NULL, "
//				+ "food_expense BOOL NOT NULL, "
//				+ "utilities BOOL NOT NULL);";
//		stmt.executeUpdate(sql);
//
//		sql = "CREATE TABLE IF NOT EXISTS PREFERENCES "
//				+ "(id INTEGER PRIMARY KEY, "
//				+ "mainFrameWidth INT NOT NULL, "
//				+ "mainFrameHeight INT NOT NULL, "
//				+ "selectedView TEXT NOT NULL);";
//		stmt.executeUpdate(sql);
//
//		sql = "CREATE TABLE IF NOT EXISTS SHIFT_DATAS "
//				+ "(id INTEGER PRIMARY KEY, "
//				+ "shift INT NOT NULL, "
//				+ "date TEXT NOT NULL, "
//				+ "user_id INT NOT NULL, "
//				+ "food REAL NOT NULL DEFAULT 0.0, "
//				+ "rest_supp REAL NOT NULL DEFAULT 0.0, "
//				+ "off_supp REAL NOT NULL DEFAULT 0.0, "
//				+ "rep_maint REAL NOT NULL DEFAULT 0.0, "
//				+ "freight REAL NOT NULL DEFAULT 0.0, "
//				+ "cred_cards REAL NOT NULL DEFAULT 0.0, "
//				+ "store_cash REAL NOT NULL DEFAULT 0.0, "
//				+ "z_dept_tl REAL NOT NULL DEFAULT 0.0, "
//				+ "overrings REAL NOT NULL DEFAULT 0.0, "
//				+ "beg_cash REAL NOT NULL DEFAULT 0.0, "
//				+ "z_tx REAL NOT NULL DEFAULT 0.0, "
//				+ "z_coupon REAL NOT NULL DEFAULT 0.0, "
//				+ "school_charges REAL NOT NULL DEFAULT 0.0, "
//				+ "tax_exempt_sales REAL NOT NULL DEFAULT 0.0, "
//				+ "donations REAL NOT NULL DEFAULT 0.0, "
//				+ "gift_certs REAL NOT NULL DEFAULT 0.0, "
//				+ "ecards REAL NOT NULL DEFAULT 0.0, "
//				+ "discounts REAL NOT NULL DEFAULT 0.0, "
//				+ "mgr_on_duty TEXT NOT NULL DEFAULT '');";
//		stmt.executeUpdate(sql);
//
//		sql = "CREATE TABLE IF NOT EXISTS OTHER_PAID_OUTS "
//				+ "(id INTEGER PRIMARY KEY, "
//				+ "shift_data_id INT NOT NULL, "
//				+ "label TEXT NOT NULL DEFAULT '', "
//				+ "cost REAL NOT NULL DEFAULT 0.0);";
//		stmt.executeUpdate(sql);
//
//		sql = "CREATE TABLE IF NOT EXISTS EMPLOYEES "
//				+ "(id INTEGER PRIMARY KEY, "
//				+ "emp_id INT NOT NULL, "
//				+ "first_name TEXT NOT NULL, "
//				+ "last_name TEXT NOT NULL);";
//		stmt.executeUpdate(sql);
//
//		sql = "CREATE TABLE IF NOT EXISTS REGISTER_AUDITS "
//				+ "(id INTEGER PRIMARY KEY, "
//				+ "date_time TEXT NOT NULL, "
//				+ "shift INT NOT NULL, "
//				+ "tape_read REAL NOT NULL, "
//				+ "cash_count REAL NOT NULL, "
//				+ "audit INT NOT NULL, "
//				+ "register TEXT NOT NULL, "
//				+ "manager_id TEXT NOT NULL);";
//		stmt.executeUpdate(sql);
//
//		sql = "CREATE TABLE IF NOT EXISTS REGISTER_AUDIT_EMPLOYEE "
//				+ "(id INTEGER PRIMARY KEY, "
//				+ "audit_id INT NOT NULL, "
//				+ "employee_id INT NOT NULL);";
//		stmt.executeUpdate(sql);
//
//		sql = "CREATE TABLE IF NOT EXISTS VENDORS "
//				+ "(id INTEGER PRIMARY KEY, "
//				+ "name TEXT NOT NULL);";
//		stmt.executeUpdate(sql);
//
//		sql = "CREATE TABLE IF NOT EXISTS VENDOR_INVOICES "
//				+ "(id INTEGER PRIMARY KEY, "
//				+ "date TEXT NOT NULL, "
//				+ "manager_id INT NOT NULL, "
//				+ "vendor_id INT NOT NULL, "
//				+ "invoice_num TEXT NOT NULL, "
//				+ "amount REAL NOT NULL, "
//				+ "include_in_food BOOL NOT NULL DEFAULT TRUE);";
//		stmt.executeUpdate(sql);

		stmt.close();
		db.con.close();

		return true;
	}

	public ResultSet getResultSet(String sql) throws SQLException {
		ResultSet rs = null;
		Statement stmt = null;

		stmt = con.createStatement();
		rs = stmt.executeQuery(sql);

		stmt.close();
		con.close();

		return rs;
	}

}
