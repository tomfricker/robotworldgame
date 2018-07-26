package npcs;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import robot.game.GameObjects;
import robot.game.HUD;
import robot.game.ID;
import robot.game.Manager;
import robot.game.SidePanel;

public class Flower extends GameObjects {
	
	Manager manager;
	SidePanel side;
	HUD hud;
	boolean interacted;

	public Flower(int x, int y, ID id, Manager manager, SidePanel side, HUD hud) {
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
		File imageFile = new File("pictures\\Flower.png");
		try {
			Image flower = ImageIO.read(imageFile);
			g.drawImage(flower, x, y, null);
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
					hud.setScore(hud.getScore() + 5);
					interacted = true;
				}
			}
		}
	}


}
