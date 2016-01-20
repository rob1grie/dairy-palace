/*
 * Interfacews between the ShiftData model and the Shift views
 */
package controller;

import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Database;
import model.ShiftData;

/**
 *
 * @author Rob
 */
public class ShiftController {

	private ShiftData data;
	private int prevId;
	private int nextId;

	public ShiftController() {
		this.data = null;
		this.prevId = -1;
		this.nextId = -1;
	}

	public ShiftData getData(int id) throws Exception {
		// Returns a ShiftData object with this ID
		if (id == -1) {
			return null;
		}

		this.data = new ShiftData(id);

		this.setPrevId();
		this.setNextId();

		return this.data;
	}

	public int getNextId() {
		return this.nextId;
	}

	public int getPrevId() {
		return this.prevId;
	}

	private void setNextId() {
		// Get the previous record
		String sql = "SELECT MIN(id) AS id FROM SHIFT_DATAS WHERE id > " + this.data.getId();
		try {
			ResultSet rs = Database.load(sql);
			if (rs.next()) {
				this.nextId = rs.getInt("id");
			}
			rs.close();
		} catch (Exception ex) {
			Logger.getLogger(ShiftController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public boolean isLastShiftData() {
		// Returns whether this.data contains the last record in the table
		return (this.nextId == -1);
	}

	private void setPrevId() {
		// Get the next record
		String sql = "SELECT MAX(id) AS id FROM SHIFT_DATAS WHERE id < " + this.data.getId();
		try {
			ResultSet rs = Database.load(sql);
			if (rs.next()) {
				this.prevId = rs.getInt("id");
			}
			rs.close();
		} catch (Exception ex) {
			Logger.getLogger(ShiftController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public boolean isFirstShiftData() {
		// Returns whether this.data contains the first record in the table
		return (this.prevId == -1);
	}

	public static int getLastDataId() {
		String sql = "SELECT MAX(id) AS id FROM SHIFT_DATAS";
		int lastId = 0;

		try {
			ResultSet rs = Database.load(sql);
			if (rs.next()) {
				lastId = rs.getInt("id");
			}
			rs.close();
		} catch (Exception ex) {
			Logger.getLogger(ShiftController.class.getName()).log(Level.SEVERE, null, ex);
		}

		return lastId;
	}

}
