package robot.game;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Player extends GameObjects {
	
	protected Player(int x, int y, ID id) {
		super(x, y, id);
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		//this makes sure that the player does not go out of the bounds of the board
		x = Game.clamp(x, 8, Game.HEIGHT - 72);
		y = Game.clamp(y, 8, Game.HEIGHT - 72);
	}

	@Override
	public void render(Graphics g) {
		/* Make sure to change the location of the robot to where it is on your hard drive
		 * this can be done by right clicking on the robot.png and selecting properties
		 * copy the file location and paste into the speech marks below. The robot.png
		 * should be located in the robot.game package underneath the classes to your left <------.
		 */
		File imageFile = new File("pictures\\HelensRobot.png");
		//InputStream input = getClass().getResourceAsStream("HelenRobot.png");
		try {
			Image robot = ImageIO.read(imageFile);
			g.drawImage(robot, x, y, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
