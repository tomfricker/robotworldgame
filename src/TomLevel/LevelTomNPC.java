package TomLevel;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import robot.game.Game;
import robot.game.GameObjects;
import robot.game.HUD;
import robot.game.ID;
import robot.game.Manager;
import robot.game.SidePanel;

public class LevelTomNPC extends GameObjects {
	
	private Manager manager;
	private SidePanel side;
	private HUD hud;
	boolean interacted;

	public LevelTomNPC(int x, int y, ID id, Manager manager, SidePanel side, HUD hud) {
		super(x, y, id);
		this.manager = manager;
		this.side = side;
		this.hud = hud;
		interacted = false;
	}

	@Override
	public void tick() {
		if(interacted == false)
			interact();
	}

	@Override
	public void render(Graphics g) {
		File imageFile = new File("C:/Users/MissH/Documents/TomsVersion/src/robot/game/RobotBuddy.png");
		try {
			Image robot = ImageIO.read(imageFile);
			g.drawImage(robot, x, y, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * This method sets up the interactions between the player object and the NPC.
	 * This character asks for the answer to a question then displays both correct and incorrect answers typed in the side panel.
	 */
	public void interact() {
		for(GameObjects gameObject : Manager.objectList) {
			if(gameObject.getId() == ID.Player) {
				if(gameObject.getX() == x && gameObject.getY() == y) {
					String answer = "int x = 4;";
					String input = JOptionPane.showInputDialog(null, "Please declare the number of robots on the board that look just like me.\n"
							+ "I need to be declared in a variable called x");
					if(input == null || input.length() == 0) {
						gameObject.setX(gameObject.getX()-Game.boardIndex);
					}
					//if the answer is incorrect the player can continue to move round the board and the interactions can still take place
					else if (!input.equals(answer)) {
						SidePanel.addText("~" + input + "\n");
						SidePanel.addText("~incorrect\n\n");
						gameObject.setX(gameObject.getX()-Game.boardIndex);
					}
					//if the player is correct the interaction will not continue to be prompted
					else if(input.equals(answer)) {
						SidePanel.addText("~" + input + "\n");
						SidePanel.addText("~correct\n\n");
						hud.setScore(hud.getScore() + 50);
						interacted = true;
					}
				}
			}
		}
	}
}
