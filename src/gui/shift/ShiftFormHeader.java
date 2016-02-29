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
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import model.ShiftData;
import model.User;
import org.apache.commons.lang3.text.WordUtils;

/**
 *
 * @author Rob
 */
public class ShiftFormHeader extends JPanel implements ActionListener {

	private JLabel dateLabel;
	private JLabel shiftLabel;
	private JLabel dowLabel;
	private JLabel enteredByLabel;
	private JTextField dateField;
	private JTextField shiftField;
	private JTextField dowField;
	private JTextField enteredByField;

	private ShiftFormHeaderToolbar shiftFormHeaderToolbar;

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

		shiftFormHeaderToolbar = new ShiftFormHeaderToolbar();
		c.gridx++;
		add(shiftFormHeaderToolbar, c);
		c.insets = insetField;
	}

	public void load(ShiftData data) throws SQLException, ClassNotFoundException {
		if (data.getDate() != null) {
			DateTimeFormatter format = DateTimeFormatter.ofPattern("MM-d-yyyy");
			dateField.setText(data.getDate().format(format));
			shiftField.setText(String.valueOf(data.getShift()));
			dowField.setText(WordUtils.capitalizeFully(data.getDate().getDayOfWeek().toString().substring(0, 3)));
			enteredByField.setText(User.getInitialsFromId(data.getUserId()));
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton clicked = (JButton) e.getSource();
		String label = clicked.getName();

//		shiftToolbarListener.viewSelected(label);
	}
}
