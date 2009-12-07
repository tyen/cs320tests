package cs320;

import java.awt.Dimension;
import java.awt.Rectangle;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JViewport;

import java.awt.Color;

@SuppressWarnings("serial")
public class AddDrugDisplay extends DisplayObject {

	private JButton addButton = null;
	private JLabel addLabel = null;
	private PatientRecord Record = null;

	/**
	 * This method initializes 
	 * 
	 */
	public AddDrugDisplay() {
		super();
		initialize();
	}
	
	public AddDrugDisplay(PatientRecord r) {
		super();
		Record = r;
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 */
	private void initialize() {
        addLabel = new JLabel();
        addLabel.setBounds(new Rectangle(423, 2, 122, 16));
        addLabel.setText("Add New Drug");
        this.setLayout(null);
        this.setBounds(new Rectangle(0, 0, 570, 20));
        this.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
        this.add(getAddButton(), null);
        this.add(addLabel, null);
	}

	/**
	 * This method initializes addButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getAddButton() {
		if (addButton == null) {
			addButton = new JButton();
			addButton.setText("+");
			addButton.setBounds(new Rectangle(550, 0, 20, 20));
			addButton.setPreferredSize(new Dimension(20, 20));
			
			addButton.addActionListener(new java.awt.event.ActionListener() {   
				public void actionPerformed(java.awt.event.ActionEvent e) {
						int DrugCt = Record.GetDrugs().size();
						int nextY = DrugCt * 150;
						
						Record.AddDrug(new Drug());
						JViewport VPort = ((JScrollPane)AddDrugDisplay.this.getParent().getComponentAt(0, 265)).getViewport();
						
						DrugDisplay NextDrug = new DrugDisplay(Record, Record.GetDrugs().getLast());
						NextDrug.setLocation(0, nextY);
						VPort.add(NextDrug, DrugCt - 1);

						VPort.validate();
				}
			});
			
		}
		
		return addButton;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
