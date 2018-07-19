package robot.game;

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
	private int keepScore;
	
	public Level(Manager manager, SidePanel side, int level) {
		this.manager = manager;
		this.side = side;
		
		loadLevel(1);
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
	}
	
	/**
	* Create the objects unique to level 1
	*/
	public void levelOne() {
		manager.addObject(new Player(Cells.A, Cells.A, ID.Player));
		manager.addObject(new LevelTomNPC(Cells.D, Cells.C, ID.NPC, manager, side));
		manager.addObject(new LevelTomNPC1(Cells.H, Cells.A, ID.NPC, manager, side));
		manager.addObject(new LevelTomNPC2(Cells.A, Cells.H, ID.NPC, manager, side));
		manager.addObject(new LevelTomNPC3(Cells.H, Cells.H, ID.NPC, manager, side));
		
		side.setText("~Welcome to level 1 of Robot World!\n\n"
					+ "~Please move Robbie the Robot around the board using the arrow keys.\n\n"
					+ "~Good luck and enjoy your adventure in Robot World!\n\n");
	}
	
	/**
	 * add objects for level 2
	 */
	public void levelTwo() {
		manager.addObject(new Player(Cells.A, Cells.A, ID.Player));
		manager.addObject(new LevelHelenNPC(Cells.B, Cells.A, ID.NPC, manager, side));
		
		side.setText("~Welcome to level Helen of Robot World!\n\n"
				+ "~Please move the Robot around the board by typing in code, e.g. robot.move(right);\n\n");
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
