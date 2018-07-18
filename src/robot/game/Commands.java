package robot.game;

public class Commands {

	
	public static void moveUp() {
		if (GameObjects.getId() == ID.Player) {
			int newY = Cells.decrementCell(Player.getY());
			Player.setY(newY);
		}
	}
	
	public static void moveDown() {
		if (GameObjects.getId() == ID.Player) {
			int newY = Cells.incrementCell(Player.getY());
			Player.setY(newY);
		}
	}
	
	public static void moveLeft() {
		if (GameObjects.getId() == ID.Player) {
			int newX = Cells.decrementCell(Player.getX());
			Player.setX(newX);
		}
	}
	
	public static void moveRight() {
		if (GameObjects.getId() == ID.Player) {
			int newX = Cells.incrementCell(Player.getX());
			Player.setX(newX);
		}
	}
	
	/**
	 * Checks which command has been entered and does it
	 * @return 
	 */
	public static String checkCommands() {
		String inputText = SidePanel.getInput();
		
		if(inputText.equals("robot.move(up);")) {
			Commands.moveUp();
			return inputText + "\n\n";
		}
		else if(inputText.equals("robot.move(down);")) {
			Commands.moveDown();
			return inputText + "\n\n";
		}
		else if(inputText.equals("robot.move(left);")) {
			Commands.moveLeft();
			return inputText + "\n\n";
		}
		else if(inputText.equals("robot.move(right);")) {
			Commands.moveRight();
			return inputText + "\n\n";
		}
		else {
			return "~I don't understand, try typing something else. \n\n";
		}
		
	}
	
	
	
}
