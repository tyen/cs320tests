package harness;

import test.system.*;
import junit.framework.Test;
import junit.framework.TestSuite;

public class SystemTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Unit test suite for edu.cs320.project");
		//$JUnit-BEGIN$
		suite.addTestSuite(LoginSystemTest.class);
		suite.addTestSuite(AllergySystemTest.class);
		suite.addTestSuite(SearchSystemTest.class);
		suite.addTestSuite(SystemTest.class);
		suite.addTestSuite(NavigationSystemTest.class);
		//$JUnit-END$
		return suite;
	}

}
