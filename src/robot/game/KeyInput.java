package robot.game;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
	
		//private CodePanel codePanel;
		
	public KeyInput(Manager manager) {
			
			//codePanel = new CodePanel();
		
	}
	
	/**
	 * This method controls the player object by the arrow keys on the keyboard
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		
		int key = e.getKeyCode();
		
		for(GameObjects gameObject : Manager.objectList) {
			if(gameObject.getId() == ID.Player) {
				if(key == KeyEvent.VK_UP)
					//gameObject.setVelY(-5);
					gameObject.setY(gameObject.getY()-Game.boardIndex);
				if(key == KeyEvent.VK_DOWN)
					//gameObject.setVelY(5);
					gameObject.setY(gameObject.getY()+Game.boardIndex);
				if(key == KeyEvent.VK_LEFT)
					//gameObject.setVelX(-5);
					gameObject.setX(gameObject.getX()-Game.boardIndex);
				if(key == KeyEvent.VK_RIGHT)
					//gameObject.setVelX(5);
					gameObject.setX(gameObject.getX()+Game.boardIndex);
			}
		}
		
				
	}
	
	/**
	 * This method makes sure the player stops moving when an arrow key is released
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		
		int key = e.getKeyCode();
		
		for(GameObjects gameObject : Manager.objectList) {
			if(gameObject.getId() == ID.Player) {
				if(key == KeyEvent.VK_UP)
					gameObject.setVelY(0);
				if(key == KeyEvent.VK_DOWN)
					gameObject.setVelY(0);
				if(key == KeyEvent.VK_LEFT)
					gameObject.setVelX(0);
				if(key == KeyEvent.VK_RIGHT)
					gameObject.setVelX(0);
			}
		}
	}

}
