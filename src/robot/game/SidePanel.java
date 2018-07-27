package robot.game;

import java.awt.Adjustable;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import robot.game.Game.STATE;

public class SidePanel extends Panel{

	private static final long serialVersionUID = -7645124710017857401L;
	
	static JTextArea outputTextArea;
	private static JScrollPane scrollPane;
	//static JTextField inputTextField;
	JButton menuButton;
	JButton helpButton;
	JPanel buttonBar;
	
	public SidePanel() {
		//create side panel
	    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setPreferredSize(new Dimension(220, HEIGHT));
	    
		//sets the font for input and output text
		Font font = new Font("Monospaced", Font.BOLD, 18);
		
	    //create input box
        //inputTextField = new JTextField(20);

        //gets input when enter is pressed
        //inputTextField.addActionListener(new ActionListener() {
        //	public void actionPerformed(ActionEvent e) {
        //		Commands.printCommands();
        //		refreshInputBox();
        //	}
        //});
        
        //inputTextField.setFont(font);
		//inputTextField.setText("Enter code here");
		//inputTextField.addMouseListener(new MouseAdapter() {
		//	public void mouseClicked(MouseEvent e) {
		//		refreshInputBox();
		//	}
		//});
		
        //create output box
		SidePanel.outputTextArea = new JTextArea(20, 20);
		outputTextArea.setFont(font);
		outputTextArea.setLineWrap(true);
		outputTextArea.setWrapStyleWord(true);
		SidePanel.scrollPane = new JScrollPane(outputTextArea);
		
		menuButton = new JButton("Main Menu");
		menuButton.setToolTipText("Click this button to go back to the Main Menu.");
		menuButton.setFont(font);
		//menuButton.setBackground(Color.blue);
		//menuButton.setForeground(Color.white);
		menuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//go to main menu
				Manager.clearAll();
				Game.gameState = STATE.Menu;
			}
		});
		
		helpButton = new JButton("Help");
		helpButton.setFont(font);
		//helpButton.setBackground(Color.red);
		//helpButton.setForeground(Color.white);
		helpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//open help window
			}
		});
		
		buttonBar = new JPanel();
		buttonBar.setLayout(new BoxLayout(buttonBar, BoxLayout.X_AXIS));
		
		buttonBar.add(menuButton);
		buttonBar.add(helpButton);
		
		add(buttonBar);
		
		//add I/O boxes to side panel
		add(scrollPane);
		//add(inputTextField);
	}
	
	/**
	 * Sets the message to be displayed in the output text area
	 * @param text
	 */
	public void setText(String text) {
		outputTextArea.setText(text);
		scrollToBottom();
	}
	
	/**
	 * Adds text underneath current message in the output text area
	 * @param text
	 */
	public static void addText(String text) {
		outputTextArea.append(text);
		scrollToBottom();
	}
	
	/**
	 * This method sets the scrollpane to it's bottom most value.
	 * Taken from https://stackoverflow.com/questions/5147768/scroll-jscrollpane-to-bottom
	 * @param scrollPane
	 */
	private static void scrollToBottom() {
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
	//public static String getInput() {
	//	return inputTextField.getText();
	//}
	
	/**
	 * Clears input box
	 */
	//public void refreshInputBox() {
	//	inputTextField.setText("");
	//}
	
	
	/**
	 * not being used - ignore atm
	 */
	//public void checkInput() {
	//	String inputText = getInput();
	//	
	//	if (inputText.endsWith(";")) {
	//		addText(inputText + "\n\n");
	//		inputTextField.setText("");
	//	}
	//	else {
	//		addText("~You are missing something... \n\n");
	//	}
	//}
}
