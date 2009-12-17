package test;

import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import edu.cs320.project.*;
import static org.junit.Assert.*;

public class Utility {

	private static SmartRobot rob = SmartRobot.getInstance();
	private static LoginDisplay loginDisplay;
	private static SearchMainDisplay searchMainDisplay;
	private static PatientRecordDisplay patientRecordDisplay;
	private static AddAllergyDisplay addAllergyDisplay;
	private static PatientInfoDisplay patientInfoDisplay;
	private static LinkedList<AllergyDisplay> allergyDisplays;
	private static AddDrugDisplay addDrugDisplay;
	private static LinkedList<DrugDisplay> drugDisplays;

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
		rob.type(username);
		rob.mouseClick(loginDisplay.getPasswordFieldTest());
		rob.type(password);
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
			String height, String weight, String gender, String address){
		assertTrue(DisplayController.GetInstance().getCurrentDisplay() instanceof PatientRecordDisplay);
		patientRecordDisplay = (PatientRecordDisplay)DisplayController.GetInstance().getCurrentDisplay();
		patientInfoDisplay = (PatientInfoDisplay) patientRecordDisplay.getPatientInfoDisplayTest();
		
		if(firstName != null) {
			rob.mouseTripleClick(patientInfoDisplay.getFirstField());
			rob.type(firstName);
		}

		if (lastName != null) {
			rob.mouseTripleClick(patientInfoDisplay.getLastField());
			rob.type(lastName);
		}

		if (dob != null) {
			rob.mouseTripleClick(patientInfoDisplay.getDobField());
			rob.type(dob);
		}

		if (patientID != null) {
			rob.mouseTripleClick(patientInfoDisplay.getIdField());
			rob.type(patientID);
		}

		if (height != null) {
			rob.mouseTripleClick(patientInfoDisplay.getHeightField());
			rob.type(height);
		}

		if (weight != null) {
			rob.mouseTripleClick(patientInfoDisplay.getWeightField());
			rob.type(weight);
		}

		if (gender != null) {
			if(gender.equals("female")){
				rob.mouseClick(patientInfoDisplay.getGenderDropDown());
				rob.typeDownArrow();
				rob.typeEnter();
			}
			else if(gender.equals("male")){
				rob.mouseClick(patientInfoDisplay.getGenderDropDown());
				rob.typeUpArrow();
				rob.typeEnter();
			}
		}
		
		if (address != null) {
			rob.mouseTripleClick(patientInfoDisplay.getAddressField());
			rob.type(address);
		}
	}

	public static void fillOutPatientAllergy(String allergy, String reaction){
		assertTrue(DisplayController.GetInstance().getCurrentDisplay() instanceof PatientRecordDisplay);
		
		patientRecordDisplay = (PatientRecordDisplay)DisplayController.GetInstance().getCurrentDisplay();
		addAllergyDisplay = patientRecordDisplay.getAddAllergyDisplay();
		
		rob.mouseClick(addAllergyDisplay.getAddAllergyButton());
		
		allergyDisplays = addAllergyDisplay.getAllergyDisplays();
		rob.scrollDown(patientRecordDisplay.getAllergyPanel().getVerticalScrollBar());
		
		AllergyDisplay lastAllergyDisplay = allergyDisplays.getLast();
		JTextField allergyField = lastAllergyDisplay.getAllergyFieldTest();
		JTextField reactionField = lastAllergyDisplay.getReactionFieldTest();
		
		rob.mouseClick(allergyField);
		rob.type(allergy);
		
		rob.mouseClick(reactionField);
		rob.type(reaction);
				
		JButton saveButton = lastAllergyDisplay.getSaveButtonTest();
		rob.mouseClick(saveButton);
		sleep(1);
	}
	
	/**
	 * Fills out a new patient drug
	 * @param allergy
	 * @param reaction
	 */
	public static void fillOutPatientDrug(String drugName, String dosage, String unit, String route,
			String frequency, String startDate, String stopDate, String prescriber, String reason){
		assertTrue(DisplayController.GetInstance().getCurrentDisplay() instanceof PatientRecordDisplay);
		
		patientRecordDisplay = (PatientRecordDisplay)DisplayController.GetInstance().getCurrentDisplay();
		addDrugDisplay = patientRecordDisplay.getAddDrugDisplay();
		
		rob.mouseClick(addDrugDisplay.getAddDrugButton());
		
		drugDisplays = addDrugDisplay.getDrugDisplays();
		rob.scrollDown(patientRecordDisplay.getDrugPanel().getVerticalScrollBar());
		
		DrugDisplay lastDrugDisplay = drugDisplays.getLast();
		
		rob.mouseClick(lastDrugDisplay.getDrugNameField());
		rob.type(drugName);
		
		rob.mouseClick(lastDrugDisplay.getDosageField());
		rob.type(dosage);
		
		rob.mouseClick(lastDrugDisplay.getDosageUnitField());
		rob.type(unit);
		
		rob.mouseClick(lastDrugDisplay.getRouteField());
		rob.type(route);
		
		rob.mouseClick(lastDrugDisplay.getFrequencyField());
		rob.type(frequency);
		
		rob.mouseClick(lastDrugDisplay.getStartDateField());
		rob.type(startDate);
		
		rob.mouseClick(lastDrugDisplay.getStopDateField());
		rob.type(stopDate);
		
		rob.mouseClick(lastDrugDisplay.getPrescriberField());
		rob.type(prescriber);
		
		rob.mouseClick(lastDrugDisplay.getReasonField());
		rob.type(reason);
				
		rob.mouseClick(lastDrugDisplay.getSaveButton());
		sleep(1);
	}
	
	public static void goBackFromPatientRecord() {
		patientRecordDisplay = (PatientRecordDisplay)DisplayController.GetInstance().getCurrentDisplay();

		rob.mouseClick(patientRecordDisplay.getGoBackButtonTest());
		sleep(1);
	}
	
	public static void logoutFromPatientRecord() {
		patientRecordDisplay = (PatientRecordDisplay)DisplayController.GetInstance().getCurrentDisplay();

		rob.mouseClick(patientRecordDisplay.getLogoutButtonTest());
		sleep(1);
	}

	/**
	 * Wait for the specified number of seconds before moving to next statement.
	 * @param seconds number of seconds to wait
	 */
	public static void sleep(int seconds){
		rob.delay(seconds * 1000);
	}

	public static void confirmPatientRecord() {
		patientRecordDisplay = (PatientRecordDisplay)DisplayController.GetInstance().getCurrentDisplay();

		rob.mouseClick(patientRecordDisplay.getSubmitButtonTest());
		sleep(1);
		rob.mouseClick(patientRecordDisplay.getSubmitButtonTest());
		sleep(1);
	}
}
