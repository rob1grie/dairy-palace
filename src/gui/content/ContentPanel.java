/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.content;

import gui.shift.ShiftDataTableModel;
import java.awt.CardLayout;
import javax.swing.JPanel;

/**
 *
 * @author rob
 */
public final class ContentPanel extends JPanel {
	private final TablePanel tablePanel;	// Reusable TablePanel 
	private final FormPanel formPanel;		// Reusable FormPanel

	private PanelMode panelMode;
	private ContentMode contentMode;
	
	public ContentPanel() throws Exception {
		tablePanel = new TablePanel();
		tablePanel.getTableModelData();
		
		formPanel = new FormPanel();
		
		setLayout(new CardLayout());
		add(tablePanel, PanelMode.TABLEVIEW);
		add(formPanel, PanelMode.FORMVIEW);
		
		setPanelMode(PanelMode.FORMVIEW);
		
		// TODO Populate tablePanel after displaying formPanel 
	}

	public void setPanelMode(PanelMode mode) {
		this.panelMode = mode;
		CardLayout cl = (CardLayout)getLayout();
		cl.show(this, this.panelMode.name());
	}
	
	public void setContentMode(ContentMode mode) {
		this.contentMode = mode;
		
		switch (this.contentMode) {
			case SHIFT:
				break;
			case REGISTER:
				break;
			case INVOICE:
				break;
		}
	}
	
	public void buildShiftContentPanel() {
		ShiftDataTableModel tableModel = new ShiftDataTableModel();
		
	}
}
