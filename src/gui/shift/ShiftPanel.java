/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.shift;

import java.awt.BorderLayout;
import java.sql.SQLException;
import java.text.ParseException;
import javax.swing.JPanel;
import model.ShiftData;

/**
 *
 * @author Rob
 */
public class ShiftPanel extends JPanel {
	private ShiftContentPanel shiftContentPanel;
	private final ShiftToolbar shiftToolbar;
	
	public ShiftPanel() throws Exception {
		setLayout(new BorderLayout());
		
		shiftContentPanel = new ShiftContentPanel();
		shiftToolbar = new ShiftToolbar();
		
		shiftToolbar.setToolbarListener(new ShiftToolbarListener() {

			@Override
			public void viewSelected(String name) {
				shiftContentPanel.showPanel(name);
			}
		});
		
		add(shiftToolbar, BorderLayout.SOUTH);
		add(shiftContentPanel, BorderLayout.CENTER);
	}
	
	public void load(ShiftData data) throws SQLException, ClassNotFoundException {
		// Loads ShiftFormPanel with data and sets top row of ShiftTablePanel to data
		this.shiftContentPanel.load(data);
	}
	
	public void loadLatest() throws SQLException, ClassNotFoundException, ParseException {
		// Gets the record for the latest ShiftData record and loads the panel
		int lastId = ShiftData.getLastDataId();
		ShiftData data = new ShiftData(lastId);
		this.load(data);
	}
}
