package robot.game;

import java.awt.Graphics;
import java.util.LinkedList;

public class Manager {

	
	public static LinkedList<GameObjects> objectList = new LinkedList<>();
	public static LinkedList<GameObjects> flowerList = new LinkedList<>();
	
	//tick is the update stage of all objects in the game i.e. when the pieces move or their actions are carried out. 
	public void tick() {
		for(GameObjects object : objectList) {
			object.tick();
		}
		for(GameObjects flower : flowerList) {
			flower.tick();
		}
	}
	
	//render is the method which draws the objects into the game window. 
	public void render(Graphics g) {
		for(GameObjects object : objectList) {
			object.render(g);
		}
		for(GameObjects flower : flowerList) {
			flower.render(g);
		}
	}
		
	public void addObject(GameObjects object) {
		objectList.add(object);
	}
		
	public static void removeObject(GameObjects object) {
		objectList.remove(object);
	}
	
	public static void clearAll() {
		objectList.clear();
		flowerList.clear();
	}
	
	public void addFlower(GameObjects flower) {
		flowerList.add(flower);
	}
	
	public static void removeFlower(GameObjects flower) {
		flowerList.remove(flower);
	}
}
