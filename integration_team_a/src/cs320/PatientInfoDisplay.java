import java.awt.Color;
import java.awt.Rectangle;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class PatientInfoDisplay extends DisplayObject {

	private JTextField firstField = null;
	private JTextField lastField = null;
	private JTextField idField = null;
	private JTextField dobField = null;
	private JTextField heightField = null;
	private JTextField weightField = null;
	private JComboBox genderDropDown = null;
	private JLabel firstLabel = null;
	private JLabel lastLabel = null;
	private JLabel idLabel = null;
	private JLabel dobLabel = null;
	private JLabel heightLabel = null;
	private JLabel weightLabel = null;
	private JLabel genderLabel = null;
	private PatientInfo Patient;
	/**
	 * This method initializes 
	 * 
	 */
	public PatientInfoDisplay() {
		super();
		initialize();
	}
	
	public PatientInfoDisplay(PatientInfo p) {
		super();
		Patient = p;
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 */
	private void initialize() {
        genderLabel = new JLabel();
        genderLabel.setBounds(new Rectangle(458, 116, 37, 16));
        genderLabel.setHorizontalAlignment(SwingConstants.CENTER);
        genderLabel.setText("Sex");
        weightLabel = new JLabel();
        weightLabel.setBounds(new Rectangle(287, 116, 60, 16));
        weightLabel.setHorizontalAlignment(SwingConstants.CENTER);
        weightLabel.setText("Weight");
        heightLabel = new JLabel();
        heightLabel.setBounds(new Rectangle(199, 116, 57, 16));
        heightLabel.setHorizontalAlignment(SwingConstants.CENTER);
        heightLabel.setText("Height");
        dobLabel = new JLabel();
        dobLabel.setBounds(new Rectangle(40, 116, 111, 16));
        dobLabel.setHorizontalAlignment(SwingConstants.CENTER);
        dobLabel.setText("Date of Birth");
        idLabel = new JLabel();
        idLabel.setBounds(new Rectangle(414, 47, 75, 16));
        idLabel.setHorizontalAlignment(SwingConstants.CENTER);
        idLabel.setText("Patient ID");
        lastLabel = new JLabel();
        lastLabel.setBounds(new Rectangle(234, 47, 77, 16));
        lastLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lastLabel.setText("Last Name");
        firstLabel = new JLabel();
        firstLabel.setBounds(new Rectangle(55, 47, 80, 16));
        firstLabel.setHorizontalAlignment(SwingConstants.CENTER);
        firstLabel.setText("First Name");
        this.setLayout(null);
        this.setBounds(new Rectangle(0, 0, 575, 150));
        this.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
        this.add(getFirstField(), null);
        this.add(getLastField(), null);
        this.add(getIdField(), null);
        this.add(getDobField(), null);
        this.add(getHeightField(), null);
        this.add(getWeightField(), null);
        this.add(getGenderDropDown(), null);
        this.add(firstLabel, null);
        this.add(lastLabel, null);
        this.add(idLabel, null);
        this.add(dobLabel, null);
        this.add(heightLabel, null);
        this.add(weightLabel, null);
        this.add(genderLabel, null);
	}

	/**
	 * This method initializes firstField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getFirstField() {
		if (firstField == null) {
			firstField = new JTextField();
			firstField.setBounds(new Rectangle(13, 13, 165, 28));
			if (Patient != null)
				firstField.setText(Patient.GetFirstName());
		}
		return firstField;
	}

	/**
	 * This method initializes lastField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getLastField() {
		if (lastField == null) {
			lastField = new JTextField();
			lastField.setBounds(new Rectangle(190, 13, 165, 28));
			if (Patient != null)
				lastField.setText(Patient.GetLastName());
		}
		return lastField;
	}

	/**
	 * This method initializes idField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getIdField() {
		if (idField == null) {
			idField = new JTextField();
			idField.setBounds(new Rectangle(369, 13, 165, 28));
			if (Patient != null)
				idField.setText(Long.toString(Patient.GetMedicalRecordNumber()));
		}
		return idField;
	}

	/**
	 * This method initializes dobField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getDobField() {
		if (dobField == null) {
			dobField = new JTextField();
			dobField.setBounds(new Rectangle(13, 82, 165, 28));
			if (Patient != null) {
				dobField.setText(Patient.GetDateOfBirth().toString(false, false));
			}
		}
		return dobField;
	}

	/**
	 * This method initializes heightField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getHeightField() {
		if (heightField == null) {
			heightField = new JTextField();
			heightField.setBounds(new Rectangle(190, 82, 75, 28));
			if (Patient != null)
				heightField.setText(Integer.toString(Patient.GetHeight()));
		}
		return heightField;
	}

	/**
	 * This method initializes weightField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getWeightField() {
		if (weightField == null) {
			weightField = new JTextField();
			weightField.setBounds(new Rectangle(280, 82, 75, 28));
			if (Patient != null)
				weightField.setText(Integer.toString(Patient.GetWeight()));
		}
		return weightField;
	}

	/**
	 * This method initializes genderDropDown	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getGenderDropDown() {
		if (genderDropDown == null) {
			genderDropDown = new JComboBox();
			genderDropDown.setBounds(new Rectangle(419, 83, 115, 27));
			genderDropDown.setMaximumRowCount(2);
			genderDropDown.addItem("Male");
			genderDropDown.addItem("Female");
			if (Patient != null)
				genderDropDown.setSelectedItem(Patient.GetGender());
		}
		return genderDropDown;
	}

}
