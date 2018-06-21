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
			
			Game.manager.addObject(new Board(0,0,ID.background));
			Game.manager.addObject(new Player(100,100,ID.cat));
			Game.manager.addObject(new Player(400,400, ID.mouse));
		}
		
	
	}
	

	public void complete() {
		//when completion criteria of a level is met, removes all objects from the manager's linked list and proceeds with the next part of the game. 
		Game.manager.objectList.clear();
		//to do: add some sort of recognition for the completion of a level. 
	}
	
	public void fail() {
		//when a level is failed, removes all objects from the manager's linked list and the rest is to be decided on. 
				Game.manager.objectList.clear();
		//to do: add some sort of failure effect. 
	}
	
	//to do: add completion and failure criteria.
	
}// end of class
