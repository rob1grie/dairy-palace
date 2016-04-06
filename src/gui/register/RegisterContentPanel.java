/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.register;

import java.awt.CardLayout;
import javax.swing.JPanel;
import model.RegisterAudit;

/**
 *
 * @author rob
 */
public final class RegisterContentPanel extends JPanel {
	private final RegisterTablePanel tablePanel;
	private final RegisterFormPanel formPanel;
	
	final static String TABLEPANEL = "Table";
	final static String FORMPANEL = "Form";

	public RegisterContentPanel() {
		tablePanel = new RegisterTablePanel();
		tablePanel.getTableModelData();
		
		formPanel = new RegisterFormPanel();
		
		setLayout(new CardLayout());
		add(tablePanel, TABLEPANEL);
		add(formPanel, FORMPANEL);
		
		showPanel(FORMPANEL);
		
		// TODO Populate tablePanel after displaying formPanel 
		
	}
	
	public void showPanel(String name) {
		CardLayout cl = (CardLayout)getLayout();
		cl.show(this, name);
	} 

	void load(RegisterAudit data) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
}
