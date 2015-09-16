/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rob
 */
public class OtherPO {

	private int id;
	private int shiftDataId;
	private String label;
	private float cost;

	private Database db;

	public OtherPO(int id) throws Exception {
		if (id == -1) {
			// Creating a new empty OtherPO
			this.id = id;
			this.shiftDataId = -1;
			this.label = "";
			this.cost = 0;

		} else {
			this.id = id;

			if (this.db == null) {
				db = new Database();
			}

			getOtherPO();
		}
	}

	public OtherPO(int shiftDataId, String label, float cost) {
		this.shiftDataId = shiftDataId;
		this.label = label;
		this.cost = cost;
	}

	public void getOtherPO() throws Exception {
		// Gets OtherPO with this id
		String sql = "SELECT * FROM OTHER_PAID_OUTS WHERE ID=" + this.id;

		this.db = new Database();
		this.db.connect();
		Statement stmt = this.db.con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);

		try {
			while (rs.next()) {
				this.shiftDataId = rs.getInt("shift_data_id");
				this.label = rs.getString("label");
				this.cost = rs.getFloat("cost");
			}
			rs.close();
		} catch (SQLException e) {
			Logger.getLogger(ShiftData.class.getName()).log(Level.SEVERE, null, e);
		}

		db.disconnect();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getShiftDataId() {
		return shiftDataId;
	}

	public void setShiftDataId(int shiftDataId) {
		this.shiftDataId = shiftDataId;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}
	
	public void insert() throws Exception {
		// Save this to a new record
		String sql = "INSERT INTO OTHER_PAID_OUTS (shift_data_id, label, cost) " +
				"VALUES (" + this.shiftDataId + ", '" + this.label + "', " + this.cost + ")";
		db = new Database();
		db.connect();
		Statement stmt = db.con.createStatement();
		
		int id = stmt.executeUpdate(sql);
		this.id = id;
		
		db.disconnect();
	}

}
