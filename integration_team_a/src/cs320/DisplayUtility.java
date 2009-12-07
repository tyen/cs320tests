import java.util.*;

/**
 * The Display Utility is a sub-module of the Display Controller.
 * It provides a method for each displayable module or object within the
 * application. These methods create a DisplayObject and pass it to the
 * Display Controller. It then returns whether those DisplayObjects were
 * created successfully.
 */

public class DisplayUtility {
	private static DisplayUtility Instance = null;
	private DisplayController DispController;
	private HashMap<Object, DisplayObject> InterfaceExists = new HashMap<Object, DisplayObject>();
	
	/**
	* Protected Constructor for DisplayUtility
	*/
	protected DisplayUtility() {
		DispController = DisplayController.GetInstance();
	}
	
	/**
	 * Returns a singleton instance of DisplayUtility
	 * @return Singleton Instance of DisplayUtility
	 */
	public static DisplayUtility GetInstance() {
		if (Instance == null)
			Instance = new DisplayUtility();
		return Instance;
	}
	
	/**
	* When a Display() function is called, the Display Utility needs
	* to check if Object ToBeDisplayed has a DisplayObject that already
	* exists. If it exists, then the DisplayObject can simply be updated,
	* rather than created again. The ObjectExists() function checks the
	* interfaceExists Map for the object that was passed to Display()
	* for an existing DisplayObject, and returns True if one exists,
	* and False if one does not.
	* @param	ToBeDisplayed	The object that might have an interface associated with it already.
	* @return	True if there is an interface associated with toBeDisplayed, False otherwise.
	*/
	protected boolean ObjectExists(Object ToBeDisplayed) {
		return InterfaceExists.containsKey(ToBeDisplayed);
	}
	
	public boolean ObjectExistsTest(Object ToBeDisplayed) {
		return ObjectExists(ToBeDisplayed);
	}
	
	/**
	* Creates a DisplayObject for the passed PatientRecord, and passes this
	* DisplayObject to the Display Controller for display. This function
	* checks if the PatientRecord record has a corresponding DisplayObject
	* that already exists. If one does, it is updated rather than created.
	* If the creation or update and display occur successfully, this method
	* returns True, otherwise False.
	* @param	Record	PatientRecord to be displayed.
	* @return	True if creation/updating of DisplayObject succeeds, false if it fails
	*/
	public boolean Display(PatientRecord Record) {
		if (ObjectExists(Record)) {
			DisplayObject DisplayRecord = InterfaceExists.get(Record);
			
			//Update interface
			
			InterfaceExists.put(Record, DisplayRecord);
			
			DispController.Display(DisplayRecord);
			
			return true;
		}
		else {
			DisplayObject DisplayRecord = new PatientRecordDisplay();
			
			InterfaceExists.put(Record, DisplayRecord);
			
			DispController.Display(DisplayRecord);
			
			return true;
		}
	}
	
	/**
	* Creates a DisplayObject for the passed Allergy, and passes this
	* DisplayObject to the Display Controller for display. This function
	* checks if the Allergy allergy has a corresponding DisplayObject
	* that already exists. If one does, it is updated rather than created.
	* If the creation or update and display occur successfully, this method
	* returns True, otherwise False.
	* @param	AllergyToDisplay	Allergy to be displayed.
	* @return	True if creation/updating of DisplayObject succeeds, false if it fails
	*/
	public boolean Display(Allergy AllergyToDisplay) {
		if (ObjectExists(AllergyToDisplay)) {
			DisplayObject DisplayAllergy = InterfaceExists.get(AllergyToDisplay);
			
			//Update interface
			
			InterfaceExists.put(AllergyToDisplay, DisplayAllergy);
			
			DispController.Display(DisplayAllergy);
			
			return true;
		}
		else {
			DisplayObject DisplayAllergy = new AllergyDisplay();
		
			InterfaceExists.put(AllergyToDisplay, DisplayAllergy);
			
			DispController.Display(DisplayAllergy, new Location(30, 210));
			
			return true;
		}
	}
	
	/**
	* Creates a DisplayObject for the passed Drug, and passes this
	* DisplayObject to the Display Controller for display. This function
	* checks if the Drug drug has a corresponding DisplayObject
	* that already exists. If one does, it is updated rather than created.
	* If the creation or update and display occur successfully, this method
	* returns True, otherwise False.
	* @param	DrugToDisplay	Drug to be displayed.
	* @return	True if creation/updating of DisplayObject succeeds, false if it fails
	*/
	public boolean Display(Drug DrugToDisplay) {
		if (ObjectExists(DrugToDisplay)) {
			DisplayObject DisplayDrug = InterfaceExists.get(DrugToDisplay);
			
			//Update interface
			
			DispController.Display(DisplayDrug);
			
			InterfaceExists.put(DrugToDisplay, DisplayDrug);
			
			return true;
		}
		else {
			DisplayObject DisplayDrug = new DrugDisplay();
			
			InterfaceExists.put(DrugToDisplay, DisplayDrug);
			
			DispController.Display(DisplayDrug, new Location(30, 60));
			
			return true;
		}
	}
	
	/**
	* Creates a DisplayObject for the passed PatientInfo, and passes this
	* DisplayObject to the Display Controller for display. This function
	* checks if the PatientInfo info has a corresponding DisplayObject
	* that already exists. If one does, it is updated rather than created.
	* If the creation or update and display occur successfully, this method
	* returns True, otherwise False.
	* @param	Info	PatientInfo to be displayed.
	* @return	True if creation/updating of DisplayObject succeeds, false if it fails
	*/
	public boolean Display(PatientInfo Info) {
		if (ObjectExists(Info)) {
			DisplayObject DisplayInfo = InterfaceExists.get(Info);
			
			//Update Interface
			
			InterfaceExists.put(Info, DisplayInfo);
			
			DispController.Display(DisplayInfo);
			
			return true;
		}
		else {
			DisplayObject DisplayInfo = new PatientInfoDisplay();
			
			InterfaceExists.put(Info, DisplayInfo);
			
			DispController.Display(DisplayInfo);
			
			return true;
		}
	}
	
	/**
	* Creates a DisplayObject for the passed QuestionSet, and passes this
	* DisplayObject to the Display Controller for display. This function
	* checks if the QuestionSet questions has a corresponding DisplayObject
	* that already exists. If one does, it is updated rather than created.
	* If the creation or update and display occur successfully, this method
	* returns True, otherwise False.
	* @param	Questions	QuestionSet to be displayed.
	* @return	True if creation/updating of DisplayObject succeeds, false if it fails
	*/
	public boolean Display(QuestionSet Questions) {
		if (ObjectExists(Questions)) {
			DisplayObject DisplayQuestions = InterfaceExists.get(Questions);
			
			//Update interface
			
			InterfaceExists.put(Questions, DisplayQuestions);
			
			DispController.Display(DisplayQuestions);
			
			return true;
		}
		else {
			DisplayObject DisplayQuestions = new QuestionDisplay();
			
			InterfaceExists.put(Questions, DisplayQuestions);
			
			DispController.Display(DisplayQuestions);
			
			return true;
		}
	}
	
	/**
	* Creates a DisplayObject for the passed Search, and passes this
	* DisplayObject to the Display Controller for display. This function
	* checks if the Search search has a corresponding DisplayObject
	* that already exists. If one does, it is updated rather than created.
	* If the creation or update and display occur successfully, this method
	* returns True, otherwise False.
	* @param	SearchToDisplay	Search to be displayed.
	* @return	True if creation/updating of DisplayObject succeeds, false if it fails
	*/
	public boolean Display(Search SearchToDisplay) {
		if (ObjectExists(SearchToDisplay)) {
			DisplayObject displaySearch = InterfaceExists.get(SearchToDisplay);
			
			//Update interface
			 
			InterfaceExists.put(SearchToDisplay, displaySearch);
			
			DispController.Display(displaySearch);
			
			return true;
		}
		else {
			DisplayObject displaySearch = new SearchMainDisplay();
			
			InterfaceExists.put(SearchToDisplay, displaySearch);
			
			DispController.Display(displaySearch);
			
			return true;
		}
	}
	/**
	* Creates a DisplayObject for the passed Login, and passes this
	* DisplayObject to the Display Controller for display. This function
	* checks if the Login login has a corresponding DisplayObject
	* that already exists. If one does, it is updated rather than created.
	* If the creation or update and display occur successfully, this method
	* returns True, otherwise False.
	* @param	LoginToDisplay	Login to be displayed.
	* @return	True if creation/updating of DisplayObject succeeds, false if it fails
	*/
	public boolean Display(Login LoginToDisplay) {
		if (ObjectExists(LoginToDisplay)) {
			DisplayObject DisplayLogin = InterfaceExists.get(LoginToDisplay);
			
			InterfaceExists.put(LoginToDisplay, DisplayLogin);
			
			DispController.Display(DisplayLogin);
			
			return true;
		}
		else {
			DisplayObject DisplayLogin = new LoginDisplay();
			
			InterfaceExists.put(LoginToDisplay, DisplayLogin);
			
			DispController.Display(DisplayLogin, new Location(0, 20));
			
			return true;
		}	
	}
	

}
