/*
 * Define the contents of the top portion of the ShiftForm
 *	This header will contain the buttons to change from table to form	
 */
package gui.shift;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import model.Database;
import model.ShiftData;
import model.User;
import org.apache.commons.lang3.text.WordUtils;
import utils.Utils;

/**
 *
 * @author Rob
 */
public class ShiftFormHeader extends JPanel {

	private JLabel dateLabel;
	private JLabel shiftLabel;
	private JLabel dowLabel;
	private JLabel enteredByLabel;
	private JTextField dateField;
	private JTextField shiftField;
	private JTextField dowField;
	private JTextField enteredByField;

	private JButton nextButton;
	private JButton prevButton;
	private JButton gotoButton;

	private ShiftFormHeaderListener shiftFormHeaderListener;

	public ShiftFormHeader() {
		setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));

		layoutComponents();
	}

	private void layoutComponents() {
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		Insets insetLabel = new Insets(0, 5, 0, 0);
		Insets insetField = new Insets(0, 10, 0, 0);

		dateLabel = new JLabel("Date:");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		add(dateLabel, c);
		c.insets = insetLabel;

		dateField = new JTextField(8);
		dateField.setHorizontalAlignment(JTextField.CENTER);
		c.gridx++;
		add(dateField, c);
		c.insets = insetField;

		shiftLabel = new JLabel("Shift:");
		c.gridx++;
		add(shiftLabel, c);
		c.insets = insetLabel;

		shiftField = new JTextField(2);
		shiftField.setHorizontalAlignment(JTextField.CENTER);
		c.gridx++;
		add(shiftField, c);
		c.insets = insetField;

		dowLabel = new JLabel("Day:");
		c.gridx++;
		add(dowLabel, c);
		c.insets = insetLabel;

		dowField = new JTextField(4);
		dowField.setHorizontalAlignment(JTextField.CENTER);
		c.gridx++;
		add(dowField, c);
		c.insets = insetField;

		enteredByLabel = new JLabel("Entered by:");
		c.gridx++;
		add(enteredByLabel, c);
		c.insets = insetLabel;

		enteredByField = new JTextField(5);
		enteredByField.setHorizontalAlignment(JTextField.CENTER);
		c.gridx++;
		add(enteredByField, c);
		c.insets = new Insets(0, 20, 0, 0);

		prevButton = new JButton("<<");
		prevButton.setName("Previous");
		prevButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ShiftFormHeaderEvent ev = new ShiftFormHeaderEvent(prevButton, "Previous");
				if (shiftFormHeaderListener != null) {
					shiftFormHeaderListener.shiftFormHeaderEventOccurred(ev);
				}
			}
		});
		c.gridx++;
		add(prevButton, c);

		nextButton = new JButton(">>");
		nextButton.setName("Next");
		nextButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ShiftFormHeaderEvent ev = new ShiftFormHeaderEvent(nextButton, "Next");
				if (shiftFormHeaderListener != null) {
					shiftFormHeaderListener.shiftFormHeaderEventOccurred(ev);
				}
			}
		});
		c.gridx++;
		add(nextButton, c);

		gotoButton = new JButton("GoTo");
		gotoButton.setName("GoTo");
		gotoButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ShiftFormHeaderEvent ev = new ShiftFormHeaderEvent(gotoButton, "GoTo");
				if (shiftFormHeaderListener != null) {
					shiftFormHeaderListener.shiftFormHeaderEventOccurred(ev);
				}				
			}
		});
		c.gridx++;
		add(gotoButton, c);

	}
	
	public String getDate() {
		return this.dateField.getText();
	}
	
	public String getShift() {
		return this.shiftField.getText();
	}

	public void setShiftFormHeaderListener(ShiftFormHeaderListener listener) {
		this.shiftFormHeaderListener = listener;
	}

	public void load(ShiftData data) throws SQLException, ClassNotFoundException {
		if (data.getDate() != null) {
			DateTimeFormatter format = DateTimeFormatter.ofPattern("MM-d-yyyy");
			dateField.setText(data.getDate().format(format));
			shiftField.setText(String.valueOf(data.getShift()));
			dowField.setText(WordUtils.capitalizeFully(data.getDate().getDayOfWeek().toString().substring(0, 3)));
			enteredByField.setText(User.getInitialsFromId(data.getUserId()));

			// ShiftData sets previousId and nextId to -1 if no previous or next records are found
			if (data.getPreviousId() == 0) {
				this.prevButton.setEnabled(false);
			} else {
				this.prevButton.setEnabled(true);
			}

			if (data.getNextId() == 0) {
				this.nextButton.setEnabled(false);
			} else {
				this.nextButton.setEnabled(true);
			}
		}
	}

}
