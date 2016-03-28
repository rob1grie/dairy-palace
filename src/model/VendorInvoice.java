/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import utils.Utils;

/**
 *
 * @author Rob
 */
public class VendorInvoice {

	private int id;
	private LocalDate invoiceDate;
	private int managerId;
	private int vendorId;
	private String invoiceNum;
	private float amount;
	private boolean includeInFood;

	public VendorInvoice() {

	}

	public VendorInvoice(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return invoiceDate;
	}

	public void setDate(LocalDate date) {
		this.invoiceDate = date;
	}

	public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}

	public int getVendorId() {
		return vendorId;
	}

	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
	}

	public String getInvoiceNum() {
		return invoiceNum;
	}

	public void setInvoiceNum(String invoiceNum) {
		this.invoiceNum = invoiceNum;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public boolean isIncludeInFood() {
		return includeInFood;
	}

	public void setIncludeInFood(boolean includeInFood) {
		this.includeInFood = includeInFood;
	}

	public static boolean importData(ResultSet rs) throws SQLException, ClassNotFoundException, ParseException {
//		 ***** ResultSet is from the DBF database *****
		boolean result = true;

		Database db = new Database();
		db.connect();
		PreparedStatement pStmt = null;
		String sql = "";

		while (rs.next() && result) {
			VendorInvoice invoice = VendorInvoice.getVendorInvoiceFromDbfResultSet(rs);

			sql = "INSERT INTO VENDOR_INVOICES (invoice_date, manager_id, vendor_id, invoice_num, amount, include_in_food) "
					+ "VALUES ( ?, ?, ?, ?, ?, ?) ";

			pStmt = db.con.prepareStatement(sql, new String[]{"ID"});

			pStmt.setString(1, invoice.invoiceDate.toString());
			pStmt.setInt(2, invoice.managerId);
			pStmt.setInt(3, invoice.vendorId);
			pStmt.setString(4, invoice.invoiceNum);
			pStmt.setFloat(5, invoice.amount);
			pStmt.setBoolean(6, invoice.includeInFood);

			result = Database.insert(pStmt) == 1;
		}

		pStmt.close();
		db.con.commit();
		db.disconnect();

		return result;
	}

	public static VendorInvoice getVendorInvoiceFromDbfResultSet(ResultSet rs) throws SQLException, ClassNotFoundException, ParseException {
		// Loads fields from the current row of rs, so do NOT move the row pointer!
		VendorInvoice data = new VendorInvoice();

		data.invoiceDate = Utils.getDateFromString(rs.getString("this_date"), "yyyy-M-d");
		String test = data.invoiceDate.toString();

		// Some imported records have an empty MGR field
		User user = User.getUserFromInitials(rs.getString("mgr"));

		if (user != null) {
			data.managerId = user.getId();
		} else {
			data.managerId = -1;
		}

		data.vendorId = Vendor.getVendorFromName(rs.getString("vendor")).getId();
		data.invoiceNum = rs.getString("invoicenum");
		data.amount = rs.getFloat("amount");
		data.includeInFood = rs.getInt("includefc") == 1;

		return data;
	}

}
