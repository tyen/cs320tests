package cs320;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.ListIterator;
import java.util.List;

/**
 * PatientRecord
 * <p>
 * The PatientRecord encapsulates all of the current patient information and
 * provides display and store functions to the utility modules. Contains a
 * PatientInfo module identifying the patient, a QuestionSet module to provide
 * questions for the passive questionnaire, and multiple Drug and Allergy
 * modules representing the drugs and/or allergies identified during the
 * interview.
 */
public class PatientRecord extends AbstractModule {

	private String P_ID;
	private static PatientRecord instance;
	private LinkedList<Drug> DrugsList;
	private LinkedList<Allergy> AllergyList;
	private LinkedList<String> NotesList;
	private QuestionSet Questions;
	private PatientInfo Patient;
	private String RecordCreator;
	private String LastModifiedBy;
	private DateWrapper DateCreated;
	private DateWrapper DateLastModified;
	boolean AppendOnly;
	boolean SummaryMode;

	/**
	 * Is private. We create a PatientRecord by calling the
	 * createPatientRecord() method.
	 * 
	 * @param void
	 * @return an 'empty' PatientRecord object with default field values
	 */
	private PatientRecord() {

		this.DrugsList = new LinkedList<Drug>();
		this.AllergyList = new LinkedList<Allergy>();
		this.NotesList = new LinkedList<String>();
		this.Questions = new QuestionSet(new LinkedList<String>());
		this.Patient = new PatientInfo();
		this.RecordCreator = DisplayController.GetInstance()
				.GetCurrentUserName();
		this.LastModifiedBy = DisplayController.GetInstance()
				.GetCurrentUserName();
		this.DateCreated = new DateWrapper();
		this.DateLastModified = new DateWrapper();
		this.AppendOnly = false;
		this.SummaryMode = false;
	}

	/**
	 * Constructor: Creates a PatientRecord corresponding to the PatientRecord
	 * held in Storage that corresponds to the Input Patient.
	 * 
	 * @param PatientInfo
	 *            patient
	 */
	private PatientRecord(PatientInfo patient) {
		new PatientRecord(GetPatientFromStorage(patient));

	}

	/**
	 * Constructor: Accepts a Hashmap of Strings that consitutes a
	 * PatientRecord, and creates a PatientRecord filled in with the information
	 * contained within the Hashmap. Also looks up associated information
	 * (Drugs, Allergies, etc.).
	 * 
	 * @param HashMap
	 *            <String,String> storable
	 */
	public PatientRecord(HashMap<String, String> storable) {

		String pid = storable.get("p_id");
		this.P_ID = pid;

		Iterator<String> it = storable.keySet().iterator();

		while (it.hasNext()) {

			String field = it.next();

			if (field == "RecordCreator")
				RecordCreator = storable.get(field);

			if (field == "LastModifiedBy")
				LastModifiedBy = storable.get(field);

			if (field == "DateCreated")
				DateCreated = new DateWrapper(storable.get(field));

			if (field == "DateLastModified")
				DateLastModified = new DateWrapper(storable.get(field));

			if (field == "AppendOnly")
				AppendOnly = (storable.get(field) == "true");

			if (field == "SummaryMode")
				SummaryMode = (storable.get(field) == "true");
		}// end while loop

		HashMap<String, String> inputMap = new HashMap<String, String>();
		inputMap.put("p_id", pid);

		/*** Grabbing Existing Lists from Storage ****/
		List<HashMap<String, String>> drugResults = Storage.GetInstance()
				.Retrieve("cs320.Drug", inputMap);
		for (HashMap<String, String> map : drugResults)
			DrugsList.add(new Drug(map));

		List<HashMap<String, String>> allergyResults = Storage.GetInstance()
				.Retrieve("cs320.Allergy", inputMap);
		for (HashMap<String, String> map : allergyResults)
			AllergyList.add(new Allergy(map));

		List<HashMap<String, String>> noteResults = Storage.GetInstance()
				.Retrieve("cs320.Note", inputMap);
		for (HashMap<String, String> map : noteResults)
			NotesList.add(map.get("Note"));

		Patient = new PatientInfo(Storage.GetInstance().Retrieve(
				"cs320.Patient", inputMap).get(0));

	}

	public HashMap<String, String> toHashMap() {

		HashMap<String, String> internalsMap = GetPatientFromStorage(Patient);
		internalsMap.put("RecordCreator", this.RecordCreator);
		internalsMap.put("LastModifiedBy", this.LastModifiedBy);
		internalsMap.put("DateCreated", this.DateCreated.toString());
		internalsMap.put("DateLastModified", this.DateLastModified.toString());
		internalsMap.put("AppendOnly", "" + this.AppendOnly);
		internalsMap.put("SummaryMode", "" + this.SummaryMode);
		return internalsMap;
	}

	/**
	 * Static Factory Method for a new PatientRecord.
	 * 
	 * @param PatientInfo
	 * @return a patient record populated with data from storage matching the
	 *         input patient
	 */
	public static PatientRecord createPatientRecord(PatientInfo patient) {
		return (patient == null) ? new PatientRecord() : new PatientRecord(
				patient);
	}

	/**
	 * Returns a singleton instance of the PatientRecord object, allowing other
	 * modules to access fields that they need.
	 * 
	 * @param void
	 * @return the singleton PatientRecord
	 */
	public static PatientRecord GetInstance() {
		if (instance == null)
			instance = new PatientRecord();
		return instance;
	}

	/**
	 * Adds a note to the end of the list of notes and returns whether this
	 * succeeded. If there is an error, it will be caught so that the system can
	 * attempt to add the note again.
	 * 
	 * @param a
	 *            note (String)
	 * @return true: Note was successfully added to the end of the List of
	 *         Notes; false: otherwise.
	 */
	public boolean AddNote(String note) {
		this.NotesList.add(note);
		return true;
	}

	/**
	 * addDrug():
	 * <p>
	 * Adds a new DrugInfo object to the current PatientRecord and returns
	 * whether this succeeded. If there is an error, it will be caught so that
	 * the system can attempt to add the drug again.
	 * 
	 * @param a
	 *            drug (Drug)
	 * @return true: Drug was added to the PatientRecord List of Drugs; false:
	 *         otherwise.
	 */
	public boolean AddDrug(Drug drug) {
		this.DrugsList.add(drug);
		return true;

	}

	/**
	 * addAllergy():
	 * <p>
	 * Adds a new Allergy object to the current PatientRecord and returns
	 * whether this action succeeded. If there is an error, it will be caught so
	 * that the system can attempt to add the allergy again.
	 * 
	 * @param an
	 *            allergy (Allergy)
	 * @return true: Allergy object was added to PatientRecord List of
	 *         Allergies; false: otherwise.
	 */
	public boolean AddAllergy(Allergy allergy) {
		this.AllergyList.add(allergy);
		return true;
	}

	/**
	 * Removes all of the items in the RemoveList from the appropriate list
	 * inside the PatientRecord.
	 * 
	 * @param RemoveList
	 * @return true: input items are removed from the appropriate list; false:
	 *         otherwise
	 */
	@SuppressWarnings("unchecked")
	public boolean UpdateIterable(LinkedList<AbstractModule> RemoveList) {

		//Base Case
		if (RemoveList.isEmpty())
			return true;
		
		//Create an AbstractModule to identify the Subclass of AbstractModule in the List
		AbstractModule type = RemoveList.getFirst();
		LinkedList list;
		
		//Iterate through List to find ones that are to be 
		//removed from the appropriate List
		if (type instanceof Allergy){
			
			list = new LinkedList <Allergy>();
			for(AbstractModule a : RemoveList)	
				list.add((Allergy)a);
			
			AllergyList.removeAll(list);
		}
		
		else if (type instanceof Drug) {
			
			list = new LinkedList <Drug>();
			for(AbstractModule a : RemoveList)
				list.add((Drug)a);
			
			DrugsList.removeAll(list);
		}
		else
			return false;
		return true;
	}

	/**
	 * Remove Notes from the Notes List in the Patient Record.
	 * @param RemoveList
	 * @return 
	 */
	public boolean UpdateNotes(LinkedList<String> RemoveList) {
		return NotesList.removeAll(RemoveList);
	}

	/**
	 * gets the hashmap for a patient from storage. This is a helper function
	 * 
	 * @param the
	 *            patientInfo object we want to retrieve
	 * @return the hashmap corresponding to the input patientInfo that has been
	 *         retrieved from storage.
	 */
	private HashMap<String, String> GetPatientFromStorage(PatientInfo patient) {
		
		// This snippet is from Ethan's code
		HashMap<String, String> searchTerm = new HashMap<String, String>();
		searchTerm.put("First Name", patient.GetFirstName());
		searchTerm.put("Last Name", patient.GetLastName());
		searchTerm.put("Date of Birth", patient.GetDateOfBirth().toString());
		List<HashMap<String, String>> intermediateResults = new LinkedList<HashMap<String, String>>();
		Storage store = Storage.GetInstance();
		intermediateResults = store.Retrieve("cs320.Patient", searchTerm);
		// This is the end of Ethan's code
		return intermediateResults.get(0);
	}

	/**
	 * Amalgamates the question sets of all submodules and places this set in
	 * the Questions field.
	 * 
	 * @param void
	 * @return true: Summary is successfully generated; false: otherwise.
	 */
	public boolean GenerateQuestionSet() {
		
		QuestionSet newQuestionSet = 
			new QuestionSet(Storage.GetInstance().Retrieve("cs320.QuestionSet", null).get(0));
		ListIterator<Drug> DrugsListIt = DrugsList.listIterator(0);
		Drug tempDrug;
		if (DrugsList.isEmpty()) {
		} else {
			while (DrugsListIt.hasNext()) {
				tempDrug = DrugsListIt.next();
				newQuestionSet.UpdateQuestionSet(tempDrug
						.GetAdditionalQuestions());
			}
		}
		newQuestionSet.UpdateQuestionSet(Questions.GetQuestions());

		Questions = newQuestionSet;

		return true;
	}

	/**
	 * Returns the DisplayObject constructed from Display Utilities elements
	 * that displays all elements associated with the current PatientRecord.
	 * 
	 * @param void
	 * @return the DisplayObject generated by this display function.
	 */
	public boolean Display() {
		return DisplayUtility.GetInstance().Display(this);
	}
	
	/**
	 * Save():
	 * <p>
	 * Sends the current PatientRecord's information to the storage module to
	 * store, and returns whether this succeeded. If the save fails, the error
	 * that caused the failure will be caught so that PatientRecord can attempt
	 * to interface with the storage module again to retry the save.
	 * 
	 * @param void
	 * @return true: PatientInfo accepted as appendable; false: otherwise.
	 */
	public boolean Save() {

		Storage s = Storage.GetInstance();

		// For every Drug and Allergy, save them.
		for (Drug d : DrugsList)
			d.Save();
		for (Allergy a : AllergyList)
			a.Save();

		for (String note : NotesList) {

			HashMap<String, String> noteMap = new HashMap<String, String>();
			noteMap.put("Note", note);
			noteMap.put("p_id", this.GetP_ID());
			s.Save("Note", noteMap);
		}

		Patient.Save();
		s.Save("cs320.patient", this.toHashMap());

		return true;
	}

	/**
	 * store():
	 * <p>
	 * Sends the current PatientRecord's information to the storage module to
	 * store, sets the appendOnly flag to true, and returns whether this
	 * succeeded. If store fails, the error that caused the failure will be
	 * caught so that PatientRecord can attempt to interface with the storage
	 * module again to retry the store.
	 * 
	 * @param void
	 * @return true: PatientRecord is saved to the database; false: otherwise
	 */
	public boolean Store() {

		this.AppendOnly = true;
		return this.Save();

	}

	/********************************** Getters and Setters ****************************************/

	/**
	 * Return the PID for this PatientRecord.
	 * 
	 * @return String P_ID
	 */
	public String GetP_ID() {
		return this.P_ID;
	}

	/**
	 * @param void
	 * @return the p_id corresponding to the patientInfo object contained in
	 *         this PatientRecord object
	 */
	public String RetrieveP_ID() {
		this.P_ID = GetPatientFromStorage(Patient).get("p_id");
		return this.P_ID;
	}


	/**
	 * Returns the list of drugs that are currently associated with the current
	 * PatientRecord. If there is an error accessing the list of drugs, it will
	 * be caught so that the system can attempt to retrieve the list again.
	 * 
	 * @return list of drugs already added by user
	 */
	public LinkedList<Drug> GetDrugs() {
		return this.DrugsList;
	}

	/**
	 * Returns the list of notes that are currently associated with the current
	 * PatientRecord. If there is an error accessing the list of notes, it will
	 * be caught so that the system can attempt to retrieve the list again.
	 * 
	 * @return list of notes
	 */
	public LinkedList<String> GetNotes() {
		return this.NotesList;
	}

	/**
	 * Returns the PatientInfo that represents the patient who is associated
	 * with the current PatientRecord. If there is an error retrieving the
	 * PatientInfo, it will be caught so the system can attempt to retrieve the
	 * PatientInfo again.
	 * 
	 * @return Patient
	 */
	public PatientInfo GetPatientInfo() {
		return this.Patient;
	}

	/**
	 * Returns the list of allergies currently associated with the current
	 * PatientRecord. If there is an error retrieving the allergy list, it will
	 * be caught so the system can attempt to retrieve the list again.
	 * 
	 * @return The List of Allergies.
	 */
	public LinkedList<Allergy> GetAllergies() {
		return this.AllergyList;
	}

	/**
	 * Returns the user that created the current PatientRecord. If there is an
	 * error retrieving the creator of the PatientRecord, it will be caught so
	 * that the system can attempt to retrieve it again.
	 * 
	 * @return the User who created this PatientRecord
	 */
	public String GetCreatorUser() {
		return this.RecordCreator;
	}

	/**
	 * Returns the user that most recently modified the current PatientRecord.
	 * If there is an error retrieving the most recent editor of the
	 * PatientRecord, it will be caught so that the system can attempt to
	 * retrieve it again.
	 *
	 * @return the User who last modified this Patient Record
	 */
	public String GetLastModifiedUser() {
		return this.LastModifiedBy;
	}

	/**
	 * Returns the QuestionSet that is contained by the PatientRecord
	 * 
	 * @return the QuestionSet that is contained by the PatientRecord
	 */
	public QuestionSet GetQuestions() {
		return this.Questions;
	}

	/**
	 * Returns the DateCreated DateWrapper object.
	 * 
	 * @return DateWrapper
	 */
	public DateWrapper GetDateCreated() {
		return this.DateCreated;
	}

	/**
	 * Returns the DateLastModified DateWrapper object.
	 * 
	 * @return DateWrapper
	 */
	public DateWrapper GetDateLastModified() {
		return this.DateLastModified;
	}

	/**
	 * Return the AppendOnly Flag.
	 * @return AppendOnly
	 */
	public boolean GetAppendOnly() {
		return this.AppendOnly;
	}

	/**
	 * Return the SummaryMode Flag.
	 * @return SummaryMode
	 */
	public boolean GetSummaryMode() {
		return this.SummaryMode;
	}

	
	public String toString() {

		return "PatientRecord #" + this.GetP_ID() + "\n"
				+ this.GetPatientInfo() + "Date Created: "
				+ this.GetDateCreated() + "\n" + "Creator: "
				+ this.GetCreatorUser() + "\n";
	}

	
	/************ These setters are for testing purposes only. **************/

	public void SetDrugsList(LinkedList<Drug> drugsList) {
		DrugsList = drugsList;
	}

	public void SetAllergyList(LinkedList<Allergy> allergyList) {
		AllergyList = allergyList;
	}

	public void SetNotesList(LinkedList<String> notesList) {
		NotesList = notesList;
	}

	public void SetQuestions(QuestionSet questions) {
		Questions = questions;
	}

	public void SetPatient(PatientInfo patient) {
		Patient = patient;
	}

	public void SetRecordCreator(String recordCreator) {
		RecordCreator = recordCreator;
	}

	public void SetLastModifiedBy(String lastModifiedBy) {
		LastModifiedBy = lastModifiedBy;
	}

	public void SetDateCreated(DateWrapper dateCreated) {
		DateCreated = dateCreated;
	}

	public void SetDateLastModified(DateWrapper dateLastModified) {
		DateLastModified = dateLastModified;
	}

	public void SetAppendOnly(boolean appendOnly) {
		AppendOnly = appendOnly;
	}

	public void SetSummaryMode(boolean summaryMode) {
		SummaryMode = summaryMode;
	}

} /** End PatientRecord **/
