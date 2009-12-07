package cs320;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * The Drug Information Module encapsulates information associated with drugs
 * that are appended to patient records. Information that pertains to an
 * individual includes drug name, the dosage of the drug the route of delivery
 * of the drug (e.g. intravenously, orally, etc.), the frequency which the drug
 * is taken, the dosage of the drug, the prescriber of the drug and start and
 * end date of the period which the patient has taken the drug. Information
 * that is associated with a specific drug is maximum and minimum dosages, whether
 * the drug is on the list of �critical� drugs and questions that may be associated
 * with the drug.
 */
public class Drug extends AbstractModule{
	
	/**
	 * The identification number that is associated with the drug.
	 */
	private long drugId;
	
	/**
	 * The name that is associated with the drug.
	 */
	private String name;
	
	/**
	 * The numerical dosage amount that is associated with the drug.
	 */
	private double dosage;
	
	/**
	 * The unit of measurement that is associated with the drug ex.(mg).
	 */
	private String dosageUnit;
	
	/**
	 * The minimum dosage that the drug is administered under typical circumstances.
	 */
	private double minDosage;
	
	/**
	 * the maximum dosage that the drug is administered under typical circumstances.
	 */
	private double maxDosage;
	
	/**
	 * The method by which the patient takes the drug.
	 */
	private String route;
	
	/**
	 * The date that the patient began taking the drug.
	 */
	private DateWrapper startDate;
	
	/**
	 * The date that the patient stopped taking the drug.
	 */
	private DateWrapper stopDate;
	
	/**
	 * Whether or not the drug is able to be edited.
	 */
	private boolean editable;
	
	/**
	 * Whether or not the drug is a critical drug.
	 */
	private boolean critical;
	
	/**
	 * Whether or not the drug is a questionable drug.
	 */
	private boolean questionable;
	
	/**
	 * The person who prescribed the patient the drug.
	 */
	private String prescriber;
	
	/**
	 * A list of additional questions that are associated with the drug. 
	 */
	private List<String> addlQuestions;
	
	/**
	 * String representing the frequency a drug is taken
	 */
	private String frequency;
	
	/**
	 * String representing why a drug is taken
	 */
	private String reason;
	
	
	/**
	 * Constructor that gets an instance of the storage module and initializes the addlQuestions Linked List.
	 */
	public Drug() {
		name = "";
		dosage = 0;
		dosageUnit = "";
		route = "";
		startDate = new DateWrapper();
		stopDate = new DateWrapper();
		editable = true;
		prescriber = "";
		frequency = "";
		reason = "";
		this.addlQuestions = new LinkedList<String>();
	}
	
	/**
	 * Constructor that creates a drug from a HashMap of Strings to Strings
	 */
	public Drug (HashMap <String, String> drugMap){
			
			if(drugMap.containsKey("Name"))
				this.name = drugMap.get("Name");
			if(drugMap.containsKey("Dosage"))
				this.dosage = Double.parseDouble(drugMap.get("Dosage"));
			if(drugMap.containsKey("DosageUnit"))
				this.dosageUnit = drugMap.get("DosageUnit");
			if(drugMap.containsKey("MinDosage"))
				this.minDosage = Double.parseDouble(drugMap.get("MinDosage"));
			if(drugMap.containsKey("MaxDosage"))
				this.maxDosage = Double.parseDouble(drugMap.get("MaxDosage"));
			if(drugMap.containsKey("Route"))
				this.route = drugMap.get("Route");
			if(drugMap.containsKey("StartDate"))
				this.startDate = new DateWrapper(drugMap.get("StartDate"));
			if(drugMap.containsKey("StopDate"))
				this.stopDate = new DateWrapper(drugMap.get("StopDate"));
			if(drugMap.containsKey("IsEditable"))
				this.editable = Boolean.valueOf(drugMap.get("IsEditable"));
			if(drugMap.containsKey("IsCritical"))
				this.critical = Boolean.valueOf(drugMap.get("IsCritical"));
			if(drugMap.containsKey("StartDate"))
				this.questionable = Boolean.valueOf(drugMap.get("IsQuestionable"));
			if(drugMap.containsKey("Prescriber"))
				this.prescriber = drugMap.get("Prescriber");
			
			this.addlQuestions = new LinkedList<String>();
			//Get the Drug ID
			String DrugID = drugMap.get("d_id");
			this.drugId = Long.valueOf(DrugID);
			
			//Create a Hashmap to put into the Retrieve function
			HashMap <String, String> inputMap = new HashMap <String, String> ();
			inputMap.put("d_id", DrugID);
			
			//Create a LinkedList of Hashmaps to iterate through and retrieve the drug-specific questions
			LinkedList <HashMap <String, String>> questions = 
				(LinkedList <HashMap <String, String>>)Storage.GetInstance().Retrieve("drug_questions", inputMap);
			
			ListIterator <HashMap<String, String>> itr = questions.listIterator();
			while(itr.hasNext())
				this.addlQuestions.add(itr.next().get("question"));
	}
	
	/**
	 * Returns 'true' if the drug is critical and 'false' if the drug is not.
	 */
	public boolean CheckDosage(){
		return (this.minDosage <= this.dosage  && this.dosage <= this.maxDosage);
	}
	
	/**
	 * Makes sure that the date that the patient started taking the drug is after the patient stopped. 
	 */
	public boolean CheckDate(){
		return this.startDate.before(this.stopDate);
	}
	
	/**
	 * Returns 'true' if the drug is critical and 'false' otherwise.
	 */
	public boolean IsCritical(){ return this.critical;}
	
	/**
	 * Returns 'true' if the drug is questionable and 'false' otherwise.
	 */
	public boolean IsQuestionable() { return this.questionable;}
	
	/**
	 * Returns 'true' if the drug is editable and 'false' otherwise.
	 */
	public boolean IsEditable(){ return this.editable;}
	
	/**
	 * Queries the database using the retrieve function in the storage module, searching for
	 * all questions associated with a given drug. These questions are then return as a list
	 * of Strings. If the result set is empty, an empty list is returned. 
	 */
	public List<String> GetAdditionalQuestions() {
		return this.addlQuestions;
	}
	
	/**
	 * Returns the drug identification number that is associated with the drug.
	 */
	public long GetDrugID(){
		return this.drugId;
	}
	
	/**
	 * Returns the name of the drug.
	 */
	public String GetName(){ 
		return this.name;
	}
	
	/**
	 * Returns the numerical dosage value of the drug.
	 */
	public double GetDosage(){
		return this.dosage;
	}
	
	/**
	 * Returns the dosage unit that is associated with the drug. 
	 */
	public String GetDosageUnit(){
		return this.dosageUnit;
	}
	
	/**
	 * Returns the frequency the drug is being taken on
	 */
	public String GetFrequency() {
		return this.frequency;
	}
	
	/**
	 * Returns the reason the drug is being taken for
	 */
	public String GetReason() {
		return reason;
	}
	
	/**
	 * Returns the value contained in the minDosage internal.
	 */
	public double GetMinDosage() {
		return minDosage;
	}
	
	/**
	 * Returns the value contained in the maxDosage internal.
	 */
	public double GetMaxDosage() {
		return maxDosage;
	}
	
	/**
	 * Returns the date that the patient started taking the drug.
	 */
	public DateWrapper GetStartDate(){
		return this.startDate;
	}
	
	/**
	 * Returns the date that the patient stopped taking the drug.
	 */
	public DateWrapper GetStopDate(){
		return this.stopDate;
	}
	
	/**
	 * Returns the method by which the patient takes the drug.
	 */
	public String GetRoute(){
		return this.route;
	}
	
	/**
	 * Returns the person who prescribed the drug to the patient.
	 */
	public String GetPrescriber(){
		return this.prescriber;
	}
	
	/**
	 * Adds a list of additional questions that will be associated with the drug.
	 */
	public void AddAdditionalQuestions(List <String> questions){
		this.addlQuestions.addAll(questions);
	}
	
	/**
	 * Sets the list of additional questions associated with the drug to the list that is passed in. 
	 */
	public void SetAdditionalQuestions(List <String> questions){
		this.addlQuestions = questions;
	}
	
	/**
	 * Sets the name internal to the input value, the storage module is then queried using the
	 * drug name, minDosage, maxDosage, critical are all set based upon corresponding information
	 * contained in the storage module. If there is no associated result set with the drug name
	 * the questionable variable is set to True. 
	 */
	public void SetName(String name) {
		this.name = name;
	}
	
	/**
	 * Sets the dosage internal to the input value, if the value is greater than maxDosage or
	 * less than minDosage, questionable is set to True.
	 */
	public void SetDosage(double dosage) {
		this.dosage = dosage;
	}
	
	/**
	 * Sets dosageUnit to input value.
	 */
	public void SetDosageUnit(String dosageUnit) {
		this.dosageUnit = dosageUnit;
	}
	
	/**
	 * Sets the minimum typical dosage that is associated with the drug.
	 */
	public void SetMinDosage (double min){
		this.minDosage = min;
	}
	
	/**
	 * Sets the maximum typical dosage that is associated with the drug.
	 */
	public void SetMaxDosage (double max){
		this.maxDosage = max;
	}
	
	/**
	 * Sets route to input value.
	 */
	public void SetRoute(String route) {
		this.route = route;
	}
	
	/**
	 * Sets frequency to the input value
	 */
	public void SetFrequency(String f) {
		frequency = f;
	}
	
	/**
	 * Sets reason taken to the input value
	 */
	public void SetReason(String r) {
		reason = r;
	}
	
	/**
	 * Sets startDate to input value.
	 */
	public void SetStartDate(DateWrapper start) {
		this.startDate = start;
	}
	
	/**
	 * Sets end Date to input value.
	 */
	public void SetStopDate(DateWrapper stop) {
		this.stopDate = stop;
	}
	
	/**
	 * Sets editable to input value.
	 */
	public void SetEditable(boolean editable) {
		this.editable = editable;
	}
	
	/**
	 * Sets whether or not the drug is a critical drug.
	 */
	public void SetCritical (boolean t){
		this.critical = t;
	}
	
	/**
	 * Sets wheather or not the drug is a questionable drug.
	 */
	public void SetQuestionable (boolean q){
		this.questionable = q;
	}
	
	/**
	 * Sets prescriber to input value.
	 */
	public void SetPrescriber(String prescriber) {
		this.prescriber = prescriber;
	}
	
	
	/**
	 * Converts drug information into a HashMap of Keys and Values.
	 */
	public HashMap<String,String> toHashMap() {
		PatientRecord p = PatientRecord.GetInstance();
		String PID = p.GetP_ID();
		HashMap <String, String> saveMap = new HashMap <String, String> ();
		saveMap.put("p_id", PID);
		saveMap.put("Name", this.GetName());
		saveMap.put("Dosage", Double.toString(this.GetDosage()));
		saveMap.put("DosageUnit", this.GetDosageUnit());
		saveMap.put("MaxDosage", Double.toString(this.GetMaxDosage()));
		saveMap.put("MinDosage", Double.toString(this.GetMinDosage()));
		saveMap.put("Route", this.GetRoute());
		saveMap.put("StartDate", this.GetStartDate().toString());
		saveMap.put("StopDate", this.GetStopDate().toString());
		saveMap.put("IsEditable", Boolean.toString(this.IsEditable()));
		saveMap.put("IsCritical", Boolean.toString(this.IsCritical()));
		saveMap.put("IsQuestionable", Boolean.toString(this.IsQuestionable()));
		saveMap.put("Prescriber", this.GetPrescriber());
		return saveMap;
	}
	
	/**
	 * Calls function save() in the storage module. All internals from the Drug Information
	 * Module are inserted into the storage module. In the case that the insertion is
	 * successful the function returns True, otherwise the function returns False. 
	 */
	public boolean Save(){
		return Storage.GetInstance().Save("cs320.drug", toHashMap());
	}

	/**
	 * Calls the Display Utility associated with the Drug.
	 */
	public boolean Display(){
		return DisplayUtility.GetInstance().Display(this);
	}
	
	/**
	 * Permanently stores the drug information in the database.
	 */
	public boolean Store() {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * Overwrite of the toString method for testing and debugging.
	 */
	public String toString(){	
		return "Name: " + this.GetName() + ", Dosage: " + this.GetDosage();
	}
}