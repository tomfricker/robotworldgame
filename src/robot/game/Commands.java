package robot.game;

import java.util.ArrayList;

import npcs.ArrayListNPC;
import npcs.Drawing;

/**
 * This class has a array of all the commands available to the user and an ArrayList of all the commands the user has entered. 
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
			"robot.pickup(flower);", "drawFlower();",
			"robot.move(up, 2);", "robot.move(up, 3);", "robot.move(up, 4);", "robot.move(up, 5);", "robot.move(up, 6);", "robot.move(up, 7);", 
			"robot.move(down, 2);", "robot.move(down, 3);", "robot.move(down, 4);", "robot.move(down, 5);", "robot.move(down, 6);", "robot.move(down, 7);", 
			"robot.move(left, 2);", "robot.move(left, 3);", "robot.move(left, 4);", "robot.move(left, 5);", "robot.move(left, 6);", "robot.move(left, 7);", 
			"robot.move(right, 2);", "robot.move(right, 3);", "robot.move(right, 4);", "robot.move(right, 5);", "robot.move(right, 6);", "robot.move(right, 7);",
			"chars.add(a);","chars.add(e);","chars.add(i);","chars.add(o);","chars.add(u);", "chars.remove(1);",
			"help"
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
				}
			}
		}
	}
	
	/**
	 * Shows drawing of flower
	 */
	public static void drawFlower() {
		Drawing.setVisibility(true);
	}
	
	/**
	 * Commands which make the player move in the specified direction and the specified number of squares
	 * @param direction player moves in (up, down, left, right)
	 * @param number of squares player moves in (<8)
	 */
	public static void move(String direction, int number) {
		for(GameObjects gameObject : Manager.objectList) {
			if (gameObject.getId() == ID.Player) {
				for(int i = 1; i <= number; i++) {
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
			}
		}
	}
	
	/**
	 * Add parameter to ArrayListNPC
	 * @param c
	 */
	public static void addToAL(char c) {
		for(GameObjects o : Manager.objectList) {
			if (o.getId() == ID.Collection) {
				ArrayListNPC al = (ArrayListNPC)o;
				al.add(c);
			}
		}
	}
	
	/**
	 * Removes parameter from ArrayListNPC
	 * @param i
	 */
	public static void removeFromAL(int i) {
		for(GameObjects o : Manager.objectList) {
			if (o.getId() == ID.Collection) {
				ArrayListNPC al = (ArrayListNPC)o;
				al.remove(i);
			}
		}
	}
	
	/**
	 * Opens help window
	 */
	public static void help() {
		new HelpWindow();
	}
	
	/**
	 * Checks if the users input is a valid command
	 * @return boolean value
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
	 * Prints valid code in codePanel output box or displays error message in sidePanel
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
			else if(command.equals("drawFlower();")) {
				drawFlower();
			}
			else if(command.equals("robot.move(up, 2);")) {
				move("up", 2);
			}
			else if(command.equals("robot.move(up, 3);")) {
				move("up", 3);
			}
			else if(command.equals("robot.move(up, 4);")) {
				move("up", 4);
			}
			else if(command.equals("robot.move(up, 5);")) {
				move("up", 5);
			}
			else if(command.equals("robot.move(up, 6);")) {
				move("up", 6);
			}
			else if(command.equals("robot.move(up, 7);")) {
				move("up", 7);
			}
			else if(command.equals("robot.move(down, 2);")) {
				move("down", 2);
			}
			else if(command.equals("robot.move(down, 3);")) {
				move("down", 3);
			}
			else if(command.equals("robot.move(down, 4);")) {
				move("down", 4);
			}
			else if(command.equals("robot.move(down, 5);")) {
				move("down", 5);
			}
			else if(command.equals("robot.move(down, 6);")) {
				move("down", 6);
			}
			else if(command.equals("robot.move(down, 7);")) {
				move("down", 7);
			}
			else if(command.equals("robot.move(left, 2);")) {
				move("left", 2);
			}
			else if(command.equals("robot.move(left, 3);")) {
				move("left", 3);
			}
			else if(command.equals("robot.move(left, 4);")) {
				move("left", 4);
			}
			else if(command.equals("robot.move(left, 5);")) {
				move("left", 5);
			}
			else if(command.equals("robot.move(left, 6);")) {
				move("left", 6);
			}
			else if(command.equals("robot.move(left, 7);")) {
				move("left", 7);
			}
			else if(command.equals("robot.move(right, 2);")) {
				move("right", 2);
			}
			else if(command.equals("robot.move(right, 3);")) {
				move("right", 3);
			}
			else if(command.equals("robot.move(right, 4);")) {
				move("right", 4);
			}
			else if(command.equals("robot.move(right, 5);")) {
				move("right", 5);
			}
			else if(command.equals("robot.move(right, 6);")) {
				move("right", 6);
			}
			else if(command.equals("robot.move(right, 7);")) {
				move("right", 7);
			}
			else if(command.equals("chars.add(a);")||command.equals("chars.add(e);")||command.equals("chars.add(i);")||command.equals("chars.add(o);")||command.equals("chars.add(u);")) {
				addToAL('a');
			}
			else if(command.equals("chars.remove(1);")) {
				removeFromAL(1);
			}
			else if(command.equals("help")) {
				help();
			}
		}
	}
	
}
