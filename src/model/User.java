/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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
	private Position position;

	private Database db;

	public User() {
		this.id = -1;
		this.username = "";
		this.password = "";
		this.firstName = "";
		this.lastName = "";
		this.position = new Position();
		
		if (db == null) {
			db = new Database();
		}
	}

	public User(String id, String username, String password, String firstName, String lastName, int position_id) {
		this.id = Integer.parseInt(id);
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
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

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public boolean insert() throws Exception {
		// Insert user into the USERS table
		boolean result = false;
		this.db.connect();

		Statement stmt = null;
		String sql = "INSERT INTO USERS (username, password, first_name, last_name, position_id) "
				+ "VALUES (" + this.username + ", '" + this.password
				+ "', '" + this.firstName + "', '" + this.lastName + "', " + this.position.getId() + ");";

		try {
			stmt = db.con.createStatement();
			stmt.executeUpdate(sql);

		} catch (Exception e) {
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}

		return true;
	}

	public static boolean importData(ResultSet rs) throws Exception {
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
		usr.username = rs.getString("id");
		usr.password = rs.getString("password");

		return usr;
	}

	public static String getUsernameFromId(int id) throws Exception {
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

	public static User getUserFromUsername(String userName) throws Exception {
		User user = null;

		Database db = new Database();
		db.connect();

		Statement stmt = db.con.createStatement();
		String sql = "SELECT * FROM USERS WHERE username = '" + userName + "'";
		ResultSet rs = stmt.executeQuery(sql);
		ResultSetMetaData md = rs.getMetaData();
		int test = md.getColumnCount();
		for (int i = 1; i <= test; i++) {
			String col = md.getColumnName(i);
			int test2 = 0;
		}

		if (rs.next()) {
			user = new User(rs);
		}

		return user;
	}

}
