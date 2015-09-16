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
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Rob
 */
public class OtherPOPanel extends JPanel {
	private JLabel otherPOLabel;
	private JButton addButton;
	private OtherPOTablePanel tablePanel;
	
	public OtherPOPanel() {
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		setPreferredSize(new Dimension(350,250));
		setMinimumSize(new Dimension(350,250));
		
//		setBorder(BorderFactory.createLineBorder(Color.cyan, 2));
		
		otherPOLabel = new JLabel("OTHER PAID OUTS");
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 0;
		c.gridy = 0;
		c.weighty = 0.0;
		add(otherPOLabel, c);
		
		addButton = new JButton("Add Other Paid Out");
		c.anchor = GridBagConstraints.FIRST_LINE_END;
		c.insets = new Insets(0, 30, 0, 0);
		c.gridx++;
		add(addButton, c);
		
		tablePanel = new OtherPOTablePanel();
		c.insets = new Insets(20, 0, 0, 0);
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 0;
		c.gridy++;
		c.gridwidth = 2;
		c.weighty = 1.0;
		add(tablePanel, c);
	}
}
