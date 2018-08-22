package npcs;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import robot.game.Game;
import robot.game.GameObjects;
import robot.game.HUD;
import robot.game.ID;
import robot.game.Manager;
import robot.game.SidePanel;

/**
 * This class extends GameObjects, it draws a flower on the board.
 * 
 * @author MissH
 *
 */

public class Drawing extends GameObjects {

	Manager manager;
	SidePanel side;
	HUD hud;
	boolean interacted;
	static boolean visibility = false;
	
	/**
	 * Constructor for Drawing
	 * @param x
	 * @param y
	 * @param id
	 * @param manager
	 * @param side
	 * @param hud
	 */
	public Drawing(int x, int y, ID id, Manager manager, SidePanel side, HUD hud) {
		super(x, y, id);
		this.manager = manager;
		this.side = side;
		this.hud = hud;
		interacted = false;
	}
	
	/**
	 * When it is interacted with it will call the interact method
	 */
	@Override
	public void tick() {
		if(interacted == false)
			interact();
	}

	/**
	 * Draws the flower
	 */
	@Override
	public void render(Graphics g) {
		if(getVisibility() == true) {
			//set background
			int a = (Game.HEIGHT - 320);
			int b = (Game.WIDTH - 540);
			g.setColor(Color.YELLOW);
			g.fillRect(b, a, 80*4, 80*4);
		
			//draws flower				
			int p = Game.HEIGHT - 190;
			int q = Game.WIDTH - 470;
			g.setColor(Color.MAGENTA);
			g.fillOval(p, q, 50, 80);
		
			Graphics2D g2d = (Graphics2D)g;
			AffineTransform old = g2d.getTransform();
		
			for(int i = 1; i < 5; i++) {
				g2d.rotate(Math.toRadians(-72), p, q);
				p -= 110;
				q -= 40;
				g2d.setColor(Color.MAGENTA);
				g2d.fillOval(p, q, 50, 80);
			}
			g2d.setTransform(old);	
		}
	}
	
	/**
	 * If visibility is true (code drawFlower(); has been entered) the score will increase and the level will end.
	 */
	public void interact() {
		if(getVisibility() == true) {
			hud.setScore(hud.getScore() + 50);
			hud.setLevelEnd(true);
		}
		interacted = true;			
	}
	
	/**
	 * Returns the boolean variable visibility
	 * @return true or false
	 */
	public boolean getVisibility() {
		return visibility;
	}
	
	/**
	 * Sets the boolean variable visibility to true or false
	 * @param newvisibility The new value of visibility
	 */
	public static void setVisibility(boolean newvisibility) {
		visibility = newvisibility;
	}
	
}
