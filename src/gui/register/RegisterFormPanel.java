/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.register;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import model.RegisterAudit;

/**
 *
 * @author rob
 */
public class RegisterFormPanel extends JPanel {
	private JLabel empNumLabel;
	private JLabel regNoLabel;
	private JLabel dateLabel;
	private JLabel timeLabel;
	private JLabel shiftLabel;
	private JLabel tapeReadingLabel;
	private JLabel cashCountLabel;
	private JLabel mgrAuditLabel;
	private JLabel mgrLabel;
	private JLabel deltaLabel;
	
	private JTextField empNumField;
	private JTextField regNoField;
	private JTextField dateField;
	private JTextField timeField;
	private JTextField shiftField;
	private JTextField tapeReadingField;
	private JTextField cashCountField;
	private JCheckBox mgrAuditField;
	private JTextField mgrField;
	private JTextField deltaField;
	
	private RegisterAudit registerData;
	
	public RegisterFormPanel() {
		
		layoutComponents();
		
		setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		
	}
	
	private void layoutComponents() {
		setLayout(new GridBagLayout());
		setPreferredSize(new Dimension(375, 200));
		GridBagConstraints c = new GridBagConstraints();

		//////// Layout labels //////////////
		empNumLabel = new JLabel("Emp. Number(s): ");
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(20, 0, 0, 10);
		add(empNumLabel, c);

		regNoLabel = new JLabel("Register No.: ");
		c.gridy++;
		add(regNoLabel, c);
		
		dateLabel = new JLabel("Date: ");
		c.gridy++;
		add(dateLabel, c);
		
		timeLabel = new JLabel("Time: ");
		c.gridy++;
		add(timeLabel, c);
		
		shiftLabel = new JLabel("Shift: ");
		c.gridy++;
		add(shiftLabel, c);
		
		tapeReadingLabel = new JLabel("Tape Reading: ");
		c.gridy++;
		add(tapeReadingLabel, c);
		
		cashCountLabel = new JLabel("Cash Count: ");
		c.gridy++;
		add(cashCountLabel, c);
		
		mgrAuditLabel = new JLabel("Manager's Audit: ");
		c.gridy++;
		add(mgrAuditLabel, c);
		
		mgrLabel = new JLabel("Manager: ");
		c.gridy++;
		add(mgrLabel, c);
		
		//////// Layout fields //////////////
		empNumField = new JTextField(6);
		empNumField.setHorizontalAlignment(JTextField.CENTER);
		c.gridx = 1;
		c.gridy = 0;
		c.weighty = 0.0;
		c.insets = new Insets(20, 10, 0, 0);
		add(empNumField, c);

		regNoField = new JTextField(6);
		regNoField.setHorizontalAlignment(JTextField.CENTER);
		c.gridy++;
		add(regNoField, c);

		dateField = new JTextField(6);
		dateField.setHorizontalAlignment(JTextField.CENTER);
		c.gridy++;
		add(dateField, c);

		timeField = new JTextField(6);
		timeField.setHorizontalAlignment(JTextField.CENTER);
		c.gridy++;
		add(timeField, c);

		shiftField = new JTextField(3);
		shiftField.setHorizontalAlignment(JTextField.CENTER);
		c.gridy++;
		add(shiftField, c);

		tapeReadingField = new JTextField(6);
		tapeReadingField.setHorizontalAlignment(JTextField.RIGHT);
		c.gridy++;
		add(tapeReadingField, c);

		cashCountField = new JTextField(6);
		cashCountField.setHorizontalAlignment(JTextField.RIGHT);
		c.gridy++;
		add(cashCountField, c);
		
		deltaLabel = new JLabel("(+/-)");
		c.gridx++;
		add(deltaLabel, c);
		
		deltaField = new JTextField(4);
		deltaField.setEditable(false);
		c.gridx++;
		add(deltaField, c);
		
		c.gridx -= 2;

		mgrAuditField = new JCheckBox();
		c.gridy++;
		add(mgrAuditField, c);

		mgrField = new JTextField(4);
		mgrField.setHorizontalAlignment(JTextField.CENTER);
		c.gridy++;
		add(mgrField, c);

		
	}
}
