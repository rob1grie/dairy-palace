/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.*;
import java.util.Arrays;
import java.util.Properties;

/**
 *
 * @author Rob
 */
public class DbfConnection {
	private String jdbURL;
	private Properties props;
	private Connection conn;
	private Statement stmt;
	
	public DbfConnection() throws ClassNotFoundException {
		jdbURL = "jdbc:dbf:/./data/?caseInsensitive=true";
		
		Class.forName("com.caigen.sql.dbf.DBFDriver");
		
		props = new Properties();
		props.setProperty("delayedClose", "0");
				
	}
	
	public void importDbf(String dbfFile) throws SQLException, Exception {
		conn = DriverManager.getConnection(this.jdbURL, this.props);
		stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		
		String sql = "SELECT * FROM " + dbfFile + ";";
		
		// Need to handle tables that have a date to filter
		// SHIFT, REGISTER, VENDINV have THIS_DATE, DEPOSIT has DEP_DATE
		String[] thisDate = new String[] {"shift", "register", "vendinv"};
//		ImportFilter importFilter = par
		if (Arrays.asList(thisDate).contains(dbfFile)) {
			sql = sql + " WHERE THIS_DATE >= ";
		}
		
		ResultSet rs = stmt.executeQuery(sql);
		ResultSetMetaData md = rs.getMetaData();
		int cols = md.getColumnCount();
		
		Object colVal;
				
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
//			case "register":
//				RegisterAudit.importData(rs);
//				break;
		}
				
		stmt.close();
		conn.close();
		
	}
}
