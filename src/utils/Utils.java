package utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Utils {

	public static String getFileExtension(String name) {
		int pointIndex = name.lastIndexOf(".");

		if (pointIndex == -1) {
			return null;
		}

		if (pointIndex == name.length() - 1) {
			return null;
		}

		return name.substring(pointIndex + 1, name.length());
	}

	public static String getFileName(String name) {
		// R:\Dropbox\Dairy Palace program\CSV data\EMPLOYEE
		int slashIndex = name.lastIndexOf("\\");
		int pointIndex = name.lastIndexOf(".");

		// If pointIndex not found, file doesn't have an extension
		if (pointIndex == -1) {
			return null;
		}

		return name.substring(slashIndex + 1, pointIndex);
	}

	public static Date getLocalDate(String dateString) throws ParseException {
		// Receives dateString as mm/dd/yyyy and returns a Date object
		Object date1 = new SimpleDateFormat("MM/dd/yyyy").parseObject(dateString);

		return (Date) date1;
	}

	public static String getLocalDateTime(String dateString) throws ParseException {
		// Receives dateString as mm/dd/yyyy hh:mm am
		DateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm a", Locale.ENGLISH);
		Date date = format.parse(dateString);
		return format.format(date);
	}
	
	public static int getDayOfWeek(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		
		return calendar.get(Calendar.DAY_OF_WEEK);
	}
	
	public static String getDayOfWeekString(Date date) {
		int dow = Utils.getDayOfWeek(date);

		String[] days = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
		
		// dow is 1-based, days is 0-based
		return days[dow-1];
	}
}
