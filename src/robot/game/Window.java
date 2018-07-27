package robot.game;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;

public class Window extends Canvas {

	private static final long serialVersionUID = 966700232577608739L;

	/**
	 * Creates the frame (whole window) and adds components to it.
	 * Sets size, etc., of frame, makes it visible and adds it to the game.
	 * @param width
	 * @param height
	 * @param title
	 * @param game
	 */
    public Window(int width, int height, String title, Game game, SidePanel side, CodePanel code) {
		JFrame frame = new JFrame(title);
		
		Container contentPane = frame.getContentPane();
		contentPane.setLayout(new BorderLayout());
		contentPane.setPreferredSize(new Dimension(width + CodePanel.CPWIDTH, height));
		contentPane.setMaximumSize(new Dimension(width + CodePanel.CPWIDTH, height));
		contentPane.setMinimumSize(new Dimension(width + CodePanel.CPWIDTH, height));		
		
		//add side panel to frame on RHS
		contentPane.add(side, BorderLayout.WEST);
		contentPane.add(game, BorderLayout.CENTER);
		contentPane.add(code, BorderLayout.EAST);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		game.start();
	}
}
