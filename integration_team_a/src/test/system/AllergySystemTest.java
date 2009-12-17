package test.system;

import java.util.HashMap;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
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
	
	private class AllergyReaction {
		public String allergy;
		public String reaction;
		public AllergyReaction(String allergy, String reaction) {
			this.allergy = allergy;
			this.reaction = reaction;
		}
	}
	
	LinkedList<AllergyReaction> ars = new LinkedList<AllergyReaction>();
	
	public void testAllergy1() {
		// add 10 random allergies
		
		int iterations = 10;
		for (int i = 0; i < iterations; i++) {
			String allergy = InputGenerator.randomMinString(10);
			String reaction = InputGenerator.randomMinString(10);
			ars.add(new AllergyReaction(allergy, reaction));
			assertEquals(allergyDisplays.size(), i);
			rob.mouseClick(addAllergyDisplay.getAddAllergyButton());
			assertEquals(allergyDisplays.size(), i + 1);
			
			JScrollBar scrollBar = patientRecordDisplay.getAllergyPanel().getVerticalScrollBar();
			rob.scrollDown(scrollBar);
			
			AllergyDisplay lastAllergyDisplay = allergyDisplays.getLast();
			JTextField allergyField = lastAllergyDisplay.getAllergyFieldTest();
			assertTrue(allergyField.getText().equals(""));
			JTextField reactionField = lastAllergyDisplay.getReactionFieldTest();
			assertTrue(reactionField.getText().equals(""));
			
			rob.mouseClick(allergyField);
			rob.type(allergy);
			assertTrue(allergyField.getText().equals(allergy));
			
			rob.mouseClick(reactionField);
			rob.type(reaction);
			
			assertTrue(reactionField.getText().equals(reaction));
			
			JButton saveButton = lastAllergyDisplay.getSaveButtonTest();
			rob.mouseClick(saveButton);
		}
		
		
		
		Utility.sleep(10);
	}
}
