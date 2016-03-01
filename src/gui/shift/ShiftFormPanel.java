/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.shift;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.sql.SQLException;
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
	private ShiftData shiftData;

	public ShiftFormPanel() {
		Dimension dim = getPreferredSize();
		dim.width = 375;
		setPreferredSize(dim);
		setMinimumSize(dim);
		this.shiftData = null;
		
		Border innerBorder = BorderFactory.createTitledBorder("Shift Data");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

		layoutComponents();

	}

	public void layoutComponents() {
		setLayout(new BorderLayout());

		formHeader = new ShiftFormHeader();
		formHeader.setShiftFormHeaderListener(new ShiftFormHeaderListener() {
			@Override
			public void changeRecord(String direction) {
				switch (direction) {
					case "Previous":
						System.out.println("Previous");
						break;
					case "Next":
						System.out.println("Next");
						break;
				}
			}
		});
		
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
	
	public ShiftData getShiftData() {
		return this.shiftData;
	}
	
	public void setShiftData(ShiftData data) {
		this.shiftData = data;
	}

	public void load() throws SQLException, ClassNotFoundException {
		formHeader.load(this.shiftData);
		formLeft.load(this.shiftData);
		formRight.load(this.shiftData);
	}
}
