/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author rob
 */
public class MonthYearDialog extends JDialog {
	private JButton okButton;
	private JButton cancelButton;
	private JTextField month;
	private JTextField year;
	
	public MonthYearDialog(JFrame parent) {
		super(parent, "Select Import Date", true);
		
		this.layoutComponents();
		
		this.setSize(400, 300);
		this.setLocationRelativeTo(parent);
	}
	
	private void layoutComponents() {
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		
		gc.gridy = 0;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.NONE;
		
		gc.gridx = 0;
		add(new JLabel("Month: "), gc);
		
		month = new JTextField(5);
		gc.gridx++;
		add(month, gc);
		
		gc.gridx++;
		add(new JLabel("Year: "), gc);
		
		year = new JTextField(8);
		gc.gridx++;
		add(year, gc);
		
		gc.gridy++;
		gc.gridx = 0;
		okButton = new JButton("OK");
		add(okButton, gc);
		
		gc.gridx++;
		cancelButton = new JButton("Cancel");
		add(cancelButton, gc);
	}
}
