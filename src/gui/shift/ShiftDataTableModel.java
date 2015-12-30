/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.shift;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Database;
import model.ShiftData;


public class ShiftDataTableModel extends AbstractTableModel {

	private List<ShiftData> data;
	
	private String[] colNames = {"Date", "Shift", "Total Paid Outs"};
	
	public ShiftDataTableModel() {
		data = new LinkedList<>();
	}

	@Override
	public String getColumnName(int column) {
		return colNames[column];
	}
	
	@Override
	public int getRowCount() {
		return data.size();
	}

	@Override
	public int getColumnCount() {
		return colNames.length;
	}

	@Override
	public Object getValueAt(int row, int col) {
		ShiftData shiftData = this.data.get(row);
		
		switch(col) {
			case 0:
				return shiftData.getDate();
			case 1:
				return shiftData.getShift();
			case 2:
				return 0;
		}
		
		return null;
	}
	
	public void setData(List<ShiftData> data) {
		this.data = data;
	}
	
	public void load() throws SQLException, Exception {
		data.clear();

		// TODO provide means of setting start and count of rows
		String sql = "SELECT id FROM SHIFT_DATAS LIMIT 0, 25;";
		
		try (ResultSet rs = Database.load(sql)) {
			while(rs.next()) {
				ShiftData shiftData = new ShiftData(rs.getInt("id"));
				data.add(shiftData);
			}
		}
	}
	
	public List<ShiftData> getShiftData() throws SQLException, Exception {
		this.load();
		return Collections.unmodifiableList(data);
	}
}
