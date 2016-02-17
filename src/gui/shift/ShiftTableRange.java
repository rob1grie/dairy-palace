/*
 Maintains a singleton which contains the range of rows to select for a ShiftTableModel
 */
package gui.shift;

/**
 *
 * @author rob
 */
public class ShiftTableRange {

	private int offset;
	private int count;
	private static ShiftTableRange instance = null;

	private ShiftTableRange() {
		// Exists only to defeat instantiation.
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	public static ShiftTableRange getInstance() {
		if (instance == null) {
			instance = new ShiftTableRange();
			instance.offset = 0;
			instance.count = 25;
			// TODO Set count according to height of window and save in DairySettings
		}
		return instance;
	}
}
