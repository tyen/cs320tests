package test;

import java.awt.AWTException;
import edu.cs320.project.*;
import edu.cs320.project.InputGenerator;
import static org.junit.Assert.*;

public class Utility {

	private static SmartRobot rob;
	private static LoginDisplay loginDisplay;
	private static SearchMainDisplay searchMainDisplay;
	
	static{
		try {
			rob = new SmartRobot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
	
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
		rob.mouseClick(searchMainDisplay.getNameTxtTest());
		rob.type(firstName); //creates random name of at most 20 characters
		rob.mouseClick(searchMainDisplay.getLstNameTxtTest());
		rob.type(lastName); //creates random name of at most 20 characters
		rob.mouseClick(searchMainDisplay.getDobFieldTest());
		rob.type(dob);
		rob.mouseClick(searchMainDisplay.getSearchButtonTest());
	}
	
	/**
	 * Wait for the specified number of seconds before moving to next statement.
	 * @param seconds number of seconds to wait
	 */
	public static void sleep(int seconds){
		rob.delay(seconds * 1000);
	}
}
