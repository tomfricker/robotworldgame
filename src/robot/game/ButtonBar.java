package robot.game;

import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;

import javax.swing.BoxLayout;

public class ButtonBar extends Panel implements ActionListener{

	private static final long serialVersionUID = 1L;
	JButton menuButton;
	JButton helpButton;
	static int buttonBarSize = 35;
	
	public ButtonBar() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setPreferredSize(new Dimension(WIDTH, buttonBarSize));
		
		menuButton = new JButton("Main Menu");
		menuButton.setActionCommand("menu");
		menuButton.addActionListener(this);
		menuButton.setToolTipText("Click this button to go back to the Mani Menu.");
		menuButton.setFont(new java.awt.Font("Monospaced", Font.BOLD, 20));
		menuButton.setBackground(Color.blue);
		menuButton.setForeground(Color.white);
		add(menuButton);
		
		helpButton = new JButton("Help");
		helpButton.setFont(new java.awt.Font("Monospaced", Font.BOLD, 20));
		helpButton.setBackground(Color.red);
		helpButton.setForeground(Color.white);
		add(helpButton);
	}
	
	public void actionPerformed(ActionEvent e) {
		if("menu".equals(e.getActionCommand())) {
			//go to: gameState = STATE.Menu;
		}
	}
}
