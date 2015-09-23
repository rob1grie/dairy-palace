/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 *
 * @author rob
 */
public class MonthYearDialog extends JDialog {
	private JButton okButton;
	private JButton cancelButton;
	private JTextField monthField;
	private JTextField yearField;
	
	private MonthYearListener monthYearListener;
		
	public MonthYearDialog(JFrame parent, int m, int y) {
		super(parent, "Select Import Date", true);
		
		this.layoutComponents();
		
		this.monthField.setText(((Integer)m).toString());
		this.yearField.setText(((Integer)y).toString());
		
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (monthYearListener != null) {
					monthYearListener.monthYearSet(
							Integer.parseInt(monthField.getText()),
							Integer.parseInt(yearField.getText()));
				}
			}
		
		});
		
		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		
		});
		
		this.setSize(380, 125);
		this.setLocationRelativeTo(parent);
		
	}
	
	private void layoutComponents() {
		JPanel controlsPanel = new JPanel();
		JPanel buttonsPanel = new JPanel();
		
		int space = 15;
		Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);
		Border titleBorder = BorderFactory.createTitledBorder("Enter beginning Month and Year for Import");
		
		controlsPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titleBorder));
		
		controlsPanel.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		
		gc.gridy = 0;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.NONE;
		
		gc.gridy++;
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.EAST;
		controlsPanel.add(new JLabel("Month: "), gc);
		
		monthField = new JTextField(5);
		gc.gridx++;
		gc.anchor = GridBagConstraints.WEST;
		controlsPanel.add(monthField, gc);
		
		gc.gridx++;
		gc.anchor = GridBagConstraints.EAST;
		controlsPanel.add(new JLabel("Year: "), gc);
		
		yearField = new JTextField(8);
		gc.gridx++;
		gc.anchor = GridBagConstraints.WEST;
		controlsPanel.add(yearField, gc);
		
		buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		okButton = new JButton("OK");
		buttonsPanel.add(okButton);
		
		cancelButton = new JButton("Cancel");
		buttonsPanel.add(cancelButton);
		
		Dimension btnSize = cancelButton.getPreferredSize();
		okButton.setPreferredSize(btnSize);
		
		setLayout(new BorderLayout());
		add(controlsPanel, BorderLayout.CENTER);
		add(buttonsPanel, BorderLayout.SOUTH);
	}

	public void setMonthYearListener(MonthYearListener monthYearListener) {
		this.monthYearListener = monthYearListener;
	}
	
	public boolean validateFields() {
		boolean result = false;
		
		int month = Integer.parseInt(this.monthField.getText());
		int year = Integer.parseInt(this.yearField.getText());
		int maxYear = Calendar.getInstance().get(Calendar.YEAR);
		
		result = (month >= 1) && (month <= 12) 
				&& (year >= 2000) && (year <= maxYear);
		
		return result;
	}
}
