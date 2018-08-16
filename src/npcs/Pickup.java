package npcs;

import java.awt.Color;
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
