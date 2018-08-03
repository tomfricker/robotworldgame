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

public class Drawing extends GameObjects {

	Manager manager;
	SidePanel side;
	HUD hud;
	boolean interacted;
	static boolean visibility = false;
	
	public Drawing(int x, int y, ID id, Manager manager, SidePanel side, HUD hud) {
		super(x, y, id);
		this.manager = manager;
		this.side = side;
		this.hud = hud;
		interacted = false;
	}
	
	@Override
	public void tick() {
		if(interacted == false)
			interact();
	}

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
	
	public void interact() {
		if(getVisibility() == true) {
			hud.setScore(hud.getScore() + 50);
			hud.setLevelEnd(true);
		}
		interacted = true;			
	}
	
	public boolean getVisibility() {
		return visibility;
	}
	
	public static void setVisibility(boolean newvisibility) {
		visibility = newvisibility;
	}
	
}
