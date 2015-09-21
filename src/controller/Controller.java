/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
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

	public void importDbf(File[] files) throws Exception {
		DbfConnection dbf = new DbfConnection();
		
		HashMap keyedFiles = this.reKeyFileList(files);

		// Get data in a sequential order for depencies
		if (keyedFiles.get("employee") != null) {
			dbf.importDbf("employee");
		}
		
		if (keyedFiles.get("users") != null) {
			dbf.importDbf("users");
		}
		
		if (keyedFiles.get("shift") != null) {
			dbf.importDbf("shift");
		}
		
		if (keyedFiles.get("register") != null) {
			dbf.importDbf("register");
		}
		
		if (keyedFiles.get("vendors") != null) {
			dbf.importDbf("vendors");
		}
		
		if (keyedFiles.get("vendinv") != null) {
			dbf.importDbf("vendinv");
		}
		
//		// TODO import USERS before SHIFT in order to have user_id and username
//		try {
//			for (File file : files) {
//				if (file.getName().length() > 0) {
//					String fileName = Utils.getFileName(file.getName());
//					System.out.println(fileName);
//				dbf.importDbf(fileName);
//				}
//			}
//		} catch (Exception ex) {
//			Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
//		}
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
