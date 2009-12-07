package cs320;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class AllergyDisplay extends DisplayObject {

	private static final long serialVersionUID = 1L;
	private JButton deleteButton = null;
	private JButton saveButton = null;
	private JTextField allergyField = null;
	private JTextField reactionField = null;
	private JLabel allergyLabel = null;
	private JLabel reactionLabel = null;
	private PatientRecord Record = null;
	private Allergy DisplayAllergy = null;
	private JTextField[] textFields;
	/**
	 * This is the default constructor
	 */
	public AllergyDisplay() {
		super();
		Record = PatientRecord.GetInstance();
		DisplayAllergy = new Allergy();
		initialize();
	}
	
	public AllergyDisplay(Allergy a) {
		super();
		Record = PatientRecord.GetInstance();
		DisplayAllergy = a;
		initialize();
	}
	
	public AllergyDisplay(PatientRecord r, Allergy a) {
		super();
		Record = r;
		DisplayAllergy = a;
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		reactionLabel = new JLabel();
		reactionLabel.setBounds(new Rectangle(205, 38, 63, 16));
		reactionLabel.setText("Reaction");
		allergyLabel = new JLabel();
		allergyLabel.setBounds(new Rectangle(12, 38, 60, 16));
		allergyLabel.setText("Allergy");
		this.setLayout(null);
		this.setLocation(new Point(0, 0));
		this.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
		this.setSize(new Dimension(550, 65));
		this.setPreferredSize(new Dimension(550, 65));
		this.add(getDeleteButton(), null);
		this.add(getAllergyField(), null);
		this.add(getReactionField(), null);
		this.add(getSaveButton(), null);
		this.add(allergyLabel, null);
		this.add(reactionLabel, null);
		textFields = new JTextField[2];
		textFields[0] = getAllergyField();
		textFields[1] = getReactionField();
	}

	/**
	 * This method initializes deleteButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getDeleteButton() {
		if (deleteButton == null) {
			deleteButton = new JButton();
			deleteButton.setText("Delete");
			deleteButton.setBounds(new Rectangle(383, 37, 84, 29));
			deleteButton.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			
			deleteButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					AllergyDisplay.this.setVisible(false);
				}
			});
			
		}
		return deleteButton;
	}
	
	/**
	 * This method initializes saveButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getSaveButton() {
		if (saveButton == null) {
			saveButton = new JButton();
			saveButton.setText("Save");
			saveButton.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			saveButton.setBounds(new Rectangle(470, 37, 75, 29));
			saveButton.setHorizontalAlignment(SwingConstants.RIGHT);

			saveButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if (saveButton.getText().equals("Save")) {
						saveButton.setText("Edit");
						for (JTextField j : textFields)
							j.setEditable(false);
						DisplayAllergy.SetCause(allergyField.getText());
						DisplayAllergy.SetReaction(reactionField.getText());
					}
					else {
						saveButton.setText("Save");
						for (JTextField j : textFields)
							j.setEditable(true);
					}
				}
			});
			
		}
		return saveButton;
	}

	/**
	 * This method initializes allergyField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getAllergyField() {
		if (allergyField == null) {
			allergyField = new JTextField();
			allergyField.setColumns(15);
			allergyField.setHorizontalAlignment(JTextField.LEFT);
			allergyField.setBounds(new Rectangle(8, 9, 188, 28));
			if (DisplayAllergy != null)
				allergyField.setText(DisplayAllergy.GetCause());
			}
		return allergyField;
	}

	/**
	 * This method initializes reactionField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getReactionField() {
		if (reactionField == null) {
			reactionField = new JTextField();
			reactionField.setColumns(25);
			reactionField.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			reactionField.setHorizontalAlignment(JTextField.LEFT);
			reactionField.setBounds(new Rectangle(201, 9, 338, 28));
			if (DisplayAllergy != null)
				allergyField.setText(DisplayAllergy.GetReaction());
		}
		return reactionField;
	}

}  //  @jve:decl-index=0:visual-constraint="30,40"
