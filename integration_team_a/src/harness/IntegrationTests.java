package harness;

import test.integration.*;
import junit.framework.Test;
import junit.framework.TestSuite;

public class IntegrationTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Unit test suite for edu.cs320.project");
		//$JUnit-BEGIN$
		suite.addTestSuite(AllergyIntegrationTest.class);
		suite.addTestSuite(CreateUserIntegrationTest.class);
		suite.addTestSuite(DateWrapperIntegrationTest.class);
		suite.addTestSuite(DisplayControllerIntegrationTest.class);
		suite.addTestSuite(DrugIntegrationTest.class);
		suite.addTestSuite(LoginIntegrationTest.class);
		suite.addTestSuite(QuestionSetIntegrationTest.class);
		suite.addTestSuite(Search2IntegrationTest.class);
		suite.addTestSuite(SearchIntegrationTest.class);
		//$JUnit-END$
		return suite;
	}

}
