package tests;

import edu.cs320.project.TestAllergy;
import edu.cs320.project.TestCreateUser1;
import edu.cs320.project.TestDateWrapper;
import edu.cs320.project.TestDisplayController;
import edu.cs320.project.TestDisplayUtility;
import edu.cs320.project.TestDrug;
import edu.cs320.project.TestIntegrationLoginUser;
import edu.cs320.project.TestIntegrationSearch;
import edu.cs320.project.TestLogin;
import edu.cs320.project.TestPatientInfo;
import edu.cs320.project.TestPatientRecord;
import edu.cs320.project.TestQuestionSet;
import edu.cs320.project.TestSearch;
import junit.framework.Test;
import junit.framework.TestSuite;

public class AllJUnitTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for edu.cs320.project");
		//$JUnit-BEGIN$
		suite.addTestSuite(TestIntegrationLoginUser.class);
		suite.addTestSuite(TestLogin.class);
		suite.addTestSuite(TestDateWrapper.class);
		suite.addTestSuite(TestDrug.class);
		suite.addTestSuite(TestSearch.class);
		suite.addTestSuite(TestDisplayController.class);
		suite.addTestSuite(TestCreateUser1.class);
		suite.addTestSuite(TestAllergy.class);
		suite.addTestSuite(TestQuestionSet.class);
		suite.addTestSuite(TestIntegrationSearch.class);
		suite.addTestSuite(TestPatientRecord.class);
		suite.addTestSuite(TestDisplayUtility.class);
		suite.addTestSuite(TestPatientInfo.class);
		//$JUnit-END$
		return suite;
	}

}
