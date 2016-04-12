/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.shift;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Rob
 */
public class ShiftToolbar extends JPanel implements ActionListener {
	private JButton formButton;
	private JButton tableButton;
	private JButton newButton;
	private JButton editButton;
	private JButton saveButton;
	private JButton cancelButton;
	
	private ShiftToolbarListener shiftToolbarListener;
	
	public ShiftToolbar() {
		setBorder(BorderFactory.createEtchedBorder());

		layoutToolbar();
	}

	public void setToolbarListener(ShiftToolbarListener listener) {
		this.shiftToolbarListener = listener;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton clicked = (JButton) e.getSource();
		String name = clicked.getName();

		shiftToolbarListener.viewSelected(name);
	}

	private void layoutToolbar() {
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LAST_LINE_END;
		c.weightx = 0.5;
		
		formButton = new JButton("Form View");
		tableButton = new JButton("Table View");
		Dimension buttonDim = tableButton.getPreferredSize();

		formButton.addActionListener(this);
		formButton.setName("Form");
		formButton.setPreferredSize(buttonDim);
		add(formButton, c);
		
		tableButton.addActionListener(this);
		tableButton.setName("Table");
		c.gridx++;
		add(tableButton, c);
		
		
	}
	
	public void toggleVisible() {
		// Toggles which button is visible, testing formButton for visibility
		if (this.formButton.isVisible()) {
			this.formButton.setVisible(false);
			this.tableButton.setVisible(true);
		}
		else {
			this.formButton.setVisible(true);
			this.tableButton.setVisible(false);
		}
	}
	
	public void toggleVisible(String buttonName) {
		// Overridden method to specifiy which button will be visible
		switch (buttonName) {
			case "Form":
				this.formButton.setVisible(true);
				this.tableButton.setVisible(false);
				break;
			case "Table":
				this.formButton.setVisible(false);
				this.tableButton.setVisible(true);
				break;
		}
	}
}
