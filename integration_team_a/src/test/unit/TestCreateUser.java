package test.unit;

import junit.framework.TestCase;
import java.util.*;
import edu.cs320.project.*;

/**
 * 
 */

/**
 * @author JHaan
 *
 */
public class TestCreateUser extends TestCase {

	/**
	 * @param name
	 */
	Login Login1 = new Login();
	User User1;
	String LoginName;
	User.UserType Type1;
	public TestCreateUser() {
		super();
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		Login1 = new Login();
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
		Login1 = new Login();
	}
	
	public void test_CreateUser1(){
		
		LoginName = "sdooley";
		Type1 = User.UserType.NURSE;
	
		User1 = Login1.CreateUser(LoginName, Type1);

		assertEquals(LoginName, User1.GetUserName());
		assertEquals(Type1, User1.GetUserType());
	}
	
	public void test_CreateUser2(){
		
		LoginName = "tyen";
		Type1 = User.UserType.DOCTOR;
	
		User1 = Login1.CreateUser(LoginName, Type1);

		assertEquals(LoginName, User1.GetUserName());
		assertEquals(Type1, User1.GetUserType());
						
	}
	
	public void test_CreateUser3(){
		
		LoginName = "jmolly";
		Type1 = User.UserType.PHARMACIST;
	
		User1 = Login1.CreateUser(LoginName, Type1);

		assertEquals(LoginName, User1.GetUserName());
		assertEquals(Type1, User1.GetUserType());
						
	}

	public void test_CreateUser4(){
		
		LoginName = "mfisher";
		Type1 = User.UserType.ADMIN;
	
		User1 = Login1.CreateUser(LoginName, Type1);

		assertEquals(LoginName, User1.GetUserName());
		assertEquals(Type1, User1.GetUserType());
						
	}
	
}

