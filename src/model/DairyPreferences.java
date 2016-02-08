/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Dimension;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * Defines the preferences to be used by the program
 */
public class DairyPreferences {
	private int id;
	private String importDirectory;
	private int importMonth;
	private int importYear;
	private Dimension mainFrameSize;
	
	public DairyPreferences() throws SQLException, ClassNotFoundException {			
		// Load preferences from database
		// Will only be one record
		String sql = "SELECT * FROM PREFERENCES";
		ResultSet rs = Database.load(sql);
		
		if (rs.next()) {
			this.id = rs.getInt("id");
			this.importDirectory = rs.getString("import_directory");
			this.importMonth = rs.getInt("import_month");
			this.importYear = rs.getInt("import_year");
			this.mainFrameSize = new Dimension(rs.getInt("main_frame_width"), rs.getInt("main_frame_height"));
		} else {
			// No initial settings
			this.importDirectory = System.getProperty("user.home");
			this.importMonth = Calendar.getInstance().get(Calendar.MONTH);
			this.importYear = Calendar.getInstance().get(Calendar.YEAR);
		
			this.mainFrameSize = new Dimension(600, 500);
			
			this.insert();
		}
		
	}

	public String getImportDirectory() {
		return importDirectory;
	}

	public void setImportDirectory(String importDirectory) throws SQLException, ClassNotFoundException {
		this.importDirectory = importDirectory;
		this.save();
	}
	
	public int getImportMonth() {
		return importMonth;
	}

	public void setImportMonth(int importMonth) throws SQLException, ClassNotFoundException {
		this.importMonth = importMonth;
		this.save();
	}

	public int getImportYear() {
		return importYear;
	}

	public void setImportYear(int importYear) throws SQLException, ClassNotFoundException {
		this.importYear = importYear;
		this.save();
	}
	
	public Dimension getMainFrameSize() {
		return mainFrameSize;
	}
	
	public void setMainFrameSize(Dimension dim) throws SQLException, ClassNotFoundException {
		this.mainFrameSize = dim;
		this.save();
	}
	
	private void save() throws SQLException, ClassNotFoundException {
		String sql = "UPDATE PREFERENCES SET main_frame_width = ?, main_frame_height = ?, import_month = ?, import_year = ?, "
				+ "import_directory = ? WHERE id = ?";
		
		Database db = new Database();
		db.connect();
		
		PreparedStatement pStmt;
		pStmt = db.con.prepareStatement(sql);
		
		pStmt.setInt(1, this.mainFrameSize.width);
		pStmt.setInt(2, this.mainFrameSize.height);
		pStmt.setInt(3, this.importMonth);
		pStmt.setInt(4, this.importYear);
		pStmt.setString(5, this.importDirectory);
		pStmt.setInt(6, this.id);
		
		pStmt.executeUpdate();
		pStmt.close();
		db.con.commit();
		db.disconnect();
	}
	
	private void insert() throws SQLException, ClassNotFoundException {
		this.id = -1;
		
		String sql = "INSERT INTO PREFERENCES (main_frame_width, main_frame_height, import_month, import_year, import_directory) "
				+ "VALUES (?, ?, ?, ?, ?)";
		
		Database db = new Database();
		db.connect();
		
		PreparedStatement pStmt;
		pStmt = db.con.prepareStatement(sql, new String[]{"ID"});
		
		pStmt.setInt(1, this.mainFrameSize.width);
		pStmt.setInt(2, this.mainFrameSize.height);
		pStmt.setInt(3, this.importMonth);
		pStmt.setInt(4, this.importYear);
		pStmt.setString(5, this.importDirectory);
		
		pStmt.executeUpdate();
		ResultSet gk = pStmt.getGeneratedKeys();
		
		while (gk.next()) {
			this.id = gk.getInt("1");
		}
		
		pStmt.close();
		db.con.commit();
		db.disconnect();
	}
	
}
