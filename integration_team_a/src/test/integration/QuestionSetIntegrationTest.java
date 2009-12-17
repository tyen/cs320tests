package test.integration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import edu.cs320.project.*;


import junit.framework.TestCase;

/**
 * Test cases for QuestionSet.
 * 
 * @author Tristan Peck
 */
public class QuestionSetIntegrationTest extends TestCase {
	
	private List<String>
		EmptyList,
		StandardOnly,
		NonstandardOnly,
		NonstandardOnly2,
		SAndN,
		SAndN2;
	private QuestionSet Set;
	
	/**
	 * @param name
	 */
	public QuestionSetIntegrationTest(String name) {
		super(name);
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {		
		super.setUp();
		this.Set = null;
		this.EmptyList = new LinkedList<String>();
		this.StandardOnly = Arrays.asList("S1", "S2", "S3");
		this.NonstandardOnly = Arrays.asList("N1", "N2", "N3");
		this.NonstandardOnly2 = Arrays.asList("N4", "N5", "N6, N7");
		this.SAndN = Arrays.asList("S1", "S2", "S3", "N1", "N2", "N3");
		this.SAndN2 = Arrays.asList("S1", "S2", "S3", "N4", "N5", "N6, N7");
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();

		this.Set = null;
		this.EmptyList = null;
		this.StandardOnly = null;
		this.NonstandardOnly = null;
		this.NonstandardOnly2 = null;
		this.SAndN = null;
		this.SAndN2 = null;
	}

	/**
	 * Tests that GetQuestions returns the exact contents of StaticQuestionSet after
	 * a QuestionSet is constructed and passed an empty list.
	 */
	public final void test_MakeEmptyQuestionSet() {
		this.Set = new QuestionSet(new LinkedList<String>());
		assertNotNull(this.Set);
		assertEquals(this.EmptyList, this.Set.GetQuestions());
		
		System.out.println("\nReturned: " + this.Set.GetQuestions() + "\nExpected: " + this.EmptyList);
	}

	/**
	 * Tests that GetQuestions returns the exact contents of StaticQuestionSet after
	 * a QuestionSet is constructed and passed a nonempty, non-null list.
	 */
	public final void test_MakeFilledQuestionSet() {
		this.Set = new QuestionSet(new LinkedList<String>(Arrays.asList("S1", "S2", "S3")));
		assertNotNull(this.Set);
		assertEquals(this.StandardOnly, this.Set.GetQuestions());
		
		System.out.println("\nReturned: " + this.Set.GetQuestions() + "\nExpected: " + this.StandardOnly);
	}

	/**
	 * Tests that GetQuestions returns the exact contents of DynamicQuestionSet after
	 * a nonempty, non-null list is passed to UpdateQuestionSet in a QuestionSet initialized with empty
	 * StaticQuestionSet and empty DynamicQuestionSet.
	 */
	public final void test_PushListToEmptyQuestionSet() {
		this.Set = new QuestionSet(new LinkedList<String>());
		assertNotNull(this.Set);
		assertEquals(this.EmptyList, this.Set.GetQuestions());
		this.Set.UpdateQuestionSet(Arrays.asList("N1", "N2", "N3"));
		assertEquals(this.NonstandardOnly, this.Set.GetQuestions());
		
		System.out.println("\nReturned: " + this.Set.GetQuestions() + "\nExpected: " + this.NonstandardOnly);
	}

	/**
	 * Tests that GetQuestions returns the exact contents of DynamicQuestionSet after
	 * a nonempty, non-null list is passed to UpdateQuestionSet in a QuestionSet initialized with empty
	 * StaticQuestionSet and nonempty, non-null DynamicQuestionSet.
	 */
	public final void test_PushListToEmptyQuestionSet2() {
		this.Set = new QuestionSet(new LinkedList<String>());
		assertNotNull(this.Set);
		this.Set.UpdateQuestionSet(Arrays.asList("N4", "N5", "N6, N7"));
		assertEquals(this.NonstandardOnly2, this.Set.GetQuestions());
		this.Set.UpdateQuestionSet(Arrays.asList("N1", "N2", "N3"));
		assertEquals(this.NonstandardOnly, this.Set.GetQuestions());
		
		System.out.println("\nReturned: " + this.Set.GetQuestions() + "\nExpected: " + this.NonstandardOnly);
	}

	/**
	 * Tests that GetQuestions returns a merger of StaticQuestionSet and DynamicQuestionSet after
	 * a nonempty, non-null list is passed to UpdateQuestionSet in a QuestionSet initialized with nonempty, non-null
	 * StaticQuestionSet and empty DynamicQuestionSet.
	 */
	public final void test_PushListToFilledQuestionSet() {
		this.Set = new QuestionSet(new LinkedList<String>(Arrays.asList("S1", "S2", "S3")));
		assertNotNull(this.Set);
		assertEquals(this.StandardOnly, this.Set.GetQuestions());
		this.Set.UpdateQuestionSet(Arrays.asList("N1", "N2", "N3"));
		assertEquals(this.SAndN, this.Set.GetQuestions());
		
		System.out.println("\nReturned: " + this.Set.GetQuestions() + "\nExpected: " + this.SAndN);
	}

	/**
	 * Tests that GetQuestions returns a merger of StaticQuestionSet and DynamicQuestionSet after
	 * a nonempty, non-null list is passed to UpdateQuestionSet in a QuestionSet initialized with nonempty, non-null
	 * StaticQuestionSet and nonempty, non-null DynamicQuestionSet.
	 */
	public final void test_PushListToFilledQuestionSet2() {
		this.Set = new QuestionSet(new LinkedList<String>(Arrays.asList("S1", "S2", "S3")));
		assertNotNull(this.Set);
		this.Set.UpdateQuestionSet(Arrays.asList("N4", "N5", "N6, N7"));
		assertEquals(this.SAndN2, this.Set.GetQuestions());
		this.Set.UpdateQuestionSet(Arrays.asList("N1", "N2", "N3"));
		assertEquals(this.SAndN, this.Set.GetQuestions());
		
		System.out.println("\nReturned: " + this.Set.GetQuestions() + "\nExpected: " + this.SAndN);
	}

	/**
	 * Tests that GetQuestions returns an empty list after
	 * an empty list is passed to UpdateQuestionSet in a QuestionSet initialized with empty
	 * StaticQuestionSet and empty DynamicQuestionSet.
	 */
	public final void test_PushEmptyToEmptyQuestionSet() {
		this.Set = new QuestionSet(new LinkedList<String>());
		assertNotNull(this.Set);
		assertEquals(this.EmptyList, this.Set.GetQuestions());
		this.Set.UpdateQuestionSet(new LinkedList<String>());
		assertEquals(this.EmptyList, this.Set.GetQuestions());
		
		System.out.println("\nReturned: " + this.Set.GetQuestions() + "\nExpected: " + this.EmptyList);
	}
	
	/**
	 * Tests that GetQuestions returns an empty list after
	 * an empty list is passed to UpdateQuestionSet in a QuestionSet initialized with empty
	 * StaticQuestionSet and nonempty, non-null DynamicQuestionSet.
	 */
	public final void test_PushEmptyToEmptyQuestionSet2() {
		this.Set = new QuestionSet(new LinkedList<String>());
		assertNotNull(this.Set);
		this.Set.UpdateQuestionSet(Arrays.asList("N1", "N2", "N3"));
		assertEquals(this.NonstandardOnly, this.Set.GetQuestions());
		this.Set.UpdateQuestionSet(new LinkedList<String>());
		assertEquals(this.EmptyList, this.Set.GetQuestions());
		
		System.out.println("\nReturned: " + this.Set.GetQuestions() + "\nExpected: " + this.EmptyList);
	}

	/**
	 * Tests that GetQuestions returns the exact contents of StaticQuestionSet after
	 * an empty list is passed to UpdateQuestionSet in a QuestionSet initialized with nonempty, non-null
	 * StaticQuestionSet and empty DynamicQuestionSet.
	 */
	public final void test_PushEmptyToFilledQuestionSet() {
		this.Set = new QuestionSet(new LinkedList<String>(Arrays.asList("S1", "S2", "S3")));
		assertNotNull(this.Set);
		assertEquals(this.StandardOnly, this.Set.GetQuestions());
		this.Set.UpdateQuestionSet(new LinkedList<String>());
		assertEquals(this.StandardOnly, this.Set.GetQuestions());
		
		System.out.println("\nReturned: " + this.Set.GetQuestions() + "\nExpected: " + this.StandardOnly);
	}

	/**
	 * Tests that GetQuestions returns the exact contents of StaticQuestionSet after
	 * an empty list is passed to UpdateQuestionSet in a QuestionSet initialized with nonempty, non-null
	 * StaticQuestionSet and nonempty, non-null DynamicQuestionSet.
	 */
	public final void test_PushEmptyToFilledQuestionSet2() {
		this.Set = new QuestionSet(new LinkedList<String>(Arrays.asList("S1", "S2", "S3")));
		assertNotNull(this.Set);
		this.Set.UpdateQuestionSet(Arrays.asList("N1", "N2", "N3"));
		assertEquals(this.SAndN, this.Set.GetQuestions());
		this.Set.UpdateQuestionSet(new LinkedList<String>());
		assertEquals(this.StandardOnly, this.Set.GetQuestions());
		
		System.out.println("\nReturned: " + this.Set.GetQuestions() + "\nExpected: " + this.StandardOnly);
	}

	/**
	 * Tests that GetQuestions returns an empty List of String after
	 * null is passed to UpdateQuestionSet in a QuestionSet initialized with empty
	 * StaticQuestionSet and empty DynamicQuestionSet.
	 */
	public final void test_PushNullToEmptyQuestionSet() {
		this.Set = new QuestionSet(new LinkedList<String>());
		assertNotNull(this.Set);
		assertEquals(this.EmptyList, this.Set.GetQuestions());
		this.Set.UpdateQuestionSet(null);
		assertEquals(this.EmptyList, this.Set.GetQuestions());
		
		System.out.println("\nReturned: " + this.Set.GetQuestions() + "\nExpected: " + this.EmptyList);
	}

	/**
	 * Tests that GetQuestions returns an empty List of String after
	 * null is passed to UpdateQuestionSet in a QuestionSet initialized with empty
	 * StaticQuestionSet and nonempty, non-null DynamicQuestionSet.
	 */
	public final void test_PushNullToEmptyQuestionSet2() {
		this.Set = new QuestionSet(new LinkedList<String>());
		assertNotNull(this.Set);
		this.Set.UpdateQuestionSet(Arrays.asList("N1", "N2", "N3"));
		assertEquals(this.NonstandardOnly, this.Set.GetQuestions());
		this.Set.UpdateQuestionSet(null);
		assertEquals(this.EmptyList, this.Set.GetQuestions());
		
		System.out.println("\nReturned: " + this.Set.GetQuestions() + "\nExpected: " + this.EmptyList);
	}

	/**
	 * Tests that GetQuestions returns the exact contents of StaticQuestionSet after
	 * null is passed to UpdateQuestionSet in a QuestionSet initialized with nonempty, non-null
	 * StaticQuestionSet and empty DynamicQuestionSet.
	 */
	public final void test_PushNullToFilledQuestionSet() {
		this.Set = new QuestionSet(new LinkedList<String>(Arrays.asList("S1", "S2", "S3")));
		assertNotNull(this.Set);
		assertEquals(this.StandardOnly, this.Set.GetQuestions());
		this.Set.UpdateQuestionSet(null);
		assertEquals(this.StandardOnly, this.Set.GetQuestions());
		
		System.out.println("\nReturned: " + this.Set.GetQuestions() + "\nExpected: " + this.StandardOnly);
	}
	
	/**
	 * Tests that GetQuestions returns the exact contents of StaticQuestionSet after
	 * null is passed to UpdateQuestionSet in a QuestionSet initialized with nonempty, non-null
	 * StaticQuestionSet and nonempty, non-null DynamicQuestionSet.
	 */
	public final void test_PushNullToFilledQuestionSet2() {
		this.Set = new QuestionSet(new LinkedList<String>(Arrays.asList("S1", "S2", "S3")));
		assertNotNull(this.Set);
		this.Set.UpdateQuestionSet(Arrays.asList("N1", "N2", "N3"));
		assertEquals(this.SAndN, this.Set.GetQuestions());
		this.Set.UpdateQuestionSet(null);
		assertEquals(this.StandardOnly, this.Set.GetQuestions());
		
		System.out.println("\nReturned: " + this.Set.GetQuestions() + "\nExpected: " + this.StandardOnly);
	}
	
	/**
	 * Tests that GetQuestions returns the exact questions described in a full Map after
	 * this Map is passed to the QuestionSet's constructor.
	 */
	public final void test_PassFullMapToQuestionSet() {
		List<HashMap<String, String>> list = new LinkedList<HashMap<String, String>>();
		assertNotNull(list);
		
		HashMap<String, String> map0 = new HashMap<String, String>();
		map0.put("number", "0");
		map0.put("question", "S1");
		assertEquals(map0.size(), 2);
		
		HashMap<String, String> map1 = new HashMap<String, String>();
		map1.put("number", "1");
		map1.put("question", "S2");
		assertEquals(map1.size(), 2);
		
		HashMap<String, String> map2 = new HashMap<String, String>();
		map2.put("number", "2");
		map2.put("question", "S3");
		assertEquals(map2.size(), 2);
		
		list.add(map0);
		list.add(map1);
		list.add(map2);
		assertEquals(list.size(), 3);
		
		this.Set = new QuestionSet(list);
		assertNotNull(this.Set);
		List<String> l = this.Set.GetQuestions();
		assertEquals(this.StandardOnly, this.Set.GetQuestions());
		
		System.out.println("\nReturned: " + this.Set.GetQuestions() + "\nExpected: " + this.StandardOnly);
	}
	
	/**
	 * Tests that GetQuestions returns no questions after
	 * an empty Map is passed to the QuestionSet's constructor.
	 */
	public final void test_PassEmptyMapToQuestionSet() {
		List<HashMap<String, String>> list = new LinkedList<HashMap<String, String>>();
		assertEquals(list.size(), 0);
		
		this.Set = new QuestionSet(list);
		assertNotNull(this.Set);
		assertEquals(this.EmptyList, this.Set.GetQuestions());
		
		System.out.println("\nReturned: " + this.Set.GetQuestions() + "\nExpected: " + this.EmptyList);
	}
}
