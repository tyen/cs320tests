package test.integration;

import java.util.HashMap;
import java.util.LinkedList;
import test.InputGenerator;
import edu.cs320.project.*;
import junit.framework.Assert;
import junit.framework.TestCase;

public class DrugIntegrationTest extends TestCase {

	/**
	 * Constructor.
	 */
	public DrugIntegrationTest(String name) {
		super(name);
	}

	/**
	 * SetUp Method.
	 */
	protected void setUp() throws Exception {
		super.setUp();
	}
	
	/**
	 * Number of iterations that each test will be run for.
	 */
	int TEST_ITERATIONS = 1000;
	
	/**
	 * Instance of drug that is used for testing.
	 */
	Drug drug = InputGenerator.randomDrug();
	
	/**
	 * Test that tests the 'name' getter and setter methods
	 */
	public void testGetSetName() {
		for(int i=0;i<1000;i++)
		{
			String testString = InputGenerator.randomString();
			drug.SetName(testString);
			assertEquals(testString, drug.GetName());
		}
	}
	
	/**
	 * Test that tests the 'Dosage' getter and setter methods.
	 */
	public void testGetSetDosage(){
		for(int i=0;i<TEST_ITERATIONS;i++)
		{
			double testDouble = InputGenerator.randomDouble();
			drug.SetDosage(testDouble);
			assertEquals(testDouble, drug.GetDosage());
		}
	}
	
	/**
	 * Test that tests the 'DosageUnit' getter and setter methods.
	 */
	public void testGetSetDosageUnit(){
		for(int i=0;i<TEST_ITERATIONS;i++)
		{
			String testString = InputGenerator.randomString();
			drug.SetDosageUnit(testString);
			assertEquals(testString, drug.GetDosageUnit());
		}
	}
	
	/**
	 * Test that tests the 'MinDosage' getter and setter methods.
	 */
	public void testGetSetMinDosage(){
		for(int i=0;i<TEST_ITERATIONS;i++)
		{
			double testDouble = InputGenerator.randomDouble();
			drug.SetMinDosage(testDouble);
			assertEquals(testDouble, drug.GetMinDosage());
		}
	}
	
	/**
	 * Test that tests the 'MaxDosage' getter and setter methods.
	 */
	public void testGetSetMaxDosage(){
		for (int i=0;i<TEST_ITERATIONS;i++)
		{
			double testDouble = InputGenerator.randomDouble();
			drug.SetMaxDosage(testDouble);
			assertEquals(testDouble, drug.GetMaxDosage());
		}
	}
	
	/**
	 * Test that tests the 'Route' getter and setter methods.
	 */
	public void testGetSetRoute(){
		for(int i=0;i<TEST_ITERATIONS;i++)
		{
			String testString = InputGenerator.randomString();
			drug.SetRoute(testString);
			assertEquals(testString, drug.GetRoute());
		}
	}
	
	/**
	 * Test that tests the 'StartDate' getter and setter methods.
	 */
	public void testGetSetStartDate(){
		for(int i=0;i<TEST_ITERATIONS;i++)
		{
			DateWrapper testDateWrapper = InputGenerator.randomDateWrapper();
			drug.SetStartDate(testDateWrapper);
			assertEquals(testDateWrapper, drug.GetStartDate());
		}
	}
	
	/**
	 * Test that tests the 'StopDate' getter and setter methods.
	 */
	public void testGetSetStopDate(){
		for(int i=0;i<TEST_ITERATIONS;i++)
		{
			DateWrapper testDateWrapper = InputGenerator.randomDateWrapper();
			drug.SetStopDate(testDateWrapper);
			assertEquals(testDateWrapper, drug.GetStopDate());
		}
	}
	
	/**
	 * Test that tests the 'Editable' getter and setter methods.
	 */
	public void testGetSetEditable(){
		for(int i=0;i<TEST_ITERATIONS;i++)
		{
			boolean testBoolean;
			int randomInt = InputGenerator.randomInt(1);
			if (randomInt == 0)
				testBoolean = false;
			else
				testBoolean = true;
			drug.SetEditable(testBoolean);
			assertEquals(testBoolean, drug.IsEditable());
		}
	}
	
	/**
	 * Test that tests the 'Critical' getter and setter methods.
	 */
	public void testGetSetCritical(){
		for(int i=0;i<TEST_ITERATIONS;i++)
		{
			boolean testBoolean;
			int randomInt = InputGenerator.randomInt(1);
			if (randomInt == 0)
				testBoolean = false;
			else
				testBoolean = true;
			drug.SetCritical(testBoolean);
			assertEquals(testBoolean, drug.IsCritical());
		}
	}
	
	/**
	 * Test that tests the 'Questionable' getter and setter methods.
	 */
	public void testGetSetQuestionable(){
		for(int i=0;i<TEST_ITERATIONS;i++)
		{
			boolean testBoolean;
			int randomInt = InputGenerator.randomInt(1);
			if (randomInt == 0)
				testBoolean = false;
			else
				testBoolean = true;
			drug.SetQuestionable(testBoolean);
			assertEquals(testBoolean, drug.IsQuestionable());
		}
	}
	
	/**
	 * Test that tests the 'Prescriber' getter and setter methods.
	 */
	public void testGetSetPrescriber(){
		for(int i=0;i<TEST_ITERATIONS;i++)
		{
			String testString = InputGenerator.randomString();
			drug.SetPrescriber(testString);
			assertEquals(testString, drug.GetPrescriber());
		}
	}
	
	/**
	 * Test that tests the 'addlQuestions' getter and setter methods.
	 */
	public void testGetSetaddlQuestions(){
		for(int i=0;i<TEST_ITERATIONS;i++)
		{
			LinkedList<String> testQuestions = new LinkedList<String>();
			testQuestions.add(InputGenerator.randomString());
			drug.SetAdditionalQuestions(testQuestions);
			assertEquals(testQuestions, drug.GetAdditionalQuestions());
		}
	}
	
	/**
	 * Test that tests the 'DrugID' getter method.
	 */
	public void testGetDrugId(){
		
		for(int i=0;i<TEST_ITERATIONS;i++)
		{
			Drug testDrug = InputGenerator.randomDrug();
			int testID = testDrug.GetDrugID();
			Assert.assertEquals(testID, testDrug.GetDrugID());
		}
	}

	
	/**
	 * TearDown method.
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	
	
	public void test_StorageAndDrugInteraction(){
		
		Storage s = Storage.GetInstance();
			
		HashMap <String, String> map = new HashMap <String, String>();
		
		for(int i = 0; i < TEST_ITERATIONS; i++){
			
			Drug a = InputGenerator.randomDrug();
			Drug b = InputGenerator.randomDrug();
			int aid = a.GetDrugID();
			int bid = b.GetDrugID();
			map.put("drug_one", Integer.toString(aid));
			map.put("drug_two", Integer.toString(bid));
			StorageWrapper.Save("cs320.interacts_with", map);
			
			//Create a new HashMap that will test if the drugs appear under the interacts_with table
			HashMap <String, String> NewMap = new HashMap <String, String>();
			NewMap.put("drug_one", Integer.toString(aid));
			NewMap.put("drug_two", Integer.toString(bid));
			
			//Assert that the NewMap (with the same Drug IDs) exists within 
			//the interacts_with table
			Assert.assertTrue(StorageWrapper.Exist("cs320.interacts_with", NewMap));
			map.clear();
		}
			
	}
	
	
	
	
}