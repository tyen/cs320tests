package harness;

import test.integration.*;
import junit.framework.Test;
import junit.framework.TestSuite;

public class IntegrationTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Unit test suite for edu.cs320.project");
		//$JUnit-BEGIN$
		suite.addTestSuite(LoginUserIntegrationTest.class);
		suite.addTestSuite(SearchIntegrationTest.class);
		//$JUnit-END$
		return suite;
	}

}
