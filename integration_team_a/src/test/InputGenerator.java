package test;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import edu.cs320.project.*;

public class InputGenerator {
	private static Random generator;
	private static ArrayList<Character> alpha = new ArrayList<Character>();
	private static LinkedList<Drug> generatedDrugs = new LinkedList<Drug>();
	private static LinkedList<Allergy> generatedAllergies = new LinkedList<Allergy>();
	private static LinkedList<PatientInfo> generatedPatients = new LinkedList<PatientInfo>();
	private static LinkedList<PatientRecord> generatedPatientRecords = new LinkedList<PatientRecord>();
	private static LinkedList<QuestionSet> generatedQuestionSets = new LinkedList<QuestionSet>();
	private static LinkedList<User> generatedUsers = new LinkedList<User>();
	private static LinkedList<String> generatedNotes = new LinkedList<String>();
	
	static {
		generator = new Random();
		for(char c = 'A'; c <= 'Z'; ++c)
			alpha.add(c);
		for(char c = 'a'; c <= 'z'; ++c)
			alpha.add(c);
	}
	
	private InputGenerator() {}
	
	public static LinkedList<Drug> getGeneratedDrugs(){
		return generatedDrugs;
	}
	
	public static LinkedList<Allergy> getGeneratedAllergy(){
		return generatedAllergies;
	}
	
	public static LinkedList<PatientInfo> getGeneratedPatients(){
		return generatedPatients;
	}
	
	public static LinkedList<PatientRecord> getGeneratedPatientRecords(){
		return generatedPatientRecords;
	}
	
	public static LinkedList<QuestionSet> getGeneratedQuestionSets(){
		return generatedQuestionSets;
	}
	
	public static LinkedList<String> getGeneratedNotes() {
		return generatedNotes;
	}
	
	public static LinkedList<User> getGeneratedUsers(){
		return generatedUsers;
	}
	
	public static String randomString()
	{
		int size = generator.nextInt(100)+1;
		StringBuilder string = new StringBuilder();
		for(int x=0;x<size;x++)
			string.append(alpha.get(generator.nextInt(52)));
		
		return string.toString();
	}
	
	public static String randomString(int maxSize)
	{
		int size = generator.nextInt(maxSize)+1;
		StringBuilder string = new StringBuilder();
		for(int x = 0; x < size; x++)
			string.append(alpha.get(generator.nextInt(52)));
		
		return string.toString();
	}
	
	public static String randomMinString(int minSize)
	{
		int size = generator.nextInt(minSize)+minSize;
		StringBuilder string = new StringBuilder();
		for(int x = 0; x < size; x++)
			string.append(alpha.get(generator.nextInt(52)));
		
		return string.toString();
	}
	
	public static LinkedList <String> randomStrings()
	{
		
		LinkedList<String> questions = new LinkedList<String>();
		
		for(int i = 0; i < generator.nextInt(50)+1; i++)
			questions.add(InputGenerator.randomString(InputGenerator.randomInt(50)+1));
		
		return questions;
	}
	
	public static int randomInt(){
		return generator.nextInt();
	}
	
	public static double randomDouble(){
		return generator.nextDouble();
	}
	
	public static int randomNegativeInt(){
		return -generator.nextInt(Integer.MAX_VALUE-1)+1;
	}
	
	public static int randomInt(int range){
		return generator.nextInt(range);
	}
	
	public static int randomNegativeInt(int range){
		return -generator.nextInt(Integer.MAX_VALUE-1)+1;
	}
	
	private static boolean isLeapYear(int year) {
		if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)
			return true;
		else
			return false;
	}
	
	public static String randomDateString() {
		int year = randomDateYear();
		int month = randomDateMonth();
		int day = randomDateDay(year, month);
		
		int hour = randomDateHour();
		int minute = randomDateMinute();
		int second = randomDateSecond();

		return String.format("%04d-%02d-%02d %02d:%02d:%02d",
			year, month, day, hour, minute, second);
	}
	
	public static String randomDateDisplayString() {
		int year = randomDateYear();
		int month = randomDateMonth();
		int day = randomDateDay(year, month);
		
		int hour = randomDateHour();
		int minute = randomDateMinute();
		int second = randomDateSecond();

		return String.format("%02d/%02d/%04d %02d:%02d:%02d",
			year, month, day, hour, minute, second);
	}
	
	public static int randomDateYear() {
		return 1900 + generator.nextInt(200);
	}
	
	public static int randomDateMonth() {
		return generator.nextInt(12) + 1;
	}

	public static int randomDateDay(int year, int month) {
		final int[] maxMonthDays = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		int numDays = maxMonthDays[month - 1];
		if (month == 2 && isLeapYear(year))
			numDays = 29;
		
		return generator.nextInt(numDays) + 1;
	}
	
	public static int randomDateHour() {
		return generator.nextInt(24);
	}
	
	public static int randomDateMinute() {
		return generator.nextInt(60);
	}
	
	public static int randomDateSecond() {
		return generator.nextInt(60);
	}
	
	public static int randomDateMillisecond() {
		return generator.nextInt(1000);
	}
	
	public static DateWrapper randomDateWrapper() {
		return new DateWrapper(randomDateString());
	}
	
	public static DateWrapper randomDateWrapper(DateWrapper startDate) {
		DateWrapper date = (DateWrapper) startDate.clone();
		//date.setTimeInMillis(startDate.getTimeInMillis()+randomInt());
		return date;
	}
	
	public static Drug randomDrug(){
		
		Drug drug = new Drug();
		drug.SetDosage(generator.nextFloat());
		drug.SetDosageUnit(randomString());
		drug.SetName(randomString());
		drug.SetPrescriber(randomString());
		drug.SetRoute(randomString(60));
		drug.SetStartDate(randomDateWrapper());
		drug.SetStopDate(randomDateWrapper());
		drug.setDrugID(randomInt());
		
		LinkedList <String> addlQs = new LinkedList <String> ();
		
		drug.SetAdditionalQuestions(InputGenerator.randomStrings());
		
		generatedDrugs.add(drug);
		
		return drug;
	}
	
	public static String randomNote() {
		String note = randomString();
		generatedNotes.add(note);
		return note;
	}
	
	public static PatientRecord randomPatientRecord() {
		PatientRecord record = PatientRecord.GetInstance();
		
		LinkedList<Drug> drugList = new LinkedList<Drug>();
		LinkedList<Allergy> allergyList = new LinkedList<Allergy>();
		LinkedList<String> notesList = new LinkedList<String>();
		for(int i = 0; i < generator.nextInt(100); i++) {
			drugList.add(randomDrug());
			allergyList.add(randomAllergy());
			notesList.add(randomNote());
		}
		
		QuestionSet questions = new QuestionSet(InputGenerator.randomStrings());
		LinkedList<String> allQuestions = new LinkedList<String>();
		for (Drug a : drugList)
			for (String question : a.GetAdditionalQuestions())
				allQuestions.add(question);
		
		questions.UpdateQuestionSet(allQuestions);
		PatientInfo patient = randomPatient();
		String recordCreator = randomString();
		String lastModifiedBy = randomString();
		DateWrapper dateCreated = randomDateWrapper();
		DateWrapper dateLastModified = randomDateWrapper(dateCreated);
		boolean appendOnly = generator.nextBoolean();
		boolean summaryMode = generator.nextBoolean();
		
		record.SetDrugsList(drugList);
		record.SetAllergyList(allergyList);
		record.SetNotesList(notesList);
		record.SetQuestions(questions);
		record.SetPatient(patient);
		record.SetRecordCreator(recordCreator);
		record.SetLastModifiedBy(lastModifiedBy);
		record.SetDateCreated(dateCreated);
		record.SetDateLastModified(dateLastModified);
		record.SetAppendOnly(appendOnly);
		
		
		generatedPatientRecords.add(record);
		
		return record;
	}
	
	public static QuestionSet randomQuestionSet() {
		LinkedList<String> staticQuestions = new LinkedList<String>();
		LinkedList<String> dynamicQuestions = new LinkedList<String>();
		for(int i = 0; i < generator.nextInt(100); i++) {
			staticQuestions.add(randomString());
			dynamicQuestions.add(randomString());
		}
		QuestionSet set = new QuestionSet(staticQuestions);
		set.UpdateQuestionSet(dynamicQuestions);
		
		generatedQuestionSets.add(set);
		
		return set;
	}
	
	public static PatientInfo randomPatient() {
        PatientInfo temp = new PatientInfo(randomString(), randomString(), randomDateString());
        temp.SetHeight(randomInt(90)+1);
        temp.SetWeight(randomInt(500)+1);
        int gen = randomInt(2);
        if(gen==0)
            temp.SetGender("male");
        else if(gen==1)
            temp.SetGender("female");
        else
            temp.SetGender("other");
        
        temp.SetMedicalNumber(randomInt() + "");
        
        generatedPatients.add(temp);
        
        return temp;
    }

    public static Allergy randomAllergy() {
        Allergy temp = new Allergy(randomString(), randomString());
        
        generatedAllergies.add(temp);
        
        return temp;
    }
    
    public static User randomUser() {
    	int gen = randomInt(3);
    	String type = null;
    	if(gen == 0)
    		type = "nurse";
    	else if(gen == 1)
    		type = "admin";
    	else if(gen == 2)
    		type = "pharmacist";
    	
    	User user = new User(randomString(), type);
    	
    	user.password = randomString();
        
        generatedUsers.add(user);
        
        return user;
    }
    
    public static User randomUser(int maxSize) {
    	int gen = randomInt(3);
    	String type = null;
    	if(gen == 0)
    		type = "nurse";
    	else if(gen == 1)
    		type = "admin";
    	else if(gen == 2)
    		type = "pharmacist";
    	
    	User user = new User(randomString(maxSize), type);
    	
    	user.password = randomString(maxSize);
        
        generatedUsers.add(user);
        
        return user;
    }
    
    public static User randomMinUser(int minSize) {
    	int gen = randomInt(3);
    	String type = null;
    	if(gen == 0)
    		type = "nurse";
    	else if(gen == 1)
    		type = "admin";
    	else if(gen == 2)
    		type = "pharmacist";
    	
    	User user = new User(randomString(minSize), type);
    	
    	user.password = randomString(minSize);
        
        generatedUsers.add(user);
        
        return user;
    }
    
    public static void reset() {
    	generatedAllergies.clear();
    	generatedDrugs.clear();
    	generatedPatients.clear();
    	generatedPatientRecords.clear();
    	generatedQuestionSets.clear();
    	generatedUsers.clear();
    	generatedNotes.clear();
    }
}
