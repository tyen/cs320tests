package test.unit;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import edu.cs320.project.*;
import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * 91% coverage. Display function was all that did not get covered.
 * 
 * @author ewortzma
 * 
 */
public class TestSearch extends TestCase {

	List<PatientInfo> singleResult;
	List<PatientInfo> multipleResult;
	List<PatientInfo> emptyResult;

	/**
	 * @param name
	 */
	public TestSearch(String name) {
		super(name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * generates 1000 random PatientInfo objects, then extracts data from the
	 * objects and searches for the random patients in the database.
	 */
	public void test_searchForPatient1() {
		Storage store = Storage.GetInstance();
		Search search = Search.GetInstance();

		List<PatientInfo> existingPatients = InputGenerator
				.getGeneratedPatients();
		Assert.assertNotNull(existingPatients);

		for (int i = 0; i < 5; i++) {
			PatientInfo testPatient = InputGenerator.randomPatient();
			HashMap<String, String> guineaPig = toHashMap(testPatient);
			String first = guineaPig.get("first_name");
			String last = guineaPig.get("last_name");
			DateWrapper dob = new DateWrapper(guineaPig.get("dob"));
			search.SearchForPatient(first, last, dob);
			HashMap<String, String> searchTerm = new HashMap<String, String>();
			searchTerm.put("first_name", first);
			searchTerm.put("last_name", last);
			searchTerm.put("dob", dob.toString());
			List<HashMap<String, String>> expectedHashMap = StorageWrapper.Retrieve(
					"cs320.patient", searchTerm);
			if (expectedHashMap.isEmpty()) {
				expectedHashMap.add(toHashMap(new PatientInfo(searchTerm)));
			}

			ListIterator<HashMap<String, String>> it = expectedHashMap
					.listIterator();

			List<PatientInfo> expected = new LinkedList<PatientInfo>();

			while (it.hasNext()) {
				expected.add(new PatientInfo(it.next()));
			}

			System.out.println("Expected: ");
			printResults(expected);
			System.out.print("\n");
			System.out.println("Returned: ");
			printResults(search.getSearchResults());
			System.out.print("\n\n");

			ListIterator<PatientInfo> it1 = expected.listIterator();
			ListIterator<PatientInfo> it2 = search.getSearchResults()
					.listIterator();

			Assert.assertTrue(expected.size() == search.getSearchResults()
					.size());

			while (it1.hasNext() && it2.hasNext()) {
				PatientInfo exp = it1.next();
				PatientInfo act = it2.next();
				Assert.assertEquals(exp.GetFirstName(), act.GetFirstName());
				Assert.assertEquals(exp.GetLastName(), act.GetLastName());
				Assert.assertEquals(exp.GetDateOfBirth().toString(), act.GetDateOfBirth().toString());
				Assert.assertEquals(exp.GetHeight(), act.GetHeight());
				Assert.assertEquals(exp.GetWeight(), act.GetWeight());
				Assert.assertEquals(exp.GetGender(), act.GetGender());
				Assert.assertEquals(exp.GetMedicalRecordNumber(), act
						.GetMedicalRecordNumber());
			}
		}
	}

	/**
	 * Iteratively walks through all patients in the database and attempts to
	 * search for them.
	 */
	public void test_searchForPatient2() {
		Storage store = Storage.GetInstance();
		Search search = Search.GetInstance();

		List<HashMap<String, String>> existingPatients = StorageWrapper.Retrieve(
				"cs320.patient", null);
		Assert.assertNotNull(existingPatients);
		Assert.assertTrue(existingPatients.size() > 0);

		for (int i = 0; i < existingPatients.size(); i++) {
			PatientInfo testPatient = new PatientInfo(existingPatients.get(i));
			HashMap<String, String> guineaPig = toHashMap(testPatient);
			String first = guineaPig.get("first_name");
			String last = guineaPig.get("last_name");
			DateWrapper dob = new DateWrapper(guineaPig.get("dob"));
			search.SearchForPatient(first, last, dob);
			HashMap<String, String> searchTerm = new HashMap<String, String>();
			searchTerm.put("first_name", first);
			searchTerm.put("last_name", last);
			searchTerm.put("dob", dob.toString());
			List<HashMap<String, String>> expectedHashMap = StorageWrapper.Retrieve(
					"cs320.patient", searchTerm);
			if (expectedHashMap.isEmpty()) {
				expectedHashMap.add(toHashMap(new PatientInfo(searchTerm)));
			}

			ListIterator<HashMap<String, String>> it = expectedHashMap
					.listIterator();

			List<PatientInfo> expected = new LinkedList<PatientInfo>();

			while (it.hasNext()) {
				expected.add(new PatientInfo(it.next()));
			}

			System.out.println("Expected: ");
			printResults(expected);
			System.out.print("\n");
			System.out.println("Returned: ");
			printResults(search.getSearchResults());
			System.out.print("\n\n");

			ListIterator<PatientInfo> it1 = expected.listIterator();
			ListIterator<PatientInfo> it2 = search.getSearchResults()
					.listIterator();

			Assert.assertTrue(expected.size() == search.getSearchResults()
					.size());

			while (it1.hasNext() && it2.hasNext()) {
				PatientInfo exp = it1.next();
				PatientInfo act = it2.next();
				Assert.assertEquals(exp.GetFirstName(), act.GetFirstName());
				Assert.assertEquals(exp.GetLastName(), act.GetLastName());
				Assert.assertEquals(exp.GetDateOfBirth().toString(), act.GetDateOfBirth().toString());
				Assert.assertEquals(exp.GetHeight(), act.GetHeight());
				Assert.assertEquals(exp.GetWeight(), act.GetWeight());
				Assert.assertEquals(exp.GetGender(), act.GetGender());
				Assert.assertEquals(exp.GetMedicalRecordNumber(), act
						.GetMedicalRecordNumber());
			}
		}
	}

	/**
	 * takes a list of PatientInfo objects, and prints each element in the list
	 * one at a time.
	 * 
	 * @param list
	 *            a list of PatientInfo objects
	 */
	public void printResults(List<PatientInfo> list) {
		ListIterator<PatientInfo> it = list.listIterator();

		while (it.hasNext()) {
			printInfo(it.next());
		}
	}

	/**
	 * takes a PatientInfo object and prints all information stored in the
	 * object
	 * 
	 * @param info
	 *            the PatientInfo object to be printed
	 */
	public void printInfo(PatientInfo info) {
		System.out.println("First: " + info.GetFirstName());
		System.out.println("Last: " + info.GetLastName());
		System.out.println("DOB: " + info.GetDateOfBirth());
		System.out.println("Gender: " + info.GetGender());
		System.out.println("Height: " + info.GetHeight());
		System.out.println("Weight: " + info.GetWeight());
		System.out.println("Medical Record Number: "
				+ info.GetMedicalRecordNumber() + "\n");
	}

	public HashMap<String, String> toHashMap(PatientInfo patient) {
		HashMap<String, String> map = new HashMap<String, String>();

		if (patient.GetFirstName() != "" && patient.GetFirstName() != null) {
			map.put("first_name", patient.GetFirstName());
		}
		if (patient.GetLastName() != "" && patient.GetLastName() != null) {
			map.put("last_name", patient.GetLastName());
		}
		if (patient.GetDateOfBirth() != null) {
			map.put("dob", patient.GetDateOfBirth().toString());
		}
		if (patient.GetGender() != "" && patient.GetGender() != null) {
			map.put("sex", patient.GetGender());
		}
		if (patient.GetHeight() != 0) {
			map.put("height", "" + patient.GetHeight());
		}
		if (patient.GetWeight() != 0) {
			map.put("weight", "" + patient.GetWeight());
		}
		if (patient.GetMedicalRecordNumber() != "") {
			map.put("mrn", "" + patient.GetMedicalRecordNumber());
		}

		return map;
	}
}
