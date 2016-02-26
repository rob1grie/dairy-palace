/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.shift;

import java.awt.BorderLayout;
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
		
		shiftToolbar.setToolbarListener((String name1) -> {
			shiftContentPanel.showPanel(name1);
		});
		
		add(shiftToolbar, BorderLayout.NORTH);
		add(shiftContentPanel, BorderLayout.CENTER);
	}
	
	public void load(ShiftData data) {
		// Loads ShiftFormPanel with data and sets top row of ShiftTablePanel to data
		
	}
}
