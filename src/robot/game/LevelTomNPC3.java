package robot.game;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class LevelTomNPC3 extends GameObjects {
	
	Manager manager;
	SidePanel side;
	boolean interacted;

	public LevelTomNPC3(int x, int y, ID id, Manager manager, SidePanel side) {
		super(x, y, id);
		this.manager = manager;
		this.side = side;
		interacted = false;
	}

	@Override
	public void tick() {
		if(interacted == false)
			interact();
	}

	@Override
	public void render(Graphics g) {
		File imageFile = new File("C:/Users/MissH/git/RobotWorld/src/robot/game/RobotBuddy.png");
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
					String message = "An example of a number variable declaration is:\n"
							+ "int y = 12;";
					JOptionPane.showMessageDialog(null, message);
					interacted = true;
					SidePanel.addText("~" + message + "\n\n");
				}
			}
		}
	}
}
