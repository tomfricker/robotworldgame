package robot.game;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class LevelHelenNPC extends GameObjects {
	
	Manager manager;
	SidePanel side;
	boolean interacted;

	public LevelHelenNPC(int x, int y, ID id, Manager manager, SidePanel side) {
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
		File imageFile = new File("C:/Users/MissH/git/RobotWorld/src/robot/game/Flower.png");
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
					String answer = "robot.pickup(flower);";
					String input = JOptionPane.showInputDialog(null, "Answer this correctly to pickup the flower.\n"
							+ "What code would make the robot pickup the flower?");
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
						interacted = true;
					}
				}
			}
		}
	}
}
