package test.unit;

import java.util.HashMap;
import java.util.Map;
import edu.cs320.project.*;
import test.InputGenerator;
import junit.framework.TestCase;

/**
 * @author Louis DeMaria
 */
public class TestAllergy extends TestCase {

	/**
	 * Constructor.
	 */
	public TestAllergy(String name) {
		super(name);
	}

	/**
	 * SetUp Method.
	 */
	protected void setUp() throws Exception {
		super.setUp();
		
		key = InputGenerator.randomString();
		value = InputGenerator.randomString();
		map = new HashMap<String, String>();
		map.put(key, value);
		allergy = new Allergy(map);
	}
	
	/**
	 * A String that is used to create 'key' value of a HashMap.
	 */
	String key;
	
	/**
	 * A String that is used to create a 'value' value of a HashMap.
	 */
	String value;
	
	/**
	 * A map that is used to create an instance of Allergy.
	 */
	Map<String, String> map;
	
	/**
	 * The number of times that each test is run.
	 */
	int TEST_ITERATIONS = 1000;
	
	/**
	 * An instance of the Allergy module that will be tested.
	 */
	Allergy allergy = InputGenerator.randomAllergy();
	
	/**
	 * Test that tests the 'Cause' getter and setter methods.
	 */
	public void testGetSetCause(){
		for(int i=0;i<TEST_ITERATIONS;i++){
			String testString = InputGenerator.randomString();
			allergy.SetCause(testString);
			assertEquals(testString, allergy.GetCause());
		}
	}
	
	/**
	 * Test that tests the 'Reaction' getter and setter methods.
	 */
	public void testGetSetReaction(){
		for(int i=0;i<TEST_ITERATIONS;i++){
			String testString = InputGenerator.randomString();
			allergy.SetReaction(testString);
			assertEquals(testString, allergy.GetReaction());
		}
	}
	
	/**
	 * Test that tests the Save function.
	 */
	public void testSave(){
		for(int i=0;i<TEST_ITERATIONS;i++){
			assertEquals(true, allergy.Save());
		}
	}
	
	/**
	 * Test that tests Allergy's public constructor.
	 */
	public void testPublicConstructor1(){
		for(int i=0;i<TEST_ITERATIONS;i++){
			
		}
	}
	
	/**
	 * Test that tests Allergy's public constructor.
	 */
	public void testPublicConstructor2(){
		for(int i=0;i<TEST_ITERATIONS;i++){
			
		}
	}

	/**
	 * TearDown Method.
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}
}