package robot.game;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * 
 * @author 
 *
 */
public class CodePanel extends Panel{

	private static final long serialVersionUID = 1L;

	static final int CPWIDTH = 250;
	
	static JTextArea codeOutput;
	static JTextField codeInput;
	JButton enterButton;
	JButton deleteButton;
	JButton clearButton;
	JPanel buttons;
	
	public CodePanel() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setPreferredSize(new Dimension(CPWIDTH, HEIGHT));
		
		Font font = new Font("Monospaced", Font.BOLD, 16);
		
		codeOutput = new JTextArea(20,20);
		codeOutput.setFont(font);
		codeOutput.setLineWrap(true);
		codeOutput.setWrapStyleWord(true);
		add(codeOutput);
		
		codeInput = new JTextField(20);
		codeInput.setFont(font);
		codeInput.setText("Write code here");
		codeInput.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				refreshInputBox();
			}
		});
		add(codeInput);
		
		//prints in codeOutput when enter key is pressed 
        codeInput.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Commands.printCommands();
        		if(Commands.checkCommands() == true) {
        			Commands.addToCommandList(getInput());
        		}
        		refreshInputBox();
        	}
        });
		
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
		
		buttons = new JPanel();
		buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
		buttons.add(enterButton);
		//buttons.add(deleteButton);
		buttons.add(clearButton);
		
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

}
