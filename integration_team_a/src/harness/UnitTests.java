package harness;

import test.unit.*;
import junit.framework.Test;
import junit.framework.TestSuite;

public class UnitTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Unit test suite for edu.cs320.project");
		//$JUnit-BEGIN$
		suite.addTestSuite(TestPatientRecord.class);
		suite.addTestSuite(TestPatientInfo.class);
		suite.addTestSuite(TestAllergy.class);
		suite.addTestSuite(TestQuestionSet.class);
		suite.addTestSuite(TestSearch.class);
		suite.addTestSuite(TestCreateUser.class);
		suite.addTestSuite(TestLogin.class);
		suite.addTestSuite(TestDisplayController.class);
		suite.addTestSuite(TestDateWrapper.class);
		suite.addTestSuite(TestDrug.class);
		//$JUnit-END$
		return suite;
	}

}
