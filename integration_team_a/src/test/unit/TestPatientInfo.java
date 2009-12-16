package test.unit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import edu.cs320.project.*;
import test.InputGenerator;

import junit.framework.TestCase;


public class TestPatientInfo extends TestCase {

	private final static int MAX_IT = 1000;
	
	public TestPatientInfo(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void test_Patient7(){
		for (int i=1; i<MAX_IT; i++) {
			InputGenerator.reset();
			String firstName = InputGenerator.randomString();
			String lastName = InputGenerator.randomString();
			DateWrapper dateOfBirth = new DateWrapper();
			int height = Math.abs(InputGenerator.randomInt());
			int weight = Math.abs(InputGenerator.randomInt());
			String mrn = Integer.toString(Math.abs(InputGenerator.randomInt()));
			String cmrn = Integer.toString(Math.abs(InputGenerator.randomInt()));
			String gender = "Male";
			String address = InputGenerator.randomString();

			HashMap<String, String> aMap = new HashMap<String, String>();
			aMap.put("first_name", firstName);
			aMap.put("last_name", lastName);
			aMap.put("dob", dateOfBirth.toString());
			aMap.put("height", "" + height);
			aMap.put("weight", "" + weight);
			aMap.put("mrn", "" + mrn);
			aMap.put("cmrn", "" + cmrn);
			aMap.put("sex", gender);
			aMap.put("is_editable", "true");
			aMap.put("address", address);

			PatientInfo aPatient = new PatientInfo(aMap);

			assertEquals(firstName, aPatient.GetFirstName());
			assertEquals(lastName, aPatient.GetLastName());
			//assertEquals(dateOfBirth, aPatient.GetDateOfBirth().toString());
			assertEquals(height, aPatient.GetHeight());
			assertEquals(weight, aPatient.GetWeight());
			assertEquals(mrn, aPatient.GetMedicalRecordNumber());
			//assertEquals(cmrn, aPatient.GetCMRN());
			assertEquals(gender, aPatient.GetGender());
			//assertEquals(true, aPatient.GetEditable());

			assertTrue(aPatient.Save());
			List<HashMap<String, String>> retrResults = StorageWrapper.Retrieve("cs320.patient",	aMap);
			
			System.out.println(retrResults);
			
			assertEquals(retrResults.get(0), aMap);
		}
	}








}
