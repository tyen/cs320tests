package TestReader;

import java.io.File;
import java.util.ArrayList;

public class TestReaderDriver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		XMLParser p = new XMLParser("test.xml");
		ArrayList<TestCase> t = new ArrayList<TestCase>();
		p.parseXMLFile();
		p.parseDocument();
		t = p.getTests();
		for (TestCase test : t)
			System.out.println(test);
		//File d = new File("../target");
		//System.out.println(d.isDirectory());
	}

}
