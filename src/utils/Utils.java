package utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

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
		int slashIndex = name.lastIndexOf("\\");
		int pointIndex = name.lastIndexOf(".");

		// If pointIndex not found, file doesn't have an extension
		if (pointIndex == -1) {
			return null;
		}

		return name.substring(slashIndex + 1, pointIndex);
	}
	
	public static LocalTime getTimeFromString(String timeString) {
		// Receives timeString as hh:mm and returns a LocalTime object
		LocalTime time = null;
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
			time = LocalTime.parse(timeString, formatter);
			System.out.printf("%s%n", time);
			
		} catch (DateTimeParseException exc) {
			System.out.printf("%s is not parsable%n", timeString);
			throw exc;
		}
		
		return time;
	}

	public static LocalDate getDateFromString(String dateString, String format) throws ParseException {
		// Receives dateString as mm/dd/yyyy and returns a LocalDate object
		LocalDate date = null;
		try {
			DateTimeFormatter formatter
					= DateTimeFormatter.ofPattern(format);
			date = LocalDate.parse(dateString, formatter);
		} catch (DateTimeParseException exc) {
			System.out.printf("%s is not parsable!%n", dateString);
			throw exc;      // Rethrow the exception.
		}
		return date;
	}

	public static Date getDateTimeFromString(String dateString) throws ParseException {
		// Receives dateString as yyyy-MM-dd hh:mm am
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm a", Locale.ENGLISH);
		Date date = format.parse(dateString);

		return date;
	}

	public static String[] getDateTimeComponentsFromString(String dateString) {
		String[] components = dateString.split(" ");

		return components;
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
		return days[dow - 1];
	}

	public static String parseDateToString(Date date) throws ParseException {
		// Returns Date object from String MM/dd/yyyy
		DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		return format.format(date);
	}
	
	public static String parseDateTimeToString(Date date) throws ParseException {
		// Returns Date object from String MM/dd/yyyy hh:mm a
		DateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
		return format.format(date);
	}

	public static int countResultSetRows(ResultSet rs) throws SQLException {
		int size = 0;
		if (rs != null) {
			rs.beforeFirst();
			rs.last();
			size = rs.getRow();
			rs.beforeFirst();
		}

		return size;
	}
	
	public static boolean validateDateText(String dateText) {
		// Validates that dateText is in the format mm-dd-yyyy and represents a valid date
		
		String exp = "^(((((((0?[13578])|(1[02]))[\\.\\-/]?((0?[1-9])|([12]\\d)|(3[01])))|(((0?[469])|(11))[\\.\\-/]?((0?[1-9])|([12]\\d)|(30)))|((0?2)[\\.\\-/]?((0?[1-9])|(1\\d)|(2[0-8]))))[\\.\\-/]?(((19)|(20))?([\\d][\\d]))))|((0?2)[\\.\\-/]?(29)[\\.\\-/]?(((19)|(20))?(([02468][048])|([13579][26])))))$";
		if (!Pattern.matches(exp, dateText)) {
			return false;
		}

		return true;
	}
}
