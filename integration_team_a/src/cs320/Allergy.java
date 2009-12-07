import java.util.*;
/** 
 *The Allergy module encapsulates a description of an allergy. 
 *Each instance of an Allergy will have a cause and a reaction, 
 *which will be stored as Strings. 
 *For example, if a patient was allergic to penicillin and his 
 *or her reaction to the penicillin was a rash, the cause would 
 *be "penicillin" and the reaction would be "rash."
 **/
public class Allergy extends AbstractModule {
	
	/**
	 * Cause of the allergy.
	 */
	private String Cause;
	
	/**
	 * Reaction that happens as a result of the allergy.
	 */
	private String Reaction;
	
	Allergy() {
		
	}

	/**
	 * Constructor for the Allergy module.
	 * The Allergy module's private internal variables will 
	 * be set to the parameters given in the function call.
	 */
	Allergy(String Cause, String Reaction){
		this.Cause = Cause;
		this.Reaction = Reaction;
	}
	
	/**
	 * Constructor that creates an allergy from a Map of Strings to Strings. 
	 */
	public Allergy(Map<String, String> Values) {
		Set<String> keySet = Values.keySet();
		Iterator<String> keySetIterator = keySet.iterator();
		this.Cause = keySetIterator.next();
		LinkedList<String> valueList = new LinkedList<String>();
		valueList.add(Values.toString());
		this.Reaction = valueList.getFirst();
	}

	/**
	 * Returns the patient allergy cause.
	 */
	public String GetCause() {
		return this.Cause;
	}

	/**
	 * Returns the patient allergy reaction.
	 */
	public String GetReaction() {
		return this.Reaction;
	}

	/**
	 * Sets the patient allergy cause.
	 */
	public void SetCause(String Cause) {
		this.Cause = Cause;
		
	}

	/**
	 * Sets the patient allergy reaction.
	 */
	public void SetReaction(String Reaction) {
		this.Reaction = Reaction;
	}
	

	/**
	 * Converts an allergy to a HashMap of Strings to Strings. 
	 */
	public HashMap<String,String> toHashMap() {
		HashMap <String, String> saveMap = new HashMap <String, String> ();
		saveMap.put("Cause", this.GetCause());
		saveMap.put("Reaction", this.GetReaction());
		return saveMap;
	}
	
	/**
	 * Saves the Allergy.
	 */
	public boolean Save() {
		Storage.GetInstance().Save("cs320.allergy", toHashMap());
		return true;
	}

	/**
	 * Stores the allergy permanently in the database.
	 */
	public boolean Store() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
