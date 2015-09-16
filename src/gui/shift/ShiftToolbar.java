/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.shift;

import java.awt.Dimension;
import java.awt.FlowLayout;
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
		String label = clicked.getName();

		shiftToolbarListener.viewSelected(label);
		// TODO Hide selected button and show the other
	}

	private void layoutToolbar() {
		setLayout(new FlowLayout(FlowLayout.LEFT));
		
		formButton = new JButton("Form View");
		tableButton = new JButton("Table View");
		
		Dimension buttonDim = tableButton.getPreferredSize();

		formButton.addActionListener(this);
		formButton.setName("Form");
		formButton.setPreferredSize(buttonDim);
		
		tableButton.addActionListener(this);
		tableButton.setName("Table");
		
		add(formButton);
		add(tableButton);
		
	}
	
}
