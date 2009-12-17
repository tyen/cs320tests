package test.system;

import java.util.HashMap;
import test.SmartRobot;
import test.Utility;
import edu.cs320.project.*;
import junit.framework.TestCase;

public class NavigationSystemTest extends TestCase {
	SmartRobot rob;
	LoginDisplay loginDisplay;
	SearchMainDisplay searchMainDisplay;
	
	static {
		DisplayController.main(null);
	}
	
	public NavigationSystemTest(String name) {
		super(name);
		rob = SmartRobot.getInstance();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("username", "cs320");
		map.put("password", Login.GetMD5("cs320"));

		if(!StorageWrapper.Exist("cs320.user", map)){
			map.put("username", "nurse");
			StorageWrapper.Save("cs320.user", map);
		}
	}

	protected void setUp() throws Exception {
		super.setUp();
		
		// If not at the login screen
		if(DisplayController.GetInstance().getCurrentDisplay() instanceof SearchMainDisplay){
			Utility.logoutFromSearchMainDisplay();
		}
		
		loginDisplay = (LoginDisplay) DisplayController.GetInstance().getCurrentDisplay();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		DisplayController.GetInstance().TearDown();
	}
	
	public void testNavigation1() {
		Utility.login("cs320", "cs320", 3);
		assertTrue(DisplayController.GetInstance().getCurrentDisplay() instanceof SearchMainDisplay);
		Utility.searchForPatient("Sean", "Dooley", "9/12/1975");
		assertTrue(DisplayController.GetInstance().getCurrentDisplay() instanceof PatientRecordDisplay);
		Utility.goBackFromPatientRecord();
		assertTrue(DisplayController.GetInstance().getCurrentDisplay() instanceof SearchMainDisplay);
	}
	
	public void testNavigation2() {
		Utility.login("cs320", "cs320", 3);
		assertTrue(DisplayController.GetInstance().getCurrentDisplay() instanceof SearchMainDisplay);
		Utility.searchForPatient("Sean", "Dooley", "9/12/1975");
		assertTrue(DisplayController.GetInstance().getCurrentDisplay() instanceof PatientRecordDisplay);
		Utility.logoutFromPatientRecord();
		assertTrue(DisplayController.GetInstance().getCurrentDisplay() instanceof LoginDisplay);
	}
	
	public void testNavigation3() {
		Utility.login("cs320", "cs320", 3);
		assertTrue(DisplayController.GetInstance().getCurrentDisplay() instanceof SearchMainDisplay);
		Utility.searchForPatient("Sean", "Dooley", "9/12/1975");
		assertTrue(DisplayController.GetInstance().getCurrentDisplay() instanceof PatientRecordDisplay);
		Utility.confirmPatientRecord();
		assertTrue(DisplayController.GetInstance().getCurrentDisplay() instanceof SummaryDisplay);
	}
	
}
