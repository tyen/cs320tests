package test.integration;

import java.util.ArrayList;

import test.InputGenerator;
import edu.cs320.project.*;
import junit.framework.TestCase;

public class LoginUserIntegrationTest extends TestCase {
	private static int iterations = 50;
	
	Login login = new Login();
	
	public LoginUserIntegrationTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	// Create a user, insert it into the database,
	// check to see if we can login
	public void testLoginUser1() {
		Storage.GetInstance().deleteFromClient("cs320.user");
		ArrayList<User> uniqUsers = new ArrayList<User>();
		for(int i = 0; i < iterations; i++) {
			User newUser = InputGenerator.randomUser(10);
			if (!uniqUsers.contains(newUser))
				uniqUsers.add(newUser);
		}
		for(User u : uniqUsers) {
			assertTrue(StorageWrapper.Save("cs320.user",u.toHashMap()));
			login.LoginToSystem(u.GetUserName(), u.password);
			assertTrue(login.GetSuccessfulLogin());
		}
	}
	
}

