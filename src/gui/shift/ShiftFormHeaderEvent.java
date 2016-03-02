/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.shift;

import java.util.EventObject;

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
	
}
