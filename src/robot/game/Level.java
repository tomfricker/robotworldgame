package robot.game;

/**
 * 
 * @author James
 * Levels allow for game progression by creating objects to be used within a level and providing their own completion criteria
 * as well as the failure conditions. 
 *
 */
public class Level {
	
	public Level(int level) {
		loadLevel(level);
	}

	/**
	 * 
	 * @param level the number of the level being played
	 * 
	 * this just adds game objects to the manager for the game to use. 
	 * more of these can be added when the game is developed. 
	 */
	private void loadLevel(int level) {
		if(level ==1) {
			
			//add the player to the game objects and display it on the board
			Game.manager.addObject(new Board(0,0,ID.background));
			Game.manager.addObject(new Player(8, 8, ID.Player));
			//Game.manager.addObject(new LevelHelenNPC1(8+(Game.boardIndex*7), 8, ID.NPC, Game.manager, Game.side));
			//Game.manager.addObject(new LevelHelenNPC2(8, 8+(Game.boardIndex*7), ID.NPC, Game.manager, Game.side));
			//Game.manager.addObject(new LevelHelenNPC3(8+(Game.boardIndex*7), 8+(Game.boardIndex*7), ID.NPC, Game.manager, Game.side));
			Game.side.setText("~Welcome to level 1 of Robot World!\n\n"
					+ "~Please move Robbie the Robot around the board using the arrow keys.\n\n"
					+ "~Good luck and enjoy your adventure in Robot World!\n\n");
		}
		
	
	}
	

	public void complete() {
		//when completion criteria of a level is met, removes all objects from the manager's linked list and proceeds with the next part of the game. 
		//Game.manager.objectList.clear();
		//to do: add some sort of recognition for the completion of a level. 
	}
	
	public void fail() {
		//when a level is failed, removes all objects from the manager's linked list and the rest is to be decided on. 
				//Game.manager.objectList.clear();
		//to do: add some sort of failure effect. 
	}
	
	//to do: add completion and failure criteria.
	
}// end of class
