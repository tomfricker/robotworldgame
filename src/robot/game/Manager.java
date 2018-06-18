package robot.game;

import java.awt.Graphics;
import java.util.LinkedList;

public class Manager {

	
	LinkedList<GameObjects> objectList = new LinkedList<>();
	
	public void tick() {
		for(int i = 0; i<objectList.size(); i++) {
			GameObjects object = objectList.get(i);
			object.tick();
		}
		
	}
	
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
