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

/**
 *
 * @author Rob
 */
public class Vendor {

	private int id;
	private String name;

	private Database db;

	public Vendor() {

	}

	public Vendor(int id) {
		this.id = id;

		if (this.db == null) {
			this.db = new Database();
		}
	}

	public Vendor(String name) {
		this.name = name;
		if (this.db == null) {
			this.db = new Database();
		}
	}

	public Vendor(ResultSet rs) throws SQLException {
		this.id = Integer.parseInt(rs.getString("id"));
		this.name = rs.getString("name");
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static boolean importData(ResultSet rs) throws SQLException, ClassNotFoundException, ParseException {
//		 ***** ResultSet is from the DBF database *****

		// rs is from a DBF record
		boolean result = true;

		Database db = new Database();
		db.connect();
		PreparedStatement pStmt = null;
		String sql = "";

		while (rs.next() && result) {
			Vendor vendor = Vendor.getVendorFromDbfResultSet(rs);

			sql = "INSERT INTO VENDORS (name) VALUES ( ?) ";

			pStmt = db.con.prepareStatement(sql, new String[]{"ID"});

			pStmt.setString(1, vendor.name);

			result = Database.insert(pStmt) == 1;
		}

		pStmt.close();
		db.con.commit();
		db.disconnect();

		return result;
	}

	public static Vendor getVendorFromDbfResultSet(ResultSet rs) throws SQLException, ClassNotFoundException, ParseException {
		// Loads fields from the current row of rs, so do NOT move the row pointer!
		Vendor data = new Vendor();

		data.name = rs.getString("vendor");

		return data;
	}
	
	public static Vendor getVendorFromName(String name) throws SQLException, ClassNotFoundException {
		String sql = "SELECT * FROM VENDORS WHERE name = '" + name + "'";

		Vendor vendor = Vendor.getVendorFromSql(sql);

		return vendor;
		
	}

	public static Vendor getVendorFromSql(String sql) throws SQLException, ClassNotFoundException {
		Vendor vendor = null;

		Database db = new Database();
		db.connect();

		Statement stmt = db.con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);

		if (rs.next()) {
			vendor = new Vendor(rs);
		}

		db.disconnect();

		return vendor;
	}

}
