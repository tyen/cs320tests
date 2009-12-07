import java.awt.Dimension;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Rectangle;
import java.util.ArrayList;
import javax.swing.ListSelectionModel;

@SuppressWarnings("serial")
public class SearchDisplay extends DisplayObject {

	private JScrollPane resultScrollPane = null;
	private JTable resultTable = null;
	private JButton selectButton = null;
	private JButton goBackButton = null;
	private DisplayController d = DisplayController.GetInstance();
	private User SysUser;
	
	/**
	 * This method initializes 
	 * 
	 */
	public SearchDisplay() {
		super();
		initialize();
	}
	
	public SearchDisplay(User u) {
		super();
		SysUser = u;
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 */
	private void initialize() {
        this.setLayout(null);
        this.setSize(new Dimension(800, 600));
        this.add(getSelectButton(), null);
        this.add(getResultScrollPane(), null);
        this.add(getGoBackButton(), null);
			
	}

	/**
	 * This method initializes resultScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getResultScrollPane() {
		if (resultScrollPane == null) {
			resultScrollPane = new JScrollPane();
			resultScrollPane.setBounds(new Rectangle(213, 5, 454, 420));
			resultScrollPane.setViewportView(getResultTable());
		}
		return resultScrollPane;
	}

	/**
	 * This method initializes resultTable	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private JTable getResultTable() {
		if (resultTable == null) {
			resultTable = new JTable();
			resultTable.setAutoCreateColumnsFromModel(false);
			resultTable.setRowSelectionAllowed(true);
			resultTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			resultTable.setShowHorizontalLines(true);
			resultTable.setShowVerticalLines(true);
			resultTable.setShowGrid(true);
			resultTable.addFocusListener(new java.awt.event.FocusListener() {
				public void focusGained(java.awt.event.FocusEvent e) {
					
					System.out.println("focusGained()"); // TODO Auto-generated Event stub focusGained()
				}
				public void focusLost(java.awt.event.FocusEvent e) {
				}
			});
		}
		return resultTable;
	}

	/**
	 * This method initializes selectButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getSelectButton() {
		if (selectButton == null) {
			selectButton = new JButton();
			selectButton.setBounds(new Rectangle(480, 435, 158, 29));
			selectButton.setText("Select");
			selectButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					
					ArrayList<Integer> selection = new ArrayList<Integer>();
					int [] selected = new int [1];
					PatientInfo selectedPatient = new PatientInfo("John", "Doe");
					selected=resultTable.getSelectedRows();
					selection.add(selected[0]);
					if(selection.isEmpty()!=true){
					selectedPatient=(PatientInfo) resultTable.getValueAt(selection.get(0),0);
					selectedPatient.Display();
					}
					else{
						JOptionPane.showMessageDialog(null, "No Patient Selected");
						
					}
				}
			});
		}
		return selectButton;
	}

	/**
	 * This method initializes goBackButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getGoBackButton() {
		if (goBackButton == null) {
			goBackButton = new JButton();
			goBackButton.setBounds(new Rectangle(240, 435, 160, 29));
			goBackButton.setText("Go Back");
			goBackButton.setToolTipText("");
			goBackButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					SearchDisplay.this.setVisible(false);
					d.Display(new SearchMainDisplay());
				}
			});
		}
		return goBackButton;
	}
	
}  //  @jve:decl-index=0:visual-constraint="107,45"
