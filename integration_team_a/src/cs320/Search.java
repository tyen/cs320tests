import java.util.LinkedList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;

/**
 * 
 * @author Ethan
 * 
 */
public class Search implements Displayable {
	/*
	 * a list of formatted search results for the DisplayUtility
	 */
	private List<PatientInfo> searchResults;

	private static Search instance;

	public Search() {
		this.searchResults = new LinkedList<PatientInfo>();
	}

	/**
	 * 
	 * @param first
	 *            patient's first name
	 * @param last
	 *            patient's last name
	 * @param dateOfBirth
	 *            patient's date of birth
	 */

	public void SearchForPatient(String first, String last,
			DateWrapper dateOfBirth) {

		HashMap<String, String> searchTerm = new HashMap<String, String>();

		/*
		 * format search credentials to be sent to storage module
		 */
		searchTerm.put("firstName", first);
		searchTerm.put("lastName", last);
		searchTerm.put("dateOfBirth", dateOfBirth.toString());
		/*
		 * construct passable search term
		 */
		List<HashMap<String, String>> intermediateResults = new LinkedList<HashMap<String, String>>();

		Storage store = Storage.GetInstance();

		/*
		 * if the patient exists, retreive all matching terms from the data
		 * repository
		 */
		if (store.Exist("cs320.patient", searchTerm)) {
			intermediateResults = store.Retrieve("cs320.patient", searchTerm);
		}
		/*
		 * else place the search term in the list
		 */
		else {
			intermediateResults.add(searchTerm);
		}

		ListIterator<HashMap<String, String>> it = intermediateResults
				.listIterator();

		while (it.hasNext()) {
			searchResults.add(new PatientInfo(it.next()));
		}
	}

	public boolean Display() {
		DisplayUtility.GetInstance().Display(this);
		return true;
	}

	public List<PatientInfo> getSearchResults() {
		return searchResults;
	}

	public static Search GetInstance() {
		if (instance == null)
			instance = new Search();
		return instance;
	}
}
