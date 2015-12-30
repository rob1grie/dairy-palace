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
import model.OtherPO;
import model.ShiftData;

/**
 *
 * @author Rob
 */
public class OtherPOTableModel extends AbstractTableModel {
	
	private List<OtherPO> data;
	
	private String[] colNames = {"Label", "Cost"};
	
	public OtherPOTableModel() {
		data = new LinkedList<>();
		// TODO test data
		data.add(new OtherPO(1, "Joe's gas", 15));
		data.add(new OtherPO(1, "Kim's taxi fare", 10));
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
		OtherPO otherPO = this.data.get(row);
		
		switch(col) {
			case 0:
				return otherPO.getLabel();
			case 1:
				return otherPO.getCost();
		}
		
		return null;
	}
	
	public void setData(List<OtherPO> data) {
		this.data = data;
	}
	
	public void load(int shiftID) throws SQLException, Exception {
		data.clear();
		
		String sql = "SELECT id FROM OTHER_PAID_OUTS WHERE shift_data_id = " + shiftID + " ORDER BY id;";
		
		try (ResultSet rs = Database.load(sql)) {
			while(rs.next()) {
				OtherPO otherPO = new OtherPO(rs.getInt("id"));
				data.add(otherPO);
			}
		}
	}
	
	public List<OtherPO> getOtherPO(int shiftID) throws SQLException, Exception {
		this.load(shiftID);
		return Collections.unmodifiableList(data);
	}
}
