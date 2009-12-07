import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;


public class StorageDummy {
//MERGED ETHAN AND NIK
	private static StorageDummy instance = null;
	public static void reset() {
		InputGenerator.reset();
		instance = new StorageDummy();

	private long p_id = 0;
	private long d_id = 0;
	private LinkedList<HashMap<String, String>> patients = new LinkedList<HashMap<String, String>>();
	private LinkedList<HashMap<String, String>> allergies = new LinkedList<HashMap<String, String>>();
	private LinkedList<HashMap<String, String>> drugs = new LinkedList<HashMap<String, String>>();
	private LinkedList<HashMap<String, String>> questionSets = new LinkedList<HashMap<String, String>>();
	private LinkedList<HashMap<String, String>> drug_questions = new LinkedList<HashMap<String, String>>();

	public StorageDummy() {
	}
		"cs320.user",
		"cs320.allergy",
		"cs320.drug",
		"cs320.drug_questions",
		"cs320.note",
		"cs320.QuestionSet"

	@SuppressWarnings("unchecked")
	/**public void init() {
		System.out.println("init()");
		int i;
			//System.out.println("Patient");
			InputGenerator.randomDrug().Save();
			System.out.println("Drug");
			InputGenerator.randomAllergy().Save();
			System.out.println("Allergy");
			InputGenerator.randomQuestionSet().Save();
			System.out.println("QuestionSet");
			Drug temp = new Drug(drugs.getLast());
			drug_questions.add(dqToHash((LinkedList)temp.GetAdditionalQuestions(), temp.GetDrugID()));
			System.out.println("got it");
			drug_questions.add(dqToHash((LinkedList) temp
					.GetAdditionalQuestions(), temp.GetDrugID()));
			System.out.println("drug_questions");
		}**/

	public HashMap<String, String> dqToHash(LinkedList<String> input, long D_ID) {
		HashMap<String, String> results = new HashMap<String, String>();
		results.put("d_id", "" + D_ID);
		for (String dq : input) {
			results.put("question", dq);
		}
		return results;
	}

	public boolean Exist(String Relation, HashMap<String, String> Input) {
		if(this.Retrieve(Relation, Input).isEmpty())
			return false;
		else
			return true;
	}

	@SuppressWarnings("unchecked")

			if(Relation.equals("cs320.QuestionSet")){
		}
		LinkedList<HashMap<String, String>> results = new LinkedList<HashMap<String, String>>();
/**		if (relation_to_store.containsKey(Relation)) {
					results.add((HashMap<String,String>) map.clone());**/
		if (Relation.equals("cs320.patient")) {
				if(contains(patient,Input)){
					results.add((HashMap<String, String>) patient.clone());
				}
			}
		} else if (Relation.equals("cs320.drug")) {
			if (drugs.isEmpty()) {
			} else {
				for (HashMap<String, String> drug : drugs) {
					if (contains(drug, Input)) {
						results.add((HashMap<String, String>) drug.clone());
					}
				}
			}
			}
		} else if (Relation.equals("cs320.allergy")) {
			if (allergies.isEmpty()) {
			} else {
				for (HashMap<String, String> allergy : allergies) {
					if (contains(allergy, Input)) {
						results.add((HashMap<String, String>) allergy.clone());
					}
				}
			}
					results.add((HashMap<String, String>) qset.clone());
				}
			}
			}
		} else if (Relation.equals("cs320.drug_question")) {
			if (drug_questions.isEmpty()) {
			} else {
				for (HashMap<String, String> dq : drug_questions) {
					if (contains(dq, Input)) {
						results.add((HashMap<String, String>) dq.clone());
					}
				}
			}
		}
		else {
			System.out.println("Invalid relation '" + Relation + "'");
		}
		return results;
	}

	public static StorageDummy GetInstance() {
		if (instance == null)
			instance = new StorageDummy();
		return instance;
	}
	
	/**
	 * Checks to see if a given HashMap is a subset of another HashMap.
	 * 
	 * @param HashMap
	 *            <String,String> bigHash the hashmap that may contain the other
	 *            parameter
	 * @param HashMap
	 *            <String,String> subsetHash the hashmap that may be contained
	 *            by the other parameter
	 * @return boolean result True if subsetHash is contained within bigHash.
	 *         False: otherwise
	 */
		HashMap<String,String> oldMap= bigHash;
		bigHash.putAll(subsetHash);
		return bigHash.equals(oldMap);
	}
	
	/**
	 * Save attempts to assign all Values to their corresponding Fields within
	 * the Relation in the data repository. If the information is inserted
	 * without error, the function returns True, otherwise it returns False.
	 * 
	 * @param Relation
	 * @param Input
	 * @return
	 */
	@SuppressWarnings("unchecked")
	boolean Save(String Relation, Map<String, String> Input) {
		if (relation_to_store.containsKey(Relation))
			System.out.println("Invalid relation" + Relation);
		
		if (Relation.equals("cs320.patient")) {
			return patients.add((HashMap<String, String>)((HashMap<String, String>)Input).clone());
		}
		else {
			System.out.println("Invalid relation: '" + Relation + "'");
		}
		return false;
	}

	/**
	 * PushRecord() checks whether there is currently a connection to the server
	 * data repository by checking the status of the ServerConnected variable.
	 * In the case that there is not currently a connection to the server data
	 * repository one is made, using the Connect() function. After a connection
	 * is made the client data repository transfers all completed records to the
	 * server data repository. After the information has been successfully
	 * transferred and acknowledged the information is removed from the client
	 * data repository. If the entire process is successful it returns True,
	 * otherwise it returns False.
	 * 
	 * @return True if Sync was succesful, false otherwise
	 */
	boolean Push() {
		return true;
	}

	/**
	 * Store flags the relation relating to the patient record matching with
	 * PatientID within the data repository as only able to be appended to. It
	 * then calls the pushRecord method.
	 * 
	 * @param PatientId
	 * @return True if success, false otherwise.
	 * 
	 */
	boolean Store(String PatientId) {
		return true;
	}

	public HashMap<String, String> toHashMap(PatientInfo patient) {
		HashMap<String, String> map = new HashMap<String, String>();

		if (patient.GetFirstName() != "" && patient.GetFirstName() != null) {
			map.put("firstName", patient.GetFirstName());
		}
		if (patient.GetLastName() != "" && patient.GetLastName() != null) {
			map.put("lastName", patient.GetLastName());
		}
		if (patient.GetDateOfBirth() != null) {
			map.put("dateOfBirth", patient.GetDateOfBirth().toString());
		}
		if (patient.GetGender() != "" && patient.GetGender() != null) {
			map.put("gender", patient.GetGender());
		}
		if (patient.GetHeight() != 0) {
			map.put("height", "" + patient.GetHeight());
		}
		if (patient.GetWeight() != 0) {
			map.put("weight", "" + patient.GetWeight());
		}
		if (patient.GetMedicalRecordNumber() != 0) {
			map.put("medicalRecordNumber", ""
					+ patient.GetMedicalRecordNumber());
		}

		return map;
	}
}

/*
 * generate 10000 random combinations to be in the database randomly select 1000
 * of the combinations in the database and try to log in with them assert
 * loggedin random generate 1000 more that are no included in the first 10000
 * and try to log in with them assert notloggedin
 */