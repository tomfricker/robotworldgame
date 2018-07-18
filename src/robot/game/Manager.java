package robot.game;

import java.awt.Graphics;
import java.util.LinkedList;

public class Manager {

	
	static LinkedList<GameObjects> objectList = new LinkedList<>();
	
	//tick is the update stage of all objects in the game i.e. when the pieces move or their actions are carried out. 
	public void tick() {
		for(GameObjects object : objectList) {
			object.tick();
		}
	}
	
	//render is the method which draws the objects into the game window. 
	public void render(Graphics g) {
		for(GameObjects object : objectList) {
			object.render(g);
		}
	}
		
	public void addObject(GameObjects object) {
		objectList.add(object);
	}
		
	public static void removeObject(GameObjects object) {
		objectList.remove(object);
	}
}
