package test.unit;

import junit.framework.TestCase;
import java.util.*;

import test.InputGenerator;
import edu.cs320.project.*;

/**
 * 
 */

/**
 * @author JHaan
 *
 */
public class TestLogin extends TestCase {

	/**
	 * @param name
	 */
	public TestLogin() {
		super();
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	/*
	 * Generates 1000 random users and tries to log in with them
	 * Should fail every time, barring the freak generation of a valid
	 * user/password
	 */
	public void test_LoginToSystem1(){
		for (int i=0; i < 5; i++) {
			User NewUser = InputGenerator.randomUser();
			Login l = new Login();
			l.LoginToSystem(NewUser.GetUserName(), InputGenerator.randomString());
			assertFalse(l.GetSuccessfulLogin());
		}
	}
	
	/*
	 * Generates 1000 random users and tries to log in with them to uppercase
	 * Should fail every time, barring the freak generation of a valid
	 * user/password
	 */
	public void test_LoginToSystem2(){
		for (int i=0; i<5; i++) {
			Login l = new Login();
			l.LoginToSystem("TBONCI", "TEST");
			assertFalse(l.GetSuccessfulLogin());
		}
	}
	
	/*
	 * Generates 1000 random users and tries to log in with them to lowercase
	 * Should fail every time, barring the freak generation of a valid
	 * user/password
	 */
	public void test_LoginToSystem3(){
		for (int i=0; i<5; i++) {
			Login l = new Login();
			l.LoginToSystem("tbonci", "TEST");
			assertFalse(l.GetSuccessfulLogin());
		}
	}
	public void test_LoginToSystem4(){
		for (int i=0; i<5; i++) {
			Login l = new Login();
			l.LoginToSystem("TBONCI", "test");
			assertTrue(l.GetSuccessfulLogin());
		}
	}
	
	/*
	 * Tests blank username
	 */
	public void test_LoginToSystem5(){
		Login l = new Login();
		l.LoginToSystem("" , "test");
		assertFalse(l.GetSuccessfulLogin());
	}
	
	/*
	 * Tests blank password
	 */
	public void test_LoginToSystem6(){
		Login l = new Login();
		l.LoginToSystem("tbonci" , "");
		assertFalse(l.GetSuccessfulLogin());
	}
	
	/*
	 * Tests both blank
	 */
	public void test_LoginToSystem7(){
		Login l = new Login();
		l.LoginToSystem("", "");
		assertFalse(l.GetSuccessfulLogin());
	}
	
	/*
	 * Tests that should pass
	 */
	public void test_LoginToSystem8() {
		Login l = new Login();
		l.LoginToSystem("tbonci", "test");
		assertTrue(l.GetSuccessfulLogin());
	}
	public void test_LoginToSystem9() {
		Login l = new Login();
		l.LoginToSystem("tBonCi", "tEst");
		assertFalse(l.GetSuccessfulLogin());
	}
	
}

