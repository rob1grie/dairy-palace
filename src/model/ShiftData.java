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
import java.util.ArrayList;
import utils.Utils;

/**
 *
 * @author Rob
 */
public class ShiftData {

	private int id;
	private int shift;
	private LocalDate shiftDate;
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

	public ShiftData() {

	}

	public ShiftData(int id) throws SQLException, ClassNotFoundException, ParseException {
		// Constructor using just the id
		this.id = id;

		this.getShiftData();
	}

	public ShiftData(String shift, LocalDate shiftDate, int userId, String food, String restSupp, String offSupp,
			String repMaint, String freight, String credCards, String storeCash, String zDeptTl,
			String overrings, String begCash, String zTx, String zCoupon, String schoolCharges,
			String taxExemptSales, String donations, String giftCerts, String ecards, String discounts, String mgrOnDuty) throws ParseException {
		// Constructor being passed all values for the object
		this.shift = Integer.parseInt(shift);
		this.shiftDate = shiftDate;
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

	public ShiftData(ResultSet rs) throws SQLException, ParseException {
		// Constructor from a ResultSet presumably only containing one record
		// Calling method must have moved the row pointer in the ResultSet
		this.getShiftDataFromResultSet(rs);
	}

	private void getShiftDataFromResultSet(ResultSet rs) throws SQLException, ParseException {
		// Loads fields from the current row of rs, so do NOT move the row pointer!
		this.shift = rs.getInt("shift");
		this.shiftDate = Utils.getDateFromString(rs.getDate("shift_date").toString());
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

	private void getShiftData() throws SQLException, ClassNotFoundException, ParseException {
		// Gets the ShiftData record with this ID
		String sql = "SELECT * FROM SHIFT_DATAS WHERE ID = " + this.id;

		ResultSet rs = Database.load(sql);

		if (rs.next()) {
			this.getShiftDataFromResultSet(rs);
		}
		rs.close();
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

	public LocalDate getDate() {
		return shiftDate;
	}

	public void setDate(LocalDate shiftDate) {
		this.shiftDate = shiftDate;
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

	public static boolean importData(ResultSet rs) throws SQLException, ClassNotFoundException, ParseException {
		/*
		 Import process:
		 ***** ResultSet is from the DBF database *****
		 1 - Create ShiftData object from the ResultSet rs
		 2 - Query USERS with SHIFT_DATAS.ENTERED_BY = USERS.USERNAME and get user_id
		 3 - Test each OTHERn_CST to insert into OTHER_PAID_OUTS
		 */

		// rs is from a DBF record
		boolean result = true;

		Database db = new Database();
		db.connect();
		PreparedStatement pStmt = null;
		String sql = "";

		while (rs.next() && result) {
			ShiftData shift = ShiftData.getShiftDataFromDbfResultSet(rs);

			sql = "INSERT INTO SHIFT_DATAS (shift, shift_date, user_id, food, rest_supp, off_supp, rep_maint, freight, cred_cards, "
					+ "store_cash, z_dept_tl, overrings, beg_cash, z_tx, z_coupon, school_charges, tax_exempt_sales, donations, "
					+ "gift_certs, ecards, discounts, mgr_on_duty) "
					+ "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";

			pStmt = db.con.prepareStatement(sql, new String[]{"ID"});

			pStmt.setInt(1, shift.shift);
			pStmt.setString(2, shift.shiftDate.toString());
			pStmt.setInt(3, shift.userId);
			pStmt.setFloat(4, shift.food);
			pStmt.setFloat(5, shift.restSupp);
			pStmt.setFloat(6, shift.offSupp);
			pStmt.setFloat(7, shift.repMaint);
			pStmt.setFloat(8, shift.freight);
			pStmt.setFloat(9, shift.credCards);
			pStmt.setFloat(10, shift.storeCash);
			pStmt.setFloat(11, shift.zDeptTl);
			pStmt.setFloat(12, shift.overrings);
			pStmt.setFloat(13, shift.begCash);
			pStmt.setFloat(14, shift.zTx);
			pStmt.setFloat(15, shift.zCoupon);
			pStmt.setFloat(16, shift.schoolCharges);
			pStmt.setFloat(17, shift.taxExemptSales);
			pStmt.setFloat(18, shift.donations);
			pStmt.setFloat(19, shift.giftCerts);
			pStmt.setFloat(20, shift.ecards);
			pStmt.setFloat(21, shift.discounts);
			pStmt.setString(22, shift.mgrOnDuty);

			result = Database.insert(pStmt) == 1;
			ResultSet gk = pStmt.getGeneratedKeys(); // will return the ID in id);

			while (gk.next()) {
				shift.id = gk.getInt("1");
			}
			
			if (result) {
				ArrayList<OtherPO> otherPOs = OtherPO.importData(rs, shift.id);

				if (otherPOs.size() > 0) {
					try (Statement stmt = db.con.createStatement()) {
						for (OtherPO otherPO : otherPOs) {
							sql = "INSERT INTO OTHER_PAID_OUTS (shift_data_id, label, cost) "
									+ "VALUES (" + otherPO.getShiftDataId() + ", '" + otherPO.getLabel() + "', " + otherPO.getCost() + ")";
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

	public static ShiftData getShiftDataFromDbfResultSet(ResultSet rs) throws SQLException, ClassNotFoundException, ParseException {
		// Loads fields from the current row of rs, so do NOT move the row pointer!
		ShiftData data = new ShiftData();

		data.shift = rs.getInt("shift");
		data.shiftDate = Utils.getDateFromString(rs.getDate("this_date").toString());

		// Get ID using the initials of this user
		String userName = rs.getString("entered_by");
		User user = User.getUserFromInitials(userName);
		if (user != null) {
			data.userId = user.getId();
		} else {
			data.userId = -1;
		}

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

}
