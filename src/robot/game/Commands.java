package robot.game;

public class Commands {

	
	public static void moveUp() {
		int newY = Cells.decrementCell(GameObjects.getY());
		GameObjects.setY(newY);
	}
	
	public static void moveDown() {
		int newY = Cells.incrementCell(GameObjects.getY());
		GameObjects.setY(newY);
	}
	
	public static void moveLeft() {
		int newX = Cells.decrementCell(GameObjects.getX());
		GameObjects.setX(newX);
	}
	
	public static void moveRight() {
		int newX = Cells.incrementCell(GameObjects.getX());
		GameObjects.setX(newX);
	}
	
	
	
}
