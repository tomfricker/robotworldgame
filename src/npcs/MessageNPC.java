package npcs;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import robot.game.GameObjects;
import robot.game.HUD;
import robot.game.ID;
import robot.game.Manager;
import robot.game.SidePanel;

/**
 * NPC stands for non playable character.
 * 
 * This class extends GameObjects so that it can be easily stored within the Manager
 * class' list to be iterated over. It's function within the game is to display
 * information to the user. This is one way of teaching the user about programming.
 * 
 * It will display it's information within a pop up window and also copy that message
 * to the SidePanel class so that the user can access that information once the NPC
 * has been interacted with.
 * 
 * Once the character has been interacted with by the Player it won't keep keep trying
 * to display it's information.
 * 
 * @author Robot World Group
 *
 */
public class MessageNPC extends GameObjects {
	
	//Fields for the MessageNPC
	Manager manager;
	SidePanel side;
	HUD hud;
	boolean interacted;
	private String message;

	/**
	 * Constructor for MessageNPC
	 * @param x
	 * @param y
	 * @param id
	 * @param manager
	 * @param side
	 * @param hud
	 * @param message
	 */
	public MessageNPC(int x, int y, ID id, Manager manager, SidePanel side, HUD hud, String message) {
		super(x, y, id);
		this.manager = manager;
		this.side = side;
		this.hud = hud;
		interacted = false;
		this.message = message;
	}

	/**
	 * Having the interact method within tick will ensure that if the player comes across the NPC in the game it
	 * will display it's message, if and only if it has not already displayed it's message.
	 */
	public void tick() {
		if(interacted == false)
			interact();
	}

	/**
	 * Displays the image of the NPC on the board at the specified position.
	 */
	public void render(Graphics g) {
		File imageFile = new File("pictures\\walle.png");
		try {
			Image robot = ImageIO.read(imageFile);
			g.drawImage(robot, x, y, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * This method sets up the interactions between the player object and the NPC.
	 * It searches through the object list in the manager class to see if the Player
	 * object occupies the same space as itself. This character then displays a message 
	 * in a pop up window and in the side panel and will set it's interacted value to
	 * true so it can no longer add pop ups to the screen.
	 */
	public void interact() {
		for(GameObjects gameObject : Manager.objectList) {
			if(gameObject.getId() == ID.Player) {
				if(gameObject.getX() == x && gameObject.getY() == y) {
					JOptionPane.showMessageDialog(null, message);
					hud.setScore(hud.getScore() + 10);
					hud.setInteractions(hud.getInteractions() + 1);
					interacted = true;
					SidePanel.addText("~" + message + "\n\n");
				}
			}
		}
	}
}
