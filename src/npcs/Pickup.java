package npcs;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import robot.game.Bag;
import robot.game.GameObjects;
import robot.game.ID;
import robot.game.Manager;
import robot.game.SidePanel;

/**
 * A PickUp object can be added to a level in the game and acts as a token which may be collected by the 
 * Player and used to score the player's progress through the level. 
 * They may be one of three in game types which represent char, int and boolean primitive types. 
 * @author jatg
 *
 */
public class Pickup extends GameObjects {
	
	private SidePanel side;
	PICKUPID pid;
	HashMap<PICKUPID,String> idmap = new HashMap<>();
	private String name;

	public Pickup(int x, int y, ID id, PICKUPID pid, SidePanel side, String name) {
		super(x, y, id);
		this.pid = pid;
		this.side = side;
		idmap.put(PICKUPID.Int,"Int");
		idmap.put(PICKUPID.Char,"Char");
		idmap.put(PICKUPID.Boolean,"Boolean");
		this.name = name;
	}//char

	/**
	 * updates the state of the Pickup object as the Manager class iterates over all GameObjects
	 */
	public void tick() {
		for(GameObjects o : Manager.objectList) {
			
			if(o.getId() == ID.Player && pid != PICKUPID.Picked) {
				
				if(o.getX() == x && o.getY() == y) {
					
					pickUp();
				}
			}
		}
		
	}

	/**
	 * Renders the Pickup in the game when it is stored in the Manager. 
	 * The image file used to render the object depends on the PICKUPID of the pickup object. 
	 * if the pickup has been picked up, and therefore has the PICKUPID of Picked, it is not rendered at all.  
	 */
	public void render(Graphics g) {
		if(pid == PICKUPID.Picked) {}
		else {
			if(pid == PICKUPID.Int) {
				if(name == "int1") {
					File imageFile = new File("pictures//int1.png");
					try {
						Image int1 = ImageIO.read(imageFile);
						g.drawImage(int1, x, y, null);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if(name == "int2") {
					File imageFile = new File("pictures//int2.png");
					try {
						Image int2 = ImageIO.read(imageFile);
						g.drawImage(int2, x, y, null);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			if(pid == PICKUPID.Char) {
				if(name == "char1") {
					File imageFile = new File("pictures//char1.png");
					try {
						Image char1 = ImageIO.read(imageFile);
						g.drawImage(char1, x, y, null);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if(name == "char2") {
					File imageFile = new File("pictures//char2.png");
					try {
						Image char2 = ImageIO.read(imageFile);
						g.drawImage(char2, x, y, null);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			if(pid == PICKUPID.Boolean) {
				if(name == "true") {
					File imageFile = new File("pictures//TRUE.png");
					try {
						Image TRUE = ImageIO.read(imageFile);
						g.drawImage(TRUE, x, y, null);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if(name == "false") {
					File imageFile = new File("pictures//FALSE.png");
					try {
						Image FALSE = ImageIO.read(imageFile);
						g.drawImage(FALSE, x, y, null);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
	}
	
	/**
	 * checks the in game type of any Bag object which may be present and,
	 * if it is the same as the type of the pickup, adds it to the Bag. 
	 */
	public void pickUp() {
		String bagpickupType = Bag.getBagType();
		if( bagpickupType.equals(idmap.get(pid))){
			Bag.addToBag(this);
			setPicked();
		}
	}
	
	/**
	 * gets the PICKUPID of the Pickup. 
	 * @return the PICKUPID of the pickup object
	 */
	public PICKUPID getPID() {
		return pid;
	}
	
	/**
	 * changes the PICKUPID of the Pickup to Picked, and therefore, stops 
	 * it from rendering and, as far as a player is concerned, removes it from the game.  
	 */
	public void setPicked() {
		pid = PICKUPID.Picked;
	}

}
