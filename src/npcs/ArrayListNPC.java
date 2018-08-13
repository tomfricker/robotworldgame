package npcs;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;

import robot.game.Bag;
import robot.game.Cells;
import robot.game.GameObjects;
import robot.game.HUD;
import robot.game.ID;
import robot.game.Manager;
import robot.game.SidePanel;

public class ArrayListNPC extends GameObjects {
	
	public String primType;
	public SidePanel side;
	public HUD hud;
	public int targetValue;
	public HashMap<String,PICKUPID> revidmap = new HashMap<>();
	public boolean interacted = false;
	private ArrayList<GameObjects> tail = new ArrayList<>();
	public Manager manager;
	

	public ArrayListNPC(int x, int y, ID id,String primType,Manager manager,SidePanel side, HUD hud,int targetValue, boolean b) {
		super(x, y, id);
		this.primType = primType;
		this.side = side;
		this.hud = hud;
		this.targetValue = targetValue;
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
		int tailPositionX = x + 25;
		int tailPositionY = y + 50;
		for(GameObjects o:tail) {
			g.fillOval(tailPositionX, tailPositionY, 10, 10);
			tailPositionY += 20;
			
		}
		
	}
	
	public void interact() {
		
	}

	public void add(char c) {
		tail.add(new Pickup(64,64,ID.NPC, PICKUPID.Boolean,side, "false"));
		hud.setScore(hud.getScore()+10);
		if(hud.getStage() == 2 && hud.getScore() == 100) {
			hud.setStage(3);
			side.addText("~ Congratulations! You have completed the introduction to Collections in Java Programming");
			hud.setLevelEnd(true);
		}
	}
	
	public void remove(int i) {
		tail.remove(i);
	}
	

}
