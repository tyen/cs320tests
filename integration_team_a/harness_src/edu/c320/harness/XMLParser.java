package edu.c320.harness;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class XMLParser {
	
	private Document dom;
	private ArrayList<TestCase> tests = new ArrayList<TestCase>();
	private File fileName;
	private String errorMessage;
	
	public XMLParser(String fileName){
		this.fileName = new File(fileName);
	}

	public void parseXMLFile(){
		//get the factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		try {

			//Using factory get an instance of document builder
			DocumentBuilder db = dbf.newDocumentBuilder();

			//parse using builder to get DOM representation of the XML file
			dom = db.parse(fileName);


		}catch(ParserConfigurationException pce) {
			pce.printStackTrace();
		}catch(SAXException se) {
			se.printStackTrace();
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	public void parseDocument(){
		//get the root element
		Element docEle = dom.getDocumentElement();
		

		//get a nodelist of  elements
		NodeList nl = docEle.getElementsByTagName("testcase");
	
		if(nl != null) {
			for(int i = 0 ; i < nl.getLength();i++) {
				//get the employee element
				Element el = (Element)nl.item(i);

				//get the Employee object
				TestCase t = getTestCase(el);
				
				//add it to list
				tests.add(t);
			}
		}
	}
	
	/**
	 * Takes in a testcase element and creates a TestCase object out of it
	 */
	private TestCase getTestCase(Element testEl) {

		//for each <testcase> element check if there exists a failure element and if
		//there is, set the test to indicate failure. Also grabs name and time attributes
		//of the test
		
		
		boolean isSuccessful = true;
		String failureMessage = "";
		if(getTextValue(testEl, "failure") != null){
			isSuccessful = false;
			failureMessage = getTextValue(testEl, "failure");
		}
		if(getTextValue(testEl, "error") != null){
			isSuccessful = false;
			errorMessage = getTextValue(testEl, "error");
		}
		String className = testEl.getAttribute("classname");
		String testName = testEl.getAttribute("name");
		String time = testEl.getAttribute("time");

		//Create a new TestCase with the value read from the xml nodes
		TestCase t = new TestCase(isSuccessful, className, testName, time);
		
		if(getTextValue(testEl, "failure") != null){
			t.setFailureMessage(failureMessage);
			t.setActual(t.getActual(failureMessage));
			t.setExpected(t.getExpected(failureMessage));
		}
		if(getTextValue(testEl, "error") != null){
			t.setError(errorMessage);
		}

		return t;
	}


	/**
	 * Takes an xml element and the tag name, look for the tag and get
	 * the text content 
	 */
	private String getTextValue(Element ele, String tagName) {
		String textVal = null;
		NodeList nl = ele.getElementsByTagName(tagName);
		if(nl != null && nl.getLength() > 0) {
			Element el = (Element)nl.item(0);
			textVal = el.getFirstChild().getNodeValue();
		}

		return textVal;
	}
	
	public ArrayList<TestCase> getTests(){
		return tests;
	}


}
