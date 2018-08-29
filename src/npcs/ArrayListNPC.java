package npcs;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import robot.game.Bag;
import robot.game.GameObjects;
import robot.game.HUD;
import robot.game.ID;
import robot.game.Manager;
import robot.game.SidePanel;

/**
 * ArrayListNPC is an NPC for use in the game that as methods which can 
 * simulate adding items to an ArrayList. 
 * extends the GameObjects class and can be added to the 
 * 
 * @author James Galbraith
 */

public class ArrayListNPC extends GameObjects {
	
	public String primType;
	public SidePanel side;
	public HUD hud;
	public HashMap<String,PICKUPID> revidmap = new HashMap<>();
	public boolean interacted = false;
	private ArrayList<GameObjects> tail = new ArrayList<>();
	public Manager manager;
	

	public ArrayListNPC(int x, int y, ID id,String primType,Manager manager,SidePanel side, HUD hud, boolean b) {
		super(x, y, id);
		this.primType = primType;
		this.side = side;
		this.hud = hud;
		revidmap.put("Int",PICKUPID.Int);
		revidmap.put("Char",PICKUPID.Char);
		revidmap.put("Boolean",PICKUPID.Boolean);
		Bag bag = Bag.getInstance();
		this.manager = manager;
		if (b) {
			
			for(int i = 0; i<5;i++) {
				tail.add(new Pickup( x,  y, ID.NPC, PICKUPID.Char, side, "char1"));
			}
		}
		
	}

	/**
	 * Overridden tick method which allows the ArrayListNPC to update it's position/state.
	 */
	public void tick() {
		interact();
		
	}

	/**
	 * Renders the ArrayListNPC objects depending on their type
	 * Also renders the "tail" of the ArratyListNPC which represents the objects
	 * stored in the collection.
	 */
	public void render(Graphics g) {
		/**
		 * can be used to render different images for the ArrayListNPC should more types be needed. 
		if(primType == "Int") {g.setColor(Color.RED);}
		if(primType == "Char") {g.setColor(Color.BLUE);}
		if(primType == "Boolean") {g.setColor(Color.GREEN);}
		*/
		File imageFile = new File("pictures//basketWalle.png");
		try {
			Image FALSE = ImageIO.read(imageFile);
			g.drawImage(FALSE, x, y, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//g.fillRect(x+10, y+10, 40, 40);
		g.setColor(Color.BLUE);
		int tailPositionX = x + 25;
		int tailPositionY = y + 65;
		for(GameObjects o:tail) {
			g.fillOval(tailPositionX, tailPositionY, 10, 10);
			tailPositionY += 20;
			
		}
		
	}
	
	/**
	 * ArrayListNPC does not require an interact method because it's add character 
	 * method is called by a command. However, testing suggested a reminder would
	 * be helpful for the player to better understand the stage. Therefore a message
	 * box was added to provide more information if the player makes contact with the
	 * ArrayListNPC. 
	 */
	public void interact() {
		for(GameObjects o : Manager.objectList) {
			if( o.getId() == ID.Player) {
			
				// gets player coordinates
				if(o.getX() == x && o.getY() == y) {
					if(!interacted) {
						JOptionPane.showMessageDialog(null, "Use the Code Panel to add chars to me!");
						interacted = true;
					}
				}
			}
		}
	}

	/**
	 * adds a character pickup object to the ArrayListNPC. 
	 * @param c char 
	 */
	public void add(char c) {
		tail.add(new Pickup(64,64,ID.NPC, PICKUPID.Boolean,side, "false"));
		if(tail.size() == 4) {JOptionPane.showMessageDialog(null, "Only 1 more char to add! Well Done and keep going!");}
		hud.setScore(hud.getScore()+10);
		if(HUD.getStage() == 2 && hud.getScore() == 100) {
			hud.setStage(3);
			SidePanel.addText("~ Congratulations! You have completed the introduction to Collections in Java Programming");
			hud.setLevelEnd(true);
		}
	}
	
	/**
	 * Removes an item from the 
	 * @param i the index of an item in the ArrayListNPCs tail 
	 */
	public void remove(int i) {
		Iterator<GameObjects> it = tail.iterator();
		while(it.hasNext()) {
			
		}
			
		
		tail.remove(i);
	}
	

}
