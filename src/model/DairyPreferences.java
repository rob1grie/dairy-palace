/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Dimension;
import java.util.prefs.Preferences;

/**
 *
 * Defines the preferences to be used by the program
 */
public class DairyPreferences {
	private Preferences settings;
	
	private String importDirectory;
	private Dimension mainFrameSize;
	
	public DairyPreferences() {	
		settings = Preferences.userRoot().node(this.getClass().getName());
		
		this.importDirectory = settings.get("importDirectory", System.getProperty("user.home"));
		
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
	
	public Dimension getMainFrameSize() {
		return mainFrameSize;
	}
	
	public void setMainFrameSize(Dimension dim) {
		this.mainFrameSize = dim;
		this.settings.putInt("mainFrameWidth", dim.width);
		this.settings.putInt("mainFrameHeight", dim.height);
	}
}
