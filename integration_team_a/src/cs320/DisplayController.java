import java.util.ArrayList;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class DisplayController extends JFrame  {
	
	private static DisplayController Instance = null;
	private Login SysLogin;
	private JFrame Window;
	private TopBarDisplay TopBar;
	private String CurrentUser;
	private ArrayList<DisplayObject> DisplayedObjects = new ArrayList<DisplayObject>();
	
	public static void main(String[] args) {
		Instance = DisplayController.GetInstance();
		Instance.SysLogin = new Login();
		Instance.SysLogin.Display();
	}
	
	/**
	* Protected Constructor for DisplayUtility
	*/
	protected DisplayController(){
		InitBase();
	}
	
	/**
	 * Returns a singleton instance of DisplayController
	 * @return Singleton Instance of DisplayController
	 */
	public static DisplayController GetInstance() {
		if (Instance == null)
			Instance = new DisplayController();
		return Instance;
	}
	
	/**
	 * Initializes the base frame for the software
	 */
	private void InitBase() {
		Window = new JFrame("Medical History Software");
		Window.setBounds(0, 0, 800, 600);
		Window.setVisible(true);
		Window.setResizable(false);
		Window.getContentPane().setLayout(null);
		Window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		TopBar = new TopBarDisplay();
		TopBar.setLocation(0,0);
		TopBar.getUserLabel().setText("");
		
		Window.add(TopBar);
	}
	
	/**
	 * Displays DispObj at 0,0
	 * @param DispObj Object to Display
	 */
	public void Display(DisplayObject DispObj) {
		Window.add(DispObj);
		DisplayedObjects.add(DispObj);
		Refresh();
	}
	
	/**
	 * Displays DispObj at Loc
	 * @param DispObj Object to Display
	 * @param Loc Location at which to display DispObj
	 */
	public void Display(DisplayObject DispObj, Location Loc) {
		Window.add(DispObj);
		DisplayedObjects.add(DispObj);
		if (Loc.getX() < 0 || Loc.getY() < 0)
			DispObj.setLocation(0,0);
		else
			DispObj.setLocation(Loc);
		Refresh();
	}
	
	/**
	 * Refreshes the screen
	 */
	private void Refresh() {
		Window.repaint();
		Window.validate();
		TearDown();
	}
	
	public void NewUser(String UName) {
		if (UName != null) {
			CurrentUser = UName;
			TopBar.getUserLabel().setText(CurrentUser);
		}
	}
	
	public String GetCurrentUserName() {
		return CurrentUser;
	}
	
	public void TearDown() {
		ArrayList<DisplayObject> ToRemove = new ArrayList<DisplayObject>();
		for (DisplayObject d : DisplayedObjects)
			if (!d.isVisible()) {
				Window.remove(d);
				ToRemove.add(d);
			}
		for (DisplayObject d : ToRemove)
			DisplayedObjects.remove(d);
	}
	
	public void ShowLogin() {
		TearDown();
		SysLogin.Display();
		Refresh();
	}
	
	protected JFrame GetWindow() {
		return Window;
	}

}
