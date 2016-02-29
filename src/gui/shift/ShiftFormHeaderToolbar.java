/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.shift;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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

	private void layoutComponents() {
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		prevButton = new JButton("<<");
		prevButton.setName("Prev");
		c.anchor = GridBagConstraints.LINE_END;
		c.gridx++;
		add(prevButton, c);
		c.insets = new Insets(0, 0, 0, 0);

		nextButton = new JButton(">>");
		nextButton.setName("Next");
		c.anchor = GridBagConstraints.LINE_END;
		c.gridx++;
		add(nextButton, c);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
}
