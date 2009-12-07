package cs320;

import java.util.HashMap;


/**
 * Storeable
 * 
 * Marker interface for saving() and storing() capabilities. The abstract class
 * AbstractModule realizes this interface.
 * 
 * @author mbarrenecheajr
 *
 */
public interface Storeable {
	
	/** Storing allows the Storage Module to officially mark this module
	 * as complete and append-only.
	 * @return
	 */
	public boolean Store();
	
	/**
	 * Saving allows the state of module to be written to the Data Repository via
	 * the Storage Module.
	 * @return
	 */
	public boolean Save();
}
