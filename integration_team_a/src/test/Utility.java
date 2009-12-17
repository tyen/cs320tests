package test;

import edu.cs320.project.*;
import edu.cs320.project.InputGenerator;
import static org.junit.Assert.*;

public class Utility {

	private static SmartRobot rob = SmartRobot.getInstance();
	private static LoginDisplay loginDisplay;
	private static SearchMainDisplay searchMainDisplay;
	
	/**
	 * Login to the system with specified username and password.
	 * Sleeps the specified number of seconds.
	 * @param username The username to type
	 * @param password The password to type
	 * @param seconds The number of seconds to sleep
	 */
	public static void login(String username, String password, int seconds){
		assertTrue(DisplayController.GetInstance().getCurrentDisplay() instanceof LoginDisplay);
		loginDisplay = (LoginDisplay)DisplayController.GetInstance().getCurrentDisplay();
		rob.mouseClick(loginDisplay.getUserNameFieldTest());
		rob.type("cs320");
		rob.mouseClick(loginDisplay.getPasswordFieldTest());
		rob.type("cs320");
		rob.mouseClick(loginDisplay.getSubmitButtonTest());
		sleep(seconds);
	}
	
	/**
	 * Login to the system with specified username and password.
	 * Sleeps 3 seconds.
	 * @param username The username to type
	 * @param password The password to type
	 */
	public static void login(String username, String password){
		login(username, password, 3);
	}
	
	/**
	 * Logout from the search main display.
	 * Sleeps one second.
	 */
	public static void logoutFromSearchMainDisplay(){
		assertTrue(DisplayController.GetInstance().getCurrentDisplay() instanceof SearchMainDisplay);
		searchMainDisplay = (SearchMainDisplay)DisplayController.GetInstance().getCurrentDisplay();
		rob.mouseClick(searchMainDisplay.getLogoutButtonTest());
		sleep(1);
	}
	
	/**
	 * searches for the Patient with the given name and date of birth
	 * @param firstName
	 * @param lastName
	 * @param dob
	 */
	public static void searchForPatient(String firstName, String lastName, String dob){
		assertTrue(DisplayController.GetInstance().getCurrentDisplay() instanceof SearchMainDisplay);
		searchMainDisplay = (SearchMainDisplay)DisplayController.GetInstance().getCurrentDisplay();
		rob.mouseClick(searchMainDisplay.getNameTxtTest());
		rob.type(firstName); //creates random name of at most 20 characters
		rob.mouseClick(searchMainDisplay.getLstNameTxtTest());
		rob.type(lastName); //creates random name of at most 20 characters
		rob.mouseClick(searchMainDisplay.getDobFieldTest());
		rob.type(dob);
		rob.mouseClick(searchMainDisplay.getSearchButtonTest());
		sleep(3);
	}
	
	public static void searchForNewPatient(String firstName, String lastName, String dob){
		searchForPatient(firstName, lastName, dob);
		rob.typeEnter();
		sleep(3);
	}	
	
	/**
	 * Wait for the specified number of seconds before moving to next statement.
	 * @param seconds number of seconds to wait
	 */
	public static void sleep(int seconds){
		rob.delay(seconds * 1000);
	}
}
