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

/**
 * This class creates a panel that displays messages to the user. This includes 
 * introductory text for a level or messages from NPCs to the user.
 * 
 * It also contains two buttons that take the user to the main menu of the game
 * or opens a new help window.
 * 
 * @author Robot World Group
 *
 */
public class SidePanel extends Panel{

	//Fields of the SidePanel class
	private static final long serialVersionUID = -7645124710017857401L;
	static JTextArea outputTextArea;
	private static JScrollPane scrollPane;
	//static JTextField inputTextField;
	JButton menuButton;
	JButton helpButton;
	JPanel buttonBar;
	
	/**
	 * Constructor of the SidePanel
	 */
	public SidePanel() {
		//create side panel
	    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setPreferredSize(new Dimension(220, HEIGHT));
	    
		//sets the font for input and output text
		Font font = new Font("Monospaced", Font.BOLD, 18);
		
        //create output text box
		SidePanel.outputTextArea = new JTextArea(20, 20);
		outputTextArea.setFont(font);
		outputTextArea.setLineWrap(true);
		outputTextArea.setWrapStyleWord(true);
		outputTextArea.setEditable(false);
		SidePanel.scrollPane = new JScrollPane(outputTextArea);
		
		//Create button to take you to main menu
		menuButton = new JButton("Main Menu");
		menuButton.setToolTipText("Click this button to go back to the Main Menu.");
		menuButton.setFont(font);
		//menuButton.setBackground(Color.blue);
		//menuButton.setForeground(Color.white);
		menuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//go to main menu
				Manager.clearAll();
				HUD.setInteractions(0);
				Game.gameState = STATE.Menu;
			}
		});
		
		//Create button to open help window
		helpButton = new JButton("Help");
		helpButton.setFont(font);
		//helpButton.setBackground(Color.red);
		//helpButton.setForeground(Color.white);
		helpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new HelpWindow();
							
			}
		});
		
		//Create button layout
		buttonBar = new JPanel();
		buttonBar.setLayout(new BoxLayout(buttonBar, BoxLayout.X_AXIS));
		buttonBar.add(menuButton);
		buttonBar.add(helpButton);
		
		//add elements to panel
		add(buttonBar);
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
}
