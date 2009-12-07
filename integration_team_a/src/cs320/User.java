import java.awt.event.MouseEvent;
import java.util.HashMap;

/**
 * User Module
 * The User Module is in charge of maintaining a user object that represents the current user of the system
 * as well as user-specific functionality and information
 * 
 *  @author Joshua Haan
 */

public class User {
	
	private String UserName;
	private UserType Utype;
	private DisplayController DisplayCont = DisplayController.GetInstance();
	private long Time = System.currentTimeMillis();
	//MouseMotionListener MouseEvent = new MouseMotionListener(MouseEvent e);
	
	/**
	 * The type of user
	 */
	public enum UserType {
		NURSE, PHARMACIST, DOCTOR, ADMIN
	}
	
	/**
	 * This is the constructor for an instance of the User Module. 
	 * user becomes userName and type becomes uType.
	 */
	public User(String User, UserType UType) {
		UserName = User;
		Utype = UType;
	}
	
	public User(String UName, String Type) {
		UserName = UName;
		if (Type.toLowerCase().equals("nurse"))
			Utype = UserType.NURSE;
		else if (Type.toLowerCase().equals("pharmacist"))
			Utype = UserType.PHARMACIST;
		else if (Type.toLowerCase().equals("doctor"))
			Utype = UserType.DOCTOR;
		else if (Type.toLowerCase().equals("admin"))
			Utype = UserType.ADMIN;
	}

	/**
	 * The Sleep() function locks the screen and prevents data entry until the user re-authenticates. 
	 * This function is run on a timer that is reset upon user activity. There is no expected output.
	 */
	public void Sleep() {
		//Log out after some amount of time
		CheckTimeout();
	}
	
	/**
	 * This function returns the Username of the current system user being represented by the User Module. 
	 * The String that is returned is the Username corresponding to this User Module's instance.
	 */
	public String GetUserName() {
		return UserName;
	}
	
	/**
	 * This function returns the current user's UserType, which is an enumeration representing the type of user,
	 * for example, "Nurse" or "Pharmacist."
	 */
	public UserType GetUserType() {
		return Utype;
	}
	
	public void MouseMoved(MouseEvent e) {
		Time=System.currentTimeMillis();
		System.out.println("YOU MOVED THE MOUSE");
	}
	
	public void MouseDragged(MouseEvent e) {
		Time = System.currentTimeMillis();
		System.out.println("YOU MOVED THE MOUSE");
	}
	
	/** This function checks to see if ten minutes has passed without mouse motion.  If there has been no mouse
	 * motion then the login screen will be displayed and the user will need to login to the system
	 * once again
	 * 
	 */
	public void CheckTimeout(){	
		if ((System.currentTimeMillis() - Time) >= 60000) {
			DisplayCont.ShowLogin();
		}
	}
	
	
	//For testing
	
	public String password;
	
	public HashMap<String, String> toHashMap() {
		HashMap<String, String> map = new HashMap<String, String>();
		
		map.put("username", this.UserName);
		map.put("type", this.Utype.toString().toLowerCase());
		map.put("password", this.password);
		
		return map;
	}
}
