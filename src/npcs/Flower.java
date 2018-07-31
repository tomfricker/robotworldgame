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
	boolean interacted;
	
	/**
	 * Constructor for Flowers
	 * @param x
	 * @param y
	 * @param id
	 * @param manager
	 * @param side
	 * @param hud
	 */
	public Flower(int x, int y, ID id, Manager manager, SidePanel side, HUD hud) {
		super(x, y, id);
		this.manager = manager;
		this.side = side;
		this.hud = hud;
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
		File imageFile = new File("pictures\\Flower.png");
		try {
			Image flower = ImageIO.read(imageFile);
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
					hud.setInteractions(hud.getInteractions() + 1);
					interacted = true;
				}
			}
		}
	}


}
