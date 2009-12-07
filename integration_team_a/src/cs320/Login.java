import java.util.*;

/**
 * The Login Module handles interaction between the User and Storage Modules. 
 * This will perform validation of user credentials by checking user-inputted information against 
 * the data repository. It creates an instance of the User Module upon a successful login.
 * @author Joshua Haan
 *
 */
public class Login {

	private DisplayUtility DisplayUtil;
	private Storage Store;
	private boolean SuccessfulLogin;
	private User NewUser;
	
	public Login() {
		DisplayUtil = DisplayUtility.GetInstance();
	}

	/**
	 * The LoginToSystem() function communicates with the Storage Module. 
	 * It accesses the Storage Module's Exist() function and checks for the existence 
	 * of a Username and Password match. If there is a Username and Password match, 
	 * a User Module instance will be created and populated. 
	 * If the passed Username and Password do not exist as a pair, then logging into the system 
	 * will not occur, and the Login interface will be displayed again. 
	 */
	public void LoginToSystem(String UserName, String Password){
		HashMap<String, String> UserAndPass = new HashMap<String, String>();
		
		UserAndPass.put("Username", UserName);
		UserAndPass.put("Password", Password);
		
		//if (Store.Exist("cs320.user", UserAndPass)) {
		if (Store.Exist("cs320.user", UserAndPass)) {
			LinkedList<HashMap<String, String>> UserInfo = (LinkedList<HashMap<String, String>>) Store.Retrieve("cs320.user", UserAndPass);
			NewUser = CreateUser(UserName, UserInfo.get(0).get("Type"));
			SuccessfulLogin = true;
		}
		//Else clear user/password and redisplay login
		else {
			SuccessfulLogin = false;
		}
	}
	
	public User GetUser() {
		return NewUser;
	}
	
	public User CreateUser(String User, String UserType) {
		User U = new User(User, UserType);
		return U;
	}
	
	public User CreateUser(String User, User.UserType Type){
		User U = new User(User, Type);
		return U;
	}
	
	public void Display(){
		DisplayUtil.Display(this);
	}
	
	public boolean GetSuccessfulLogin() {
		return SuccessfulLogin;
	}
}
