/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.shift;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.sql.SQLException;
import java.text.ParseException;
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
	private ShiftData shiftData;
	private ShiftFormHeaderListener shiftFormHeaderListener;

	public ShiftFormPanel() {
		Dimension dim = getPreferredSize();
		dim.width = 375;
		setPreferredSize(dim);
		setMinimumSize(dim);

		this.shiftData = new ShiftData();

		Border innerBorder = BorderFactory.createTitledBorder("Shift Data");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

		layoutComponents();

	}

	public void layoutComponents() {
		setLayout(new BorderLayout());

		formHeader = new ShiftFormHeader();
		formHeader.setShiftFormHeaderListener((ShiftFormHeaderEvent e) -> {
			String name1 = e.getButtonName();
			switch (name1) {
				case "Next": {
					try {
						this.loadNext();
						this.load();
					} catch (SQLException | ClassNotFoundException | ParseException ex) {
						Logger.getLogger(ShiftFormPanel.class.getName()).log(Level.SEVERE, null, ex);
					}
				}
				break;
				case "Previous":
			{
				try {
					this.loadPrevious();
					this.load();
				} catch (SQLException | ClassNotFoundException | ParseException ex) {
					Logger.getLogger(ShiftFormPanel.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
					break;
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
		return shiftData;
	}

	public void setShiftData(ShiftData shiftData) {
		this.shiftData = shiftData;
	}

	public void load() throws SQLException, ClassNotFoundException {
		formHeader.load(this.shiftData);
		formLeft.load(this.shiftData);
		formRight.load(this.shiftData);
	}

	private void loadNext() throws SQLException, ClassNotFoundException, ParseException {
		if (this.shiftData.getNextId() > 0) {
			this.shiftData = new ShiftData(this.shiftData.getNextId());
		}
	}

	private void loadPrevious() throws SQLException, ClassNotFoundException, ParseException {
		if (this.shiftData.getPreviousId() > 0) {
			this.shiftData = new ShiftData(this.shiftData.getPreviousId());
		}
	}

	public void setShiftFormHeaderListener(ShiftFormHeaderListener listener) {
		this.shiftFormHeaderListener = listener;
	}
}
