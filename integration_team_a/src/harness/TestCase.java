package harness;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.regex.*;

import harness.DBHandler.NoDBConnectivityException;

public class TestCase {
	private static DBHandler dbHandler = DBHandler.getInstance();
	
	private int harnessRunID;
	private boolean isSuccessful;
	private String className;
	private String testName;
	private String time;
	private String failureMessage;
	private String actual = "";
	private String expected = "";
	private String error;
	private int id;
	
	public TestCase(boolean isSuccessful, String className, String name, String time){
		this.setSuccessful(isSuccessful);
		this.setClassName(className);
		this.setTestName(name);
		this.setTime(time);
	}
	
	public String toString(){
		return "passed: " + isSuccessful + ", Test from class: " + className + ", test name: " + testName + 
		", time: " + time + ", Expected: " + expected + ", Actual: " + actual+ ", Error: " + error;
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
	
	/**
	 * @return The HashMap representation of the test case
	 */
	public HashMap<String, String> toHashMap(){
		HashMap<String, String> map = new HashMap<String, String>();
		
		map.put("test_id", Integer.toString(this.id));
		map.put("harness_run_id", Integer.toString(this.harnessRunID));
		map.put("pass", Boolean.toString(isSuccessful));
		//map.put("duration", "'" + this.time + "'");
		if(this.error != null)
			map.put("stacktrace", "'"+this.error.substring(0, this.error.length() < 512 ? this.error.length() : 512)+"'");
		if(this.failureMessage != null)
			map.put("log", "'"+this.failureMessage.substring(0, this.failureMessage.length() < 256 ? this.failureMessage.length() : 256)+"'");
		
		return map;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param error the error to set
	 */
	public void setError(String error) {
		this.error = error;
	}

	/**
	 * @return the error
	 */
	public String getError() {
		return error;
	}
	
	public boolean save() {
		if(!findTestID())
			return false;
		
		dbHandler.connect();
		
		try {
			dbHandler.insert("runsm2m", this.toHashMap());
			return true;
		}
		catch (NoDBConnectivityException e) {
			return false;
		}
	}
	
	private boolean findTestID() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("name", "'"+this.testName+"'");
		
		try {
			// Add the test if it doesn't exist in the DB and get the id
			if(dbHandler.exists("test", map)){
				ResultSet results = dbHandler.query("test", map);
				if(results.next()){
					this.id = results.getInt("test_id");
					return true;
				}
				
				return false;
			}
			else{
				dbHandler.insert("test", map);
				this.id = dbHandler.getLastGeneratedKey();
				
				return true;
			}
		} 
		catch (NoDBConnectivityException e) {
			return false;
		} 
		catch (SQLException e) {
			return false;
		}	
	}

	/**
	 * @param harnessRunID the harnessRunID to set
	 */
	public void setHarnessRunID(int harnessRunID) {
		this.harnessRunID = harnessRunID;
	}

	/**
	 * @return the harnessRunID
	 */
	public int getHarnessRunID() {
		return harnessRunID;
	}

}
