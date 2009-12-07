package cs320;

import java.awt.Rectangle;
import javax.swing.JScrollPane;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class PatientRecordDisplay extends DisplayObject {

	private AddAllergyDisplay addAllergyDisplay = null;
	private AddDrugDisplay addDrugDisplay = null;
	private BottomBarDisplay BotBar= null;
	private JScrollPane drugPanel = null;
	private JScrollPane allergyPane = null;
	private QuestionDisplay questionDisplay = null;
	private PatientInfoDisplay patientInfoDisp=null;
	private JButton submitButton = null;
	private PatientRecord Record = null;
	private User SysUser;
	private PatientInfo Patient;

	/**
	 * This method initializes 
	 * 
	 */
	public PatientRecordDisplay() {
		super();
		initialize();
	}

	public PatientRecordDisplay(User u, PatientRecord r) {
		super();
		Record = r;
		SysUser = u;
		Patient = r.GetPatientInfo();
		initialize();
	}
	
	/**
	 * This method initializes this
	 * 
	 */
	private void initialize() {
        this.setLayout(null);
        this.setBounds(new Rectangle(0, 0, 800, 580));
        this.add(getAddAllergyDisplay(), getAddAllergyDisplay().getName());
        this.add(getAddDrugDisplay(), getAddDrugDisplay().getName());
        this.add(getDrugPanel(), null);
        this.add(getAllergyPane(), null);
        this.add(getQuestionDisplay(), getQuestionDisplay().getName());
        this.add(getSubmitButton(), null);
        this.add(getPatientInfoDisplay(), null);
        this.add(getBotBarDisplay(), null);
        if (Record != null)
        	BotBar.SetLastModUser(SysUser.GetUserName());
        
	}

	/**
	 * This method initializes addAllergyDisplay	
	 * 	
	 * @return AddAllergyDisplay	
	 */
	private AddAllergyDisplay getAddAllergyDisplay() {
		if (addAllergyDisplay == null) {
			addAllergyDisplay = new AddAllergyDisplay();
			addAllergyDisplay.setBounds(new Rectangle(0, 240, 570, 20));
		}
		return addAllergyDisplay;
	}

	/**
	 * This method initializes addDrugDisplay	
	 * 	
	 * @return AddDrugDisplay	
	 */
	private AddDrugDisplay getAddDrugDisplay() {
		if (addDrugDisplay == null) {
			addDrugDisplay = new AddDrugDisplay(Record);
			addDrugDisplay.setBounds(new Rectangle(0, 420, 570, 20));
		}
		return addDrugDisplay;
	}

	/**
	 * This method initializes drugPanel	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getDrugPanel() {
		if (drugPanel == null) {
			drugPanel = new JScrollPane();
			drugPanel.setBounds(new Rectangle(0, 265, 570, 155));
			drugPanel.getViewport().add(new DrugDisplay(), -1);
			drugPanel.getViewport().validate();
		}
		return drugPanel;
	}

	/**
	 * This method initializes allergyPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getAllergyPane() {
		if (allergyPane == null) {
			allergyPane = new JScrollPane();
			allergyPane.setBounds(new Rectangle(0, 170, 570, 70));
			allergyPane.createVerticalScrollBar().setAutoscrolls(true);
			allergyPane.getViewport().add(new AllergyDisplay());
			allergyPane.getViewport().validate();
		}
		return allergyPane;
	}

	/**
	 * This method initializes questionDisplay	
	 * 	
	 * @return QuestionDisplay	
	 */
	private QuestionDisplay getQuestionDisplay() {
		if (questionDisplay == null) {
			questionDisplay = new QuestionDisplay();
			questionDisplay.setLocation(570, 20);
		}
		return questionDisplay;
	}
	
	private PatientInfoDisplay getPatientInfoDisplay() {
		if (patientInfoDisp == null) {
			patientInfoDisp = new PatientInfoDisplay(Patient);
			patientInfoDisp.setBounds(new Rectangle(0, 20, 570, 150));
		}
		return patientInfoDisp;
	}
	
	
	
	private BottomBarDisplay getBotBarDisplay() {
		if (BotBar == null) {
			BotBar = new BottomBarDisplay();
			BotBar.setBounds(new Rectangle(0, 540, 800, 20));
			BotBar.setLocation(0, 560);
		}
		return BotBar;
	}

	/**
	 * This method initializes submitButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getSubmitButton() {
		if (submitButton == null) {
			submitButton = new JButton();
			submitButton.setBounds(new Rectangle(657, 529, 141, 29));
			submitButton.setText("Submit Record");
		}
		return submitButton;
	}

}
