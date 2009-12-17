package test.system;

import edu.cs320.project.*;
import test.*;
import test.InputGenerator;

import java.awt.AWTException;

import junit.framework.TestCase;

public class SearchSystemTest extends TestCase {
	private SmartRobot rob;
	private LoginDisplay loginDisplay;
	private SearchMainDisplay searchMainDisplay;
	private PatientRecordDisplay patientRecordDisplay;
	private PatientInfoDisplay patientInfoDisplay;
	private SummaryDisplay summaryDisplay;
	
	
	public SearchSystemTest(String name) {
		super(name);
		rob = SmartRobot.getInstance();
	}
	

	protected void setUp() throws Exception {
		StorageWrapper.deleteFromClient("cs320.patient");
		super.setUp();
		DisplayController.main(null);
		loginDisplay = (LoginDisplay) DisplayController.GetInstance().getCurrentDisplay();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	/**
	 * logs in as pharmacist and searches for a new non-existing patient. The Patient won't be
	 * found and a message should be displayed saying so. After clicking out of the message,
	 * the pharmacist should be brought back to the main search display.
	 */

	public void testSearchForNonExistingPatientPharmacist(){
		assertTrue(DisplayController.GetInstance().getCurrentDisplay() instanceof LoginDisplay);
		//login as pharmacist
		Utility.login("jmolloy", "cs320");
		assertTrue(DisplayController.GetInstance().getCurrentDisplay() instanceof SearchMainDisplay);
		searchMainDisplay = (SearchMainDisplay)DisplayController.GetInstance().getCurrentDisplay();
		//search for a random patient not already in the database
		Utility.searchForPatient(InputGenerator.randomString(20), InputGenerator.randomString(20), InputGenerator.randomDateStringNoTime());
		rob.delay(3000);
		assertTrue(DisplayController.GetInstance().getCurrentDisplay() instanceof SearchMainDisplay);
		Utility.logoutFromSearchMainDisplay();
	}

	
	/**
	 * logs in as nurse and searches for a new non-existing patient. The Patient won't be
	 * found and should be saved to the database as a new patient. This is tested by going back
	 * and searching for the same patient again.
	 */
	public void testSearchForNonExistingPatientNurse(){
		String firstName = test.InputGenerator.randomString(20); // name of 20 characters
		String lastName = test.InputGenerator.randomString(20); //last name of 20 characters
		String dob = test.InputGenerator.randomDateStringNoTime();
		assertTrue(DisplayController.GetInstance().getCurrentDisplay() instanceof LoginDisplay);
		//login as nurse
		Utility.login("cs320", "cs320");
		assertTrue(DisplayController.GetInstance().getCurrentDisplay() instanceof SearchMainDisplay);
		searchMainDisplay = (SearchMainDisplay)DisplayController.GetInstance().getCurrentDisplay();
		//search for random patient not already in the database
		Utility.searchForPatient(firstName, lastName, dob);
		//clicks okay button on message displayed
		//int x = DisplayController.GetInstance().GetWindow().getX();
		//int y = DisplayController.GetInstance().GetWindow().getY();
		rob.typeEnter();
		//rob.delay(3000);
		assertTrue(DisplayController.GetInstance().getCurrentDisplay() instanceof PatientRecordDisplay);
		patientRecordDisplay = (PatientRecordDisplay)DisplayController.GetInstance().getCurrentDisplay();
		
		Utility.fillOutPatientDemographics(null, null, null, Integer.toString(InputGenerator.randomInt(100000)), Integer.toString(InputGenerator.randomInt(1000)) , Integer.toString(InputGenerator.randomInt(1000)) , "female");
		
		//click submit button
		rob.mouseClick(patientRecordDisplay.getSubmitButtonTest());
		rob.delay(2000);
		//click confirm button
		assertTrue(DisplayController.GetInstance().getCurrentDisplay() instanceof PatientRecordDisplay);
		rob.mouseClick(patientRecordDisplay.getSubmitButtonTest());
		rob.delay(1000);
		//click finish button on summary page
		assertTrue(DisplayController.GetInstance().getCurrentDisplay() instanceof SummaryDisplay);
		summaryDisplay = (SummaryDisplay)DisplayController.GetInstance().getCurrentDisplay();
		rob.mouseClick(summaryDisplay.getSearchButtonTest());
		rob.delay(2000);
		//system goes back to main search page
		assertTrue(DisplayController.GetInstance().getCurrentDisplay() instanceof SearchMainDisplay);
		searchMainDisplay = (SearchMainDisplay)DisplayController.GetInstance().getCurrentDisplay();
		//search for the newly created patient
		Utility.searchForPatient(firstName, lastName, dob);
		assertTrue(DisplayController.GetInstance().getCurrentDisplay() instanceof PatientRecordDisplay);
		rob.mouseClick(patientRecordDisplay.getLogoutButtonTest());
	}
	
	public void testSearchForEmptyPatient(){
		assertTrue(DisplayController.GetInstance().getCurrentDisplay() instanceof LoginDisplay);
		//login as pharmacist
		Utility.login("jmolloy", "cs320");
		assertTrue(DisplayController.GetInstance().getCurrentDisplay() instanceof SearchMainDisplay);
		searchMainDisplay = (SearchMainDisplay)DisplayController.GetInstance().getCurrentDisplay();
		//search for a random patient not already in the database
		Utility.searchForPatient("", "", "");
		//clicks the okay button on the message displayed
		assertTrue(DisplayController.GetInstance().getCurrentDisplay() instanceof SearchMainDisplay);
		assertEquals(searchMainDisplay.getNoneFoundLabel().getText(), "Please Fill Out All Fields");
		rob.mouseClick(searchMainDisplay.getLogoutButtonTest());
	}
	public void testSearchForNonExistingPatientWrongDate(){
		assertTrue(DisplayController.GetInstance().getCurrentDisplay() instanceof LoginDisplay);
		//login as pharmacist
		Utility.login("jmolloy", "cs320");
		assertTrue(DisplayController.GetInstance().getCurrentDisplay() instanceof SearchMainDisplay);
		searchMainDisplay = (SearchMainDisplay)DisplayController.GetInstance().getCurrentDisplay();
		//search for a random patient not already in the database
		Utility.searchForPatient(InputGenerator.randomString(20), InputGenerator.randomString(20), InputGenerator.randomString(20));
		rob.delay(3000);
		assertEquals(searchMainDisplay.getNoneFoundLabel().getText(), "Please Correctly Fill Out All Fields");
		assertTrue(DisplayController.GetInstance().getCurrentDisplay() instanceof SearchMainDisplay);
		Utility.logoutFromSearchMainDisplay();
	}

}
