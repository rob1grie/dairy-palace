/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Rob
 */
public class MainFooter extends JPanel {
	private JLabel message;
	
	public MainFooter() {
		setLayout(new FlowLayout(FlowLayout.LEADING));
		this.message = new JLabel();
		
		add(this.message);
	}
	
	public void setMessage(String message) {
		this.message.setText(message);
	}
	
	public String getMessage() {
		return this.message.getText();
	}
}
