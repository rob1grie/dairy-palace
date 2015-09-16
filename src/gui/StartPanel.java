/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.Border;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 *
 * @author Rob
 */
public final class StartPanel extends JPanel {
	private final JTextPane welcomePane;
	
	public StartPanel() {
		Dimension dim = getPreferredSize();
		dim.width = 375;
		setPreferredSize(dim);
		setMinimumSize(dim);
		
		// TODO Set properties for welcomePane
		welcomePane = new JTextPane();
		StyledDocument doc = welcomePane.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		welcomePane.setText("Welcome to the DairyBooks v3 Program\n\nYou are logged in");
		// welcomeMessage = new JLabel("Welcome to the DairyBooks v3 Program");
		
		Border innerBorder = BorderFactory.createTitledBorder("Welcome");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		layoutComponents();
	}
	
	public void layoutComponents() {
		setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();

		
		////// First row //////////////
		gc.gridy = 0;
		
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 5);
		add(welcomePane, gc);
		// add(welcomeMessage, gc);
		
	}
}
