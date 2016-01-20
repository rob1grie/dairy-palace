/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Rob
 */
public class Position {

	private int id;
	private String position;

	public Position() {
		this.id = -1;
		this.position = "";
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

	public static Position getById(int it) throws SQLException, ClassNotFoundException {
		Position position = null;

		String sql = "SELECT * FROM POSITIONS WHERE id = id;";

		ResultSet rs = Database.load(sql);

		while (rs.next()) {
			position = new Position(rs.getInt("id"), rs.getString("position"));
		}

		return position;
	}
}
