/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

/**
 *
 * @author rob
 */
public class ImportFilter {
	private String startDate;		// Will be format mm/dd/yyyy
	
	public ImportFilter(int month, int year) {
		this.startDate = ((Integer)month).toString() + "/01/" + ((Integer)year).toString();
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
}
