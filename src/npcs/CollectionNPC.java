package npcs;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import robot.game.Bag;
import robot.game.GameObjects;
import robot.game.HUD;
import robot.game.ID;
import robot.game.Manager;
import robot.game.SidePanel;

/**
 * An NPC that extends GameObjects. The CollectionNPC calls a static class 
 * Bag object that allows for the player to collect items. Therefore the 
 * bag only exists in levels where it is relevant. 
 * Each COllectionNPC has an in game type stored as the primType field. 
 * @author jatg
 *
 */
public class CollectionNPC extends GameObjects {
	
	public String primType;
	public SidePanel side;
	//public Boolean reminded;
	//public boolean firstVisit;
	public HUD hud;
	public HashMap<String,PICKUPID> revidmap = new HashMap<>();
	public boolean interacted = false;
	private String colspectext;

	public CollectionNPC(int x, int y, ID id,String primType,SidePanel side, HUD hud) {
		super(x, y, id);
		this.primType = primType;
		this.side = side;
		this.hud = hud;
		revidmap.put("Int",PICKUPID.Int);
		revidmap.put("Char",PICKUPID.Char);
		revidmap.put("Boolean",PICKUPID.Boolean);
		Bag bag = Bag.getInstance();
		//reminded = false;
		//firstVisit = true;
		setSpecText();
		
	}

	/**
	 * updates the CollectionNPC's state 
	 */
	public void tick() {
		interact();
		
	}

	/**
	 * Renders the CollectionNPC in the game, the image file is determined by
	 * the CollectionNPC's in game type. 
	 */
	public void render(Graphics g) {
		if(primType == "Int") {
			File imageFile = new File("pictures//basketWalle.png");
			try {
				Image FALSE = ImageIO.read(imageFile);
				g.drawImage(FALSE, x, y, null);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(primType == "Char") {
			File imageFile = new File("pictures//basketWalleBlue.png");
			try {
				Image FALSE = ImageIO.read(imageFile);
				g.drawImage(FALSE, x, y, null);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(primType == "Boolean") {
			File imageFile = new File("pictures//basketWalleGreen.png");
			try {
				Image FALSE = ImageIO.read(imageFile);
				g.drawImage(FALSE, x, y, null);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}		
	}
	
	/**
	 * Checks whether the CollectionNPC should interact or not and what should occur. 
	 * If the player's bag does not have the same in game type as the COllectionNPC, it will call the 
	 * impose() method.  
	 * Otherwise, it will check the player's bag to see if they have any pickups which may be turned in 
	 * to increase the player's score. 
	 * 
	 */
	public void interact() {
		for(GameObjects o : Manager.objectList) {
			if( o.getId() == ID.Player) {
			
				// gets player coordinates
				if(o.getX() == x && o.getY() == y) {
					if(interacted ==false) {
					JOptionPane.showMessageDialog(null, "I'm an ArrayList of type"+primType+". I want you to find me all of the "+primType+" objects on the board and bring them back to me.");
					SidePanel.addText(colspectext);
					interacted = true;
					}
					//checks type of bag against collector type
					if(Bag.getBagType().equals(primType)) {
						
						/**
						//System.out.println("The bag's type is the same as this collector.");
						if(!firstVisit) {
						
							if(!reminded) {
							
								remind();
							}
							
						}
						*/
						
						if(Bag.pickups.get(getPidfromRevMap()) != null) {
							//System.out.println("pickups should be added to the collector");
							turnIn();
							if(hud.getScore() >= 50) {
								
								hud.setStage(2);
								hud.setStageEnd(true);
							}
							
						}
						
					}
					
					else {
						impose();
						//System.out.println("change bag type to match collector with " + primType);
						//Bag.setType(primType);
						//reminded = false;
					
					
					}
				}
				/**
				else { 
						reminded = false;
						
					}
					*/
			}
		}
	}
	
	/**
	 * Sets the Bag's in game type to that of the CollectionNPC and prompts the player to find 
	 * pickups in the level of the CollectionNPCs type. 
	 */
	public void impose() {
		if(Bag.bagType != primType) {
			Bag.setType(primType);
			//System.out.println("imposed the collector's type "+primType+" on the player's bag");
			//reminded = false;
			SidePanel.addText("~Look for the "+primType+" objects on the board for me.\n\n");
		}
	}
	
	/**
	public void remind() {
		side.addText("~I'm after "+primType +" types. Avoid the other collectors until you've brought me all my objects. \n\n"  );
		reminded = true;
		
	}
	*/
	
	
	/**
	 * Checks the Bag and, if there are pickups in the Bag that are of the same in game type as the 
	 * CollectionNPC, removes them and increases the player's score. 
	 */
	public void turnIn() {
		
		if (Bag.getBagType().equals(primType)) {
			PICKUPID cpid = getPidfromRevMap();
			int turnedin = Bag.pickups.get(cpid);
			if(turnedin >0) {
				hud.setScore(hud.getScore()+(10*turnedin));
				Bag.removePickups(cpid);
				SidePanel.addText("~ Thank you for those "+ turnedin + " "+ primType + "s. \n\n");
			}
			//System.out.println("Turned in "+ turnedin+ " "+cpid+"s for "+(turnedin * 10) + " points");
		}
	}
	
	/**
	 * Uses the revidmap (reverse ID map) to get the PICKUPID of a pickup object that corresponds to 
	 * the primType of the CollectionNPC
	 * @return PICKUPID The ID of the pickup "type" 
	 */
	private PICKUPID getPidfromRevMap() {
		return revidmap.get(primType);
	}
	
	/**
	 * uses the primType field of the Collection as it is constructed in order to set special text to 
	 * be displayed in the game's side bar. 
	 */
	private void setSpecText() {
		if(primType == "Boolean") {
			colspectext = "Remember that Booleans are true or false\n\n";
		}
		if(primType == "Char") {
			colspectext = "Remember, char types are character primitive types. These include letters and punctuation marks\n\n";
		}
		if(primType == "Int") {
			colspectext = "Remember, int types are integers. Integers are whole numbers.\n\n";
		}
	}
	
	
	
}

