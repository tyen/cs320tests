package harness;


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
		String testType = "";
		if(args.length > 0)
			testType = args[0];
		testType = "system";
		harnessRun = new HarnessRun();
		harnessRun.save();
		String s = File.separator;
		File workingDirectory = new File(System.getProperty("user.dir")+s+"integration_team_a"+s+"target"+s+"reports"+s+testType);
		
		FilenameFilter fileFilter = new FilenameFilter(){
			public boolean accept(File dir, String name) {
				if(name.toLowerCase().trim().endsWith("xml"))
					return true;
				else
					return false;
			}
		};
		
		File files[] = workingDirectory.listFiles(fileFilter);
		
		// Create TestCase objects for each test case
		for(File file: files){
			XMLParser p = new XMLParser(file.getAbsolutePath());

			p.parseXMLFile();
			p.parseDocument();
			tests.addAll(p.getTests());
		}
		
		// Save all test cases to DB
		for(TestCase test : tests){
			test.setTestType(testType);
			test.setHarnessRunID(harnessRun.getId());
			test.save();
		}
		
		harnessRun.setEndTime();
		harnessRun.update();
		
		DBHandler.getInstance().disconnect();
	}

}
