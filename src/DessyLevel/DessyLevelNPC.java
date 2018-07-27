package DessyLevel;

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

public class DessyLevelNPC extends GameObjects {
	
	private HUD hud;
	boolean interacted;

	public DessyLevelNPC(int x, int y, ID id, Manager manager, SidePanel side, HUD hud) {
		super(x, y, id);
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
		File imageFile = new File("pictures\\apple.png");
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
					String theory = "Robbie is hungry, feed him by getting him to eat the apple.\n"
							+ "You can use something like robot.eat(fruit). Now try it yourself:";
					String theory1 = "Here we have two classes: the Robot and the Item classes. \n" 
							+"Robbie is an Object from the Robot Class. This means he has characteristics (Fields) that are taken from (belong to) the Robot Class. \n"
							+"We’ll discuss those in detail later. Continue to the next fruit.";
					String answer = "robot.eat(apple);";
					String input = JOptionPane.showInputDialog(null, theory);
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
						SidePanel.addText("\n~" + input + "\n");
						SidePanel.addText("~correct\n\n");
						SidePanel.addText(theory1);
						hud.setScore(20);
						interacted = true;
					}
				}
			}
		}
	}
	
	
}

