package TestReader;

import java.util.regex.*;

public class TestCase {
	private boolean isSuccessful;
	private String className;
	private String testName;
	private String time;
	private String failureMessage;
	private String actual = "";
	private String expected = "";
	
	public TestCase(boolean isSuccessful, String className, String name, String time){
		this.setSuccessful(isSuccessful);
		this.setClassName(className);
		this.setTestName(name);
		this.setTime(time);
	}
	
	public String toString(){
		return "passed: " + isSuccessful + ", Test from class: " + className + ", test name: " + testName + 
		", time: " + time + ", Expected: " + expected + ", Actual: " + actual;
	}

	/**
	 * @param isSuccessful the isSuccessful to set
	 */
	public void setSuccessful(boolean isSuccessful) {
		this.isSuccessful = isSuccessful;
	}

	/**
	 * @return the isSuccessful
	 */
	public boolean isSuccessful() {
		return isSuccessful;
	}

	/**
	 * @param className the className to set
	 */
	public void setClassName(String className) {
		this.className = className;
	}

	/**
	 * @return the className
	 */
	public String getClassName() {
		return className;
	}

	/**
	 * @param testName the testName to set
	 */
	public void setTestName(String testName) {
		this.testName = testName;
	}

	/**
	 * @return the testName
	 */
	public String getTestName() {
		return testName;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}
	
	/**
	 * @param failureMessage the failureMessage to set
	 */
	public void setFailureMessage(String failureMessage){
		this.failureMessage = failureMessage;
	}
	
	public String getExpected(String failureMessage){
		Matcher regex = Pattern.compile("expected:<(.+?)>").matcher(failureMessage);
		regex.find();
			
		return regex.group(1);
	}
	
	public String getActual(String failureMessage){
		Matcher regex = Pattern.compile("but was:<(.+?)>").matcher(failureMessage);
		regex.find();
		
		return regex.group(1);
	}

	/**
	 * @param actual the actual to set
	 */
	public void setActual(String actual) {
		this.actual = actual;
	}

	/**
	 * @return the actual
	 */
	public String getActual() {
		return actual;
	}

	/**
	 * @param expected the expected to set
	 */
	public void setExpected(String expected) {
		this.expected = expected;
	}

	/**
	 * @return the expected
	 */
	public String getExpected() {
		return expected;
	}

}
