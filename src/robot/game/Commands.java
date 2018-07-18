package robot.game;

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
