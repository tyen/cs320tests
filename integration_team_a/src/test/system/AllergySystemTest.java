package test.system;

import java.util.HashMap;
import java.util.LinkedList;

import javax.swing.JTextField;

import test.SmartRobot;
import test.Utility;
import edu.cs320.project.*;
import junit.framework.TestCase;
import static org.junit.Assert.*;

public class AllergySystemTest extends TestCase {
	SmartRobot rob = SmartRobot.getInstance();
	PatientRecordDisplay patientRecordDisplay = null;
	AddAllergyDisplay addAllergyDisplay = null;
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
				
		if (DisplayController.GetInstance().getCurrentDisplay() instanceof LoginDisplay)
			Utility.login("cs320", "cs320", 1);
		
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
	
	public void testAllergy1() {
		assertTrue(allergyDisplays.isEmpty());
		rob.mouseClick(addAllergyDisplay.getAddAllergyButton());
		assertEquals(allergyDisplays.size(),1);
		AllergyDisplay lastAllergyDisplay = allergyDisplays.getLast();
		JTextField allergyField = lastAllergyDisplay.getAllergyFieldTest();
		assertTrue(allergyField.getText().equals(""));
		JTextField reactionField = lastAllergyDisplay.getReactionFieldTest();
		assertTrue(reactionField.getText().equals(""));
		
		rob.mouseClick(allergyField);
		rob.type("hello!");
		
		Utility.sleep(5);
		
		
	}
}
