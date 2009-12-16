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
	private int time;
	private String failureMessage;
	private String actual = "";
	private String expected = "";
	private String error;
	private int testID;
	private String testType;
	private int runID;
	private int projectID;
	
	public TestCase(boolean isSuccessful, String className, String name, String time){
		this.setSuccessful(isSuccessful);
		this.setClassName(className);
		this.setTestName(name);
		this.setTime(time);
	}
	
	public void setProjectID() {
		if(this.testType.equals("unit"))
			this.projectID = 8;
		else if(this.testType.equals("integration"))
			this.projectID = 7;
		else // system test
			this.projectID = 2;
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
		this.time = (int)(Float.parseFloat(time) * 1000);
	}

	/**
	 * @return the time
	 */
	public int getTime() {
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
		
		map.put("test_id", Integer.toString(this.testID));
		map.put("harness_run_id", Integer.toString(this.harnessRunID));
		map.put("pass", Boolean.toString(isSuccessful));
		map.put("duration", Integer.toString(this.time));
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
		this.testID = id;
	}
	
	/**
	 * @return the id
	 */
	public int getId() {
		return testID;
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
			this.setRunID(dbHandler.getLastGeneratedKey());
			this.saveTestType();
			
			return true;
		}
		catch (NoDBConnectivityException e) {
			return false;
		}
	}
	
	private boolean findTestID() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("name", "'"+this.testName+"'");
		map.put("project_id", Integer.toString(this.projectID));
		
		try {
			// Add the test if it doesn't exist in the DB and get the id
			if(dbHandler.exists("test", map)){
				ResultSet results = dbHandler.query("test", map);
				if(results.next()){
					this.testID = results.getInt("test_id");
					return true;
				}
				
				return false;
			}
			else{
				dbHandler.insert("test", map);
				this.testID = dbHandler.getLastGeneratedKey();
				
				return true;
			}
		} 
		catch (NoDBConnectivityException e) {
			e.printStackTrace();
			return false;
		} 
		catch (SQLException e) {
			e.printStackTrace();
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

	public void setTestType(String testType) {
		this.testType = testType;
	}
	
	public String getTestType() {
		return this.testType;
	}

	/**
	 * @param runID the runID to set
	 */
	public void setRunID(int runID) {
		this.runID = runID;
	}

	/**
	 * @return the runID
	 */
	public int getRunID() {
		return runID;
	}
	
	private boolean saveAsIntegrationTest(){
		try {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("test_id", Integer.toString(this.runID));
		
			dbHandler.insert("integrationtest", map);
			
			return true;
		} catch (NoDBConnectivityException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean saveAsUnitTest(){
		try {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("test_id", Integer.toString(this.runID));
		
			dbHandler.insert("unittest", map);
			
			return true;
		} catch (NoDBConnectivityException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @param projectID the projectID to set
	 */
	public void setProjectID(int projectID) {
		this.projectID = projectID;
	}

	/**
	 * @return the projectID
	 */
	public int getProjectID() {
		return projectID;
	}
	
	public void saveTestType(){
		if(this.testType.equals("integration"))
			this.saveAsIntegrationTest();
		else if(this.testType.equals("unit"))
			this.saveAsUnitTest();
	}

}
