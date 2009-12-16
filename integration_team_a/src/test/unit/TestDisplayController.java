package test.unit;

import java.awt.Component;
import java.awt.Point;
import java.util.Random;
import test.InputGenerator;
import edu.cs320.project.*;
import junit.framework.TestCase;

public class TestDisplayController extends TestCase {
	/**
	 * Creates an instance of DisplayController through GetInstance()
	 * twice and checks if the two instances are the same object
	 */
	public void test_GetInstance() {
		DisplayController dispCont1 = null;
		assertNull(dispCont1);
		dispCont1 = DisplayController.GetInstance();
		assertNotNull(dispCont1);
		
		DisplayController displayCont2 = DisplayController.GetInstance();
		assertNotNull(displayCont2);
		assertEquals(dispCont1, displayCont2);
		assertSame(dispCont1, displayCont2);

		System.out.println("\nReturned: " + displayCont2 + "\nExpected: " + dispCont1);
	}
	
	/**
	 * Tests TearDown by creating an instance of DisplayController.
	 * TearDown removes all non-visible components from the Window,
	 * So an invisible DisplayObject is added, and then TearDown is
	 * called, and it should be removed
	 */
	public void test_TearDown() {
		DisplayController dispController = DisplayController.GetInstance();
		assertNotNull(dispController);
		DrugDisplay disp = new DrugDisplay();
		assertTrue(disp.isVisible());
		
		assertEquals(0, dispController.getNumDisplayedObjects());
		
		//Visible DisplayDrug at this point, display
		dispController.Display(disp);
		assertEquals(1, dispController.getNumDisplayedObjects());
		
		disp.setVisible(false);
		assertFalse(disp.isVisible());
		
		dispController.TearDown();
		assertEquals(0, dispController.getNumDisplayedObjects());
	}
	
	/**
	 * Creates a display object, asserting its location is (0,0)
	 * Changes the location of the object and Displays the
	 * object at (0,0) and asserts its location is (0,0)
	 */
	public void test_Display1() {
		DisplayObject d = new DisplayObject();
		Point p = new Point(0,0);
		assertTrue(d.getLocation().equals(p));
		d.setLocation(6,3);
		assertFalse(d.getLocation().equals(p));
		DisplayController dControl = DisplayController.GetInstance();
		dControl.Display(d, new Location(0,0));
		assertEquals(p, d.getLocation());
		System.out.println("\nReturned: " + d.getLocation() + "\nExpected: " + p);
	}
	
	/**
	 * Creates a display object, asserting its location != (4,2)
	 * Displays the object at (4,2) and asserts its location is (4,2)
	 */
	public void test_Display2() {
		DisplayObject d = new DisplayObject();
		Point p = new Point(4, 2);
		assertFalse(d.getLocation().equals(p));
		DisplayController dControl = DisplayController.GetInstance();
		dControl.Display(d, new Location(4,2));
		assertEquals(p, d.getLocation());
		System.out.println("\nReturned: " + d.getLocation() + "\nExpected: " + p);
	}
	
	/**
	 * Tests display a display object at a negative location
	 * If there is a negative display location, DisplayController
	 * will set the location to (0,0).  Displays the display object
	 * at (-3,-1), ensures it wasn't displayed there, and asserts
	 * that it was displayed at (0,0).
	 */
	public void test_Display3() {
		DisplayObject d = new DisplayObject();
		Point p = new Point(-3, -1);
		assertFalse(d.getLocation().equals(p));
		DisplayController dControl = DisplayController.GetInstance();
		dControl.Display(d, new Location(-3, -1));
		assertFalse(d.getLocation().equals(p));
		assertTrue(d.getLocation().equals(new Point(0,0)));
		System.out.println("\nReturned: " + d.getLocation() + "\nExpected: " + new Point(0,0));
	}
	
	/**
	 * Tests if displaying a null display object throws
	 * a null pointer exception, asserts false is true if the
	 * exception isn't caught, and asserts true is true if it is
	 * successfully thrown/caught
	 */
	public void test_Display4() {
		DisplayObject d = null;
		DisplayController dControl = DisplayController.GetInstance();
		try {
			dControl.Display(d, new Location(4,2));
			assertTrue(false);
			System.out.println("\nUnsuccessfully Caught Null Pointer Exception");
		}
		catch (NullPointerException e) {
			assertTrue(true);
			System.out.println("\nSuccessfully Caught Null Pointer Exception");
		}
	}
	
	/**
	 * Tests if Display with no location passed correctly
	 * displays the display object at 0,0
	 */
	public void test_Display5() {
		DisplayObject d = new DisplayObject();
		DisplayController dCont = DisplayController.GetInstance();
		dCont.Display(d);
		assertEquals(d.getLocation(), new Location(0,0));
		System.out.println("\nReturned: " + d.getLocation() + "\nExpected: " + new Point(0,0));
	}
	
	/**
	 * Tests displaying a display object at a null location
	 * Should throw a null pointer exception
	 */
	public void test_Display6() {
		DisplayObject d = new DisplayObject();
		Location l = null;
		DisplayController dCont = DisplayController.GetInstance();
		try {
			dCont.Display(d, l);
			assertTrue(false);
			System.out.println("\nUnsuccessfully Caught Null Pointer Exception");
		}
		catch (NullPointerException e) {
			assertTrue(true);
			System.out.println("\nSuccessfully Caught Null Pointer Exception");
		}
		
	}
	
	/**
	 * Tests setting current user name through the display controller
	 * by generating random strings and setting the user name as that
	 * and then asserting that the randomly created string is the same
	 * as the set user string
	 */
	public void test_setUserName() {
		DisplayController dCont = DisplayController.GetInstance();
		for (int i=0; i<10000; i++) {
			String uname;
			if (InputGenerator.randomInt()%13 == 0) {
				uname = null;
				dCont.SetUserNameDisplay();
				assertNotNull(dCont.GetCurrentUserName());
			}
			else {
				uname = InputGenerator.randomString();
				dCont.SetUserNameDisplay();
				assertEquals(uname, dCont.GetCurrentUserName());
			}			
		}
	}
	
	/**
	 * Runs a test of display 20000 times with random locations
	 * Generates 500 random positive and negative numbers to use as possible x,y values
	 * Cases: Null Display Object, Null Location, Positive and Negative Location values
	 */
	public void test_displayRandomInputs() {
		DisplayController dCont = DisplayController.GetInstance();
		DisplayObject disp = new DisplayObject();
		DisplayObject nullDisp = null;
		assertNull(nullDisp);
		Location nullLoc = null;
		assertNull(nullLoc);
		int[] possibleLocs = new int[500];
		Random r = new Random();
		for (int i=0; i<500; i++) {
			if (r.nextInt()%13 == 0)
				possibleLocs[i] = -1 * r.nextInt(600);
			else
				possibleLocs[i] = r.nextInt(600);
		}
		
		int x, y;
		Location l;
		for (int i=0; i<20000; i++) {
			x = possibleLocs[Math.abs(r.nextInt()%500)];
			y = possibleLocs[Math.abs(r.nextInt()%500)];
			l = new Location(x, y);
			
			if (r.nextInt()%17 == 0) {
				try {
					dCont.Display(nullDisp, l);
					assertTrue(false);
				}
				catch (NullPointerException e) {
					assertTrue(true);
				}
			}
				
			else if (r.nextInt()%11 == 0) {
				try {
					dCont.Display(disp, nullLoc);
					assertTrue(false);
				}
				catch (NullPointerException e) {
					assertTrue(true);
				}
			}
			else {
				dCont.Display(disp, l);
				if (x < 0 || y < 0)
					assertEquals(disp.getLocation(), new Location(0,0));
				else
					assertEquals(disp.getLocation(), new Location(x, y));
			}
		}
	}
}
