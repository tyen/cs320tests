package test.integration;

import java.util.TimeZone;

import edu.cs320.project.*;
import test.InputGenerator;
import junit.framework.TestCase;

/**
 * 
 * @author Tristan Peck
 *
 */
public class DateWrapperIntegrationTest extends TestCase {
	private static final int STRESS_ITERATIONS = 1000;
	
	/**
	 * @param name
	 */
	public DateWrapperIntegrationTest(String name) {
		super(name);
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * Stress test that tests that calling {@link DateWrapper#toString()} returns a
	 * String equivalent to the fields passed to
	 * {@link DateWrapper#DateWrapper(int, int, int)}
	 * using a {@link DateWrapper} with day light savings consideration disabled.
	 */
	public void test_Constructor_SimpleDate() {
		for (int i = 0; i < STRESS_ITERATIONS; i++) {
			int year = InputGenerator.randomDateYear();
			int month = InputGenerator.randomDateMonth();
			int day = InputGenerator.randomDateDay(year, month);
			
			DateWrapper date = new DateWrapper(year, month, day);
			assertNotNull(date);
			
			TimeZone timeZone = TimeZone.getTimeZone("GMT+00");
			assertNotNull(timeZone);
			assertTrue(!timeZone.useDaylightTime());
			
			date.setTimeZone(timeZone);
			
			String expectedStrDate = String.format("%04d-%02d-%02d 00:00:00",
					year, month, day);
			
			assertEquals(expectedStrDate, date.toString());
		}
	}

	/**
	 * Stress test that tests that calling {@link DateWrapper#toString()} returns a
	 * String equivalent to the fields passed to
	 * {@link DateWrapper#DateWrapper(int, int, int, int, int, int)}
	 * using a {@link DateWrapper} with day light savings consideration disabled.
	 */
	public void test_Constructor_DateAndTime() {
		for (int i = 0; i < STRESS_ITERATIONS; i++) {
			int year = InputGenerator.randomDateYear();
			int month = InputGenerator.randomDateMonth();
			int day = InputGenerator.randomDateDay(year, month);
			
			int hour = InputGenerator.randomDateHour();
			int minute = InputGenerator.randomDateMinute();
			int second = InputGenerator.randomDateSecond();
			
			DateWrapper date = new DateWrapper(year, month, day, hour, minute, second);
			assertNotNull(date);
			
			TimeZone timeZone = TimeZone.getTimeZone("GMT+00");
			assertNotNull(timeZone);
			assertTrue(!timeZone.useDaylightTime());
			
			date.setTimeZone(timeZone);
			
			String expectedStrDate = String.format("%04d-%02d-%02d %02d:%02d:%02d",
					year, month, day, hour, minute, second);
			
			assertEquals(expectedStrDate, date.toString());
		}
	}
	
	/**
	 * Stress test that tests that calling {@link DateWrapper#toString()} returns a
	 * String equivalent to the one passed to {@link DateWrapper#DateWrapper(String)}
	 * using a {@link DateWrapper} with day light savings consideration disabled.
	 */
	public void test_Constructor_FromString() {
		for (int i = 0; i < STRESS_ITERATIONS; i++) {
			String dateString = InputGenerator.randomDateString();
			
			DateWrapper date = new DateWrapper(dateString);
			assertNotNull(date);
			
			TimeZone timeZone = TimeZone.getTimeZone("GMT+00");
			assertNotNull(timeZone);
			assertTrue(!timeZone.useDaylightTime());
			
			date.setTimeZone(timeZone);
			
			assertEquals(dateString, date.toString());
		}
	}
	
	/**
	 * Stress test that tests that calling {@link DateWrapper#FromDisplayString(String)} returns a
	 * {@link DateWrapper} equivalent to the passed String after its daylight savings
	 * considerations are disabled.
	 */
	public void test_FromDisplayString() {
		for (int i = 0; i < STRESS_ITERATIONS; i++) {
			int year = InputGenerator.randomDateYear();
			int month = InputGenerator.randomDateMonth();
			int day = InputGenerator.randomDateDay(year, month);
			
			int hour = InputGenerator.randomDateHour();
			int minute = InputGenerator.randomDateMinute();
			int second = InputGenerator.randomDateSecond();
			
			DateWrapper date = DateWrapper.FromDisplayString(String.format(
				"%02d/%02d/%04d %02d:%02d:%02d",
				month, day, year, hour, minute, second));
			assertNotNull(date);
			
			TimeZone timeZone = TimeZone.getTimeZone("GMT+00");
			assertNotNull(timeZone);
			assertTrue(!timeZone.useDaylightTime());
			
			date.setTimeZone(timeZone);
			
			String strDate = String.format("%04d-%02d-%02d %02d:%02d:%02d",
				year, month, day, hour, minute, second);
			assertNotNull(strDate);
			
			assertEquals(strDate, date.toString());
		}
	}
	
	/**
	 * Stress test that tests that calling {@link DateWrapper#toString(boolean)} returns a
	 * String equivalent to the fields passed to {@link DateWrapper#DateWrapper(int, int, int, int, int, int)}
	 * after the {@link DateWrapper}'s day light savings considerations are disabled. Tested by passing both
	 * true and false to {@link DateWrapper#toString(boolean)}.
	 */
	public void test_toString() {
		for (int i = 0; i < STRESS_ITERATIONS; i++) {
			int year = InputGenerator.randomDateYear();
			int month = InputGenerator.randomDateMonth();
			int day = InputGenerator.randomDateDay(year, month);
			
			int hour = InputGenerator.randomDateHour();
			int minute = InputGenerator.randomDateMinute();
			int second = InputGenerator.randomDateSecond();

			DateWrapper date = new DateWrapper(year, month, day, hour, minute, second);
			assertNotNull(date);
			
			TimeZone timeZone = TimeZone.getTimeZone("GMT+00");
			assertNotNull(timeZone);
			assertTrue(!timeZone.useDaylightTime());
			
			date.setTimeZone(timeZone);

			String shortDate = String.format("%04d-%02d-%02d",
				year, month, day);
			assertNotNull(shortDate);
			
			String longDate = String.format("%04d-%02d-%02d %02d:%02d:%02d",
				year, month, day, hour, minute, second);
			assertNotNull(longDate);

			assertEquals(shortDate, date.toString(false));
			assertEquals(longDate, date.toString(true));
		}
	}
	
	/**
	 * Stress test that tests that calling {@link DateWrapper#ToDisplayString(boolean)} returns a
	 * String equivalent to the fields passed to {@link DateWrapper#DateWrapper(int, int, int, int, int, int)}
	 * after the {@link DateWrapper}'s day light savings considerations are disabled. Tested by passing both
	 * true and false to {@link DateWrapper#ToDisplayString(boolean)}.
	 */
	public void test_ToDisplayString() {
		for (int i = 0; i < STRESS_ITERATIONS; i++) {
			int year = InputGenerator.randomDateYear();
			int month = InputGenerator.randomDateMonth();
			int day = InputGenerator.randomDateDay(year, month);
			
			int hour = InputGenerator.randomDateHour();
			int minute = InputGenerator.randomDateMinute();
			int second = InputGenerator.randomDateSecond();

			DateWrapper date = new DateWrapper(year, month, day, hour, minute, second);
			assertNotNull(date);
			
			TimeZone timeZone = TimeZone.getTimeZone("GMT+00");
			assertNotNull(timeZone);
			assertTrue(!timeZone.useDaylightTime());
			
			date.setTimeZone(timeZone);

			String shortDate = String.format("%02d/%02d/%04d",
				month, day, year);
			assertNotNull(shortDate);
			
			String longDate = String.format("%02d/%02d/%04d %02d:%02d:%02d",
				month, day, year, hour, minute, second);
			assertNotNull(longDate);

			assertEquals(shortDate, date.ToDisplayString(false));
			assertEquals(longDate, date.ToDisplayString(true));
		}
	}
}
