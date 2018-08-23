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

/**
 * This class extends GameObjects, it displays a flower.
 * 
 * @author MissH
 *
 */
public class Flower extends GameObjects {
	
	Manager manager;
	SidePanel side;
	HUD hud;
	File picture;
	boolean interacted;
	
	/**
	 * Constructor for Flower
	 * @param x
	 * @param y
	 * @param id
	 * @param manager
	 * @param side
	 * @param hud
	 */
	public Flower(int x, int y, ID id, Manager manager, SidePanel side, HUD hud, File picture) {
		super(x, y, id);
		this.manager = manager;
		this.side = side;
		this.hud = hud;
		this.picture = picture;
		interacted = false;
	}

	/**
	 * When player interacts with a flower it will call the interact method
	 */
	@Override
	public void tick() {
		if(interacted == false)
			interact();		
	}

	/**
	 * Displays image of flower on board 
	 */
	@Override
	public void render(Graphics g) {
		try {
			Image flower = ImageIO.read(picture);
			g.drawImage(flower, x, y, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * This method sets up the interactions between the player object and the flower.
	 * If the player is on the same square as the flower it will increase the score and interactions.
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
