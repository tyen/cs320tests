package cs320;

public abstract class AbstractModule implements Storeable, Displayable{

	
	/**
	 * Implementing Display in the abstract class allows any extensions to
	 * correctly display their module with flexibility in mind.
	 */
	public boolean Display() {
		
		//DisplayUtility.GetInstance().Display(this);
		return true;
		
	}
	
	
}
