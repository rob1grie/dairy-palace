/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.shift;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author rob
 */
public class ShiftFormHeaderToolbar extends JPanel implements ActionListener {
	private JButton nextButton;
	private JButton prevButton;
	
	private ShiftFormHeaderToolbarListener shiftFormHeaderToolbarListener;
	
	public ShiftFormHeaderToolbar() {
		this.layoutToolbar();
	}
	
	public void setToolbarListener(ShiftFormHeaderToolbarListener listener) {
		this.shiftFormHeaderToolbarListener = listener;
	}

	private void layoutToolbar() {
		setLayout(new FlowLayout());

		prevButton = new JButton("<<");
		prevButton.addActionListener(this);
		prevButton.setName("Prev");
		add(prevButton);

		nextButton = new JButton(">>");
		nextButton.addActionListener(this);
		nextButton.setName("Next");
		add(nextButton);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton clicked = (JButton) e.getSource();
		String label = clicked.getName();

		if (label.equals("Next")) {
			this.shiftFormHeaderToolbarListener.nextRecord();
		}
		else {
			this.shiftFormHeaderToolbarListener.previousRecord();
		}
	}
}
