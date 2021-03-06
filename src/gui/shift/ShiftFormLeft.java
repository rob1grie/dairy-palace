/*
 * The left portion of the Shift form, showing Cash Paid Outs
 */
package gui.shift;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.SQLException;
import java.text.DecimalFormat;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import model.ShiftData;

/**
 *
 * @author Rob
 */
public class ShiftFormLeft extends JPanel {
	private JLabel cashPOLabel;
	private JLabel foodLabel;
	private JLabel restSupLabel;
	private JLabel offSupLabel;
	private JLabel repMaintLabel;
	private JLabel freightLabel;
	private JLabel otherLabel;
	
	private JTextField foodField;
	private JTextField restSupField;
	private JTextField offSupField;
	private JTextField repMaintField;
	private JTextField freightField;
	
	private OtherPOPanel otherPOPanel;
	
	// TODO  Figure out why left text fields change size when main window height reaches a certain point
	
	public ShiftFormLeft() {
		
		layoutComponents();
		setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		
//		setBorder(BorderFactory.createLineBorder(Color.yellow, 2));
	}
	
	private void layoutComponents() {
		setLayout(new GridBagLayout());
		setPreferredSize(new Dimension(385,200));
//		setMinimumSize(new Dimension(375,200));
		GridBagConstraints c = new GridBagConstraints();
		Dimension fieldWidth = new Dimension(100, 20);
		
		cashPOLabel = new JLabel("CASH PAID OUTS");
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(20, 10, 0, 0);
		add(cashPOLabel, c);
		
//		c.insets = new Insets(20, 0, 0, 0);
		foodLabel = new JLabel("Food: ");
		c.gridy++;
		add(foodLabel, c);
		
		c.insets = new Insets(7, 10, 0, 0);
		restSupLabel = new JLabel("Restaurant Supplies: ");
		c.gridy++;
		add(restSupLabel, c);
		
		offSupLabel = new JLabel("Office Supplies: ");
		c.gridy++;
		add(offSupLabel, c);
		
		repMaintLabel = new JLabel("Repairs/Maintenance: ");
		c.gridy++;
		add(repMaintLabel, c);
		
		freightLabel = new JLabel("Freight: ");
		c.gridy++;
		add(freightLabel, c);
		
		foodField = new JTextField(7);
		foodField.setMinimumSize(fieldWidth);
		foodField.setHorizontalAlignment(JTextField.RIGHT);
		c.gridx = 1;
		c.gridy = 1;
		c.insets = new Insets(20, 15, 0, 0);
		add(foodField, c);
		
		c.insets = new Insets(7, 15, 0, 0);
		restSupField = new JTextField(7);
		restSupField.setMinimumSize(fieldWidth);
		restSupField.setHorizontalAlignment(JTextField.RIGHT);
		c.gridy++;
		add(restSupField, c);
		
		offSupField = new JTextField(7);
		offSupField.setMinimumSize(fieldWidth);
		offSupField.setHorizontalAlignment(JTextField.RIGHT);
		c.gridy++;
		add(offSupField, c);
		
		repMaintField = new JTextField(7);
		repMaintField.setMinimumSize(fieldWidth);
		repMaintField.setHorizontalAlignment(JTextField.RIGHT);
		c.gridy++;
		add(repMaintField, c);
		
		freightField = new JTextField(7);
		freightField.setMinimumSize(fieldWidth);
		freightField.setHorizontalAlignment(JTextField.RIGHT);
		c.gridy++;
//		c.weighty = 1.0;
		add(freightField, c);
		
		// TODO Add ability to pass ID of ShiftData record to the OtherPOPanel constructor
		c.insets = new Insets(10, 00, 0, 0);
		otherPOPanel = new OtherPOPanel();
		c.weighty = 1.0;
		c.gridx = 0;
		c.gridy++;
		c.gridwidth = 2;
		add(otherPOPanel, c);
	}
	
	public void load(ShiftData data) throws SQLException, ClassNotFoundException {
		DecimalFormat myFormat = new DecimalFormat("###0.00");
		
		if (data.getDate() != null) {
			foodField.setText(myFormat.format(data.getFood()));
			restSupField.setText(myFormat.format(data.getRestSupp()));
			offSupField.setText(myFormat.format(data.getOffSupp()));
			repMaintField.setText(myFormat.format(data.getRepMaint()));
			freightField.setText(myFormat.format(data.getFreight()));
		}
		
		this.otherPOPanel.load(data.getId());
	}
}
