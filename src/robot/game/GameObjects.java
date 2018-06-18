package robot.game;

import java.awt.Graphics;

public abstract class GameObjects {

	protected int x; 
	protected int y;
	
	protected int velY;
	protected int velX;
	
	protected ID id; 
	
	protected boolean pickUp; 
	
	public GameObjects(int x, int y, ID id) {
		this.x = x;
		this.y = y;
		this.id = id;		
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics g);

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getVelY() {
		return velY;
	}

	public void setVelY(int velY) {
		this.velY = velY;
	}

	public int getVelX() {
		return velX;
	}

	public void setVelX(int velX) {
		this.velX = velX;
	}
	
	public boolean getPickUp() {
		return pickUp;
	}
	
	
	
}//end of class
