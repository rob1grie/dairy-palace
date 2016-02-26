/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.shift;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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
public class ShiftFormRight extends JPanel {
	private JLabel totalCashPOsLabel;
	private JLabel endingPettyCashLabel;
	private JLabel creditCardsLabel;
	private JLabel depositLabel;
	private JLabel cashAccountedForLabel;
	private JLabel shiftSalesLabel;
	private JLabel overringsLabel;
	private JLabel netShiftSalesLabel;
	private JLabel beginningPettyCashLabel;
	private JLabel taxLabel;
	private JLabel discountsLabel;
	private JLabel ovtShortLabel;
	private JLabel cashToAccountForLabel;
	
	private JTextField totalCashPOsField;
	private JTextField endingPettyCashField;
	private JTextField creditCardsField;
	private JTextField depositField;
	private JTextField cashAccountedForField;
	private JTextField shiftSalesField;
	private JTextField overringsField;
	private JTextField netShiftSalesField;
	private JTextField beginningPettyCashField;
	private JTextField taxField;
	private JTextField discountsField;
	private JTextField ovtShortField;
	private JTextField cashToAccountForField;
	
	public ShiftFormRight() {
		
		layoutComponents();
		
		setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
//		setBorder(BorderFactory.createLineBorder(Color.yellow, 2));
	}
	
	private void layoutComponents(){
		setLayout(new GridBagLayout());
		setPreferredSize(new Dimension(375,200));
		GridBagConstraints c = new GridBagConstraints();
		
		//////// Layout labels //////////////
		
		totalCashPOsLabel = new JLabel("Total Cash Paid Outs:");
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(20, 0, 0, 10);
		add(totalCashPOsLabel, c);
				
		cashAccountedForLabel = new JLabel("Cash Accounted For:");
		c.gridy++;
		add(cashAccountedForLabel, c);
		
		shiftSalesLabel = new JLabel("Shift Sales:");
		c.gridy++;
		add(shiftSalesLabel, c);
		
		overringsLabel = new JLabel("Overrings: ");
		c.gridy++;
		add(overringsLabel, c);
		
		netShiftSalesLabel = new JLabel("Net Shift Sales:");
		c.gridy++;
		add(netShiftSalesLabel, c);
		
		taxLabel = new JLabel("Tax:");
		c.gridy++;
		add(taxLabel, c);
		
		discountsLabel = new JLabel("Discounts:");
		c.gridy++;
		add(discountsLabel, c);
		
		ovtShortLabel = new JLabel("Ovt/Short:");
		c.gridy++;
		add(ovtShortLabel, c);
		
		cashToAccountForLabel = new JLabel("Cash to Account For:");
		c.gridy++;
		c.weighty = 1.0;
		add(cashToAccountForLabel, c);
		
		//////// Layout fields //////////////
		
		totalCashPOsField = new JTextField(7);
		totalCashPOsField.setHorizontalAlignment(JTextField.RIGHT);
		c.gridx = 1;
		c.gridy = 0;
		c.weighty = 0.0;
		c.insets = new Insets(20, 10, 0, 0);
		add(totalCashPOsField, c);
		
		cashAccountedForField = new JTextField(7);
		cashAccountedForField.setHorizontalAlignment(JTextField.RIGHT);
		c.gridy++;
		add(cashAccountedForField, c);
		
		shiftSalesField = new JTextField(7);
		shiftSalesField.setHorizontalAlignment(JTextField.RIGHT);
		c.gridy++;
		add(shiftSalesField, c);
		
		overringsField = new JTextField(7);
		overringsField.setHorizontalAlignment(JTextField.RIGHT);
		c.gridy++;
		add(overringsField, c);
		
		netShiftSalesField = new JTextField(7);
		netShiftSalesField.setHorizontalAlignment(JTextField.RIGHT);
		c.gridy++;
		add(netShiftSalesField, c);
		
		taxField = new JTextField(7);
		taxField.setHorizontalAlignment(JTextField.RIGHT);
		c.gridy++;
		add(taxField, c);
		
		discountsField = new JTextField(7);
		discountsField.setHorizontalAlignment(JTextField.RIGHT);
		c.gridy++;
		add(discountsField, c);
		
		ovtShortField = new JTextField(7);
		ovtShortField.setHorizontalAlignment(JTextField.RIGHT);
		c.gridy++;
		add(ovtShortField, c);
		
		cashToAccountForField = new JTextField(7);
//		cashToAccountForField.setHorizontalAlignment(JTextField.RIGHT);
		c.gridy++;
		c.weighty = 1.0;
		add(cashToAccountForField, c);
	}
	
	public void load(ShiftData data) {
		// Load the right panel with data
		this.totalCashPOsField.setText(Float.toString(data.getTotalCashPaidOut()));
//		this.endingPettyCashField.setText(Float.toString(data.get));		endingPettyCash
//		this.creditCardsField.setText(Float.toString(data.getCredCards()));
//		this.depositField.setText(Float.toString(data.getd));			deposit
//		this.cashAccountedForField.setText(Float.toString(data.getc));	cashAccountedFor
//		this.shiftSalesField.setText(Float.toString(data.gets));			shiftSales
//		this.beginningPettyCashField.setText(Float.toString(data.));		beginningPettyCash
		this.taxField.setText(Float.toString(data.getzTx()));
		this.discountsField.setText(Float.toString(data.getDiscounts()));
//		this.ovtShortField.setText(Float.toString(data.get));				ovtShort
//		this.cashAccountedForField.setText(Float.toString(data.get));		cashAccountedFor
		
	}
	
}
