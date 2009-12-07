package cs320;

import java.awt.Rectangle;
import javax.swing.BorderFactory;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;

@SuppressWarnings("serial")
public class TopBarDisplay extends DisplayObject {

	private JLabel timeLabel = null;
	private JLabel userNameLabel = null;
	private final DateFormat TimeFormat = new SimpleDateFormat("h:mm a");
	private ActionListener TimerListener = new ActionListener() {
		public void actionPerformed(ActionEvent e)  
        {  
            Date date = new Date();  
            String time = TimeFormat.format(date);  
            getTimeLabel().setText(time);  
        }  
	};
	
	private Timer Time = new Timer(15000, TimerListener);

	/**
	 * This method initializes 
	 * 
	 */
	public TopBarDisplay() {
		super();
		initialize();
	}
	
	private JLabel getTimeLabel() {
		if (timeLabel == null) {
			timeLabel = new JLabel();
	        timeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
	        timeLabel.setBounds(new Rectangle(732, 2, 66, 16));
		}
		return timeLabel;
	}

	/**
	 * This method initializes this
	 * 
	 */
	private void initialize() {
		Time.setInitialDelay(0);
		Time.start();
        userNameLabel = new JLabel();
        userNameLabel.setText("Nurse Jane Doe");
        userNameLabel.setLocation(new Point(2, 2));
        userNameLabel.setSize(new Dimension(179, 16));
        this.setLayout(null);
        this.setBounds(new Rectangle(0, 0, 800, 20));
        this.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
        this.add(getTimeLabel(), null);
        this.add(userNameLabel, null);
        
        this.addHierarchyBoundsListener(new java.awt.event.HierarchyBoundsAdapter() {
        	public void ancestorResized(java.awt.event.HierarchyEvent e) {
        		TopBarDisplay.this.setSize((int)TopBarDisplay.this.getParent().getSize().getWidth(), (int)TopBarDisplay.this.getHeight());
        		timeLabel.setLocation((int)TopBarDisplay.this.getParent().getSize().getWidth() - 68, timeLabel.getY());
        	}
        });
			
	}
	
	public JLabel getUserLabel() {
		return userNameLabel;
	}

}
