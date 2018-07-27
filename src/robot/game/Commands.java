package robot.game;

import java.util.ArrayList;

/**
 * 
 * @author MissH
 *
 */
public class Commands {

	Manager manager;
	static ArrayList<String> commands = new ArrayList<String>();
	
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
				for(GameObjects flower : Manager.flowerList) {	
					if(player.getX() == flower.getX() && player.getY() == flower.getY()) {
						if (flower.getId() == ID.Flower) {
							Manager.removeFlower(flower);
						}
						SidePanel.addText("~You have picked up a flower!\n\n");
					}
					//repeats message for all flowers (i.e. 3 times instead of 1)
					//else {
					//	SidePanel.addText("~Find a flower to pickup!\n\n");
					//}
				}
			}
		}
	}
	
	public static void move(String direction, int number) {
		for(GameObjects gameObject : Manager.objectList) {
			if (gameObject.getId() == ID.Player) {
				int i = 1; 
				do{ 
					if(direction == "up") {
						moveUp();
					}
					if(direction == "down") {
						moveDown();
					}
					if(direction == "left") {
						moveLeft();
					}
					if(direction == "right") {
						moveRight();
					}
				}
				while(i < number);
			}
		}
	}
	
	/**
	 * Checks which command has been entered and does it
	 * @return 
	 */
	public static Boolean checkCommands() {
		String inputText = CodePanel.getInput();
		if(inputText.equals("robot.move(up);")) {
			return true;
		}
		else if(inputText.equals("robot.move(down);")) {
			return true;
		}
		else if(inputText.equals("robot.move(left);")) {
			return true;
		}
		else if(inputText.equals("robot.move(right);")) {
			return true;
		}
		else if(inputText.equals("robot.pickup(flower);")) {
			return true;
		}
		//else if(inputText.equals("robot.move(String, int);")) {
		//	return true;
		//}
		else {
			return false;
		}
	}
	
	public static void printCommands() {
		Boolean validCode = checkCommands();
		String inputText = CodePanel.getInput();
		if(validCode == true) {
			CodePanel.addText(inputText + "\n\n");
		}
		else {
			SidePanel.addText("~I don't understand:\n" + inputText + "\nTry typing something else.\n\n");
		}
	}
	
	public static void addToCommandList(String command) {
		commands.add(command);
	}
	
	public static void removeLastCommand() {
		commands.remove(commands.size()-1);
	}
	
	public static void clearCommandList( ) {
		commands.clear();
	}
	
	public static void runCommands() {
		for(String command : commands) {
			if(command.equals("robot.move(up);")) {
				moveUp();
			}
			else if(command.equals("robot.move(down);")) {
				moveDown();
			}
			else if(command.equals("robot.move(left);")) {
				moveLeft();
			}
			else if(command.equals("robot.move(right);")) {
				moveRight();
			}
			else if(command.equals("robot.pickup(flower);")) {
				pickupFlower();
			}
			//else if(command.equals("robot.move(String direction, int number);")) {
			//	move(direction, number);
			//}
		}
	}
	
}