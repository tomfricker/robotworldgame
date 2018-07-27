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

public class MessageNPC extends GameObjects {
	
	Manager manager;
	SidePanel side;
	HUD hud;
	boolean interacted;
	private String message;

	public MessageNPC(int x, int y, ID id, Manager manager, SidePanel side, HUD hud, String message) {
		super(x, y, id);
		this.manager = manager;
		this.side = side;
		this.hud = hud;
		interacted = false;
		this.message = message;
	}

	@Override
	public void tick() {
		if(interacted == false)
			interact();
	}

	@Override
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
	 * This character asks for the answer to a question then displays both correct and incorrect answers typed in the side panel.
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
