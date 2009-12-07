package cs320;

import java.awt.Dimension;
import java.awt.Rectangle;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


@SuppressWarnings("serial")
public class LoginDisplay extends DisplayObject {

	private DisplayController DisplayCont = DisplayController.GetInstance();
	private JTextField userNameField = null;
	private JPasswordField passwordField = null;
	private JLabel Password = null;
	private JLabel userNameLabel = null;
	private JButton submitButton = null;
	private JButton clearButton = null;
	private User sysUser;

	/**
	 * This method initializes 
	 * 
	 */
	public LoginDisplay() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 */
	private void initialize() {
        userNameLabel = new JLabel();
        userNameLabel.setText("User Name");
        userNameLabel.setBounds(new Rectangle(365, 247, 68, 16));
        Password = new JLabel();
        Password.setText("Password");
        Password.setBounds(new Rectangle(370, 303, 59, 16));
        this.setLayout(null);
        this.setSize(new Dimension(800, 600));
        this.add(getUserNameField(), null);
        this.add(getPasswordField(), null);
        this.add(Password, null);
        this.add(userNameLabel, null);
        this.add(getSubmitButton(), null);
        this.add(getClearButton(), null);
	}

	/**
	 * This method initializes userNameField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getUserNameField() {
		if (userNameField == null) {
			userNameField = new JTextField();
			userNameField.setColumns(15);
			userNameField.setBounds(new Rectangle(302, 218, 194, 28));
		}
		return userNameField;
	}

	/**
	 * This method initializes passwordField	
	 * 	
	 * @return javax.swing.JPasswordField	
	 */
	private JPasswordField getPasswordField() {
		if (passwordField == null) {
			passwordField = new JPasswordField();
			passwordField.setColumns(15);
			passwordField.setBounds(new Rectangle(302, 270, 194, 28));
		}
		return passwordField;
	}

	/**
	 * This method initializes submitButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getSubmitButton() {
		if (submitButton == null) {
			submitButton = new JButton();
			DisplayCont.GetWindow().getRootPane().setDefaultButton(submitButton);
			submitButton.setText("Login");
			submitButton.setBounds(new Rectangle(417, 339, 79, 29));
			submitButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					
//					if (l.GetSuccessfulLogin()) {
//						//Login
//					}
//					else {
//						userNameField.setText("");
//						passwordField.setText("");
//					}
					
					if ((userNameField.getText().equals("jdoe")) && ((new String(passwordField.getPassword())).equals("test"))) {
						DisplayController d = DisplayController.GetInstance();
						LoginDisplay.this.setVisible(false);
						sysUser = new User("Nurse Jane Doe", "NURSE");
						d.NewUser(sysUser.GetUserName());
						d.Display(new SearchMainDisplay(sysUser));
					}
					else {
						
					}
						
					//l.LoginToSystem(userNameField.getText(), new String(passwordField.getPassword()));
					
				}
			});
			
		}
		return submitButton;
	}

	/**
	 * This method initializes clearButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getClearButton() {
		if (clearButton == null) {
			clearButton = new JButton();
			clearButton.setText("Clear");
			clearButton.setBounds(new Rectangle(302, 339, 76, 29));
			
			clearButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					userNameField.setText("");
					passwordField.setText("");
				}
			});
			
		}
		return clearButton;
	}

}
