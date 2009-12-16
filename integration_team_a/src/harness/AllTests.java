package harness;

import edu.cs320.project.TestAllergy;
import edu.cs320.project.TestCreateUser1;
import edu.cs320.project.TestDateWrapper;
import edu.cs320.project.TestDisplayController;
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

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for edu.cs320.project");
		//$JUnit-BEGIN$
		/*suite.addTestSuite(TestPatientRecord.class);
		suite.addTestSuite(TestPatientInfo.class);
		suite.addTestSuite(TestAllergy.class);
		suite.addTestSuite(TestQuestionSet.class);
		suite.addTestSuite(TestSearch.class);
		suite.addTestSuite(TestCreateUser1.class);
		suite.addTestSuite(TestLogin.class);
		suite.addTestSuite(TestIntegrationLoginUser.class);
		suite.addTestSuite(TestIntegrationSearch.class);
		suite.addTestSuite(TestDisplayController.class);
		suite.addTestSuite(TestDateWrapper.class);*/
		suite.addTestSuite(TestDrug.class);
		//$JUnit-END$
		return suite;
	}

}
