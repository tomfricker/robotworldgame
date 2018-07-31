package npcs;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;

import robot.game.Bag;
import robot.game.GameObjects;
import robot.game.ID;
import robot.game.Manager;
import robot.game.SidePanel;

public class Pickup extends GameObjects {
	
	private SidePanel side;
	PICKUPID pid;
	HashMap<PICKUPID,String> idmap = new HashMap<>();

	public Pickup(int x, int y, ID id, PICKUPID pid, SidePanel side) {
		super(x, y, id);
		this.pid = pid;
		this.side = side;
		idmap.put(PICKUPID.Int,"Int");
		idmap.put(PICKUPID.Char,"Char");
		idmap.put(PICKUPID.Boolean,"Boolean");
	}//char

	@Override
	public void tick() {
		for(GameObjects o : Manager.objectList) {
			
			if(o.getId() == ID.Player && pid != PICKUPID.Picked) {
				
				if(o.getX() == x && o.getY() == y) {
					
					pickUp();
				}
			}
		}
		
	}

	@Override
	public void render(Graphics g) {
		if(pid == PICKUPID.Picked) {}
		else {
			if(pid == PICKUPID.Int) {g.setColor(Color.RED);}
			if(pid == PICKUPID.Char) {g.setColor(Color.BLUE);}
			if(pid == PICKUPID.Boolean) {g.setColor(Color.GREEN);}
			g.fillOval(x, y, 20, 20);
		}
		
	}
	
	public void pickUp() {
		String bagpickupType = Bag.getBagType();
		if( bagpickupType.equals(idmap.get(pid))){
			Bag.addToBag(this);
			setPicked();
		}
	}
	
	public PICKUPID getPID() {
		return pid;
	}
	
	public void setPicked() {
		pid = PICKUPID.Picked;
	}

}
