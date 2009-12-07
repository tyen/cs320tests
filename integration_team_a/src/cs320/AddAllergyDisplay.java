import java.awt.Dimension;
import java.awt.Rectangle;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import java.awt.Color;


@SuppressWarnings("serial")
public class AddAllergyDisplay extends DisplayObject {

	private JButton addButton = null;
	private JLabel addLabel = null;

	/**
	 * This method initializes 
	 * 
	 */
	public AddAllergyDisplay() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 */
	private void initialize() {
        addLabel = new JLabel();
        addLabel.setBounds(new Rectangle(423, 2, 119, 16));
        addLabel.setText("Add New Allergy");
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
					if(!(AddAllergyDisplay.this.getParent().getComponentAt(0, 170)).isVisible()){
						(AddAllergyDisplay.this.getParent().getComponentAt(0, 170)).setVisible(true);
					}
					else{
					((JScrollPane)AddAllergyDisplay.this.getParent().getComponentAt(0, 170)).getViewport().add(new AllergyDisplay(),-1);
					//((JScrollPane)AddAllergyDisplay.this.getParent().getComponentAt(0, 170)).getViewport().doLayout();
					//((JScrollPane)AddAllergyDisplay.this.getParent().getComponentAt(0, 170)).getViewport().setAutoscrolls(true);


					}
					
				((JScrollPane)AddAllergyDisplay.this.getParent().getComponentAt(0, 170)).getViewport().validate();

			}
				
		});
		
		}
		return addButton;
	}  //  @jve:decl-index=0:visual-constraint="10,10"
}