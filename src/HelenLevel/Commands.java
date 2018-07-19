package HelenLevel;

import java.util.Iterator;

import robot.game.Cells;
import robot.game.GameObjects;
import robot.game.ID;
import robot.game.Manager;
import robot.game.SidePanel;

public class Commands {

	Manager manager;

	
	public static void moveUp() {
		for(GameObjects gameObject : Manager.objectList) {
			if (gameObject.getId() == ID.Player) {
				int newY = Cells.decrementCell(gameObject.getY());
				gameObject.setY(newY);
			}
		}
	}
	
	public static void moveDown() {
		for(GameObjects gameObject : Manager.objectList) {
			if (gameObject.getId() == ID.Player) {
				int newY = Cells.incrementCell(gameObject.getY());
				gameObject.setY(newY);
			}
		}
	}
	
	public static void moveLeft() {
		for(GameObjects gameObject : Manager.objectList) {
			if (gameObject.getId() == ID.Player) {
				int newX = Cells.decrementCell(gameObject.getX());
				gameObject.setX(newX);
			}
		}
	}
	
	public static void moveRight() {
		for(GameObjects gameObject : Manager.objectList) {
			if (gameObject.getId() == ID.Player) {
				int newX = Cells.incrementCell(gameObject.getX());
				gameObject.setX(newX);
			}
		}
	}
	
	public static void pickupFlower() {
		for(GameObjects player : Manager.objectList) {
			if(player.getId() == ID.Player) {
		
				for(GameObjects flower : Manager.objectList) {
						
					if(player.getX() == flower.getX() && player.getY() == flower.getY()) {
					
						if (flower.getId() == ID.Flower) {
							
							//Iterator<GameObjects> it = flower.listIterator();
							//while (it.hasNext() ) {
							//	String flower.it.next();
							//	if(flower.getID() ==ID.Flower) {
							//		it.remove();
							//	}
							//}
							Manager.removeObject(flower);
						}
					}
				}
			}
		}
		
		SidePanel.addText("~You have picked up a flower!\n\n");
	}
	
	/**
	 * Checks which command has been entered and does it
	 * @return 
	 */
	public static Boolean checkCommands() {
		String inputText = SidePanel.getInput();
		if(inputText.equals("robot.move(up);")) {
			moveUp();
			return true;
		}
		else if(inputText.equals("robot.move(down);")) {
			moveDown();
			return true;
		}
		else if(inputText.equals("robot.move(left);")) {
			moveLeft();
			return true;
		}
		else if(inputText.equals("robot.move(right);")) {
			moveRight();
			return true;
		}
		else if(inputText.equals("robot.pickup(flower);")) {
			pickupFlower();
			return true;
		}
		else {
			return false;
		}
	}
	
	public static void printCommands() {
		Boolean validCode = checkCommands();
		String inputText = SidePanel.getInput();
		if(validCode == true) {
			SidePanel.addText(inputText + "\n\n");
		}
		else {
			SidePanel.addText("~I don't understand:\n" + inputText + "\nTry typing something else.\n\n");
		}
	}
	
}
