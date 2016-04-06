/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.register;

import java.awt.BorderLayout;
import java.sql.SQLException;
import java.text.ParseException;
import javax.swing.JPanel;
import model.RegisterAudit;

/**
 *
 * @author Rob
 */
public class RegisterPanel extends JPanel {
	private RegisterContentPanel registerContentPanel;
	private final RegisterToolbar registerToolbar;
	
	public RegisterPanel() throws Exception {
		setLayout(new BorderLayout());
		
		registerContentPanel = new RegisterContentPanel();
		registerToolbar = new RegisterToolbar();
		
		registerToolbar.setToolbarListener(new RegisterToolbarListener() {

			@Override
			public void viewSelected(String name) {
				registerContentPanel.showPanel(name);
				registerToolbar.toggleVisible();
			}
		});
		
		add(registerToolbar, BorderLayout.SOUTH);
		add(registerContentPanel, BorderLayout.CENTER);
		
		registerContentPanel.showPanel(RegisterContentPanel.FORMPANEL);
		registerToolbar.toggleVisible(RegisterContentPanel.TABLEPANEL);
	}
	
	public void load(RegisterAudit data) throws SQLException, ClassNotFoundException {
		// Loads ShiftFormPanel with data and sets top row of ShiftTablePanel to data
		this.registerContentPanel.load(data);
	}
	
	public void loadLatest() throws SQLException, ClassNotFoundException, ParseException {
		// Gets the record for the latest ShiftData record and loads the panel
		int lastId = RegisterAudit.getLastDataId();
		RegisterAudit data = new RegisterAudit(lastId);
		this.load(data);
	}
}
