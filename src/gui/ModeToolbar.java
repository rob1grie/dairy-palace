package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ModeToolbar extends JPanel implements ActionListener {

	private JButton shiftButton;
	private JButton registerButton;
	private JButton invoiceButton;
	
	private ModeToolbarListener toolbarListener;

	public ModeToolbar() {
		setBorder(BorderFactory.createEtchedBorder());

		layoutToolbar();
	}
	
	public void setToolbarListener(ModeToolbarListener listener) {
		this.toolbarListener = listener;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton clicked = (JButton) e.getSource();
		String label = clicked.getName();

		toolbarListener.modeSelected(label);
	}

	private void layoutToolbar() {
		setLayout(new FlowLayout(FlowLayout.LEFT));
		
		shiftButton = new JButton("Shift");
		registerButton = new JButton("Registers");
		invoiceButton = new JButton("Invoices");
		
		Dimension buttonDim = registerButton.getPreferredSize();

		shiftButton.addActionListener(this);
		shiftButton.setName("Shift");
		shiftButton.setPreferredSize(buttonDim);
		
		registerButton.addActionListener(this);
		registerButton.setName("Register");
		
		invoiceButton.addActionListener(this);
		invoiceButton.setName("Invoice");
		invoiceButton.setPreferredSize(buttonDim);

		add(shiftButton);
		add(registerButton);
		add(invoiceButton);
	}
}
