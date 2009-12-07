package cs320;

/**
 * Displayable
 * 
 * 
 * Marker interface for display and updateDisplay() methods. The abstract class
 * AbstractModule realizes this interface.
 * 
 * @author mbarrenecheajr
 *
 */
public interface Displayable {
	/**
	 * Display allows the module to be sent to the DisplayController for
	 * viewing it.
	 * @return
	 */
	public boolean Display();
}
