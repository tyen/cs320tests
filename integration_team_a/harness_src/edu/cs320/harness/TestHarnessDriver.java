package edu.c320.harness;


import java.io.File;
import java.io.FilenameFilter;
import java.util.LinkedList;

/**
 * Driver for the test harness
 * @author Sean Dooley
 *
 */
public class TestHarnessDriver {
	
	private static LinkedList<TestCase> tests = new LinkedList<TestCase>();
	private static HarnessRun harnessRun;
	
	public static void main(String[] args) {
		harnessRun = new HarnessRun();
		harnessRun.save();
		
		File workingDirectory = new File(System.getProperty("user.dir"));
		File files[] = workingDirectory.listFiles(new FilenameFilter(){
			public boolean accept(File dir, String name) {
				if(name.toLowerCase().endsWith(".xml"))
					return true;
				else
					return false;
			}
		});
		
		// Create TestCase objects for each test case
		for(File file: files){
			XMLParser p = new XMLParser(file.getAbsolutePath());

			p.parseXMLFile();
			p.parseDocument();
			tests.addAll(p.getTests());
		}
		
		// Save all test cases to DB
		for(TestCase test : tests){
			test.setHarnessRunID(harnessRun.getId());
			test.save();
		}
		
		harnessRun.setEndTime();
		harnessRun.update();
		
		DBHandler.getInstance().disconnect();
	}

}
