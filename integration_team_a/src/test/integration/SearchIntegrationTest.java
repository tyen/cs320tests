/**
 * 
 */
package test.integration;

import java.util.List;

import test.InputGenerator;
import edu.cs320.project.*;
import junit.framework.TestCase;

/**
 * @author Sean
 *
 */
public class SearchIntegrationTest extends TestCase {

	private final int MAX_ITERATIONS = 100;
	private Search s = Search.GetInstance();
	
	/**
	 * @param name
	 */
	public SearchIntegrationTest(String name) {
		super(name);
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

	/**
	 * Test method for {@link edu.cs320.project.Search#SearchForPatient(java.lang.String, java.lang.String, edu.cs320.project.DateWrapper)}.
	 * Search for patients that are not in the database.
	 */
	public void testSearchForPatient() {
		String firstName;
		String lastName;
		DateWrapper date;
		
		InputGenerator.reset();
		
		for(int i = 0; i < MAX_ITERATIONS; ++i){
			firstName = InputGenerator.randomString();
			lastName = InputGenerator.randomString();
			date = InputGenerator.randomDateWrapper();
			
			s.SearchForPatient(firstName, lastName, date);
			List<PatientInfo> patients = s.getSearchResults();
			PatientInfo patient= patients.get(0);

			assertNotNull(s.getSearchResults());
			assertEquals(1, patients.size());
			assertEquals(firstName, patient.GetFirstName());
			assertEquals(lastName, patient.GetLastName());
		}
	}
	

}
