/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Dimension;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;

/**
 *
 * Defines the preferences to be used by the program
 */
public class DairyPreferences {

	private Preferences settings;

	private String importDirectory;
	private Dimension mainFrameSize;
	private String selectedView;
	private Database db;

	public DairyPreferences() {
		this.db = new Database();
		
		try {
			if (!this.load()) {
				this.initDefaults();
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} catch (ClassNotFoundException ex) {
			System.out.println(ex.getMessage());
		}
	}

	public String getImportDirectory() {
		return importDirectory;
	}

	public void setImportDirectory(String importDirectory) throws SQLException, ClassNotFoundException {
		this.importDirectory = importDirectory;
		this.save();
	}

	public Dimension getMainFrameSize() {
		return mainFrameSize;
	}

	public void setMainFrameSize(Dimension dim) throws SQLException, ClassNotFoundException {
		this.mainFrameSize = dim;
		this.save();
	}

	public String getSelectedView() {
		return selectedView;
	}

	public void setSelectedView(String selectedView) throws SQLException, ClassNotFoundException {
		this.selectedView = selectedView;
		this.save();
	}

	private boolean load() throws SQLException, ClassNotFoundException {
		boolean result = false;

		String sql = "SELECT * FROM preferences;";
		ResultSet rs = db.getResultSet(sql);

		// For now only one record will exist in the PREFERENCES table
				
		while (rs.next()) {
			this.importDirectory = rs.getString("importDirectory");
			this.mainFrameSize = new Dimension(rs.getInt("mainFrameWidth"), rs.getInt("mainFrameHeight"));
			this.selectedView = rs.getString("selectedView");
			
			result = true;
		}
		
		return result;
	}

	private void save() throws SQLException, ClassNotFoundException {
		// Save to database
		String sql = "UPDATE preferences SET importDirectory='" + this.importDirectory 
				+ "', mainFrameWidth=" + this.mainFrameSize.width 
				+ ", mainFrameHeight=" + this.mainFrameSize.height
				+ ", selectedView='" + this.selectedView + "';";
		
		db.runUpdate(sql);

	}

	private void initDefaults() throws SQLException, ClassNotFoundException {
		// Sets defaults if no preferences exist
		this.importDirectory = System.getProperty("user.home");
		this.mainFrameSize = new Dimension(600, 500);
		this.selectedView = "shift";

		if (this.db == null) {
			this.db = new Database();
		}

		String sql = "INSERT INTO preferences (importDirectory, mainFrameWidth, mainFrameHeight, selectedView) "
				+ "VALUES ('" + this.importDirectory + "', "
				+ this.mainFrameSize.width + ", " + this.mainFrameSize.height + ", '"
				+ this.selectedView + "');";

		db.runUpdate(sql);
	}
}
