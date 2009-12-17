package test.system;

import edu.cs320.project.*;
import edu.cs320.project.InputGenerator;
import test.*;

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
		try {
			rob = new SmartRobot();
		}
		catch (AWTException e) {
			e.printStackTrace();
		}
	}

	protected void setUp() throws Exception {
		super.setUp();
		DisplayController.main(null);
		loginDisplay = (LoginDisplay) DisplayController.GetInstance().getCurrentDisplay();
		StorageWrapper.deleteFromClient("cs320.patient");
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
		rob.mouseClick(loginDisplay.getUserNameFieldTest());
		rob.type("jmolloy");
		rob.mouseClick(loginDisplay.getPasswordFieldTest());
		rob.type("cs320");
		rob.mouseClick(loginDisplay.getSubmitButtonTest());
		rob.delay(3000);
		assertTrue(DisplayController.GetInstance().getCurrentDisplay() instanceof SearchMainDisplay);
		searchMainDisplay = (SearchMainDisplay)DisplayController.GetInstance().getCurrentDisplay();
		//search for a random patient not already in the database
		Utility.searchForPatient(InputGenerator.randomString(20), InputGenerator.randomString(20), InputGenerator.randomDateStringNoTime());
		rob.delay(3000);
		//clicks the okay button on the message displayed
		int x = DisplayController.GetInstance().GetWindow().getX();
		int y = DisplayController.GetInstance().GetWindow().getY();
		rob.mouseClick(x+600, y+350);
		rob.delay(3000);
		assertTrue(DisplayController.GetInstance().getCurrentDisplay() instanceof SearchMainDisplay);
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
		rob.mouseClick(loginDisplay.getUserNameFieldTest());
		rob.type("cs320");
		rob.mouseClick(loginDisplay.getPasswordFieldTest());
		rob.type("cs320");
		rob.mouseClick(loginDisplay.getSubmitButtonTest());
		rob.delay(3000);
		assertTrue(DisplayController.GetInstance().getCurrentDisplay() instanceof SearchMainDisplay);
		searchMainDisplay = (SearchMainDisplay)DisplayController.GetInstance().getCurrentDisplay();
		//search for random patient not already in the database
		rob.mouseClick(searchMainDisplay.getNameTxtTest());
		rob.type(firstName); 
		rob.mouseClick(searchMainDisplay.getLstNameTxtTest());
		rob.type(lastName); 
		rob.mouseClick(searchMainDisplay.getDobFieldTest());
		rob.type(dob);
		rob.mouseClick(searchMainDisplay.getSearchButtonTest());
		rob.delay(3000);
		//clicks okay button on message displayed
		int x = DisplayController.GetInstance().GetWindow().getX();
		int y = DisplayController.GetInstance().GetWindow().getY();
		rob.mouseClick(x+570, y+350);
		rob.delay(3000);
		assertTrue(DisplayController.GetInstance().getCurrentDisplay() instanceof PatientRecordDisplay);
		patientRecordDisplay = (PatientRecordDisplay)DisplayController.GetInstance().getCurrentDisplay();
		//weight
		rob.mouseClick(x+300, y+110);
		rob.type(Integer.toString(test.InputGenerator.randomInt(1000)));
		//height
		rob.mouseClick(x+250, y+110);
		rob.type(Integer.toString(test.InputGenerator.randomInt(1000)));
		//patientID
		rob.mouseClick(470,70);
		rob.type(Integer.toString(InputGenerator.randomInt(100000)));
		
		//click submit button
		rob.mouseClick(patientRecordDisplay.getSubmitButtonTest());
		rob.delay(3000);
		//click confirm button
		assertTrue(DisplayController.GetInstance().getCurrentDisplay() instanceof PatientRecordDisplay);
		rob.mouseClick(patientRecordDisplay.getSubmitButtonTest());
		rob.delay(3000);
		//click finish button on summary page
		assertTrue(DisplayController.GetInstance().getCurrentDisplay() instanceof SummaryDisplay);
		summaryDisplay = (SummaryDisplay)DisplayController.GetInstance().getCurrentDisplay();
		rob.mouseClick(summaryDisplay.getSearchButtonTest());
		rob.delay(3000);
		//system goes back to main search page
		assertTrue(DisplayController.GetInstance().getCurrentDisplay() instanceof SearchMainDisplay);
		searchMainDisplay = (SearchMainDisplay)DisplayController.GetInstance().getCurrentDisplay();
		//search for the newly created patient
		rob.mouseClick(searchMainDisplay.getNameTxtTest());
		rob.type(firstName); 
		rob.mouseClick(searchMainDisplay.getLstNameTxtTest());
		rob.type(lastName); 
		rob.mouseClick(searchMainDisplay.getDobFieldTest());
		rob.type(dob);
		rob.mouseClick(searchMainDisplay.getSearchButtonTest());
		rob.delay(3000);
	}

}
