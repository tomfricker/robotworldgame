package robot.game;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Panel;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
		addComponentsToPane(frame.getContentPane());
		
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(game);
		frame.setVisible(true);
		game.start();
		 
	}
	
	/**
	 * Adds the board (grid of squares) to the centre and the text field to the RHS of the frame.
	 * @param frame
	 */
	public static void addComponentsToPane(Container frame) {
        
        if (!(frame.getLayout() instanceof BorderLayout)) {
            frame.add(new JLabel("Container doesn't use BorderLayout!"));
            return;
        }
         
        if (RIGHT_TO_LEFT) {
            frame.setComponentOrientation(
                    java.awt.ComponentOrientation.RIGHT_TO_LEFT);
        }
        
         
       

      //create side panel
	    Panel side = new Panel();
	    side.setLayout(new GridLayout(2,1));
		side.setPreferredSize(new Dimension(SIDEBARWIDTH, HEIGHT));
	    
	    //create input box
        JTextField inputTextField = new JTextField(20);
		inputTextField.setText("enter code here");
		
        //create output box
		JEditorPane outputTextArea = new JEditorPane();
		outputTextArea.setText("text appears here");
		
		//add I/O boxes to side panel
		side.add(outputTextArea);
		side.add(inputTextField);
		
		
		//add side panel to frame on RHS
		frame.add(side, BorderLayout.LINE_END);
    }
}
