package robot.game;

import java.awt.Graphics;
import java.util.LinkedList;
/**
 *  
 * The Manager class is responsible for storing the GameObjects, showing them on the board
 * and producing interaction between them.
 *
 * @author Robot World Group
 *
 */
public class Manager {

	
	public static LinkedList<GameObjects> objectList = new LinkedList<>();
	public static LinkedList<GameObjects> flowerList = new LinkedList<>();
	
	/**
	 * This method goes through all objects in its two lists and ticks them.
	 * Tick updates the stage of all objects in the game, when for example
	 * the pieces move or their actions are carried out.
	 */
	public void tick() {
		for(GameObjects object : objectList) {
			object.tick();
		}
		for(GameObjects flower : flowerList) {
			flower.tick();
		}
	}
	
	/**
	 * This method goes through all objects in its two lists and renders them.
	 * Render draws the objects in the Game Window.
	 */
	public void render(Graphics g) {
		try{
			for(GameObjects object : objectList) {
				object.render(g);
			}
			for(GameObjects flower : flowerList) {
				flower.render(g);
			}
		}
		catch(Exception e) {
			System.out.println("Warning: Could not render.");
		}

	}
	
	/**
	 * Adds a GameObject to the object List of Manager.
	 * @param object
	 */
	public void addObject(GameObjects object) {
		objectList.add(object);
	}
	
	/**
	 * Removes a GameObject from the object List of Manager.
	 * @param object
	 */
	public static void removeObject(GameObjects object) {
		objectList.remove(object);
	}
	
	/**
	 * Clears all the objects from both lists of Manager.
	 */
	public static void clearAll() {
		objectList.clear();
		flowerList.clear();
	}
	
	/**
	 * Adds a GameObject to the flower List of Manager.
	 * @param flower
	 */
	public void addFlower(GameObjects flower) {
		flowerList.add(flower);
	}
	

	/**
	 * Removes a GameObject from the flower List of Manager.
	 * @param flower
	 */
	public static void removeFlower(GameObjects flower) {
		flowerList.remove(flower);
	}
}
