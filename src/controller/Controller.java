/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import gui.ImportFilter;
import java.io.File;
import java.util.HashMap;
import model.Database;
import model.DbfConnection;
import utils.Utils;

/**
 *
 * @author Rob
 */
public class Controller {

	public Database db;

	public Controller() {
		if (db == null) {
			db = new Database();
		}
	}

	public void importDbf(File[] files, ImportFilter importFilter) throws ClassNotFoundException, Exception {
		DbfConnection dbf = new DbfConnection(importFilter);
		
			HashMap keyedFiles = this.reKeyFileList(files);

		// Get data in a sequential order for depencies
		if ((keyedFiles.get("employee") != null) &&
				(Database.getRowCount("employees") == 0)) {
			dbf.importDbf("employee");
		}
		
		if ((keyedFiles.get("users") != null) &&
				(Database.getRowCount("users") == 0)) {
			dbf.importDbf("users");
		}
		
		if ((keyedFiles.get("shift") != null) &&
				(Database.getRowCount("shift_datas") == 0)) {
			dbf.importDbf("shift");
		}
		
		if ((keyedFiles.get("register") != null) &&
				(Database.getRowCount("register_audits") == 0)) {
			dbf.importDbf("register");
		}
		
		if ((keyedFiles.get("vendors") != null) &&
				(Database.getRowCount("vendors") == 0)) {
			dbf.importDbf("vendors");
		}
		
		if ((keyedFiles.get("vendinv") != null) &&
				(Database.getRowCount("vendor_invoices") == 0)) {
			dbf.importDbf("vendinv");
		}
	}
	
	private HashMap reKeyFileList(File[] files) throws Exception {
		// Converting array of files from File.listFiles() to an array with indexes of dbf filesnames
		// Filename keys will be converted to lower case
		HashMap<String, File> newList = new HashMap<String, File>();
		
		for (File file : files) {
			if (file.getName().length() > 0) {
				String fileName = Utils.getFileName(file.getName()).toLowerCase();
				newList.put(fileName, file);
			}
		}
		
		return newList;
	}
}
