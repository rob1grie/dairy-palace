/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.shift;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.sql.SQLException;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import model.OtherPO;

/**
 *
 * @author Rob
 */
public class OtherPOTablePanel extends JPanel {
	private JTable table;
	private OtherPOTableModel tableModel;
	private JPopupMenu popup;
	private OtherPOTableListener tableListener;
	private JScrollPane scrollPane;
	
	public OtherPOTablePanel() {
		tableModel = new OtherPOTableModel();
		table = new JTable(tableModel);
		scrollPane = new JScrollPane(table);
		scrollPane.setBorder(BorderFactory.createLineBorder(Color.yellow, 2));
		scrollPane.setPreferredSize(new Dimension(300, 100));
		scrollPane.setMinimumSize(new Dimension(300, 100));
		
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createLineBorder(Color.blue, 2));
		
//		setPreferredSize(new Dimension(300, 150));
		
		add(scrollPane,	BorderLayout.BEFORE_FIRST_LINE);
	}
	
	public void setData(List<OtherPO> data) {
		tableModel.setData(data);
	}
	
	public void getTableModelData(int shiftID) throws SQLException, ClassNotFoundException {
		this.tableModel.load(shiftID);
	}
	
	public void refresh() {
		tableModel.fireTableChanged(null);
	}

	public void setShiftDataTableListener(OtherPOTableListener listener) {
		this.tableListener = listener;
	}
	
	public void load(int shiftId) throws Exception {
		this.tableModel.load(shiftId);
	}
		
}
