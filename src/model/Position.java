/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Rob
 */
public class Position {

	private int id;
	private String position;

	public Position() {
		// Default position is Employee
		// TODO  Take out hard coded default
		this.id = 2;
		this.position = "Employee";
	}
	
	public Position(int id) throws SQLException, ClassNotFoundException {
		this.id = id;
		
		String sql = "SELECT * FROM POSITIONS WHERE id=" + id;
		ResultSet rs = Database.load(sql);
		
		while (rs.next()) {
			this.position = rs.getString("position");
		}
	}

	public Position(int id, String position) {
		this.id = id;
		this.position = position;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public boolean insert() throws SQLException, ClassNotFoundException {
		boolean result = true;

		Database db = new Database();
		db.connect();
		String sql = "INSERT INTO POSITIONS (position) "
				+ "VALUES ('" + this.position + "')";

		result = Database.insert(sql) == 1;

		db.con.commit();
		db.disconnect();

		return result;
	}
	
	public boolean update() throws SQLException, ClassNotFoundException {
		boolean result = true;
		
		Database db = new Database();
		db.connect();
		String sql = "UPDATE POSITIONS SET position='" + this.position + "' WHERE id=" + this.id;

		result = Database.update(sql) == 1;

		db.con.commit();
		db.disconnect();
		
		return result;
	}
	
	public static Position getById(int id) throws SQLException, ClassNotFoundException {
		Position position = null;

		String sql = "SELECT * FROM POSITIONS WHERE id=" + id;

		ResultSet rs = Database.load(sql);

		while (rs.next()) {
			position = new Position(rs.getInt("id"), rs.getString("position"));
		}

		return position;
	}

}
