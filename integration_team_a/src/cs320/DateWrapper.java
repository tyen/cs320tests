package cs320;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

/**
 * Wrapper implementation representing a date and time accurate to
 * the millisecond. It inherits from GregorianCalendar, but adds
 * getters and setters that are less clunky to use. 
 * @author Tristan Peck
 *
 */
public class DateWrapper extends GregorianCalendar {
	/**
	 * Constructs a default DateWrapper using the system time.
	 */
	public DateWrapper() {
		super();
	}
	
	/**
	 * Constructs a DateWrapper with the given date. The time will be set
	 * to midnight at the beginning of the given date.
	 * @param Year The year AD.
	 * @param Month The month, where 1 is January, 2 is February, and so on
	 * up to 12 for December.
	 * @param Day The day of the month.
	 */
	public DateWrapper(int Year, int Month, int Day) {
		this(Year, Month, Day, 0, 0, 0, 0);
	}
	
	/**
	 * Constructs a DateWrapper with the given date and time.
	 * @param Year The year AD.
	 * @param Month The month, where 1 is January, 2 is February, and so on
	 * up to 12 for December.
	 * @param Day The day of the month.
	 * @param Hour The hour [0, 24) of the day on the 24-hour clock.
	 * @param Minute The minute [0, 60) of the hour.
	 * @param Second The second [0, 61] of the day. Values 60 and 61 are
	 * allowed for leap seconds.
	 * @param Millisecond The milliseconds [0, 1000).
	 */
	public DateWrapper(int Year, int Month, int Day,
			int Hour, int Minute, int Second, int Millisecond) {
		super(Year, Month - 1, Day, Hour, Minute, Second);
		super.set(Calendar.MILLISECOND, Millisecond);
	}
	
	/**
	 * Constructs a DateWrapper from the given String.
	 * @param DateString A String of the form "mm/dd/yyyy" or "mm/dd/yyyy HH:MM:ss.mmm". HH is
	 * on the 24-hour clock. "mm/dd/yyyy" sets the time to midnight at the beginning of
	 * the given day. This constructor is undefined for Strings that are not of either of
	 * these formats.
	 */
	public DateWrapper(String DateString) {
		//Call constructor first to dodge compile error
		super();

		StringTokenizer tokens = new StringTokenizer(DateString, "/ :.");
		
		//In the past, parsing "09" may have been interpreted
		//as an invalid octal and returned 0, but this is
		//no longer the case.
		
		this.SetMonth(Integer.parseInt(tokens.nextToken()));
		this.SetDay(Integer.parseInt(tokens.nextToken()));
		this.SetYear(Integer.parseInt(tokens.nextToken()));
		
		//if there are "HH:MM:ss.mmm" fields, parse them
		if(tokens.hasMoreTokens()) {
			this.SetHour(Integer.parseInt(tokens.nextToken()));
			this.SetMinute(Integer.parseInt(tokens.nextToken()));
			this.SetSecond(Integer.parseInt(tokens.nextToken()));
			this.SetMillisecond(Integer.parseInt(tokens.nextToken()));
		}
		//else set the time to midnight
		else {
			this.SetHour(0);
			this.SetMinute(0);
			this.SetSecond(0);
			this.SetMillisecond(0);
		}
	}

	/**
	 * 
	 * @return The year AD.
	 */
	public int GetYear() {
		return this.get(Calendar.YEAR);
	}

	/**
	 * 
	 * @return The month, where 1 is January, 2 is February, and so on
	 * up to 12 for December.
	 */
	public int GetMonth() {
		return this.get(Calendar.MONTH) + 1;
	}

	/**
	 * 
	 * @return The month in String format, where "January" is January and so on.
	 */
	public String GetMonthString() {
		switch(this.GetMonth()) {
		case 1:
			return "January";
		case 2:
			return "February";
		case 3:
			return "March";
		case 4:
			return "April";
		case 5:
			return "May";
		case 6:
			return "June";
		case 7:
			return "July";
		case 8:
			return "August";
		case 9:
			return "September";
		case 10:
			return "October";
		case 11:
			return "November";
		case 12:
			return "December";
		default:
			return "INVALID_MONTH";
		}
	}

	/**
	 * 
	 * @return The day of the month.
	 */
	public int GetDay() {
		return this.get(Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * 
	 * @return The hour [0, 24) of the day on the 24-hour clock.
	 */
	public int GetHour() {
		return this.get(Calendar.HOUR_OF_DAY);
	}
	
	/**
	 * 
	 * @return The minute [0, 60) of the hour.
	 */
	public int GetMinute() {
		return this.get(Calendar.MINUTE);
	}
	
	/**
	 * 
	 * @return The second [0, 61] of the day. Values 60 and 61 are
	 * allowed for leap seconds.
	 */
	public int GetSecond() {
		return this.get(Calendar.SECOND);
	}
	
	/**
	 * 
	 * @return The milliseconds [0, 1000).
	 */
	public int GetMillisecond() {
		return this.get(Calendar.MILLISECOND);
	}
	
	/**
	 * 
	 * @param Year The year AD.
	 */
	public void SetYear(int Year) {
		super.set(Calendar.YEAR, Year);
	}

	/**
	 * 
	 * @param Month The month, where 1 is January, 2 is February, and so on
	 * up to 12 for December.
	 */
	public void SetMonth(int Month) {
		this.set(Calendar.MONTH, Month - 1);
	}

	/**
	 * 
	 * @param Day The day of the month.
	 */
	public void SetDay(int Day) {
		this.set(Calendar.DAY_OF_MONTH, Day);
	}
	
	/**
	 * 
	 * @param Hour The hour [0, 24) of the day on the 24-hour clock.
	 */
	public void SetHour(int Hour) {
		this.set(Calendar.HOUR_OF_DAY, Hour);
	}
	
	/**
	 * 
	 * @param Minute The minute [0, 60) of the hour.
	 */
	public void SetMinute(int Minute) {
		this.set(Calendar.MINUTE, Minute);
	}
	
	/**
	 * 
	 * @param Second The second [0, 61] of the day. Values 60 and 61 are
	 * allowed for leap seconds.
	 */
	public void SetSecond(int Second) {
		this.set(Calendar.SECOND, Second);
	}
	
	/**
	 * 
	 * @param Millisecond The milliseconds [0, 1000).
	 */
	public void SetMillisecond(int Millisecond) {
		this.set(Calendar.MILLISECOND, Millisecond);
	}
	
	/**
	 * 
	 * @return This DateWrapper in the format "mm/dd/yyyy HH:MM:ss.mmm". HH is
	 * on the 24-hour clock.
	 */
	public String toString()
	{
		return this.toString(false, true);
	}
	
	/**
	 * Generates a String representing this DateWrapper based on the provided format.
	 * @param UseLongDate If true, the date portion will read as "day month, year". If false,
	 * it will read as "dd/mm/yyyy".
	 * @param IncludeTime If true, the time portion will be included in "HH:MM:ss.mmm" format,
	 * where HH is on the 24-hour clock. If false, only the date portion will be included.
	 * @return A String representation of this DateWrapper in the provided format.
	 */
	public String toString(boolean UseLongDate, boolean IncludeTime) {
		String date, time;
		
		if(UseLongDate) {
			date = this.GetDay()
				+ " " + this.GetMonthString()
				+ ", " + this.GetYear();
		}
		else {
			//Pad fields with 0s to keep String constant-width
			date = String.format("%02d/%02d/%d", this.GetMonth(),
					this.GetDay(), this.GetYear());
		}
		if(IncludeTime) {
			//Pad fields with 0s to keep String constant-width
			time = String.format(" %02d:%02d:%02d.%03d", this.GetHour(),
					this.GetMinute(), this.GetSecond(), this.GetMillisecond());
		}
		else {
			time = "";
		}
		
		return date + time;
	}
}
