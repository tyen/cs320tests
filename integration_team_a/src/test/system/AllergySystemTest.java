package test.system;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JScrollBar;
import javax.swing.JTextField;
import test.InputGenerator;
import test.SmartRobot;
import test.Utility;
import edu.cs320.project.*;
import junit.framework.TestCase;
import static org.junit.Assert.*;

public class AllergySystemTest extends TestCase {
	SmartRobot rob = SmartRobot.getInstance();
	PatientRecordDisplay patientRecordDisplay = null;
	AddAllergyDisplay addAllergyDisplay = null;
	SummaryDisplay summaryDisplay = null;
	LinkedList<AllergyDisplay> allergyDisplays = null;
	
	static {
		DisplayController.main(null);
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("username", "cs320");
		map.put("password", Login.GetMD5("cs320"));
		
		if(!StorageWrapper.Exist("cs320.user", map)){
			map.put("username", "nurse");
			StorageWrapper.Save("cs320.user", map);
		}
		
		StorageWrapper.deleteFromClient("cs320.patient");
		StorageWrapper.deleteFromClient("cs320.allergy");
	}
	
	public AllergySystemTest(String name) {
		super(name);
	}

	public void setUp() throws Exception {
		super.setUp();
		gotoPatientRecordDisplay();
	}
	
	private void gotoPatientRecordDisplay() {
		if (DisplayController.GetInstance().getCurrentDisplay() instanceof LoginDisplay)
			Utility.login("cs320", "cs320", 4);
		
		if (DisplayController.GetInstance().getCurrentDisplay() instanceof SearchMainDisplay)
			Utility.searchForPatient("Timothy", "Yen", "3-8-89");
		
		if (patientRecordDisplay == null) {
			patientRecordDisplay = (PatientRecordDisplay) DisplayController.GetInstance().getCurrentDisplay();
			assertTrue(patientRecordDisplay instanceof PatientRecordDisplay);
		}

		if (addAllergyDisplay == null) {
			addAllergyDisplay = patientRecordDisplay.getAddAllergyDisplay();
			assertTrue(addAllergyDisplay instanceof AddAllergyDisplay);
		}
		
		if (allergyDisplays == null)
			allergyDisplays = addAllergyDisplay.getAllergyDisplays();
	}

	public void tearDown() throws Exception {
		super.tearDown();
	}
	
	private String rep(int i, char c) {
		String ret = "";
		for (int j = 0; j < i; ++j)
			ret += Character.toString(c);
		return ret;
	}
	
	
	// Add 10 random allergies
	public void testAllergy1() {
		HashSet<String> ars = new HashSet<String>();
		int iterations = 5;
		for (int i = 0; i < iterations; i++) {
			String allergy = InputGenerator.randomMinString(10);
			String reaction = InputGenerator.randomMinString(10);
			rob.mouseClick(addAllergyDisplay.getAddAllergyButton());
			
			JScrollBar scrollBar = patientRecordDisplay.getAllergyPanel().getVerticalScrollBar();
			rob.scrollDown(scrollBar);
			
			AllergyDisplay lastAllergyDisplay = allergyDisplays.getLast();
			JTextField allergyField = lastAllergyDisplay.getAllergyFieldTest();
			JTextField reactionField = lastAllergyDisplay.getReactionFieldTest();
			
			rob.mouseClick(allergyField);
			rob.type(allergy);
			rob.delay(100);
			allergy = allergyField.getText();
			
			rob.mouseClick(reactionField);
			rob.delay(100);
			rob.type(reaction);
			reaction = reactionField.getText();
			
			ars.add(allergy + ":" + reaction);
			
			JButton saveButton = lastAllergyDisplay.getSaveButtonTest();
			rob.mouseClick(saveButton);
		}

		rob.mouseClick(patientRecordDisplay.getSubmitButton());
		Utility.sleep(5);
		rob.mouseClick(patientRecordDisplay.getSubmitButton());
		Utility.sleep(5);
		
		summaryDisplay = (SummaryDisplay) DisplayController.GetInstance().getCurrentDisplay();
		assertTrue(summaryDisplay instanceof SummaryDisplay);
		rob.mouseClick(summaryDisplay.getSearchButtonTest());
		
		Utility.sleep(5);
		gotoPatientRecordDisplay();
		
		HashSet<String> ars2 = new HashSet<String>();
		for (AllergyDisplay disp : allergyDisplays) {
			JTextField allergyField = disp.getAllergyFieldTest();
			JTextField reactionField = disp.getReactionFieldTest();
			ars2.add(allergyField.getText() + ":" + reactionField.getText());
		}
		assertTrue(ars.equals(ars2));
	}
}
