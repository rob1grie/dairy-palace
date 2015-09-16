/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.shift;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import model.ShiftData;

/**
 *
 * @author Rob
 */
public class ShiftTablePanel extends JPanel {
	private JTable table;
	private ShiftDataTableModel tableModel;
	private JPopupMenu popup;
	private ShiftDataTableListener tableListener;
	
	public ShiftTablePanel() {
		tableModel = new ShiftDataTableModel();
		table = new JTable(tableModel);
		popup = new JPopupMenu();
		
		JMenuItem viewItem = new JMenuItem("View Details");
		popup.add(viewItem);
		
		viewItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				
				if (tableListener != null) {
					System.out.println("View details of row " + row);
				}
			}
		
		});
		
		setLayout(new BorderLayout());
		
		add(new JScrollPane(table), BorderLayout.CENTER);
	}
	
	private void loadRange(int start, int end) {
		
	}
	
	public void setData(List<ShiftData> data) {
		tableModel.setData(data);
	}
	
	public void getTableModelData() throws SQLException, Exception {
		this.tableModel.load();
	}
	
	public void refresh() {
		tableModel.fireTableChanged(null);
	}

	public void setShiftDataTableListener(ShiftDataTableListener listener) {
		this.tableListener = listener;
	}
	
}
