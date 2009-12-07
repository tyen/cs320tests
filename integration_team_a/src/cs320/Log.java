/** 
 * The Log Module handles the creation of the system log. The system 
 * log records any interaction with the Storage Module. 
 */

package cs320;

import java.util.*;
import java.util.Date;
import java.io.*;
import java.text.*;

public class Log {
	private static Log instance = null;
	private FileWriter Log;
	
	protected Log() {
		
	}
	
	public static Log getInstance() {
		if (instance == null)
			instance = new Log();
		return instance;
	}
	
/** 
 * Writes a line in the system log that includes the 
 * time stamp, user, functionCall, and the field of the parameters 
 * passed to the function call.
 */
	public boolean Write(String Username, String FunctionCall, List<String> Fields) {
		try {
			/* used to format the time stamp */
			Date date = new Date();
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	        
			Log = new FileWriter("system_log.txt", true); //opens the log as append only
			
			Log.write(dateFormat.format(date) + "\t" + Username + "\t" + FunctionCall + "\t");
			for(Iterator<String> i = Fields.iterator(); i.hasNext();) {
				Log.write("[" + i.next() + "]");
			}
			Log.write(System.getProperty("line.separator")); //inserts new line
			Log.close();
			return true;
		}
		catch (IOException e) {
			return false;
		}
	}
	
}