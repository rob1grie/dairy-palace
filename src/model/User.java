/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Rob
 */
public class User {

	private int id;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String initials;
	private Position position;

	private Database db;

	public User() {
		this.id = -1;
		this.username = "";
		this.password = "";
		this.firstName = "";
		this.lastName = "";
		this.initials = "";
		this.position = new Position();
		
		if (db == null) {
			db = new Database();
		}
	}

	public User(
			String id, 
			String username, 
			String password, 
			String firstName, 
			String lastName, 
			String initials,
			int position_id) {
		this.id = Integer.parseInt(id);
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.initials = initials;
		this.position = Position.getById(position_id);

		if (db == null) {
			db = new Database();
		}
	}

	public User(ResultSet rs) throws SQLException {
		// Constructs User from a ResultSet which is already at the current row
		
		this.id = Integer.parseInt(rs.getString("id"));
		this.username = rs.getString("username");
		this.password = rs.getString("password");
		this.firstName = rs.getString("first_name");
		this.lastName = rs.getString("last_name");
		this.initials = rs.getString("initials");
		this.position = Position.getById(rs.getInt("position_id"));

		if (db == null) {
			db = new Database();
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getInitials() {
		return initials;
	}

	public void setInitials(String initials) {
		this.initials = initials;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public boolean insert() throws SQLException, ClassNotFoundException {
		// Insert user into the USERS table
		boolean result = false;
		this.db.connect();

		Statement stmt = null;
		String sql = "INSERT INTO USERS (username, password, first_name, last_name, initials, position_id) "
				+ "VALUES (" + this.username + ", '" + this.password
				+ "', '" + this.firstName + "', '" + this.lastName
				+ "', '" + this.initials + "', " + this.position.getId() + ")";

			stmt = db.con.createStatement();
			stmt.executeUpdate(sql);

			if (stmt != null) {
				stmt.close();
			}

		this.db.disconnect();
		return true;
	}

	public static boolean importData(ResultSet rs) throws SQLException, ClassNotFoundException {
		// rs is from a DBF record

		while (rs.next()) {
			User usr = User.getUserFromDbfResultSet(rs);

			usr.insert();
		}

		return true;
	}

	public static User getUserFromDbfResultSet(ResultSet rs) throws SQLException {
		User usr = new User();

		String[] name = rs.getString("name").split(" ");

		if (name.length > 0) {
			usr.firstName = name[0];
		}
		if (name.length > 1) {
			usr.lastName = name[1];
		}
		
		usr.initials = rs.getString("inits");
		usr.username = rs.getString("id");
		usr.password = rs.getString("password");

		return usr;
	}

	public static String getUsernameFromId(int id) throws SQLException, ClassNotFoundException {
		String username = "";

		Database db = new Database();
		db.connect();

		Statement stmt = db.con.createStatement();
		String sql = "SELECT username FROM USERS WHERE id = " + id;
		ResultSet rs = stmt.executeQuery(sql);

		if (rs.next()) {
			username = rs.getString(username);
		}

		rs.close();
		db.disconnect();

		return username;
	}

	public static User getUserFromUsername(String userName) throws SQLException, ClassNotFoundException {
		String sql = "SELECT * FROM USERS WHERE username = '" + userName + "'";
		
		User user = User.getUserFromSql(sql);
		
		return user;
	}
	
	public static User getUserFromInitials(String initials) throws SQLException, ClassNotFoundException {
		String sql = "SELECT * FROM USERS WHERE initials = '" + initials + "'";
		
		User user = User.getUserFromSql(sql);
		
		return user;
	}
	
	private static User getUserFromSql(String sql) throws SQLException, ClassNotFoundException {
		User user = null;

		Database db = new Database();
		db.connect();

		Statement stmt = db.con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);

		if (rs.next()) {
			user = new User(rs);
		}
		
		db.disconnect();

		return user;
		
	}

}
