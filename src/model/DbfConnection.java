/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import gui.ImportFilter;
import java.sql.*;
import java.util.Arrays;
import java.util.Properties;
import utils.Utils;

/**
 *
 * @author Rob
 */
public class DbfConnection {
	private String jdbURL;
	private Properties props;
	private Connection conn;
	private Statement stmt;
	private ImportFilter importFilter;
	
	public DbfConnection(ImportFilter importFilter) throws ClassNotFoundException {
		jdbURL = "jdbc:dbf:/./data/?caseInsensitive=true";
		
		Class.forName("com.caigen.sql.dbf.DBFDriver");
		
		props = new Properties();
		props.setProperty("delayedClose", "0");
				
		this.importFilter = importFilter;
	}
	
	public void importDbf(String dbfFile) throws SQLException, Exception {
		
		conn = DriverManager.getConnection(this.jdbURL, this.props);
		stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		
		String sql = "SELECT * FROM " + dbfFile;
		
		// Need to handle tables that have a date to filter
		// SHIFT, REGISTER, VENDINV have THIS_DATE, DEPOSIT has DEP_DATE
		String[] thisDate = new String[] {"shift", "register", "vendinv"};
//		ImportFilter importFilter = par
		if (Arrays.asList(thisDate).contains(dbfFile)) {
			sql = sql + " WHERE THIS_DATE >='" + this.importFilter.getStartDate() + "'";
		}
		else if (dbfFile.equals("deposit")) {
			sql = sql + " WHERE DEP_DATE >='" + this.importFilter.getStartDate() + "'";
		}
		
		ResultSet rs = stmt.executeQuery(sql);
		
		System.out.println("Import results: " + Utils.countResultSetRows(rs));
		
		// TODO Need to move switch statement up so that a query isn't ran on every single dbf file
		switch (dbfFile) { // dbfFile has been converted to lower case
			case "employee":
				Employee.importData(rs);
				break;
			case "users":
				User.importData(rs);
				break;
			case "shift":
				ShiftData.importData(rs);
				break;
			case "register":
				RegisterAudit.importData(rs);
				break;
		}
				
		stmt.close();
		conn.close();
		
	}
}
