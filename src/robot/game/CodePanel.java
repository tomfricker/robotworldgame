package robot.game;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * This class creates a panel that allows the user to enter input and then displays it.
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
	private static boolean hasFocus = false;
	
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
		
		manager = new Manager();
		this.addKeyListener(new KeyInput(manager));
		
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
		add(codeInput);
		codeInput.addFocusListener(this);
		
		
		//prints in codeOutput when enter key is pressed 
        codeInput.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		HistoryLog.addToCommandList(getInput());
        		Commands.printCommands();
        		if(Commands.checkCommands() == true) {
        			Commands.addToCommandList(getInput());
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
        		Commands.runCommands();
        		refreshInputBox();
        		Commands.clearCommandList();
        		setText("");
           	}
        });
		
		//deleteButton = new JButton("Delete");
		//deleteButton.setFont(font);
		//deleteButton.setToolTipText("This will delete the last command you entered.");
		////removes last command from list and deletes it from codeOutput
		//deleteButton.addActionListener(new ActionListener() {
        //	public void actionPerformed(ActionEvent e) {
        //		Commands.removeLastCommand();
		//		//remove last command from codeOutput
        //	}
        //});
		
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
		//buttons.add(deleteButton);
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
	public void setText(String text) {
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
	 * This method controls the player object by the arrow keys on the keyboard
	 */
	public void keyPressed(KeyEvent e) {
		
		int key = e.getKeyCode();
		int pressedKeys = 0;
			if(key == KeyEvent.VK_UP) {
				System.out.println("You made it!");
				//String phrase = HistoryLog.get(HistoryLog.length()-(pressedKeys+1));
					//pressedKeys ++;
					//codeInput.setText(phrase);
			}		
			else if (key == KeyEvent.VK_DOWN && pressedKeys != 0) {
					//add code here
					
			}
		}

	@Override
	public void focusGained(FocusEvent arg0) {
		System.out.println("Gained focus");
		hasFocus = true;
		
	}

	@Override
	public void focusLost(FocusEvent arg0) {
		hasFocus = false;
		System.out.println("Lost focus");
		
	}
	
	public static boolean getHasFocus() {
		return hasFocus;
	}
		
}

