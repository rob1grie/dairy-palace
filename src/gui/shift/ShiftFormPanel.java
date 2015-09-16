/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.shift;

import controller.ShiftController;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;
import model.ShiftData;

/**
 *
 * @author Rob
 */
public class ShiftFormPanel extends JPanel {

	private ShiftFormHeader formHeader;
	private ShiftFormRight formRight;
	private ShiftFormLeft formLeft;
	private ShiftFormFooter formFooter;
	private JPanel formCenter;

	public ShiftFormPanel() {
		Dimension dim = getPreferredSize();
		dim.width = 375;
		setPreferredSize(dim);
		setMinimumSize(dim);
		ShiftData data = null;

		Border innerBorder = BorderFactory.createTitledBorder("Shift Data");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

		layoutComponents();

		if (ShiftController.getLastDataId() > 0) {
			ShiftController controller = new ShiftController();
			try {
				data = controller.getData(ShiftController.getLastDataId());
			} catch (Exception ex) {
				Logger.getLogger(ShiftFormPanel.class.getName()).log(Level.SEVERE, null, ex);
			}

			try {
				this.fillFields(data);
			} catch (Exception ex) {
				Logger.getLogger(ShiftFormPanel.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	public void layoutComponents() {
		setLayout(new BorderLayout());

		formHeader = new ShiftFormHeader();
		formRight = new ShiftFormRight();
		formLeft = new ShiftFormLeft();
		formFooter = new ShiftFormFooter();

		formCenter = new JPanel();
		formCenter.setLayout(new BorderLayout());
		formCenter.add(formLeft, BorderLayout.WEST);
		formCenter.add(formRight, BorderLayout.CENTER);

		add(formHeader, BorderLayout.NORTH);
		add(formCenter, BorderLayout.CENTER);
		add(formFooter, BorderLayout.SOUTH);
	}

	public void fillFields(ShiftData data) throws Exception {
		formHeader.fillFields(data);
	}
}
