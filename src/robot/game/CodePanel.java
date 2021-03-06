package robot.game;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * This class creates a panel that allows the user to enter input and then displays it above.
 * 
 * It also has two buttons that run the users input or clears it.
 * 
 * @author MissH
 *
 */
public class CodePanel extends Panel implements FocusListener {

	private static final long serialVersionUID = 1L;
	//Width of the CodePanel
	static final int CPWIDTH = 250;
		
	static Manager manager;
	
	static JTextArea codeOutput;
	static JTextField codeInput;
	JButton enterButton;
	JButton deleteButton;
	JButton clearButton;
	JPanel buttons;
	
	/**
	 * Constructor of the CodePanel
	 */
	public CodePanel() {
		//Create side panel
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setPreferredSize(new Dimension(CPWIDTH, HEIGHT));
		
		//set font
		Font font = new Font("Monospaced", Font.BOLD, 16);
		
						
		//create output text box
		codeOutput = new JTextArea(20,20);
		codeOutput.setFont(font);
		codeOutput.setLineWrap(true);
		codeOutput.setWrapStyleWord(true);
		add(codeOutput);
		
		//create input text box
		codeInput = new JTextField(20);
		codeInput.setFont(font);
		codeInput.setText("Write code here");
		codeInput.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				refreshInputBox();
			}
		});
		codeInput.addKeyListener(new HistoryLog());
		codeInput.addFocusListener(this);
		codeInput.setFocusable(true);
		add(codeInput);
		
		//prints in codeOutput when enter key is pressed 
        codeInput.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Commands.printCommands();
        		if(Commands.checkCommands() == true) {
        			Commands.addToCommandList(getInput());
        			HistoryLog.addToCommandList(getInput());
        		}
        		refreshInputBox();
        	}
        });
		
        //create enter button
		enterButton = new JButton("Run code");
		enterButton.setFont(font);
		//carries out entered code when enter button is pressed
		enterButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		//goes through command list and carries it out
        		if(Commands.checkCommands() == true) {
        			Commands.addToCommandList(getInput());
        			HistoryLog.addToCommandList(getInput());
        		}
        		Commands.runCommands();
        		refreshInputBox();
        		Commands.clearCommandList();
        		setText("");
           	}
        });
		
		//create clear button
		clearButton = new JButton("Clear");
		clearButton.setFont(font);
		clearButton.setToolTipText("This will clear all the code you have entered so far.");
		//removes commands from list and clears codeOutput
		clearButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Commands.clearCommandList();
        		setText("");
        	}
        });
		
		//create button layout
		buttons = new JPanel();
		buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
		buttons.add(enterButton);
		buttons.add(clearButton);
		//add buttons to panel
		add(buttons);
	}
	
	/**
	 * Clears input box
	 */
	public void refreshInputBox() {
		codeInput.setText("");
	}
	
	/**
	 * Sets text in input box
	 */
	public static void setInput(String input) {
		codeInput.setText(input);
	}
	
	/**
	 * Returns text entered in input box
	 * @return 
	 */
	public static String getInput() {
		return codeInput.getText();
	}
	
	/**
	 * Sets the message to be displayed in the output text area
	 * @param text
	 */
	public static void setText(String text) {
		codeOutput.setText(text);
	}
	
	/**
	 * Adds text underneath current message in the output text area
	 * @param text
	 */
	public static void addText(String text) {
		codeOutput.append(text);
	}

	/**
	 * Override Methods of the FocusListener Class
	 * (not used)
	 */
	@Override
	public void focusGained(FocusEvent arg0) {
				
	}

	@Override
	public void focusLost(FocusEvent arg0) {
				
	}
	
			
}

