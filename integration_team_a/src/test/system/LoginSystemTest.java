package test.system;

import java.awt.AWTException;
import java.util.HashMap;

import test.SmartRobot;
import test.Utility;
import edu.cs320.project.*;
import junit.framework.TestCase;

public class LoginSystemTest extends TestCase {
	SmartRobot rob;
	LoginDisplay loginDisplay;
	SearchMainDisplay searchMainDisplay;
	
	public LoginSystemTest(String name) {
		super(name);
		try {
			rob = new SmartRobot();
		}
		catch (AWTException e) {
			e.printStackTrace();
		}
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
		DisplayController.main(null);
		
		// If not at the login screen
		if(DisplayController.GetInstance().getCurrentDisplay() instanceof SearchMainDisplay){
			Utility.sleep(1);
			rob.mouseClick(searchMainDisplay.getLogoutButtonTest());
			Utility.sleep(1);
		}
		
		loginDisplay = (LoginDisplay) DisplayController.GetInstance().getCurrentDisplay();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		DisplayController.GetInstance().TearDown();
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
		assertSame(loginDisplay, DisplayController.GetInstance().getCurrentDisplay());
		rob.mouseClick(loginDisplay.getUserNameFieldTest());
		rob.type("CS320");
		rob.mouseClick(loginDisplay.getPasswordFieldTest());
		rob.type("");
		rob.mouseClick(loginDisplay.getSubmitButtonTest());
		assertTrue(DisplayController.GetInstance().getCurrentDisplay() instanceof LoginDisplay);
		sleep(1);
	}
	
	public void testLoginFail6() {
		assertSame(loginDisplay, DisplayController.GetInstance().getCurrentDisplay());
		rob.mouseClick(loginDisplay.getUserNameFieldTest());
		rob.type("CS320a");
		rob.mouseClick(loginDisplay.getPasswordFieldTest());
		rob.type("cs320");
		rob.mouseClick(loginDisplay.getSubmitButtonTest());
		assertTrue(DisplayController.GetInstance().getCurrentDisplay() instanceof LoginDisplay);
		sleep(1);
	}
	
	public void testLoginFail7() {
		assertSame(loginDisplay, DisplayController.GetInstance().getCurrentDisplay());
		rob.mouseClick(loginDisplay.getUserNameFieldTest());
		rob.type("CS 320");
		rob.mouseClick(loginDisplay.getPasswordFieldTest());
		rob.type("cs320");
		rob.mouseClick(loginDisplay.getSubmitButtonTest());
		assertTrue(DisplayController.GetInstance().getCurrentDisplay() instanceof LoginDisplay);
		sleep(1);
	}
	
	public void testLoginFail8() {
		Utility.login(username, password)
		assertSame(loginDisplay, DisplayController.GetInstance().getCurrentDisplay());
		rob.mouseClick(loginDisplay.getUserNameFieldTest());
		rob.type(" CS320");
		rob.mouseClick(loginDisplay.getPasswordFieldTest());
		rob.type("cs320");
		rob.mouseClick(loginDisplay.getSubmitButtonTest());
		assertTrue(DisplayController.GetInstance().getCurrentDisplay() instanceof LoginDisplay);
		sleep(1);
	}
	
	public void testLoginFail9() {
		assertSame(loginDisplay, DisplayController.GetInstance().getCurrentDisplay());
		rob.mouseClick(loginDisplay.getUserNameFieldTest());
		rob.type(" CS320 ");
		rob.mouseClick(loginDisplay.getPasswordFieldTest());
		rob.type("cs320");
		rob.mouseClick(loginDisplay.getSubmitButtonTest());
		assertTrue(DisplayController.GetInstance().getCurrentDisplay() instanceof LoginDisplay);
		sleep(1);
	}

}
