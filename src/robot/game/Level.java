package robot.game;

import DessyLevel.DessyLevelNPC;
import DessyLevel.DessyLevelNPC1;
import HelenLevel.LevelHelenNPC;
import TomLevel.LevelTomNPC;

/**
 * 
 * @author James
 * Levels allow for game progression by creating objects to be used within a level and providing their own completion criteria
 * as well as the failure conditions. 
 *
 */
public class Level {
	
	private Manager manager;
	private SidePanel side;
	private HUD hud;
	public Level(Manager manager, SidePanel side, int level, HUD hud) {
		this.manager = manager;
		this.side = side;
		this.hud = hud;
		
		//loadLevel(1);
		loadLevel(2);
	}

	/**
	 * 
	 * @param level the number of the level being played
	 * 
	 * this just adds game objects to the manager for the game to use. 
	 * more of these can be added when the game is developed. 
	 */
	private void loadLevel(int level) {
		//Create the player and the board
		if(level == 1) {
			levelOne();
		}
		else if(level == 2) {
			levelTwo();
		}
		else if(level == 3) {
			levelThree();
		}
	}
	
	/**
	* Create the objects unique to level 1
	*/
	public void levelOne() {
		manager.addObject(new Board(0, 0, ID.background));
		manager.addObject(new Player(Cells.A, Cells.A, ID.Player));
		manager.addObject(new LevelTomNPC(Cells.D, Cells.C, ID.NPC, manager, side, hud));
		//manager.addObject(new LevelTomNPC1(Cells.H, Cells.A, ID.NPC, manager, side));
		//manager.addObject(new LevelTomNPC2(Cells.A, Cells.H, ID.NPC, manager, side));
		//manager.addObject(new LevelTomNPC3(Cells.H, Cells.H, ID.NPC, manager, side));
		
		side.setText("~Welcome to level 1 of Robot World!\n\n"
					+ "~Please move Robbie the Robot around the board using the arrow keys.\n\n"
					+ "~Good luck and enjoy your adventure in Robot World!\n\n");
	}
	
	/**
	 * add objects for level 2
	 */
	public void levelTwo() {
		manager.addObject(new Player(Cells.A, Cells.A, ID.Player));
		manager.addObject(new LevelHelenNPC(Cells.D, Cells.A, ID.NPC, manager, side, hud));
		//flower.addFlower(new Flower(Cells.D, Cells.D, ID.Flower, manager, side));
		
		side.setText("~Welcome to level Helen of Robot World!\n\n"
				+ "~Try moving the Robot around the board by typing in code, e.g. robot.move(right);\n\n");
	}
	
	/**
	 * add objects for level 3
	 */
	public void levelThree() {
		manager.addObject(new Player(Cells.B, Cells.A, ID.Player));
		manager.addObject(new DessyLevelNPC(Cells.H, Cells.A, ID.NPC, manager, side, hud));
		//manager.addObject(new LevelDessyNPC1(Cells.E, Cells.C, ID.NPC, manager, side, hud));
		side.setText("~Welcome to level 3 of Robot World - All about Classes and Objects!\n\n"
				+ "~Try moving the Robot to the apple.\n\n");
	}

	public void complete() {
		//when completion criteria of a level is met, removes all objects from the manager's linked list and proceeds with the next part of the game. 
		//manager.objectList.clear();
		//to do: add some sort of recognition for the completion of a level. 
	}
	
	public void fail() {
		//when a level is failed, removes all objects from the manager's linked list and the rest is to be decided on. 
				//Game.manager.objectList.clear();
		//to do: add some sort of failure effect. 
	}
	
	//to do: add completion and failure criteria.
	
}// end of class
