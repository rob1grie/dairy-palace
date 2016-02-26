/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.shift;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Database;
import model.ShiftData;

public class ShiftDataTableModel extends AbstractTableModel {

	private List<ShiftData> data;

	private final String[] colNames = {"Date", "Shift", "Total Paid Outs"};

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

		switch (col) {
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

	public void loadRange() throws SQLException, ClassNotFoundException, ParseException {
		ShiftTableRange range = ShiftTableRange.getInstance();
		
		String sql = "SELECT * FROM SHIFT_DATAS "
				+ "ORDER BY SHIFT_DATE DESC, SHIFT DESC "
				+ "OFFSET " + range.getOffset() + " ROWS FETCH NEXT " + range.getCount() + " ROWS ONLY";

		try (ResultSet rs = Database.load(sql)) {
			while (rs.next()) {
				ShiftData shiftData = new ShiftData(rs.getInt("id"));
				this.data.add(shiftData);
			}
		}

	}
}
