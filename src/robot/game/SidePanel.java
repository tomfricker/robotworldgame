package robot.game;

import java.awt.Adjustable;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class SidePanel extends Panel{

	private static final long serialVersionUID = -7645124710017857401L;
	
	static JTextArea outputTextArea;
	private static JScrollPane scrollPane;
	static JTextField inputTextField;
	
	public SidePanel() {
		//create side panel
	    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setPreferredSize(new Dimension(220, HEIGHT));
	    
		//sets the font for input and output text
		Font font = new Font("Monospaced", Font.BOLD, 18);
		
	    //create input box
        inputTextField = new JTextField(20);

        //gets input when enter is pressed
        inputTextField.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		checkCommands();
        		//checkInput();
        	}
        });
        
        inputTextField.setFont(font);
		inputTextField.setText("Enter code here");
		inputTextField.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				refreshInputBox();
			}
		});
		
        //create output box
		SidePanel.outputTextArea = new JTextArea(20, 20);
		outputTextArea.setFont(font);
		outputTextArea.setLineWrap(true);
		outputTextArea.setWrapStyleWord(true);
		SidePanel.scrollPane = new JScrollPane(outputTextArea);
		
		//add I/O boxes to side panel
		add(scrollPane);
		add(inputTextField);
	}
	
	/**
	 * Sets the message to be displayed in the output text area
	 * @param text
	 */
	public void setText(String text) {
		outputTextArea.setText(text);
		scrollToBottom(scrollPane);
	}
	
	/**
	 * Adds text underneath current message in the output text area
	 * @param text
	 */
	public static void addText(String text) {
		outputTextArea.append(text);
		scrollToBottom(scrollPane);
	}
	
	/**
	 * This method sets the scrollpane to it's bottom most value.
	 * Taken from https://stackoverflow.com/questions/5147768/scroll-jscrollpane-to-bottom
	 * @param scrollPane
	 */
	private static void scrollToBottom(JScrollPane scrollPane) {
	    JScrollBar verticalBar = scrollPane.getVerticalScrollBar();
	    AdjustmentListener downScroller = new AdjustmentListener() {
	        @Override
	        public void adjustmentValueChanged(AdjustmentEvent e) {
	            Adjustable adjustable = e.getAdjustable();
	            adjustable.setValue(adjustable.getMaximum());
	            verticalBar.removeAdjustmentListener(this);
	        }
	    };
	    verticalBar.addAdjustmentListener(downScroller);
	}
	
	/**
	 * Returns text entered in input box
	 * @return 
	 */
	public static String getInput() {
		return inputTextField.getText();
	}
	
	/**
	 * Checks which command has been entered and does it
	 */
	public void checkCommands() {
		String inputText = getInput();
		if(inputText.equals("robot.move(up);")) {
			Commands.moveUp();
			addText(inputText + "\n\n");
		}
		else if(inputText.equals("robot.move(down);")) {
			Commands.moveDown();
			addText(inputText + "\n\n");
		}
		else if(inputText.equals("robot.move(left);")) {
			Commands.moveLeft();
			addText(inputText + "\n\n");
		}
		else if(inputText.equals("robot.move(right);")) {
			Commands.moveRight();
			addText(inputText + "\n\n");
		}
		else {
			addText("~I don't understand, try typing something else. \n\n");
		}
		
		refreshInputBox();
	}
	
	/**
	 * Clears input box
	 */
	public void refreshInputBox() {
		inputTextField.setText("");
	}
	
	
	/**
	 * not being used - ignore atm
	 */
	public void checkInput() {
		String inputText = getInput();
		
		if (inputText.endsWith(";")) {
			addText(inputText + "\n\n");
			inputTextField.setText("");
		}
		else {
			addText("~You are missing something... \n\n");
		}
	}
}
