/*
 * Define the contents of the top portion of the ShiftForm
 *	This header will contain the buttons to change from table to form	
 */
package gui.shift;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import model.ShiftData;
import model.User;
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
		c.gridx++;
		add(dateField, c);
		c.insets = insetField;
		
		shiftLabel = new JLabel("Shift:");
		c.gridx++;
		add(shiftLabel, c);
		c.insets = insetLabel;
		
		shiftField = new JTextField(2);
		c.gridx++;
		add(shiftField, c);
		c.insets = insetField;
		
		dowLabel = new JLabel("Day:");
		c.gridx++;
		add(dowLabel, c);
		c.insets = insetLabel;

		dowField = new JTextField(4);
//		dowField.setBackground(new Color(240,240,240));
//		dowField.setForeground(Color.WHITE);
		c.gridx++;
		add(dowField, c);
		c.insets = insetField;
		
		enteredByLabel = new JLabel("Entered by:");
		c.gridx++;
		add(enteredByLabel, c);
		c.insets = insetLabel;
		
		enteredByField = new JTextField(5);
//		enteredByField.setBackground(new Color(240,240,240));
//		enteredByField.setForeground(Color.WHITE);
		c.gridx++;
		add(enteredByField, c);
		c.insets = new Insets(0, 20, 0, 0);
		
		prevButton = new JButton("<<");
		c.anchor = GridBagConstraints.LINE_END;
		c.gridx++;
		add(prevButton, c);
		c.insets = new Insets(0, 0, 0, 0);
		
		nextButton = new JButton(">>");
		c.anchor = GridBagConstraints.LINE_END;
		c.gridx++;
		add(nextButton, c);
		
	}
	
	public void fillFields(ShiftData data) throws ParseException, Exception {
		dateField.setText(data.getDate());
		shiftField.setText(String.valueOf(data.getShift()));
		dowField.setText(Utils.getDayOfWeekString(Utils.getLocalDate(data.getDate())));
		String username = User.getUsernameFromId(data.getUserId());
		enteredByField.setText(User.getUsernameFromId(data.getUserId()));
	}
}
