package robot.game;

import java.util.ArrayList;

/**
 * This class has a array of all the commands available to the user. 
 * It checks that the users input is one of the valid commands and runs it.
 * 
 * @author MissH
 *
 */
public class Commands {

	Manager manager;
	//List of commands which have been entered by user
	static ArrayList<String> commands = new ArrayList<String>();
	//array of valid commands
	private static final String[] validCommands = {
			"robot.move(up);", "robot.move(down);", "robot.move(left);", "robot.move(right);", 
			"robot.pickup(flower);", 
			"robot.move(up, 2);", "robot.move(up, 3);", "robot.move(up, 4);", "robot.move(up, 5);", "robot.move(up, 6);", "robot.move(up, 7);", 
			"robot.move(down, 2);", "robot.move(down, 3);", "robot.move(down, 4);", "robot.move(down, 5);", "robot.move(down, 6);", "robot.move(down, 7);", 
			"robot.move(left, 2);", "robot.move(left, 3);", "robot.move(left, 4);", "robot.move(left, 5);", "robot.move(left, 6);", "robot.move(left, 7);", 
			"robot.move(right, 2);", "robot.move(right, 3);", "robot.move(right, 4);", "robot.move(right, 5);", "robot.move(right, 6);", "robot.move(right, 7);"
	};
	
	/**
	 * Makes the player move up one square
	 */
	public static void moveUp() {
		for(GameObjects gameObject : Manager.objectList) {
			if (gameObject.getId() == ID.Player) {
				int newY = Cells.decrementCell(gameObject.getY());
				gameObject.setY(newY);
			}
		}
	}
	
	/**
	 * Makes the player move down one square
	 */
	public static void moveDown() {
		for(GameObjects gameObject : Manager.objectList) {
			if (gameObject.getId() == ID.Player) {
				int newY = Cells.incrementCell(gameObject.getY());
				gameObject.setY(newY);
			}
		}
	}
	
	/**
	 * Makes the player move left one square
	 */
	public static void moveLeft() {
		for(GameObjects gameObject : Manager.objectList) {
			if (gameObject.getId() == ID.Player) {
				int newX = Cells.decrementCell(gameObject.getX());
				gameObject.setX(newX);
			}
		}
	}
	
	/**
	 * Makes the player move right one square
	 */
	public static void moveRight() {
		for(GameObjects gameObject : Manager.objectList) {
			if (gameObject.getId() == ID.Player) {
				int newX = Cells.incrementCell(gameObject.getX());
				gameObject.setX(newX);
			}
		}
	}
	
	/**
	 * Makes the flower disappear if the player is on the same square
	 */
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
	
	/**
	 * Working progress - make the player move in the specified direction and the specified number of squares
	 * @param direction player moves in (up, down, left, right)
	 * @param number of squares player moves in (<8)
	 */
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
		for(int i = 0; i < validCommands.length; i++ ) {
			if(validCommands[i].equals(inputText)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * prints valid code in codePanel output box or displays error message in sidePanel
	 */
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
	
	/**
	 * Adds command entered by user to the commands list
	 * @param command
	 */
	public static void addToCommandList(String command) {
		commands.add(command);
	}
	
	/**
	 * Removes last command added to commands list (for delete button)
	 */
	public static void removeLastCommand() {
		commands.remove(commands.size()-1);
	}
	
	/**
	 * Clears commands list
	 */
	public static void clearCommandList( ) {
		commands.clear();
	}
	
	/**
	 * Runs command
	 */
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
			else if(command.equals("robot.move(up, 2);")) {
				moveUp(); moveUp();
			}
		}
	}
	
}
