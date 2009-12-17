package test.system;

import java.awt.AWTException;
import java.util.ArrayList;

import test.Utility;

import edu.cs320.project.DisplayController;
import edu.cs320.project.LoginDisplay;
import edu.cs320.project.StorageWrapper;

public class SystemTest 
{
	public static DisplayController cont;
	public static HelpSR ace; 
	private static LoginDisplay loginDisplay;
	public static void main(String[] args) 
	{
		setup();
		
		ace.login();
		
		ace.searchForNewPatient("William1939","Stumpf","06/29/88");
		
		ArrayList<String> patient = new ArrayList<String>(); 
		patient.add("32432");//id
		patient.add("100");//weight
		patient.add("80");//height
		patient.add("179 Statesville Quarry Road");
		patient.add("This guy is crazy");//notes
		
		ArrayList<String> allergy = new ArrayList<String>();
		allergy.add("chocolate");//allergy
		allergy.add("sickness");//reaction
		
		ArrayList<String> drug = new ArrayList<String>();
		drug.add("advil");//name
		drug.add("1");//dosage
		drug.add("mm");//unit
		drug.add("Oral");//route
		drug.add("6");//frequency
		drug.add("12/1/2009");//start
		drug.add("12/10/2009");//stop
		drug.add("Dr. Ace");//Prescriber
		drug.add("I love drugs");//notes
		//drug.add("Pain");//Reason
		
		int questionable = 1;
		
		ace.fillNewPatientOut(patient,allergy,drug,questionable);
		
		Utility.searchForPatient("William1939","Stumpf","06/29/88");
		

	}
	
	public static void setup()
	{
		StorageWrapper.deleteFromClient("cs320.patient");
		cont = DisplayController.GetInstance();
		cont.main(null);
		loginDisplay = (LoginDisplay) DisplayController.GetInstance().getCurrentDisplay();
		//DisplayController.main(null);
		try 
		{
			ace = new HelpSR();
		} 
		catch (AWTException e) 
		{
			e.printStackTrace();
		}
	}
	
}
