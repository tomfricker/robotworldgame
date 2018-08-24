package robot.game;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *  
 * The Player class is a subclass of GameObjects.
 * It creates an object, renders it on the game board
 * and controls its interactions through the tick method.
 * 
 * 
 * @author Robot World Group
 *
 */
public class Player extends GameObjects {
	
	/**
	 * Constructor for Player
	 * @param x
	 * @param y
	 * @param id
	 */
	protected Player(int x, int y, ID id) {
		super(x, y, id);
	}
	
	/**
	 * This method updates the stage of the Player object
	 * when it moves or interacts with other objects.
	 */
	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		//this makes sure that the player does not go out of the bounds of the board
		x = Game.clamp(x, 8, Game.HEIGHT - 72);
		y = Game.clamp(y, 8, Game.HEIGHT - 72);
	}
	
	/**
	 * This method renders the Player object - shows an 
	 * image of the Player character on the board.
	 */
	@Override
	public void render(Graphics g) {
		File imageFile = new File("pictures\\HelensRobot.png");
		try {
			Image robot = ImageIO.read(imageFile);
			g.drawImage(robot, x, y, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
