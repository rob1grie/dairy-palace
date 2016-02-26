/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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

	public OtherPO(int id) throws SQLException, ClassNotFoundException {
		if (id == -1) {
			// Creating a new empty OtherPO
			this.id = id;
			this.shiftDataId = -1;
			this.label = "";
			this.cost = 0;

		} else {
			this.id = id;

			getOtherPO();
		}
	}

	public OtherPO(int shiftDataId, String label, float cost) {
		this.shiftDataId = shiftDataId;
		this.label = label;
		this.cost = cost;
	}
	
	public OtherPO(ResultSet rs) throws SQLException {
		// Constructor using a ResultSet
		this.shiftDataId = rs.getInt("shift_data_id");
		this.label = rs.getString("label");
		this.cost = rs.getFloat("cost");
	}

	public void getOtherPO() throws SQLException, ClassNotFoundException {
		// Gets OtherPO with this id
		String sql = "SELECT * FROM OTHER_PAID_OUTS WHERE ID=" + this.id;

		ResultSet rs = Database.load(sql);

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

	public void insert() throws SQLException, ClassNotFoundException {
		// Save this to a new record
		String sql = "INSERT INTO OTHER_PAID_OUTS (shift_data_id, label, cost) "
				+ "VALUES (" + this.shiftDataId + ", '" + this.label + "', " + this.cost + ")";
		
		Database.insert(sql);
	}
	
	public static ArrayList<OtherPO> getShiftOtherPO(int shiftId) throws SQLException, ClassNotFoundException {
		ArrayList<OtherPO> al = new ArrayList<>();
		
		String sql = "SELECT * FROM OTHER_PAID_OUTS WHERE shift_data_id = " + shiftId;
		
		ResultSet rs = Database.load(sql);
		
		while (rs.next()) {
			al.add(new OtherPO(rs));
		}
		
		return al;
	}

	public static ArrayList<OtherPO> importData(ResultSet rs, int id) throws SQLException, ClassNotFoundException {
		// Receives a ResultSet from a ShiftData DBF record and returns an ArrayList of OtherPO objects
		ArrayList<OtherPO> al = new ArrayList<>();

		// Test each OTHERn_CST before adding to al
		if (rs.getFloat("OTHER1_CST") > 0.0) {
			/// OtherPO constructor expects strings for all parameters
			OtherPO otherPO = new OtherPO(
					id,
					rs.getString("OTHER1_LAB"),
					Float.parseFloat(rs.getString("OTHER1_CST")));
			al.add(otherPO);
		}
		if (rs.getFloat("OTHER2_CST") > 0.0) {
			/// OtherPO constructor expects strings for all parameters
			OtherPO otherPO = new OtherPO(
					id,
					rs.getString("OTHER2_LAB"),
					Float.parseFloat(rs.getString("OTHER2_CST")));
			al.add(otherPO);
		}
		if (rs.getFloat("OTHER3_CST") > 0.0) {
			/// OtherPO constructor expects strings for all parameters
			OtherPO otherPO = new OtherPO(
					id,
					rs.getString("OTHER3_LAB"),
					Float.parseFloat(rs.getString("OTHER3_CST")));
			al.add(otherPO);
		}
		if (rs.getFloat("OTHER4_CST") > 0.0) {
			/// OtherPO constructor expects strings for all parameters
			OtherPO otherPO = new OtherPO(
					id,
					rs.getString("OTHER4_LAB"),
					Float.parseFloat(rs.getString("OTHER4_CST")));
			al.add(otherPO);
		}

		return al;
	}

}
