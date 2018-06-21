package robot.game;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Panel;

import javax.swing.BoxLayout;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Window extends Canvas{

	private static final long serialVersionUID = 966700232577608739L;
	
	public static boolean RIGHT_TO_LEFT = false;
    
    private static JPanel squares[][] = new JPanel[6][6];	
    
    public static final int SIDEBARWIDTH = 200;

	/**
	 * Creates the frame (whole window) and adds components to it.
	 * Sets size, etc., of frame, makes it visible and adds it to the game.
	 * @param width
	 * @param height
	 * @param title
	 * @param game
	 */
    public Window(int width, int height, String title, Game game) {
		JFrame frame = new JFrame(title);
		
		//add board and side panel to frame
		addComponentsToPane(frame.getContentPane(), game);
		
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		game.start();
		 
	}
	
	/**
	 * Adds the board (grid of squares) to the centre and the text field to the RHS of the frame.
	 * @param frame, game
	 */
	public static void addComponentsToPane(Container frame, Game game) {	
		//set the layout to centre the game
      	frame.setLayout(new BorderLayout()); 
      	frame.add(game, BorderLayout.CENTER);
       

       	//create side panel
	    Panel side = new Panel();
	    side.setLayout(new BoxLayout(side, BoxLayout.Y_AXIS));
		side.setPreferredSize(new Dimension(SIDEBARWIDTH, HEIGHT));
	    
	    //create input box
        JTextField inputTextField = new JTextField(20);
		inputTextField.setText("enter code here");
		
        //create output box
		JTextArea outputTextArea = new JTextArea(20, 20);
		outputTextArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
		outputTextArea.setLineWrap(true);
		outputTextArea.setWrapStyleWord(true);
		outputTextArea.setText("Welcome to Robot World!\n\n"
				+ "Have fun and enjoy learning to program.\n\n"
				+ "Guide our friendly robot Robbie around the board and discover "
				+ "different techniques used in programming.");
		
		//add I/O boxes to side panel
		side.add(outputTextArea);
		side.add(inputTextField);
		
		
		//add side panel to frame on RHS
		frame.add(side, BorderLayout.EAST);
	}
}
