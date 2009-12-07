import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class DrugDisplay extends DisplayObject {

	private static final long serialVersionUID = 1L;
	private JPanel rightStatusIndicator = null;
	private JPanel leftStatusIndicator = null;
	private JTextField drugNameField = null;
	private JTextField dosageField = null;
	private JTextField dosageUnitField = null;
	private JTextField routeField = null;
	private JTextField frequencyField = null;
	private JTextField startDateField = null;
	private JTextField stopDateField = null;
	private JTextField prescriberField = null;
	private JTextField reasonField = null;
	private JButton saveButton = null;
	private JButton deleteButton = null;
	private JLabel drugNameLabel = null;
	private JLabel dosageLabel = null;
	private JLabel unitLabel = null;
	private JLabel routLabel = null;
	private JLabel frequencyLabel = null;
	private JLabel startDateLabel = null;
	private JLabel stopDateLabel = null;
	private JLabel prescriberLabel = null;
	private JLabel reasonLabel = null;
	private JLabel notesLabel = null;
	private JTextArea noteTextArea = null;
	private PatientRecord Record = null;
	private Drug DisplayDrug = null;
	private JTextField[] textFields;
	/**
	 * This is the default constructor
	 */
	public DrugDisplay() {
		super();
		Record = PatientRecord.GetInstance();
		DisplayDrug = new Drug();
		initialize();
	}
	
	public DrugDisplay(PatientRecord R) {
		super();
		Record = R;
		DisplayDrug = new Drug();
		initialize();
	}
	
	public DrugDisplay(PatientRecord R, Drug D) {
		super();
		Record = R;
		DisplayDrug = D;
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		notesLabel = new JLabel();
		notesLabel.setBounds(new Rectangle(146, 131, 37, 16));
		notesLabel.setHorizontalAlignment(SwingConstants.CENTER);
		notesLabel.setText("Notes");
		reasonLabel = new JLabel();
		reasonLabel.setBounds(new Rectangle(349, 96, 106, 16));
		reasonLabel.setHorizontalAlignment(SwingConstants.CENTER);
		reasonLabel.setText("Reason Taken");
		prescriberLabel = new JLabel();
		prescriberLabel.setBounds(new Rectangle(348, 59, 71, 16));
		prescriberLabel.setHorizontalAlignment(SwingConstants.CENTER);
		prescriberLabel.setText("Prescriber");
		stopDateLabel = new JLabel();
		stopDateLabel.setBounds(new Rectangle(165, 59, 61, 16));
		stopDateLabel.setHorizontalAlignment(SwingConstants.CENTER);
		stopDateLabel.setText("Stop Date");
		startDateLabel = new JLabel();
		startDateLabel.setBounds(new Rectangle(53, 59, 78, 16));
		startDateLabel.setHorizontalAlignment(SwingConstants.CENTER);
		startDateLabel.setText("Start Date");
		frequencyLabel = new JLabel();
		frequencyLabel.setBounds(new Rectangle(433, 24, 76, 16));
		frequencyLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frequencyLabel.setText("Frequency");
		routLabel = new JLabel();
		routLabel.setBounds(new Rectangle(345, 24, 38, 16));
		routLabel.setHorizontalAlignment(SwingConstants.CENTER);
		routLabel.setText("Route");
		unitLabel = new JLabel();
		unitLabel.setBounds(new Rectangle(263, 24, 38, 16));
		unitLabel.setHorizontalAlignment(SwingConstants.CENTER);
		unitLabel.setText("Unit");
		dosageLabel = new JLabel();
		dosageLabel.setBounds(new Rectangle(192, 24, 57, 16));
		dosageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		dosageLabel.setText("Dosage");
		drugNameLabel = new JLabel();
		drugNameLabel.setBounds(new Rectangle(70, 24, 84, 16));
		drugNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		drugNameLabel.setText("Drug Name");
		this.setLayout(null);
		this.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
		this.setBounds(new Rectangle(0, 0, 550, 150));
		this.setPreferredSize(new Dimension(550, 150));
		textFields = new JTextField[9];
		this.add(getDrugNameField(), null);
		textFields[0] = getDrugNameField();
		this.add(getDosageField(), null);
		textFields[1] = getDosageField();
		this.add(getDosageUnitField(), null);
		textFields[2] = getDosageUnitField();
		this.add(getRouteField(), null);
		textFields[3] = getRouteField();
		this.add(getFrequencyField(), null);
		textFields[4] = getFrequencyField();
		this.add(getStartDateField(), null);
		textFields[5] = getStartDateField();
		this.add(getStopDateField(), null);
		textFields[6] = getStopDateField();
		this.add(getPrescriberField(), null);
		textFields[7] = getPrescriberField();
		this.add(getReasonField(), null);
		textFields[8] = getReasonField();
		this.add(getSaveButton(), null);
		this.add(getDeleteButton(), null);
		this.add(drugNameLabel, null);
		this.add(dosageLabel, null);
		this.add(unitLabel, null);
		this.add(routLabel, null);
		this.add(frequencyLabel, null);
		this.add(startDateLabel, null);
		this.add(stopDateLabel, null);
		this.add(prescriberLabel, null);
		this.add(reasonLabel, null);
		this.add(getLeftStatusIndicator(), null);
		this.add(getRightStatusIndicator(), null);
		this.add(notesLabel, null);
		this.add(getNoteTextArea(), null);
	}

	/**
	 * This method initializes rightStatusIndicator	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getRightStatusIndicator() {
		if (rightStatusIndicator == null) {
			rightStatusIndicator = new JPanel();
			rightStatusIndicator.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
			rightStatusIndicator.setBounds(new Rectangle(525, 0, 25, 150));
			rightStatusIndicator.setPreferredSize(new Dimension(25, 150));
			if (DisplayDrug != null) {
				if (DisplayDrug.IsQuestionable())
					rightStatusIndicator.setBackground(new Color(255, 255, 0));
				else if (DisplayDrug.IsCritical())
					rightStatusIndicator.setBackground(new Color(255, 0, 0));
				else rightStatusIndicator.setBackground(new Color(238, 238, 238));
			}
			else rightStatusIndicator.setBackground(new Color(238, 238, 238));
			
		}
		return rightStatusIndicator;
	}

	/**
	 * This method initializes leftStatusIndicator	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getLeftStatusIndicator() {
		if (leftStatusIndicator == null) {
			leftStatusIndicator = new JPanel();
			leftStatusIndicator.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
			leftStatusIndicator.setBounds(new Rectangle(0, 0, 25, 150));
			leftStatusIndicator.setPreferredSize(new Dimension(25, 150));
			if (DisplayDrug != null) {
				if (DisplayDrug.IsQuestionable())
					leftStatusIndicator.setBackground(new Color(255, 255, 0));
				else if (DisplayDrug.IsCritical())
					leftStatusIndicator.setBackground(new Color(255, 0, 0));
				else leftStatusIndicator.setBackground(new Color(238, 238, 238));
			}
			else leftStatusIndicator.setBackground(new Color(238, 238, 238));
			
		}
		return leftStatusIndicator;
	}

	/**
	 * This method initializes drugNameField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getDrugNameField() {
		if (drugNameField == null) {
			drugNameField = new JTextField();
			drugNameField.setBounds(new Rectangle(45, 6, 137, 20));
			if (DisplayDrug != null)
				drugNameField.setText(DisplayDrug.GetName());
		}
		return drugNameField;
	}

	/**
	 * This method initializes dosageField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getDosageField() {
		if (dosageField == null) {
			dosageField = new JTextField();
			dosageField.setBounds(new Rectangle(192, 6, 59, 20));
			if (DisplayDrug != null)
				dosageField.setText(Double.toString(DisplayDrug.GetDosage()));
		}
		return dosageField;
	}

	/**
	 * This method initializes dosageUnitField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getDosageUnitField() {
		if (dosageUnitField == null) {
			dosageUnitField = new JTextField();
			dosageUnitField.setBounds(new Rectangle(266, 6, 34, 20));
			if (DisplayDrug != null)
				dosageUnitField.setText(DisplayDrug.GetDosageUnit());
		}
		return dosageUnitField;
	}

	/**
	 * This method initializes routeField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getRouteField() {
		if (routeField == null) {
			routeField = new JTextField();
			routeField.setBounds(new Rectangle(312, 6, 106, 20));
			if (DisplayDrug != null)
				routeField.setText(DisplayDrug.GetRoute());
		}
		return routeField;
	}

	/**
	 * This method initializes frequencyField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getFrequencyField() {
		if (frequencyField == null) {
			frequencyField = new JTextField();
			frequencyField.setBounds(new Rectangle(432, 6, 80, 20));
			if (DisplayDrug != null)
				frequencyField.setText(DisplayDrug.GetFrequency());
		}
		return frequencyField;
	}

	/**
	 * This method initializes startDateField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getStartDateField() {
		if (startDateField == null) {
			startDateField = new JTextField();
			startDateField.setBounds(new Rectangle(45, 41, 95, 20));
			if (DisplayDrug != null)
				startDateField.setText(DisplayDrug.GetStartDate().toString(false, false));
		}
		return startDateField;
	}

	/**
	 * This method initializes stopDateField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getStopDateField() {
		if (stopDateField == null) {
			stopDateField = new JTextField();
			stopDateField.setBounds(new Rectangle(148, 41, 95, 20));
			if (DisplayDrug != null)
				stopDateField.setText(DisplayDrug.GetStopDate().toString(false, false));
		}
		return stopDateField;
	}

	/**
	 * This method initializes prescriberField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getPrescriberField() {
		if (prescriberField == null) {
			prescriberField = new JTextField();
			prescriberField.setBounds(new Rectangle(256, 41, 255, 20));
			if (DisplayDrug != null)
				prescriberField.setText(DisplayDrug.GetPrescriber());
		}
		return prescriberField;
	}

	/**
	 * This method initializes reasonField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getReasonField() {
		if (reasonField == null) {
			reasonField = new JTextField();
			reasonField.setBounds(new Rectangle(294, 77, 216, 20));
			if (DisplayDrug != null)
				reasonField.setText(DisplayDrug.GetReason());
		}
		return reasonField;
	}

	/**
	 * This method initializes saveButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getSaveButton() {
		if (saveButton == null) {
			saveButton = new JButton();
			saveButton.setLocation(new Point(421, 119));
			saveButton.setText("Save");
			saveButton.setSize(new Dimension(75, 25));
			
			saveButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if (saveButton.getText().equals("Save")) {
						saveButton.setText("Edit");
						for (JTextField f : textFields)
							f.setEditable(false);
						noteTextArea.setEditable(false);
						DisplayDrug.SetName(drugNameField.getText());
						DisplayDrug.SetDosage(Double.parseDouble(dosageField.getText()));
						DisplayDrug.SetDosageUnit(dosageUnitField.getText());
						DisplayDrug.SetRoute(routeField.getText());
						DisplayDrug.SetFrequency(frequencyField.getText());
						DisplayDrug.SetStartDate(new DateWrapper(startDateField.getText()));
						DisplayDrug.SetStopDate(new DateWrapper(stopDateField.getText()));
						DisplayDrug.SetPrescriber(prescriberField.getText());
						DisplayDrug.SetReason(reasonField.getText());
					}
					else {
						saveButton.setText("Save");
						for (JTextField f : textFields)
							f.setEditable(true);
						noteTextArea.setEditable(true);
					}
				}
			});
			
		}
		return saveButton;
	}

	/**
	 * This method initializes deleteButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getDeleteButton() {
		if (deleteButton == null) {
			deleteButton = new JButton();
			deleteButton.setLocation(new Point(318, 119));
			deleteButton.setText("Delete");
			deleteButton.setSize(new Dimension(75, 25));
			
			deleteButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					DrugDisplay.this.setVisible(false);
					if (Record != null)
						Record.GetDrugs().remove(DisplayDrug);
				}
			});
			
		}
		return deleteButton;
	}

	/**
	 * This method initializes noteTextArea	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getNoteTextArea() {
		if (noteTextArea == null) {
			noteTextArea = new JTextArea();
			noteTextArea.setBounds(new Rectangle(44, 77, 240, 56));
			noteTextArea.setLineWrap(true);
		}
		return noteTextArea;
	}

}  //  @jve:decl-index=0:visual-constraint="331,117"
