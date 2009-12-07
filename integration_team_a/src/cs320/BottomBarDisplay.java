import java.awt.Rectangle;
import javax.swing.BorderFactory;
import java.awt.Color;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class BottomBarDisplay extends DisplayObject {

	private JLabel modifiedNameLabel = null;
	private JLabel modifiedLabel = null;
	private JLabel createdNameLabel = null;
	private JLabel createdLabel = null;

	/**
	 * This method initializes 
	 * 
	 */
	public BottomBarDisplay() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 */
	private void initialize() {
        createdLabel = new JLabel();
        createdLabel.setBounds(new Rectangle(2, 2, 80, 16));
        createdLabel.setText("Created By: ");
        createdNameLabel = new JLabel();
        createdNameLabel.setBounds(new Rectangle(81, 2, 236, 16));
        createdNameLabel.setText("Doctor Gregory House");
        modifiedLabel = new JLabel();
        modifiedLabel.setBounds(new Rectangle(447, 2, 86, 16));
        modifiedLabel.setText("Modified By: ");
        modifiedNameLabel = new JLabel();
        modifiedNameLabel.setBounds(new Rectangle(528, 2, 267, 16));
        modifiedNameLabel.setText("Nurse Nursey Nurse");
        this.setLayout(null);
        this.setBounds(new Rectangle(0, 0, 800, 20));      
        this.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
        this.add(modifiedNameLabel, null);
        this.add(modifiedLabel, null);
        this.add(createdNameLabel, null);
        this.add(createdLabel, null);
        
        this.addHierarchyBoundsListener(new java.awt.event.HierarchyBoundsAdapter() {
        	public void ancestorResized(java.awt.event.HierarchyEvent e) {
        		BottomBarDisplay.this.setSize((int)BottomBarDisplay.this.getParent().getSize().getWidth(), BottomBarDisplay.this.getHeight());
        		modifiedLabel.setLocation((int)BottomBarDisplay.this.getParent().getSize().getWidth() - 353, modifiedLabel.getY());
        		modifiedNameLabel.setLocation((int)BottomBarDisplay.this.getParent().getSize().getWidth() - 272, modifiedNameLabel.getY());
        		BottomBarDisplay.this.setLocation(0, (int)BottomBarDisplay.this.getParent().getSize().getHeight()-20);
        	}
        });
        
	}
	
	public void fitToScreen() {
		this.setSize((int)BottomBarDisplay.this.getParent().getSize().getWidth(), this.getHeight());
	}
	
	public void SetLastModUser(String u) {
		modifiedNameLabel.setText(u);
	}
	
	public void SetCreatedUser(String u) {
		createdNameLabel.setText(u);
	}

}
