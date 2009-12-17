package test.system;

import java.awt.AWTException;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import test.SmartRobot;

public class HelpSR extends SmartRobot
{

	public HelpSR() throws AWTException 
	{
		super();
	}
	public void login()
	{
		mouseMove(470,260);
		mouseClick();
		type("cs320");
		mouseMove(470,310);
		mouseClick();
		type("cs320");
		mouseMove(470,370);
		mouseClick();
	}
	
	public void searchForNewPatient(String x, String y, String z)
	{
		mouseMove(420,230);
		mouseClick();
		type(x);
		mouseMove(420,260);
		mouseClick();
		type(y);
		mouseMove(420,290);
		mouseClick();
		type(z);
		mouseMove(420,360);
		mouseClick();
		
		delay(1000);
		//Hits the "ok" button for new patient prompt
		mouseMove(550,340);
		mouseDoubleClick();
		mouseDoubleClick();
	}
	public void searchForManyPatient(String x, String y, String z)
	{
		mouseMove(420,230);
		mouseClick();
		type(x);
		mouseMove(420,260);
		mouseClick();
		type(y);
		mouseMove(420,290);
		mouseClick();
		type(z);
		mouseMove(420,360);
		mouseClick();
		//Picks first patient in list
		mouseMove(200,110);
		mouseDoubleClick();
		mouseMove(430,450);
		mouseDoubleClick();
	}
	public void searchForOldPatient(String x, String y, String z)
	{
		mouseMove(420,230);
		mouseClick();
		type(x);
		mouseMove(420,260);
		mouseClick();
		type(y);
		mouseMove(420,290);
		mouseClick();
		type(z);
		mouseMove(420,360);
		mouseClick();
	}
	
	public void fillNewPatientOut(ArrayList<String> patient, ArrayList<String> allergy, ArrayList<String> drug, int question)
	{
		//PatientID box
		mouseMove(470,70);
		mouseClick();
		type(patient.get(0));
		
		//weight
		mouseMove(290,120);
		mouseClick();
		mousePress(KeyEvent.BUTTON1_MASK);
		mouseMove(300,120);
		mouseRelease(KeyEvent.BUTTON1_MASK);	
		type(patient.get(1));

		//height
		mouseMove(200,120);
		mouseClick();
		mousePress(KeyEvent.BUTTON1_MASK);
		mouseMove(210,120);
		mouseRelease(KeyEvent.BUTTON1_MASK);	
		type(patient.get(2));
		
		//address
		mouseMove(50,160);
		mouseClick();
		type(patient.get(3));
		

		
		addAllergy(allergy);
		
		addDrug(drug,question);
	
		//notes
		mouseMove(10,530);
		mouseClick();	
		mousePress(KeyEvent.BUTTON1_MASK);
		mouseMove(50,530);
		mouseRelease(KeyEvent.BUTTON1_MASK);		
		type(patient.get(4));		
		
		//submit
		mouseMove(710,570);
		mouseDoubleClick();
		
		mouseMove(710,585);
		mouseDoubleClick();		
		
	}
	
	public void addAllergy(ArrayList<String> allergy)
	{
		//Click button
		mouseMove(520,300);
		mouseClick();
		
		
		//allergy field
		mouseMove(50,222);
		mouseClick();
		type(allergy.get(0));
		
		//reaction field
		mouseMove(225,222);
		mouseClick();
		type(allergy.get(1));
		
		//Click submit button
		mouseMove(520,260);
		mouseClick();
	}
	
	public void addDrug(ArrayList<String> drug, int y)
	{
		
		int questionable = y;
		//click button
		mouseMove(520,500);
		mouseClick();
		
		//name
		mouseMove(70,335);
		mouseClick();
		type(drug.get(0));
		
		//Dosage
		mouseMove(200,335);
		mouseClick();
		mousePress(KeyEvent.BUTTON1_MASK);
		mouseMove(230,335);
		mouseRelease(KeyEvent.BUTTON1_MASK);
		type(drug.get(1));
		
		//Unit
		mouseMove(280,335);
		mouseClick();
		type(drug.get(2));
		
		//Route
		mouseMove(330,335);
		mouseClick();
		type(drug.get(3));

		//Frequency
		mouseMove(450,335);
		mouseClick();
		type(drug.get(4));
		
		//Start
		mouseMove(55,380);
		mouseClick();
		mousePress(KeyEvent.BUTTON1_MASK);
		mouseMove(130,380);
		mouseRelease(KeyEvent.BUTTON1_MASK);
		type(drug.get(5));
		
		//Stop
		mouseMove(160,380);
		mouseClick();
		mousePress(KeyEvent.BUTTON1_MASK);
		mouseMove(225,380);
		mouseRelease(KeyEvent.BUTTON1_MASK);
		type(drug.get(6));
		
		//Prescriber
		mouseMove(280,385);
		mouseClick();
		type(drug.get(7));
		
		//Notes
		mouseMove(55,425);
		mouseClick();
		type(drug.get(8));
		
		
		//IsQuestionable
		if(questionable==1)
		{	
			mouseMove(75,465);
			mouseClick();
		}
		
		//save button
		mouseMove(460,465);
		mouseClick();
		
	}
	
	
	
	

}
