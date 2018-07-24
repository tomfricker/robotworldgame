package robot.game;

import HelenLevel.Flower;
import HelenLevel.LevelHelenNPC;
import HelenLevel.LevelHelenNPC2;
import TomLevel.LevelTomNPC;
import TomLevel.LevelTomNPC2;
import TomLevel.LevelTomNPC3;

public class Spawner {
	
	private Manager manager;
	private HUD hud;
	private SidePanel side;
	
	public Spawner(Manager manager, HUD hud, SidePanel side) {
		this.manager = manager;
		this.hud = hud;
		this.side = side;
	}
	
	public void tick() {
		//get the level and create objects once certain criteria is met
		switch(hud.getLevel()) {
			//create the objects of level 1 once you reach a certain score
			case 1: if(hud.getScore() == 15) {
				manager.addObject(new LevelTomNPC2(Cells.A, Cells.H, ID.NPC, manager, side, hud));
				hud.setScore(hud.getScore() + 5);
			}
			else if(hud.getScore() == 25) {
				manager.addObject(new LevelTomNPC3(Cells.H, Cells.H, ID.NPC, manager, side, hud));
				hud.setScore(hud.getScore() + 5);
			}
			else if(hud.getScore() == 35) {
				manager.addObject(new LevelTomNPC(Cells.E, Cells.D, ID.NPC, manager, side, hud));
				hud.setScore(hud.getScore() + 5);
			}
			//level 2
			case 2: if(hud.getScore() == 5) {
				manager.addObject(new LevelHelenNPC2(Cells.E, Cells.C, ID.NPC, manager, side, hud));
				hud.setScore(hud.getScore() + 5);
			}
			else if(hud.getScore() == 15) {
				manager.addObject(new LevelHelenNPC(Cells.D, Cells.A, ID.NPC, manager, side, hud));
				hud.setScore(hud.getScore() + 5);
			}
			else if(hud.getScore() == 30 ) {
				manager.addFlower(new Flower(Cells.C, Cells.A, ID.Flower, manager, side, hud));
				manager.addFlower(new Flower(Cells.B, Cells.G, ID.Flower, manager, side, hud));
				manager.addFlower(new Flower(Cells.G, Cells.F, ID.Flower, manager, side, hud));
				hud.setScore(hud.getScore() + 5);
			}
			//level 3
			//case 3:
			//level 4
			//case 4:
			//level 5
			//case 5:
		}
	}

}

