/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Dimension;
import java.util.Calendar;
import java.util.prefs.Preferences;

/**
 *
 * Defines the preferences to be used by the program
 */
public class DairyPreferences {
	private Preferences settings;
	
	private String importDirectory;
	private int importMonth;
	private int importYear;
	private Dimension mainFrameSize;
	
	public DairyPreferences() {	
		settings = Preferences.userRoot().node(this.getClass().getName());
		
		this.importDirectory = settings.get("importDirectory", System.getProperty("user.home"));
		
		this.importMonth = settings.getInt("importYMonth", Calendar.getInstance().get(Calendar.MONTH));
		this.importYear = settings.getInt("importYear", Calendar.getInstance().get(Calendar.YEAR));
		
		// Adding 50 to saved height for better results, don't know why
		this.mainFrameSize = new Dimension(settings.getInt("mainFrameWidth", 600), settings.getInt("mainFrameHeight", 500)+50);
	}

	public String getImportDirectory() {
		return importDirectory;
	}

	public void setImportDirectory(String importDirectory) {
		this.importDirectory = importDirectory;
		this.settings.put("importDirectory", importDirectory);
	}
	
	public int getImportMonth() {
		return importMonth;
	}

	public void setImportMonth(int importMonth) {
		this.importMonth = importMonth;
		this.settings.putInt("importMonth", importMonth);
	}

	public int getImportYear() {
		return importYear;
	}

	public void setImportYear(int importYear) {
		this.importYear = importYear;
		this.settings.putInt("importYear", importYear);
	}
	
	public Dimension getMainFrameSize() {
		return mainFrameSize;
	}
	
	public void setMainFrameSize(Dimension dim) {
		this.mainFrameSize = dim;
		this.settings.putInt("mainFrameWidth", dim.width);
		this.settings.putInt("mainFrameHeight", dim.height);
	}
}
