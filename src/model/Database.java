/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

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

	public Database() {
		
	}

	public void connect() throws SQLException, ClassNotFoundException {
		if (con != null) {
			return;
		}

			Class.forName("org.sqlite.JDBC");
//			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			con = DriverManager.getConnection("jdbc:sqlite:dairy.db");
//			con = DriverManager.getConnection("jdbc:derby:dairydb;create=true");
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

	public static boolean init() {
		// Initialize database by creating tables if they don't exist
		Database db = new Database();
		Statement stmt = null;

		try {
			db.connect();

			stmt = db.con.createStatement();
			// Users table
			String sql = "CREATE TABLE IF NOT EXISTS USERS "
					+ "(username TEXT NOT NULL, "
					+ "password TEXT NOT NULL, "
					+ "first_name TEXT NOT NULL, "
					+ "last_name TEXT NOT NULL, "
					+ "position_id INTEGER NOT NULL)";
			stmt.executeUpdate(sql);

			// Positions table
			sql = "CREATE TABLE IF NOT EXISTS POSITIONS "
					+ "(id INTEGER NOT NULL, "
					+ "position TEXT NOT NULL);";
			stmt.executeUpdate(sql);

			sql = "CREATE TABLE IF NOT EXISTS PERMISSIONS "
					+ "(id INTEGER NOT NULL, "
					+ "user_id INTEGER NOT NULL, "
					+ "view_change BOOL NOT NULL, "
					+ "reports BOOL NOT NULL, "
					+ "orders BOOL NOT NULL, "
					+ "food_expense BOOL NOT NULL, "
					+ "utilities BOOL NOT NULL);";
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE IF NOT EXISTS PREFERENCES "
					+ "(id INTEGER NOT NULL, "
					+ "mainFrameWidth INTEGER NOT NULL, "
					+ "mainFrameHeight INTEGER NOT NULL, "
					+ "selectedView TEXT NOT NULL);";
			stmt.executeUpdate(sql);

			sql = "CREATE TABLE IF NOT EXISTS SHIFT_DATAS "
					+ "(id INTEGER NOT NULL, "
					+ "shift INTEGER NOT NULL, "
					+ "date TEXT NOT NULL, "
					+ "user_id INTEGER NOT NULL, "
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
					+ "mgr_on_duty TEXT NOT NULL DEFAULT '');";
			stmt.executeUpdate(sql);

			sql = "CREATE TABLE IF NOT EXISTS OTHER_PAID_OUTS "
					+ "(id INTEGER NOT NULL, "
					+ "shifts_data_id INTEGER NOT NULL, "
					+ "label TEXT NOT NULL DEFAULT '', "
					+ "cost REAL NOT NULL DEFAULT 0.0);";
			stmt.executeUpdate(sql);

			sql = "CREATE TABLE IF NOT EXISTS EMPLOYEES "
					+ "(id INTEGER NOT NULL, "
					+ "first_name TEXT NOT NULL, "
					+ "last_name TEXT NOT NULL);";
			stmt.executeUpdate(sql);

			sql = "CREATE TABLE IF NOT EXISTS REGISTER_AUDITS "
					+ "(id INTEGER NOT NULL, "
					+ "date_time TEXT NOT NULL, "
					+ "shift INTEGER NOT NULL, "
					+ "tape_read REAL NOT NULL, "
					+ "cash_count REAL NOT NULL, "
					+ "audit INTEGER NOT NULL, "
					+ "register TEXT NOT NULL, "
					+ "manager_id TEXT NOT NULL);";
			stmt.executeUpdate(sql);

			sql = "CREATE TABLE IF NOT EXISTS REGISTER_AUDIT_EMPLOYEE "
					+ "(id INTEGER NOT NULL, "
					+ "audit_id INTEGER NOT NULL, "
					+ "employee_id INTEGER NOT NULL);";
			stmt.executeUpdate(sql);

			sql = "CREATE TABLE IF NOT EXISTS VENDORS "
					+ "(id INTEGER  NOT NULL, "
					+ "name TEXT NOT NULL);";
			stmt.executeUpdate(sql);

			sql = "CREATE TABLE IF NOT EXISTS VENDOR_INVOICES "
					+ "(id INTEGER NOT NULL, "
					+ "date TEXT NOT NULL, "
					+ "manager_id INTEGER NOT NULL, "
					+ "vendor_id INTEGER NOT NULL, "
					+ "invoice_num TEXT NOT NULL, "
					+ "amount REAL NOT NULL, "
					+ "include_in_food BOOL NOT NULL DEFAULT TRUE);";
			stmt.executeUpdate(sql);

			stmt.close();
			db.con.close();
		} catch (SQLException ex) {
			Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		} catch (Exception ex) {
			Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
		}

		return true;
	}
	
	public ResultSet getResultSet(String sql) {
		ResultSet rs = null;
		Statement stmt = null;
		
		try {
			connect();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			stmt.close();
			con.close();
		} catch (Exception ex) {
			Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return rs;
	}

	public void importData() throws Exception {
		DbfConnection dbf = new DbfConnection();
	}

	public static int getLastRowId(Statement stmt, String table) throws SQLException {
		// Get last inserted row id
		String sql = "SELECT last_insert_rowid() FROM " + table;
		ResultSet rs = stmt.executeQuery(sql);
		int rowId;

		rs.next();
		rowId = rs.getInt(1);
		
		return rowId;
	}
}
