/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 *
 * @author Rob
 */
public class Vendor {
	private int id;
	private String name;
	
	private Database db;

	public Vendor(int id) {
		this.id = id;
		
		if(this.db == null) {
			this.db = new Database();
		}
	}
	
	public Vendor(String name) {
		this.name = name;
		if(this.db == null) {
			this.db = new Database();
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public static boolean importFromFile(File file) throws Exception {
		// file is a CSV file obtained from a CSV filtered file chooser
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		try {
			br = new BufferedReader(new FileReader(file.toString()));
			// First line is field names
			line = br.readLine();

			while ((line = br.readLine()) != null) {
				// use comma as separator
				String[] vnd = line.split(cvsSplitBy);
				//TODO Get user ID from ENTERED_BY shft[2]
				Vendor vendor = new Vendor(vnd[0]);

				vendor.insert();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException ex) {
			ex.printStackTrace();
			return false;
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
					return false;
				}
			}
		}
		
		return true;
	}

	public boolean insert() throws Exception {
		// Insert into Shift_Datas table
		db.connect();

		Connection c = null;
		Statement stmt = null;

		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:dairy.db");
			c.setAutoCommit(false);

			stmt = c.createStatement();
			String sql = "INSERT INTO VENDORS (name) VALUES ('" + this.name + "');";
			stmt.executeUpdate(sql);
			
			stmt.close();
			c.commit();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			return false;
		}

		return true;
	}
	
}
