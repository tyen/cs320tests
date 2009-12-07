package cs320;

import java.awt.Dimension;
import java.awt.Rectangle;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;

@SuppressWarnings("serial")
public class SearchMainDisplay extends DisplayObject {
	
	private DisplayController DisplayCont = DisplayController.GetInstance();
	private JTextField NameTxt = null;
	private JButton SearchButton = null;
	private JTextField LstNameTxt = null;
	private JLabel NameLbl = null;
	private JLabel LstNameLbl = null;
	private JLabel DOBLbl = null;
	private User SysUser;
	private JTextField[] textFields = new JTextField[3];
	private JButton clearButton = null;
	private DisplayController d = DisplayController.GetInstance();
	private JTextField dobField = null;
	private JLabel noneFoundLabel = null;
	
	public SearchMainDisplay() {
		super();
		initialize();
	}
	
	public SearchMainDisplay(User u) {
		super();
		SysUser = u;
		initialize();
	}
	
	/**
	 * This method initializes MainSearchPane	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private void initialize() {
			noneFoundLabel = new JLabel();
			noneFoundLabel.setBounds(new Rectangle(270, 379, 295, 16));
			noneFoundLabel.setHorizontalAlignment(SwingConstants.CENTER);
			noneFoundLabel.setForeground(Color.red);
			noneFoundLabel.setFont(new Font("Lucida Grande", Font.BOLD, 13));
			noneFoundLabel.setText("No Patients Found, Please Try Again");
			noneFoundLabel.setVisible(false);
			DOBLbl = new JLabel();
			DOBLbl.setBounds(new Rectangle(372, 307, 91, 16));
			DOBLbl.setHorizontalAlignment(SwingConstants.CENTER);
			DOBLbl.setText("Date Of Birth");
			LstNameLbl = new JLabel();
			LstNameLbl.setBounds(new Rectangle(450, 233, 136, 31));
			LstNameLbl.setHorizontalAlignment(SwingConstants.CENTER);
			LstNameLbl.setText("Last Name");
			NameLbl = new JLabel();
			NameLbl.setBounds(new Rectangle(237, 233, 136, 31));
			NameLbl.setHorizontalAlignment(SwingConstants.CENTER);
			NameLbl.setText("First Name");
			this.setLayout(null);
			this.setSize(new Dimension(800, 600));
			this.add(getNameTxt(), null);
			this.add(getSearchButton(), null);
			this.add(getLstNameTxt(), null);
			this.add(NameLbl, null);
			this.add(LstNameLbl, null);
			this.add(DOBLbl, null);
			this.add(getClearButton(), null);
			this.add(getDobField(), null);
			this.add(noneFoundLabel, null);
			textFields[0] = NameTxt;
			textFields[1] = LstNameTxt;
			textFields[2] = dobField;
	}

	/**
	 * This method initializes NameTxt	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getNameTxt() {
		if (NameTxt == null) {
			NameTxt = new JTextField();
			NameTxt.setHorizontalAlignment(JTextField.LEFT);
			NameTxt.setBounds(new Rectangle(225, 210, 166, 28));
		}
		return NameTxt;
	}

	/**
	 * This method initializes SearchButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getSearchButton() {
		if (SearchButton == null) {
			SearchButton = new JButton();
			DisplayCont.GetWindow().getRootPane().setDefaultButton(SearchButton);
			SearchButton.setBounds(new Rectangle(441, 327, 160, 29));
			SearchButton.setText("Search");
			SearchButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					noneFoundLabel.setVisible(false);
					debugSearch();
					
					//Search findPatient=new Search();
					//findPatient.SearchForPatient(first,last,DOB);
					//List<PatientInfo> results = findPatient.getSearchResults();
					//if (results.size() > 1) {
						//More Than One Result
						//SearchMainDisplay.this.setVisible(false);
						//d.Display(new SearchDisplay());
					//}
					//else if (results.size() < 1) {
						//Less Than One Result
					//}
					//else {
						//One Result
					//}
					
					
				
				}
			});
		}
		return SearchButton;
	}

	/**
	 * This method initializes LstNameTxt	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getLstNameTxt() {
		if (LstNameTxt == null) {
			LstNameTxt = new JTextField();
			LstNameTxt.setBounds(new Rectangle(435, 210, 166, 31));
			LstNameTxt.setHorizontalAlignment(JTextField.LEFT);
		}
		return LstNameTxt;
	}

	/**
	 * This method initializes clearButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getClearButton() {
		if (clearButton == null) {
			clearButton = new JButton();
			clearButton.setBounds(new Rectangle(225, 327, 160, 29));
			clearButton.setText("Clear");
			clearButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					noneFoundLabel.setVisible(false);
					for (JTextField j : textFields)
						j.setText("");
				}
			});
		}
		return clearButton;
	}

	/**
	 * This method initializes dobField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getDobField() {
		if (dobField == null) {
			dobField = new JTextField();
			dobField.setBounds(new Rectangle(356, 278, 122, 28));
			dobField.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		}
		return dobField;
	}
	
	private void debugSearch() {
		if (NameTxt.getText().equals("John") && LstNameTxt.getText().equals("Doe") && (new DateWrapper(dobField.getText())).equals(new DateWrapper("1/1/2000"))) {
			SearchMainDisplay.this.setVisible(false);
			d.Display(new PatientRecordDisplay(SysUser, PatientRecord.GetInstance()));
		}
		else if (NameTxt.getText().equals("Jon")) {
			SearchMainDisplay.this.setVisible(false);
			d.Display(new SearchDisplay(SysUser));
		}
		else {
			for (JTextField j : textFields)
				j.setText("");
			
			noneFoundLabel.setVisible(true);
		}
	}

}
