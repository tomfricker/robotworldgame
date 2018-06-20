package robot.game;

import java.awt.Graphics;
import java.util.LinkedList;

public class Manager {

	
	LinkedList<GameObjects> objectList = new LinkedList<>();
	
	//tick is the update stage of all objects in the game i.e. when the picese move or their actions are carried out. 
	public void tick() {
		for(int i = 0; i<objectList.size(); i++) {
			GameObjects object = objectList.get(i);
			object.tick();
		}
		
	}
	
	
	//render is the method which draws the objects into the game window. 
	public void render(Graphics g) {
		for(int i = 0; i<objectList.size(); i++) {
			GameObjects object = objectList.get(i);
			object.render(g);
		}
	}
		
	public void addObject(GameObjects object) {
		objectList.add(object);
	}
		
	public void removeObject(GameObjects object) {
		objectList.remove(object);
	}
}
