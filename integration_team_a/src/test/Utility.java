package test;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import edu.cs320.project.*;
import edu.cs320.project.InputGenerator;
import static org.junit.Assert.*;

public class Utility {

	private static SmartRobot rob = SmartRobot.getInstance();
	private static LoginDisplay loginDisplay;
	private static SearchMainDisplay searchMainDisplay;
	private static PatientRecordDisplay patientRecordDisplay;
	
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
		rob.mouseClick(searchMainDisplay.getNameTxtTest());
		rob.type(firstName);
		rob.mouseClick(searchMainDisplay.getLstNameTxtTest());
		rob.type(lastName);
		rob.mouseClick(searchMainDisplay.getDobFieldTest());
		rob.type(dob);
		rob.mouseClick(searchMainDisplay.getSearchButtonTest());
		sleep(3);
		rob.typeEnter();
		sleep(1);
	}
	
	/**
	 * Fills out the patient with the given demographics.
	 * If a parameter is null it will be skipped.
	 * @param patientID
	 * @param height
	 * @param weight
	 * @param address
	 */
	public static void fillOutPatientDemographics(String firstName, String lastName, String dob, String patientID, 
			String height, String weight, String gender){
		assertTrue(DisplayController.GetInstance().getCurrentDisplay() instanceof PatientRecordDisplay);
		patientRecordDisplay = (PatientRecordDisplay)DisplayController.GetInstance().getCurrentDisplay();
		PatientInfoDisplay patientInfoDisplay = (PatientInfoDisplay) patientRecordDisplay.getPatientInfoDisplayTest();
		if(firstName != null) {
			rob.mouseDoubleClick(patientInfoDisplay.getFirstField());
			rob.type(firstName);
		}
		
		if (lastName != null) {
			rob.mouseDoubleClick(patientInfoDisplay.getLastField());
			rob.type(lastName);
		}
		
		if (dob != null) {
			rob.mouseDoubleClick(patientInfoDisplay.getDobField());
			rob.type(dob);
		}
		
		if (patientID != null) {
			rob.mouseDoubleClick(patientInfoDisplay.getIdField());
			rob.type(patientID);
		}
		
		if (height != null) {
			rob.mouseDoubleClick(patientInfoDisplay.getHeightField());
			rob.type(height);
		}
		
		if (weight != null) {
			rob.mouseDoubleClick(patientInfoDisplay.getWeightField());
			rob.type(lastName);
		}
		
		if (gender != null) {
			int currentGender = patientInfoDisplay.getGenderDropDown().getSelectedIndex();
			if(gender.equals("female") && currentGender != 1){
				rob.mouseClick(patientInfoDisplay.getGenderDropDown());
				rob.typeDownArrow();
			}
			else if(gender.equals("male") && currentGender != 0){
				rob.mouseClick(patientInfoDisplay.getGenderDropDown());
				rob.typeUpArrow();
			}
		}
	}
	
	public static void fillOutPatientAllergy(String allergy, String reaction){
		assertTrue(DisplayController.GetInstance().getCurrentDisplay() instanceof PatientRecordDisplay);
	}
	
	public static void searchForNewPatient(String firstName, String lastName, String dob){
		searchForPatient(firstName,lastName,dob);
		rob.typeEnter();
	}
	
	/**
	 * Wait for the specified number of seconds before moving to next statement.
	 * @param seconds number of seconds to wait
	 */
	public static void sleep(int seconds){
		rob.delay(seconds * 1000);
	}
}
