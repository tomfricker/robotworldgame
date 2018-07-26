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

public class DessyLevelNPC1 extends GameObjects {
	
	Manager manager;
	SidePanel side;
	HUD hud;
	boolean interacted;

	public DessyLevelNPC1(int x, int y, ID id, Manager manager, SidePanel side, HUD hud) {
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
		File imageFile = new File("pictures\\banana.png");
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
					String theory = "Eat the banana to unlock more information about Classes and Objects.";
					String theory1 = "Here we have two classes: the Robot and the Item classes. \n" 
							+"Robbie is an Object from the Robot Class. This means he has characteristics (Fields) that are taken from (belong to) the Robot Class. \n"
							+"We’ll discuss those in detail later. Continue to the next fruit.";
					String answer = "robot.eat(banana);";
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
						hud.setScore(hud.getScore()+20);
						interacted = true;
						more();
					}
				}
			}
		}
	}
	
	/**
	 * This method prompts further interaction after the player has answered successfully.
	 */
	public void more() {
		String theory1 = "Classes start with a capital letter, while Objects are written with a small letter.  \n" 
					+"Classes have Methods that help you make an action, but only an Object can “invoke” that method. \n"
					+"Write first the name of the Method you've seen so far, then the name of an Object.";
		String inputTest = JOptionPane.showInputDialog(null, theory1);
		String answerTest = "eat,robot";
		//incorrect answer
		if(!inputTest.equals(answerTest.trim())) {
			SidePanel.addText("\n~" + inputTest + "\n");
			SidePanel.addText("~incorrect\n\n");
			more();
		}
		
		//if the player is correct the interaction will not continue to be prompted
		else if(inputTest.equals(answerTest)) {
			SidePanel.addText("\n~" + inputTest + "\n");
			SidePanel.addText("~correct\n\n");
			hud.setScore(hud.getScore() + 40);
			interacted = true;
			
		}
		
	}
	
	
}
