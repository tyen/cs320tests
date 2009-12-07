import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Question Set Sub-Module
 * <p> The Question Set Module encapsulates a question set for the passive questionnaire.
 * It does this by managing both a default question set ({@link QuestionSet#StaticQuestionSet}) and a dynamic
 * question set ({@link QuestionSet#DynamicQuestionSet}). Its interface allows other modules to set the dynamic
 * question set. It also allows other modules to read the question set. It allows the
 * Patient Record Module to add new questions to the default passive questionnaire and allows
 * the Display Utility to correctly display the questions to the user. QuestionSet is
 * initialized to contain a default question set defined in the System Schema. </p>
 * 
 *  @author Tristan Peck
 */
public class QuestionSet implements Displayable {
    /** An immutable list of questions defined in the System Schema.*/
    private List<String> StaticQuestionSet;
    /** A mutable list of nonstandard questions from the Drug Module.*/
    private List<String> DynamicQuestionSet;
    
    /**
     * Constructs an instance of QuestionSet. This function initializes
     * {@link QuestionSet#DynamicQuestionSet} to an empty list, and
     * {@link QuestionSet#StaticQuestionSet} with the contents of Values.
     * @param Values The default question set to use. Questions are defined as (n, question),
     * where n is the index of question. Questions will be arranged in ascending order of n.
     */
    public QuestionSet(Map<String, String> Values) {
    	//Iterate through Values for every pair (n, question),
    	//where n is the index of question.
    	LinkedList<String> ValuesQuestionSet = new LinkedList<String>();
    	for(int i = 0; i < Values.size(); i++)
    		ValuesQuestionSet.addLast(Values.get(Integer.toString(i)));
    	this.StaticQuestionSet = ValuesQuestionSet;
    	
    	//Initialized empty
		this.DynamicQuestionSet = new LinkedList<String>();
    }
    
    /**
     * TEST CONSTRUCTER. Has package access, not public access.
     * @param StaticQuestionSet The default question set to use.
     */
    QuestionSet(List<String> StaticQuestionSet) {
    	this.StaticQuestionSet = StaticQuestionSet;
		this.DynamicQuestionSet = new LinkedList<String>();
    }
    
    public QuestionSet() {
		// TODO Auto-generated constructor stub
	}
	/**
     * The Display() function contains a call to the appropriate function for displaying a
     * Question Set Module's interface within the Display Utility Module. The Display
     * (QuestionSet) function within the Display Utility will return a boolean value
     * representing whether the display occurred successfully or not, so that if there is an
     * error, the Question Set Module can call Display() again.
     */
    public boolean Display() {
		return DisplayUtility.GetInstance().Display(this);
    }
    
    /**
     * Sets the question set's nonstandard questions to NonstandardQuestions by setting
     * {@link QuestionSet#DynamicQuestionSet} (a reference to a List of String) to point
     * to NonstandardQuestions. The order in which the nonstandard questions will appear in
     * the GetQuestions()'s return value is defined as the order in which an Iterator would
     * pass over the questions in NonstandardQuestions. If NonstandardQuestions is an empty
     * list or null, DynamicQuestionSet will instead be set to an empty List of String.
     * @param NonstandardQuestions The List of String to set DynamicQuestionSet to.
     */
    public void UpdateQuestionSet(List<String> NonstandardQuestions){
    	if(NonstandardQuestions == null)
    		//Ensure that this.DynamicQuestionSet is never null
    		this.DynamicQuestionSet = new LinkedList<String>();
    	else
    		this.DynamicQuestionSet = NonstandardQuestions;
    }
    
    /**
     * Returns a list formed by appending {@link QuestionSet#DynamicQuestionSet} to the end
     * of {@link QuestionSet#StaticQuestionSet} (this process produces a new list and does
     * not modify StaticQuestionSet or DynamicQuestionSet). An Iterator that passes over
     * the returned list will pass over the questions in StaticQuestionSet in correct order
     * first, and then all the questions in DynamicQuestionSet in correct order. For the
     * questions from StaticQuestionSet, the correct order is defined in the System Schema.
     * For the questions from DynamicQuestionSet, the correct order is defined as the order
     * an Iterator would pass over the elements in DynamicQuestionSet.
     * @return The merged list.
     */
    public List<String> GetQuestions() {
    	//Strings are immutable, so it is sufficient to copy the
    	//references into a new List. (This way it's impossible to
    	//modify the merged list to modify this QuestionSet's fields.)
    	LinkedList<String> allQuestions = new LinkedList<String>();
		allQuestions.addAll(this.StaticQuestionSet);
		allQuestions.addAll(this.DynamicQuestionSet);
		return allQuestions;
    }
}
