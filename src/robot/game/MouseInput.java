package robot.game;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter {
	
	private Manager manager;
	
	public MouseInput(Manager manager) {
		this.manager = manager;
	}
	
	/**
	 * This method will set the player to centre where the mouse is being clicked on the screen
	 */
	public void mousePressed(MouseEvent e) {
		
		//int x = e.getX() - 32;
		//int y = e.getY() - 32;
		
		//lock player to squares only
		double squareX = e.getX()/Game.boardIndex;
		int squareNumX = (int)squareX;
		int x = (squareNumX * Game.boardIndex)+(Game.boardIndex/2)-32;
		
		double squareY = e.getY()/Game.boardIndex;
		int squareNumY = (int)squareY;
		int y = (squareNumY * Game.boardIndex)+(Game.boardIndex/2)-32;
		
		
		for(GameObjects gameObject : manager.objectList) {
			if(gameObject.getId() == ID.Player) {
				GameObjects.setX(x);
				GameObjects.setY(y);
			}
		}
	}

}
