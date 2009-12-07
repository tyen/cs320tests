package cs320;

import java.awt.Font;
import java.awt.Rectangle;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

public class QuestionDisplay extends DisplayObject {

	private static final long serialVersionUID = 1L;
	private JTextPane questionPane = null;
	private JLabel questionnaireTitle = null;

	/**
	 * This is the default constructor
	 */
	public QuestionDisplay() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		questionnaireTitle = new JLabel();
		questionnaireTitle.setBounds(new Rectangle(24, 5, 183, 32));
		questionnaireTitle.setFont(new Font("Dialog", Font.BOLD, 24));
		questionnaireTitle.setHorizontalAlignment(SwingConstants.CENTER);
		questionnaireTitle.setText("Questionnaire");
		this.setLayout(null);
		this.setBounds(new Rectangle(0, 0, 230, 505));
		this.add(getQuestionPane(), null);
		this.add(questionnaireTitle, null);
	}

	/**
	 * This method initializes questionPane	
	 * 	
	 * @return javax.swing.JTextPane	
	 */
	private JTextPane getQuestionPane() {
		if (questionPane == null) {
			questionPane = new JTextPane();
			questionPane.setEditable(false);
			questionPane.setBounds(new Rectangle(0, 40, 230, 465));
		}
		return questionPane;
	}

}
