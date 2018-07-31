package npcs;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;

import robot.game.Bag;
import robot.game.GameObjects;
import robot.game.HUD;
import robot.game.ID;
import robot.game.Manager;
import robot.game.SidePanel;

public class CollectionNPC extends GameObjects {
	
	public String primType;
	public SidePanel side;
	public Boolean reminded = false;
	public HUD hud;
	public int targetValue;
	public HashMap<String,PICKUPID> revidmap = new HashMap<>();

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
					
					//checks type of bag against collector type
					if(Bag.getBagType().equals(primType)) {
						
						//System.out.println("The bag's type is the same as this collector.");
						if(!reminded) {
							remind();
						}
						
						if(Bag.pickups.get(getPidfromRevMap()) != null) {
							//System.out.println("pickups should be added to the collector");
							turnIn();
							}
						
					}
					
					else {
						impose();
						//System.out.println("change bag type to match collector with " + primType);
						//Bag.setType(primType);
						//reminded = false;
					
					
					}
				}	
			}
		}
	}
	
	public void impose() {
		if(Bag.bagType != primType) {
			Bag.setType(primType);
			//System.out.println("imposed the collector's type "+primType+" on the player's bag");
			reminded = false;
			side.addText("I'm a "+primType+" collector. I want you to find me all of the "+primType+" objects on the board and bring them back to me.");
		}
	}
	
	public void remind() {
		side.addText("I'm after "+primType +" types. Avoid the other collectors until you've brought me all my objects." );
		reminded = true;
	}
	
	public void turnIn() {
		
		if (Bag.getBagType().equals(primType)) {
			PICKUPID cpid = getPidfromRevMap();
			
			int turnedin = Bag.pickups.get(cpid);
			hud.setScore(hud.getScore()+(10*turnedin));
			Bag.removePickups(cpid);
			System.out.println("Turned in the "+ turnedin+ " "+cpid+"s for some points");
		}
	}
	
	private PICKUPID getPidfromRevMap() {
		return revidmap.get(primType);
	}
	
}//eoc
