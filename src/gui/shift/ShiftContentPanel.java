/*
	Contains the main content panel of the Shift view, with a CardLayout manager
 */
package gui.shift;

import java.awt.CardLayout;
import javax.swing.JPanel;

/**
 *
 * @author Rob
 */
public class ShiftContentPanel extends JPanel {
	private ShiftTablePanel tablePanel;
	private ShiftFormPanel formPanel;
	
	final static String TABLEPANEL = "Table";
	final static String FORMPANEL = "Form";

	public ShiftContentPanel() throws Exception {
		tablePanel = new ShiftTablePanel();
//		tablePanel.getTableModelData();
		
		formPanel = new ShiftFormPanel();
		
		setLayout(new CardLayout());
		add(tablePanel, TABLEPANEL);
		add(formPanel, FORMPANEL);
		
		showPanel(FORMPANEL);
	}
	
	public void showPanel(String name) {
		CardLayout cl = (CardLayout)getLayout();
		cl.show(this, name);
	}
	
}
