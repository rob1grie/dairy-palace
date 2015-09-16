/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.shift;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

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
		
		endingPettyCashLabel = new JLabel("Ending Petty Cash:");
		c.gridy++;
		c.insets = new Insets(7, 0, 0, 10);
		add(endingPettyCashLabel, c);
		
		creditCardsLabel = new JLabel("Credit Cards:");
		c.gridy++;
		add(creditCardsLabel, c);
		
		depositLabel = new JLabel("Deposit:");
		c.gridy++;
		add(depositLabel, c);
		
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
		
		beginningPettyCashLabel = new JLabel("Beginning Petty Cash:");
		c.gridy++;
		add(beginningPettyCashLabel, c);
		
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
		c.gridx = 1;
		c.gridy = 0;
		c.weighty = 0.0;
		c.insets = new Insets(20, 10, 0, 0);
		add(totalCashPOsField, c);
		
		endingPettyCashField = new JTextField(7);
		c.gridy++;
		c.insets = new Insets(7, 10, 0, 0);
		add(endingPettyCashField, c);
		
		creditCardsField = new JTextField(7);
		c.gridy++;
		add(creditCardsField, c);
		
		depositField = new JTextField(7);
		c.gridy++;
		add(depositField, c);
		
		cashAccountedForField = new JTextField(7);
		c.gridy++;
		add(cashAccountedForField, c);
		
		shiftSalesField = new JTextField(7);
		c.gridy++;
		add(shiftSalesField, c);
		
		overringsField = new JTextField(7);
		c.gridy++;
		add(overringsField, c);
		
		netShiftSalesField = new JTextField(7);
		c.gridy++;
		add(netShiftSalesField, c);
		
		beginningPettyCashField = new JTextField(7);
		c.gridy++;
		add(beginningPettyCashField, c);
		
		taxField = new JTextField(7);
		c.gridy++;
		add(taxField, c);
		
		discountsField = new JTextField(7);
		c.gridy++;
		add(discountsField, c);
		
		ovtShortField = new JTextField(7);
		c.gridy++;
		add(ovtShortField, c);
		
		cashToAccountForField = new JTextField(7);
		c.gridy++;
		c.weighty = 1.0;
		add(cashToAccountForField, c);
	}
	
}
