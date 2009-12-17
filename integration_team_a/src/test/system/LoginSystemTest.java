package test.system;

import java.util.HashMap;
import test.SmartRobot;
import test.Utility;
import edu.cs320.project.*;
import junit.framework.TestCase;

public class LoginSystemTest extends TestCase {
	SmartRobot rob;
	LoginDisplay loginDisplay;
	SearchMainDisplay searchMainDisplay;
	
	static {
		DisplayController.main(null);
	}
	
	public LoginSystemTest(String name) {
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
		DisplayController.GetInstance().TearDown();
		// If not at the login screen
		if(DisplayController.GetInstance().getCurrentDisplay() instanceof SearchMainDisplay){
			Utility.logoutFromSearchMainDisplay();
		}
		
		loginDisplay = (LoginDisplay) DisplayController.GetInstance().getCurrentDisplay();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testLoginSuccess1() {
		Utility.login("cs320", "cs320", 3);
		Utility.logoutFromSearchMainDisplay();
	}
	
	public void testLoginSuccess2() {
		Utility.login("CS320", "cs320", 2);
		Utility.logoutFromSearchMainDisplay();
	}
	
	public void testLoginSuccess3() {
		Utility.login("Cs320", "cs320", 2);
		Utility.logoutFromSearchMainDisplay();
	}
	
	@SuppressWarnings("deprecation")
	public void testLoginSuccess4() {
		assertTrue(DisplayController.GetInstance().getCurrentDisplay() instanceof LoginDisplay);
		rob.mouseClick(loginDisplay.getUserNameFieldTest());
		rob.type("Cs320");
		rob.mouseClick(loginDisplay.getPasswordFieldTest());
		rob.type("cs320");
		rob.mouseClick(loginDisplay.getClearButtonTest());
		assertEquals(0, loginDisplay.getUserNameFieldTest().getText().length());
		assertEquals(0, loginDisplay.getPasswordFieldTest().getText().length());
		Utility.sleep(1);
	}
	
	public void testLoginSuccess5() {
		Utility.login("cs320 ", "cs320", 2);
		Utility.logoutFromSearchMainDisplay();
	}
	
	public void testLoginFail1() {
		Utility.login("", "", 2);
		assertTrue(DisplayController.GetInstance().getCurrentDisplay() instanceof LoginDisplay);
		Utility.sleep(1);
	}
	
	public void testLoginFail2() {
		Utility.login("CS320", "CS320", 2);
		assertTrue(DisplayController.GetInstance().getCurrentDisplay() instanceof LoginDisplay);
		Utility.sleep(1);
	}
	
	public void testLoginFail3() {
		Utility.login("CS320", "wafweasdf", 2);
		assertTrue(DisplayController.GetInstance().getCurrentDisplay() instanceof LoginDisplay);
		Utility.sleep(1);
	}
	
	public void testLoginFail4() {
		Utility.login("CS320", "", 2);
		assertTrue(DisplayController.GetInstance().getCurrentDisplay() instanceof LoginDisplay);
		Utility.sleep(1);
	}
	
	public void testLoginFail6() {
		Utility.login("CS320a", "cs320", 2);
		assertTrue(DisplayController.GetInstance().getCurrentDisplay() instanceof LoginDisplay);
		Utility.sleep(1);
	}
	
	public void testLoginFail7() {
		Utility.login("CS 320", "cs320", 2);
		assertTrue(DisplayController.GetInstance().getCurrentDisplay() instanceof LoginDisplay);
		Utility.sleep(1);
	}
	
	public void testLoginFail8() {
		Utility.login(" CS320", "cs320", 2);
		assertTrue(DisplayController.GetInstance().getCurrentDisplay() instanceof LoginDisplay);
		Utility.sleep(1);
	}
	
	public void testLoginFail9() {
		assertSame(loginDisplay, DisplayController.GetInstance().getCurrentDisplay());
		rob.mouseClick(loginDisplay.getUserNameFieldTest());
		rob.type(" CS320 ");
		rob.mouseClick(loginDisplay.getPasswordFieldTest());
		rob.type("cs320");
		rob.mouseClick(loginDisplay.getSubmitButtonTest());
		assertTrue(DisplayController.GetInstance().getCurrentDisplay() instanceof LoginDisplay);
		Utility.sleep(1);
	}
}
