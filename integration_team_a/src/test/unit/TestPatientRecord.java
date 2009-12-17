package test.unit;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import test.InputGenerator;
import edu.cs320.project.*;
import junit.framework.Assert;
import junit.framework.TestCase;


public class TestPatientRecord extends TestCase {

	int stressMax=10;
	PatientRecord patientRecord;
	
	public TestPatientRecord(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void fake_test_init(){
		Storage store = Storage.GetInstance();
		StorageWrapper.Sync("cs320.user");
		StorageWrapper.Sync("cs320.drug");
		StorageWrapper.Sync("cs320.allergy");
		StorageWrapper.Sync("cs320.patient");
		StorageWrapper.Sync("cs320.question_set");
		StorageWrapper.Sync("cs320.drug_questions");
		StorageWrapper.Sync("cs320.takes");

	}
	/** Setup(): Create Null PatientInfo and real PatientInfo
	 * 
	 */
	public void test_PatientRecord(){
		//Tests PatientRecord(HashMap<String,String>)
		Storage store = Storage.GetInstance();
		StorageWrapper.Sync("cs320.user");
		StorageWrapper.Sync("cs320.drug");
		StorageWrapper.Sync("cs320.allergy");
		StorageWrapper.Sync("cs320.patient");
		StorageWrapper.Sync("cs320.question_set");
		StorageWrapper.Sync("cs320.drug_questions");
		//store.deleteFromClient("cs320.drug");
		int i;
		for(i=0;i<stressMax;i++){
			PatientRecord randomPatient = InputGenerator.randomPatientRecord();
			//System.out.println(randomPatient.GetDateCreated().toString());
			PatientInfo randomPatientInfo = InputGenerator.randomPatient();
			randomPatient.SetPatient(randomPatientInfo);
			//randomPatient.Save();
			Assert.assertTrue(StorageWrapper.Save("cs320.patient",randomPatientInfo.toHashMap()));
			HashMap<String,String> testRecordMap= randomPatient.toHashMap();
			PatientRecord testRecord = new PatientRecord(testRecordMap);
			Assert.assertEquals(testRecord.GetCreatorUser(),testRecordMap.get("record_creator"));
			Assert.assertEquals(testRecord.GetLastModifiedUser(),testRecordMap.get("last_modified_by"));
			Assert.assertEquals(testRecord.GetDateCreated().toString(),testRecordMap.get("date_created").toString());
			Assert.assertEquals(testRecord.GetDateLastModified().toString(),testRecordMap.get("date_last_modified").toString());
			//System.out.println( ""+ testRecord.GetAppendOnly() + " =? " + testRecordMap.get("is_editable").equals("1"));
			Assert.assertEquals(testRecord.GetAppendOnly(),testRecordMap.get("is_editable").equals("1"));
//			System.out.println( ""+ testRecord.GetSummaryMode() + " =? " + testRecordMap.get("summary_mode"));
//			Assert.assertEquals(""+testRecord.GetSummaryMode(),""+testRecordMap.get("summary_mode"));
			
			
			HashMap<String,String> inputMap = new HashMap<String,String>();
			inputMap.put("p_id", Integer.toString(testRecord.RetrieveP_ID()));
			
			//need to save random drugs to the DB
			int o;
			int ran= InputGenerator.randomInt(100);
			for(o = 0; o < ran; o++) {
				Drug adrug=InputGenerator.randomDrug();
				//adrug.Save(""+testRecord.RetrieveP_ID());
				Allergy aAll=InputGenerator.randomAllergy();
				//System.out.println("ll-"+aAll.GetCause());
				aAll.Save(""+testRecord.RetrieveP_ID());
			}
			
			int j=0;
			Iterator<HashMap<String, String>> drugMapIT=StorageWrapper.Retrieve("cs320.drug", inputMap).iterator();
			//testRecord.AddDrug(new Drug(Storage.GetInstance().Retrieve("cs320.drug", inputMap).get(0)));
			while(drugMapIT.hasNext()){
				HashMap<String,String> yar = drugMapIT.next();
				//System.out.println(";;;;"+yar.get("name"));
				Drug tempDrug=new Drug(yar);
				//System.out.println("''''"+tempDrug.GetName());
				testRecord.AddDrug(tempDrug);
				//System.out.println("->"+tempDrug.GetDrugID());
				
			}
			i=0;
			for(Drug aDrug: testRecord.GetDrugs()){
				
				HashMap<String,String> drugMap = StorageWrapper.Retrieve("cs320.drug", inputMap).get(i);
				//System.out.println(";"+aDrug.GetDrugID());
				//System.out.println(drugMap.get("d_id"));
				Assert.assertEquals(""+(aDrug.GetDrugID()),drugMap.get("d_id"));
				Assert.assertEquals(aDrug.GetName(),drugMap.get("name"));
				Assert.assertEquals(""+aDrug.GetDosage(),drugMap.get("dosage"));
				Assert.assertEquals(aDrug.GetDosageUnit(),drugMap.get("dosage_unit"));
				Assert.assertEquals(""+(int)aDrug.GetMinDosage(),drugMap.get("min_dosage"));
				Assert.assertEquals(""+(int)aDrug.GetMaxDosage(),drugMap.get("max_dosage"));
				Assert.assertEquals(aDrug.GetRoute(),drugMap.get("route"));
				Assert.assertEquals(aDrug.GetStartDate().toString(),drugMap.get("start_date").substring(0, drugMap.get("start_date").length()-2));
				Assert.assertEquals(aDrug.GetStopDate().toString(),drugMap.get("stop_date").substring(0, drugMap.get("stop_date").length()-2));
				if(!aDrug.IsEditable()){
					Assert.assertEquals("1",drugMap.get("is_editable"));
				}
				else {
					Assert.assertEquals("0",drugMap.get("is_editable"));
				}
				Assert.assertEquals(Boolean.toString(aDrug.IsCritical()),drugMap.get("is_critical"));
				
				if(aDrug.IsQuestionable()){
					Assert.assertEquals("1",drugMap.get("is_questionable"));
				}
				else {
					Assert.assertEquals("0",drugMap.get("is_questionable"));
				}
				Assert.assertEquals(aDrug.GetPrescriber(),drugMap.get("prescriber"));
				int k=0;
				for(String question: aDrug.GetAdditionalQuestions()){
					HashMap<String,String> drugQuestionInputMap = new HashMap<String,String>();
					drugQuestionInputMap.put("d_id", "" + aDrug.GetDrugID());
					LinkedList<HashMap<String,String>> questionMapList = (LinkedList<HashMap<String,String>>) StorageWrapper.Retrieve("drug_questions", drugQuestionInputMap);
					HashMap<String,String> questionMap = questionMapList.get(k);
					Assert.assertEquals(questionMap.get("question"),question);
					k++;
					}
				j++;
				i++;
				}
			
			int a=0;
			Iterator<HashMap<String, String>> alMapIT=StorageWrapper.Retrieve("cs320.allergy", inputMap).iterator();
			while(alMapIT.hasNext()){
				testRecord.AddAllergy(new Allergy(alMapIT.next()));
			}
			for(Allergy anAllergy: testRecord.GetAllergies()){
				HashMap<String,String> allergyMap = StorageWrapper.Retrieve("cs320.allergy", inputMap).get(a);
				Assert.assertEquals(anAllergy.GetCause(),allergyMap.get("cause"));
				Assert.assertEquals(anAllergy.GetReaction(),allergyMap.get("reaction"));
				a++;
				}
			
			//patient info
			HashMap<String,String> patientInfoMap = StorageWrapper.Retrieve("cs320.patient", inputMap).get(0);
			Assert.assertEquals(testRecord.GetPatientInfo().GetFirstName(), patientInfoMap.get("first_name"));
			Assert.assertEquals(testRecord.GetPatientInfo().GetGender(), patientInfoMap.get("sex"));
			Assert.assertEquals(""+testRecord.GetPatientInfo().GetHeight(), patientInfoMap.get("height"));
			Assert.assertEquals(testRecord.GetPatientInfo().GetLastName(), patientInfoMap.get("last_name"));
			Assert.assertEquals(""+testRecord.GetPatientInfo().GetMedicalRecordNumber(), ""+patientInfoMap.get("mrn"));
			Assert.assertEquals(""+testRecord.GetPatientInfo().GetWeight(), patientInfoMap.get("weight"));
			Assert.assertEquals(testRecord.GetPatientInfo().GetDateOfBirth().toString(), patientInfoMap.get("dob").substring(0,patientInfoMap.get("dob").length()-2));
			}
		}
	/** Setup(): Null Drug and complete Drug
	 *
	 */
//	public void test_addDrug(){
//		
////		Storage mine = Storage.GetInstance();
////		List<HashMap<String,String>> res = mine.Retrieve("cs320.patient", null);
////		Iterator<HashMap<String, String>> it =  res.iterator();
////		System.out.println();
////		while(it.hasNext()){
////			Iterator<String> it2 = it.next().keySet().iterator();
////			while(it2.hasNext()){
////				System.out.println(it2.next());
////			}
////		}
////		System.out.println();
//		//addDrug: Test null case. test complete case. test large number of drugs with different names;
//		//dosages, start times, stop times (also in particular, and this may be something in the Drug module,
//		//check that the a stop time before a start time doesn't work), etc. and partial drugs with name and date of birth
//		//and without name and data of birth.
//		
//		//Instantiate Test objects
//		Drug nulldrug = null;
//		Drug completeDrug;
//		
//		//Use the InputGenerator to obtain a random PatientRecord
//		patientRecord = InputGenerator.randomPatientRecord();
//		Assert.assertFalse(patientRecord.GetDrugs().isEmpty());
//		
//		//Let's mirror this druglist and test against the drug list in PatientRecord later...
//		LinkedList <Drug> druglist = patientRecord.GetDrugs();
//	
//		//Assert adding a null drug
//		Assert.assertTrue(patientRecord.AddDrug(nulldrug));
//		
//		for(int i = 0; i < stressMax; i++){
//			
//			//Use the InputGenerator to obtain random drugs
//			completeDrug = InputGenerator.randomDrug();
//			
//			//Assert Statements
//			Assert.assertTrue(patientRecord.AddDrug(completeDrug));
//			Assert.assertFalse(patientRecord.GetDrugs().isEmpty());
//			druglist.add(completeDrug);
//		}
//		
//		//Assert that both lists are equal!
//		Assert.assertEquals(druglist, patientRecord.GetDrugs());
//		Assert.assertEquals(druglist.getFirst(), patientRecord.GetDrugs().getFirst());
//		Assert.assertEquals(druglist.getLast(), patientRecord.GetDrugs().getLast());
//		
//		
//		//Check Start and Stop dates
//		completeDrug = new Drug();
//		completeDrug.SetStartDate(new DateWrapper(1999, 9, 9));
//		completeDrug.SetStopDate(new DateWrapper(1999, 9, 9));
//		Assert.assertFalse(completeDrug.CheckDate());
//		
//	}
//	
//	public void test_addNote(){
//		
//		//addNote: Test null case, test complete case, test large string, test blank string,
//		//test large number of random notes by generating random Strings of different lengths.
//
//		String nullnote = null;
//		String completeNote;
//		
//		//Use the InputGenerator to obtain a random PatientRecord
//		patientRecord = InputGenerator.randomPatientRecord();
//		
//		//Assert the Null Case
//		Assert.assertTrue(patientRecord.AddNote(nullnote));
//		
//		Assert.assertFalse(patientRecord.GetNotes().isEmpty());
//		
//		for(int i = 0; i < stressMax; i++){
//			
//			completeNote = InputGenerator.randomString();
//			Assert.assertTrue(patientRecord.AddNote(completeNote));
//			Assert.assertFalse(patientRecord.GetNotes().isEmpty());
//		}
//		
//	}
//	
//	public void test_addAllergy(){
//		
//		//addAllergy: test null case. test complete case. test large number of random allergies.
//		
//		Allergy nullallergy = null;
//		Allergy completeAllergy;
//		
//		//Use the InputGenerator to obtain a random PatientRecord
//		patientRecord = InputGenerator.randomPatientRecord();
//		
//		//Assert the null case
//		Assert.assertTrue(patientRecord.AddAllergy(nullallergy));
//		
//		Assert.assertFalse(patientRecord.GetAllergies().isEmpty());
//		
//		for(int i = 0; i < stressMax; i++){
//			
//			//Use the InputGenerator to obtain a random Allergy
//			completeAllergy = InputGenerator.randomAllergy();
//
//			Assert.assertTrue(patientRecord.AddAllergy(completeAllergy));
//			Assert.assertFalse(patientRecord.GetAllergies().isEmpty());	
//			
//		}
//		
//		completeAllergy = new Allergy ("First", "Last");
//		Assert.assertTrue(patientRecord.AddAllergy(completeAllergy));
//		Assert.assertEquals(completeAllergy, patientRecord.GetAllergies().getLast());
//		
//	
//	}
//
//	
	public void test_GenerateQuestionSet(){
		//Tests GenerateQuestionSet()
		//null drug list
		Storage store = Storage.GetInstance();
		int i=0;
		int max=InputGenerator.randomInt(100);
		HashMap<String, String> randomQS = new HashMap<String, String>();
		store.deleteFromClient("cs320.question_set");
		for(i=0;i<max; i++){
			for(String question: InputGenerator.randomQuestionSet().GetQuestions()){
				randomQS.put("question", question);}
			StorageWrapper.Save("cs320.question_set", randomQS);
		}
		PatientRecord noDrugRecord = PatientRecord.createPatientRecord(null);
		Assert.assertTrue(noDrugRecord.GenerateQuestionSet());
		List<HashMap<String, String>> oldQuestions= StorageWrapper.Retrieve("cs320.question_set", null);
		QuestionSet newb = new QuestionSet(oldQuestions);
		noDrugRecord.SetQuestions(newb);
		
		Assert.assertTrue(oldQuestions.equals(noDrugRecord.GetQuestionSet()));
		
		//not null drug list
		int i1;
		for(i1=0;i1<stressMax;i1++){
			test_GenerateQuestionSetIteration(i1);
		}
	}
	
	public void test_GenerateQuestionSetIteration(int i){
		PatientRecord hasDrugsRecord = InputGenerator.randomPatientRecord();
		QuestionSet oldQuestions = hasDrugsRecord.GetQuestionSet();
		hasDrugsRecord.GenerateQuestionSet();
		//Assert.assertEquals(oldQuestions,hasDrugsRecord.GetQuestions());
		Iterator<String> oldIt= oldQuestions.GetQuestions().iterator();
		Iterator<String> newIt= hasDrugsRecord.GetQuestionSet().GetQuestions().iterator();
		Assert.assertTrue(oldQuestions.GetQuestions().size()==hasDrugsRecord.GetQuestionSet().GetQuestions().size());
		while(oldIt.hasNext()&&newIt.hasNext()){
			String oldQ=oldIt.next();
			String newQ=newIt.next();
			Assert.assertEquals(oldQ, newQ);
		}
	}
}
		

	/*public void test_Store(){
		//null patientRecord
	}
	
}*/