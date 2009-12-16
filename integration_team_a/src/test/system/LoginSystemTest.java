package test.system;

import java.awt.AWTException;
import test.SmartRobot;
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
	}

	protected void setUp() throws Exception {
		super.setUp();
		DisplayController.main(null);
		loginDisplay = (LoginDisplay) DisplayController.GetInstance().getCurrentDisplay();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		DisplayController.GetInstance().TearDown();
	}
	
	
	public void testLoginSuccess1() {
		assertSame(loginDisplay, DisplayController.GetInstance().getCurrentDisplay());
		rob.mouseClick(loginDisplay.getUserNameFieldTest());
		rob.type("cs320");
		rob.mouseClick(loginDisplay.getPasswordFieldTest());
		rob.type("cs320");
		rob.mouseClick(loginDisplay.getSubmitButtonTest());
		sleep(4);
		assertTrue(DisplayController.GetInstance().getCurrentDisplay() instanceof SearchMainDisplay);
		searchMainDisplay = (SearchMainDisplay)DisplayController.GetInstance().getCurrentDisplay();
		rob.mouseClick(searchMainDisplay.getLogoutButtonTest());
	}
	
	public void testLoginSuccess2() {
		assertSame(loginDisplay, DisplayController.GetInstance().getCurrentDisplay());
		rob.mouseClick(loginDisplay.getUserNameFieldTest());
		rob.type("CS320");
		rob.mouseClick(loginDisplay.getPasswordFieldTest());
		rob.type("cs320");
		rob.mouseClick(loginDisplay.getSubmitButtonTest());
		sleep(4);
		assertTrue(DisplayController.GetInstance().getCurrentDisplay() instanceof SearchMainDisplay);
		searchMainDisplay = (SearchMainDisplay)DisplayController.GetInstance().getCurrentDisplay();
		rob.mouseClick(searchMainDisplay.getLogoutButtonTest());
	}
	
	public void testLoginSuccess3() {
		assertSame(loginDisplay, DisplayController.GetInstance().getCurrentDisplay());
		rob.mouseClick(loginDisplay.getUserNameFieldTest());
		rob.type("Cs320");
		rob.mouseClick(loginDisplay.getPasswordFieldTest());
		rob.type("cs320");
		rob.mouseClick(loginDisplay.getSubmitButtonTest());
		sleep(4);
		assertTrue(DisplayController.GetInstance().getCurrentDisplay() instanceof SearchMainDisplay);
		searchMainDisplay = (SearchMainDisplay)DisplayController.GetInstance().getCurrentDisplay();
		rob.mouseClick(searchMainDisplay.getLogoutButtonTest());
	}
	
	public void testLoginFail1() {
		assertSame(loginDisplay, DisplayController.GetInstance().getCurrentDisplay());
		rob.mouseClick(loginDisplay.getUserNameFieldTest());
		rob.type("");
		rob.mouseClick(loginDisplay.getPasswordFieldTest());
		rob.type("");
		rob.mouseClick(loginDisplay.getSubmitButtonTest());
		assertTrue(DisplayController.GetInstance().getCurrentDisplay() instanceof LoginDisplay);
	}
	
	public void testLoginFail2() {
		assertSame(loginDisplay, DisplayController.GetInstance().getCurrentDisplay());
		rob.mouseClick(loginDisplay.getUserNameFieldTest());
		rob.type("CS320");
		rob.mouseClick(loginDisplay.getPasswordFieldTest());
		rob.type("CS320");
		rob.mouseClick(loginDisplay.getSubmitButtonTest());
		assertSame(loginDisplay, DisplayController.GetInstance().getCurrentDisplay());
	}
	
	public void testLoginFail3() {
		assertSame(loginDisplay, DisplayController.GetInstance().getCurrentDisplay());
		rob.mouseClick(loginDisplay.getUserNameFieldTest());
		rob.type("CS320");
		rob.mouseClick(loginDisplay.getPasswordFieldTest());
		rob.type("wafweasdf");
		rob.mouseClick(loginDisplay.getSubmitButtonTest());
		assertTrue(DisplayController.GetInstance().getCurrentDisplay() instanceof LoginDisplay);
	}
	
	public void testLoginFail4() {
		assertSame(loginDisplay, DisplayController.GetInstance().getCurrentDisplay());
		rob.mouseClick(loginDisplay.getUserNameFieldTest());
		rob.type("CS320");
		rob.mouseClick(loginDisplay.getPasswordFieldTest());
		rob.type("");
		rob.mouseClick(loginDisplay.getSubmitButtonTest());
		assertTrue(DisplayController.GetInstance().getCurrentDisplay() instanceof LoginDisplay);
	}
	
	public void testLoginFail6() {
		assertSame(loginDisplay, DisplayController.GetInstance().getCurrentDisplay());
		rob.mouseClick(loginDisplay.getUserNameFieldTest());
		rob.type("CS320a");
		rob.mouseClick(loginDisplay.getPasswordFieldTest());
		rob.type("cs320");
		rob.mouseClick(loginDisplay.getSubmitButtonTest());
		assertTrue(DisplayController.GetInstance().getCurrentDisplay() instanceof LoginDisplay);
	}
	
	public void testLoginFail7() {
		assertSame(loginDisplay, DisplayController.GetInstance().getCurrentDisplay());
		rob.mouseClick(loginDisplay.getUserNameFieldTest());
		rob.type("CS320 ");
		rob.mouseClick(loginDisplay.getPasswordFieldTest());
		rob.type("cs320");
		rob.mouseClick(loginDisplay.getSubmitButtonTest());
		assertTrue(DisplayController.GetInstance().getCurrentDisplay() instanceof LoginDisplay);
	}
	
	public void testLoginFail8() {
		assertSame(loginDisplay, DisplayController.GetInstance().getCurrentDisplay());
		rob.mouseClick(loginDisplay.getUserNameFieldTest());
		rob.type(" CS320");
		rob.mouseClick(loginDisplay.getPasswordFieldTest());
		rob.type("cs320");
		rob.mouseClick(loginDisplay.getSubmitButtonTest());
		assertTrue(DisplayController.GetInstance().getCurrentDisplay() instanceof LoginDisplay);
	}
	
	public void testLoginFail9() {
		assertSame(loginDisplay, DisplayController.GetInstance().getCurrentDisplay());
		rob.mouseClick(loginDisplay.getUserNameFieldTest());
		rob.type(" CS320 ");
		rob.mouseClick(loginDisplay.getPasswordFieldTest());
		rob.type("cs320");
		rob.mouseClick(loginDisplay.getSubmitButtonTest());
		assertTrue(DisplayController.GetInstance().getCurrentDisplay() instanceof LoginDisplay);
	}
	
	private void sleep(long seconds){
		try {
			Thread.sleep(seconds * 1000);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
