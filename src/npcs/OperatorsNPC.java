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
 * An NPC that extends GameObjects. The OperatorsNPC calls a static class 
 * Bag object that allows for the player to collect items
 * and return them to the NPC
 *  which increases their score. 
 * Each OperatorsNPC has an in game type stored as the opType field. 
 * @author Robot World Group
 */

public class OperatorsNPC extends GameObjects {
    // Fields of OperatorsNPC class
	public String opType;
	public SidePanel side;
	public HUD hud;
	public HashMap<String,PICKUPID> revidmap = new HashMap<>();
	public boolean interacted = false;
	private String colspectext;
	private String message;
	
	
	public OperatorsNPC(int x, int y, ID id,String opType,SidePanel side, HUD hud, String message) {
		super(x, y, id);
		this.opType = opType;
		this.side = side;
		this.hud = hud;
		revidmap.put("GreaterThan",PICKUPID.Greater);
		revidmap.put("LessThan",PICKUPID.Less);
		revidmap.put("Equal",PICKUPID.Equal);
      	Bag bag = Bag.getInstance();
		this.message = message;
		
	
		setSpecText();
	}
	

	/**
	 * When player interacts with an OperatorsNPC it will call the interact method
	 */
	public void tick() {
		interact();
}
	
	
	/**
	 * Renders the OperatorsNPC in the game, the image file is determined by
	 * the OperatorsNPC's in game type. 
	 */
	public void render(Graphics g) {
		if(opType == "GreaterThan") {
			File imageFile = new File("pictures\\Friend1.png");
		
			try {
				Image FALSE = ImageIO.read(imageFile);
				g.drawImage(FALSE, x, y, null);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(opType == "LessThan") {
			File imageFile = new File("pictures\\Friend2.png");
			try {
				Image FALSE = ImageIO.read(imageFile);
				g.drawImage(FALSE, x, y, null);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(opType == "Equal") {
			File imageFile = new File("pictures\\Friend3.png");
			try {
				Image FALSE = ImageIO.read(imageFile);
				g.drawImage(FALSE, x, y, null);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	/**
	 * Checks whether the OperatorsNPC should interact or not and what should occur. 
	 * If the player's bag does not have the same in game type as the OperatorsNPC, it will call the 
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
					JOptionPane.showMessageDialog(null, message, "Message for You", JOptionPane.INFORMATION_MESSAGE);
				hud.setScore(hud.getScore() + 10);
						HUD.setInteractions(HUD.getInteractions() + 1);		
					SidePanel.addText(colspectext);
					interacted = true;
					}
					
					//checks type of bag against collector type
					if(Bag.getBagType().equals(opType)) {
						
						
					
						if(Bag.pickups.get(getPidfromRevMap()) != null) {
							System.out.println("pickups should be added to the collector");
							turnIn();
							if(hud.getScore() >= 500) {
								
						     	hud.setStage(2);
							hud.setStageEnd(true);
							}
					}
			}
				

				else {
					impose();
				
				
				
					}
					}       
			}	
	   }
		
}
/**
 * Sets the Bag's in game type to that of the OperatorsNPC and prompts the player to find 
 * pickups in the level of the OperatorsNPCs type. 
 */


public void impose() {
	if(Bag.bagType != opType) {
		Bag.setType(opType);
		//System.out.println("imposed the Operator's type "+opType+" on the player's bag");
		//reminded = false;
	//	SidePanel.addText("~Look for the "+opType+" objects on the board for me.\n\n");
	}
   }	
//}

	/**
	 * Checks the Bag and, if there are pickups in the Bag that are of the same in game type as the 
	 * OperatorsNPC, it removes them and increases the player's score. 
	 */
	
	
public void turnIn() {
	
	if (Bag.getBagType().equals(opType)) {
		PICKUPID cpid = getPidfromRevMap();
		int turnedin = Bag.pickups.get(cpid);
		if(turnedin >0) {
			hud.setScore(hud.getScore()+(10*turnedin));
			Bag.removePickups(cpid);
			SidePanel.addText("~ Thank you for those "+ turnedin + " "+ opType + " " + "values. \n\n");
		}
		
	}
}
	
/**
 * Uses the revidmap (reverse ID map) to get the PICKUPID of a pickup object that corresponds to 
 * the opType of the OperatorsNPC
 * @return PICKUPID The ID of the pickup "type" 
 */


private PICKUPID getPidfromRevMap() {
	return revidmap.get(opType);
}

/**
 * uses the opType field of the OperatorsNPC as it is constructed in order to set special text to 
 * be displayed in the game's side bar. 
 */
private void setSpecText() {
	if(opType == "GreaterThan") {
		colspectext = "Remember that the symbol > means greater than a value\n\n"
				+ "Here is a reminder: 5 > 2 means 5 is greater than 2!\n\n";
	}
	if(opType == "LessThan") {
		colspectext = "Remember, the operator < means less than a value\n\n"
				+ "Here is a reminder: 2 < 5 means 2 is less than 5!\n\n";
	}
	if(opType == "Equal") {
		colspectext = "Remember, there is a difference between (==) and (=).\n\n"
				+ "(==) means that both values are equal for example: 2 == 2 or x == y\n\n";
	}

  }

}





	
	
	
	
	
	
