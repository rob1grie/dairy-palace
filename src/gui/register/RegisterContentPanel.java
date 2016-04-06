/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.register;

import java.awt.CardLayout;
import javax.swing.JPanel;

/**
 *
 * @author rob
 */
public class RegisterContentPanel extends JPanel {
	public RegisterContentPanel() {
		
	}
	
	public void showPanel(String name) {
		CardLayout cl = (CardLayout)getLayout();
		cl.show(this, name);
	} 
}
