/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.shift;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.EventObject;
import model.ShiftData;

/**
 *
 * @author rob
 */
public class ShiftFormHeaderEvent extends EventObject {

	private String buttonName;
	
	public ShiftFormHeaderEvent(Object source) {
		super(source);
	}
	
	public ShiftFormHeaderEvent(Object source, String buttonName) {
		super(source);
		
		this.buttonName = buttonName;
	}

	public String getButtonName() {
		return buttonName;
	}

	public void setButtonName(String buttonName) {
		this.buttonName = buttonName;
	}
	
	public static ShiftData getNewShiftData(int id) throws SQLException, ClassNotFoundException, ParseException {
		ShiftData data = new ShiftData(id);
		
		return data;
	}
	
}
