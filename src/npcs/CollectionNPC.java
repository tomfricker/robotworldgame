/**
	fully replace the CollectionNPC class with this. 
*/

package npcs;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;

import javax.swing.JOptionPane;

import robot.game.Bag;
import robot.game.GameObjects;
import robot.game.HUD;
import robot.game.ID;
import robot.game.Manager;
import robot.game.SidePanel;

public class CollectionNPC extends GameObjects {
	
	public String primType;
	public SidePanel side;
	//public Boolean reminded;
	//public boolean firstVisit;
	public HUD hud;
	public int targetValue;
	public HashMap<String,PICKUPID> revidmap = new HashMap<>();
	public boolean interacted = false;
	private String colspectext;

	public CollectionNPC(int x, int y, ID id,String primType,SidePanel side, HUD hud,int targetValue) {
		super(x, y, id);
		this.primType = primType;
		this.side = side;
		this.hud = hud;
		this.targetValue = targetValue;
		revidmap.put("Int",PICKUPID.Int);
		revidmap.put("Char",PICKUPID.Char);
		revidmap.put("Boolean",PICKUPID.Boolean);
		Bag bag = Bag.getInstance();
		//reminded = false;
		//firstVisit = true;
		setSpecText();
		
	}

	@Override
	public void tick() {
		interact();
		
	}

	@Override
	public void render(Graphics g) {
		if(primType == "Int") {g.setColor(Color.RED);}
		if(primType == "Char") {g.setColor(Color.BLUE);}
		if(primType == "Boolean") {g.setColor(Color.GREEN);}
	
		g.fillRect(x+10, y+10, 40, 40);
		
	}
	
	
	public void interact() {
		for(GameObjects o : Manager.objectList) {
			if( o.getId() == ID.Player) {
			
				// gets player coordinates
				if(o.getX() == x && o.getY() == y) {
					if(interacted ==false) {
					JOptionPane.showMessageDialog(null, "I'm an ArrayList of type"+primType+". I want you to find me all of the "+primType+" objects on the board and bring them back to me.");
					side.addText(colspectext);
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
							if(hud.getScore() == 50) {
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
	
	public void impose() {
		if(Bag.bagType != primType) {
			Bag.setType(primType);
			//System.out.println("imposed the collector's type "+primType+" on the player's bag");
			//reminded = false;
			side.addText("~Look for the "+primType+" objects on the board for me.\n\n");
		}
	}
	
	/**
	public void remind() {
		side.addText("~I'm after "+primType +" types. Avoid the other collectors until you've brought me all my objects. \n\n"  );
		reminded = true;
		
	}
	*/
	
	public void turnIn() {
		
		if (Bag.getBagType().equals(primType)) {
			PICKUPID cpid = getPidfromRevMap();
			int turnedin = Bag.pickups.get(cpid);
			if(turnedin >0) {
				hud.setScore(hud.getScore()+(10*turnedin));
				Bag.removePickups(cpid);
				side.addText("~ Thank you for those "+ turnedin + " "+ primType + "s. \n\n");
			}
			//System.out.println("Turned in "+ turnedin+ " "+cpid+"s for "+(turnedin * 10) + " points");
		}
	}
	
	private PICKUPID getPidfromRevMap() {
		return revidmap.get(primType);
	}
	
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
	
	
	
}//eoc

