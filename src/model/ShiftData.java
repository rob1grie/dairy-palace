/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rob
 */
public class ShiftData {

	private int id;
	private int shift;
	private String date;
	private int userId;
	private float food;
	private float restSupp;
	private float offSupp;
	private float repMaint;
	private float freight;
	private float credCards;
	private float storeCash;
	private float zDeptTl;
	private float overrings;
	private float begCash;
	private float zTx;
	private float zCoupon;
	private float schoolCharges;
	private float taxExemptSales;
	private float donations;
	private float giftCerts;
	private float ecards;
	private float discounts;
	private String mgrOnDuty;

	private Database db;

	public ShiftData() {

	}

	public ShiftData(int id) throws SQLException, ClassNotFoundException {
		// Constructor using just the id
		this.id = id;

		this.getShiftData();
	}

	public ShiftData(String shift, String date, int userId, String food, String restSupp, String offSupp,
			String repMaint, String freight, String credCards, String storeCash, String zDeptTl,
			String overrings, String begCash, String zTx, String zCoupon, String schoolCharges,
			String taxExemptSales, String donations, String giftCerts, String ecards, String discounts, String mgrOnDuty) throws ParseException {
		// Constructor being passed all values for the object
		this.shift = Integer.parseInt(shift);
		this.date = date;
		this.userId = userId;
		this.food = Float.parseFloat(food);
		this.restSupp = Float.parseFloat(restSupp);
		this.offSupp = Float.parseFloat(offSupp);
		this.repMaint = Float.parseFloat(repMaint);
		this.freight = Float.parseFloat(freight);
		this.credCards = Float.parseFloat(credCards);
		this.storeCash = Float.parseFloat(storeCash);
		this.zDeptTl = Float.parseFloat(zDeptTl);
		this.overrings = Float.parseFloat(overrings);
		this.begCash = Float.parseFloat(begCash);
		this.zTx = Float.parseFloat(zTx);
		this.zCoupon = Float.parseFloat(zCoupon);
		this.schoolCharges = Float.parseFloat(schoolCharges);
		this.taxExemptSales = Float.parseFloat(taxExemptSales);
		this.donations = Float.parseFloat(donations);
		this.giftCerts = Float.parseFloat(giftCerts);
		this.ecards = Float.parseFloat(ecards);
		this.discounts = Float.parseFloat(discounts);
		this.mgrOnDuty = mgrOnDuty;

	}

	public ShiftData(ResultSet rs) throws SQLException {
		// Constructor from a ResultSet presumably only containing one record
		// Calling method must have moved the row pointer in the ResultSet
		this.getShiftDataFromResultSet(rs);
	}

	public void getShiftDataFromResultSet(ResultSet rs) throws SQLException {
		// Loads fields from the current row of rs, so do NOT move the row pointer!
		this.shift = rs.getInt("shift");
		this.date = rs.getString("date");
		this.userId = rs.getInt("user_id");
		this.food = rs.getFloat("food");
		this.restSupp = rs.getFloat("rest_supp");
		this.offSupp = rs.getFloat("off_supp");
		this.repMaint = rs.getFloat("rep_maint");
		this.freight = rs.getFloat("freight");
		this.credCards = rs.getFloat("cred_cards");
		this.storeCash = rs.getFloat("store_cash");
		this.zDeptTl = rs.getFloat("z_dept_tl");
		this.overrings = rs.getFloat("overrings");
		this.begCash = rs.getFloat("beg_cash");
		this.zTx = rs.getFloat("z_tx");
		this.zCoupon = rs.getFloat("z_coupon");
		this.schoolCharges = rs.getFloat("school_charges");
		this.taxExemptSales = rs.getFloat("tax_exempt_sales");
		this.donations = rs.getFloat("donations");
		this.giftCerts = rs.getFloat("gift_certs");
		this.ecards = rs.getFloat("ecards");
		this.discounts = rs.getFloat("discounts");
		this.mgrOnDuty = rs.getString("mgr_on_duty");
	}

	private void getShiftData() throws SQLException, ClassNotFoundException {
		// Gets the ShiftData record with this ID
		String sql = "SELECT * FROM SHIFT_DATAS WHERE ID = " + this.id;

		db = new Database();
		db.connect();
		Statement stmt = this.db.con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);

		if (rs.next()) {
			this.getShiftDataFromResultSet(rs);
		}
		rs.close();

		db.disconnect();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getShift() {
		return shift;
	}

	public void setShift(int shift) {
		this.shift = shift;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public float getFood() {
		return food;
	}

	public void setFood(float food) {
		this.food = food;
	}

	public float getRestSupp() {
		return restSupp;
	}

	public void setRestSupp(float restSupp) {
		this.restSupp = restSupp;
	}

	public float getOffSupp() {
		return offSupp;
	}

	public void setOffSupp(float offSupp) {
		this.offSupp = offSupp;
	}

	public float getRepMaint() {
		return repMaint;
	}

	public void setRepMaint(float repMaint) {
		this.repMaint = repMaint;
	}

	public float getFreight() {
		return freight;
	}

	public void setFreight(float freight) {
		this.freight = freight;
	}

	public float getCredCards() {
		return credCards;
	}

	public void setCredCards(float credCards) {
		this.credCards = credCards;
	}

	public float getStoreCash() {
		return storeCash;
	}

	public void setStoreCash(float storeCash) {
		this.storeCash = storeCash;
	}

	public float getzDeptTl() {
		return zDeptTl;
	}

	public void setzDeptTl(float zDeptTl) {
		this.zDeptTl = zDeptTl;
	}

	public float getOverrings() {
		return overrings;
	}

	public void setOverrings(float overrings) {
		this.overrings = overrings;
	}

	public float getBegCash() {
		return begCash;
	}

	public void setBegCash(float begCash) {
		this.begCash = begCash;
	}

	public float getzTx() {
		return zTx;
	}

	public void setzTx(float zTx) {
		this.zTx = zTx;
	}

	public float getzCoupon() {
		return zCoupon;
	}

	public void setzCoupon(float zCoupon) {
		this.zCoupon = zCoupon;
	}

	public float getSchoolCharges() {
		return schoolCharges;
	}

	public void setSchoolCharges(float schoolCharges) {
		this.schoolCharges = schoolCharges;
	}

	public float getTaxExemptSales() {
		return taxExemptSales;
	}

	public void setTaxExemptSales(float taxExemptSales) {
		this.taxExemptSales = taxExemptSales;
	}

	public float getDonations() {
		return donations;
	}

	public void setDonations(float donations) {
		this.donations = donations;
	}

	public float getGiftCerts() {
		return giftCerts;
	}

	public void setGiftCerts(float giftCerts) {
		this.giftCerts = giftCerts;
	}

	public float getEcards() {
		return ecards;
	}

	public void setEcards(float ecards) {
		this.ecards = ecards;
	}

	public float getDiscounts() {
		return discounts;
	}

	public void setDiscounts(float discounts) {
		this.discounts = discounts;
	}

	public String getMgrOnDuty() {
		return mgrOnDuty;
	}

	public void setMgrOnDuty(String mgrOnDuty) {
		this.mgrOnDuty = mgrOnDuty;
	}

	public static void importData(ResultSet rs) throws SQLException, ClassNotFoundException {
		/*
		 Import process:
		 ***** ResultSet is from the DBF database *****
		 1 - Create ShiftData object from the ResultSet rs
		 2 - Query USERS with SHIFT_DATAS.ENTERED_BY = USERS.USERNAME and get user_id
		 3 - Test each OTHERn_CST to insert into OTHER_PAID_OUTS
		 */

		while (rs.next()) {
			// ShiftData constructor takes a ResultSet with the row pointer at the desired location
			ShiftData shiftData = ShiftData.getShiftDataFromDbfResultSet(rs);

			// Get userId
			User user = User.getUserFromInitials(rs.getString("entered_by"));
			shiftData.userId = user.getId();

			// Test each OTHERn_CST before inserting into OTHER_PAID_OUTS
			if (rs.getFloat("OTHER1_CST") > 0.0) {
				/// OtherPO constructor expects strings for all parameters
				OtherPO otherPO = new OtherPO(
						shiftData.id,
						rs.getString("OTHER1_LAB"),
						Float.parseFloat(rs.getString("OTHER1_CST")));
				otherPO.insert();
			}
			if (rs.getFloat("OTHER2_CST") > 0.0) {
				/// OtherPO constructor expects strings for all parameters
				OtherPO otherPO = new OtherPO(
						shiftData.id,
						rs.getString("OTHER2_LAB"),
						Float.parseFloat(rs.getString("OTHER2_CST")));
				otherPO.insert();
			}
			if (rs.getFloat("OTHER3_CST") > 0.0) {
				/// OtherPO constructor expects strings for all parameters
				OtherPO otherPO = new OtherPO(
						shiftData.id,
						rs.getString("OTHER3_LAB"),
						Float.parseFloat(rs.getString("OTHER3_CST")));
				otherPO.insert();
			}
			if (rs.getFloat("OTHER4_CST") > 0.0) {
				/// OtherPO constructor expects strings for all parameters
				OtherPO otherPO = new OtherPO(
						shiftData.id,
						rs.getString("OTHER4_LAB"),
						Float.parseFloat(rs.getString("OTHER4_CST")));
				otherPO.insert();
			}

			shiftData.insert();
		}
	}

	public static ShiftData getShiftDataFromDbfResultSet(ResultSet rs) throws SQLException, ClassNotFoundException {
		// Loads fields from the current row of rs, so do NOT move the row pointer!
		ShiftData data = new ShiftData();

		data.shift = rs.getInt("shift");
		data.date = rs.getString("this_date");

		String userName = rs.getString("entered_by");
		User user = User.getUserFromInitials(userName);
		data.userId = user.getId();

		data.food = rs.getFloat("food");
		data.restSupp = rs.getFloat("rest_supp");
		data.offSupp = rs.getFloat("off_supp");
		data.repMaint = rs.getFloat("rep_maint");
		data.freight = rs.getFloat("freight");
		data.credCards = rs.getFloat("cred_crds");
		data.storeCash = rs.getFloat("store_cash");
		data.zDeptTl = rs.getFloat("z_dept_tl");
		data.overrings = rs.getFloat("overrings");
		data.begCash = rs.getFloat("beg_cash");
		data.zTx = rs.getFloat("z_tx");
		data.zCoupon = rs.getFloat("z_coupon");
		data.mgrOnDuty = "None";

		return data;
	}

	public boolean insert() throws SQLException, ClassNotFoundException {
		// Insert into Shift_Datas table
		if (this.db == null) {
			this.db = new Database();
		}
		db.connect();

		// TODO Change to using Prepared Statement
		Connection c = null;
		PreparedStatement stmt = null;

		Class.forName("org.sqlite.JDBC");
		c = DriverManager.getConnection("jdbc:sqlite:dairy.db");
		c.setAutoCommit(false);

		stmt = c.prepareStatement("INSERT INTO SHIFT_DATAS (shift, date, user_id, food, rest_supp, off_supp, rep_maint, freight, cred_cards, "
				+ "store_cash, z_dept_tl, overrings, beg_cash, z_tx, z_coupon, school_charges, tax_exempt_sales, donations, "
				+ "gift_certs, ecards, discounts, mgr_on_duty) "
				+ "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?); ");

		stmt.setInt(1, this.shift);
		stmt.setString(2, this.date);
		stmt.setInt(3, this.userId);
		stmt.setFloat(4, this.food);
		stmt.setFloat(5, this.restSupp);
		stmt.setFloat(6, this.offSupp);
		stmt.setFloat(7, this.repMaint);
		stmt.setFloat(8, this.freight);
		stmt.setFloat(9, this.credCards);
		stmt.setFloat(10, this.storeCash);
		stmt.setFloat(11, this.zDeptTl);
		stmt.setFloat(12, this.overrings);
		stmt.setFloat(13, this.begCash);
		stmt.setFloat(14, this.zTx);
		stmt.setFloat(15, this.zCoupon);
		stmt.setFloat(16, this.schoolCharges);
		stmt.setFloat(17, this.taxExemptSales);
		stmt.setFloat(18, this.donations);
		stmt.setFloat(19, this.giftCerts);
		stmt.setFloat(20, this.ecards);
		stmt.setFloat(21, this.discounts);
		stmt.setString(22, this.mgrOnDuty);

		stmt.executeUpdate();

		// TODO Import OtherPO for this record
		stmt.close();
		c.commit();
		c.close();

		db.disconnect();

		return true;
	}

	public static ResultSet load(String sql) throws SQLException, ClassNotFoundException {
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

}
