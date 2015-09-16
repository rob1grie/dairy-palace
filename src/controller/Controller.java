/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
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

		// TODO import USERS before SHIFT in order to have user_id and username
		try {
			for (File file : files) {
				if (file.getName().length() > 0) {
					String fileName = Utils.getFileName(file.getName());
					System.out.println(fileName);
				dbf.importDbf(fileName);
				}
			}
		} catch (Exception ex) {
			Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
