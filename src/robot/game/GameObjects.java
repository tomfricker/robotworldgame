package robot.game;

import java.awt.Graphics;

public abstract class GameObjects {

	protected static int x; 
	protected static int y;
	
	protected int velY;
	protected int velX;
	
	protected ID id; 

	protected boolean pickUp; 
	
	public GameObjects(int x, int y, ID id) {
		GameObjects.x = x;
		GameObjects.y = y;
		this.id = id;		
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics g);

	public static int getX() {
		return x;
	}

	public static void setX(int x) {
		GameObjects.x = x;
	}

	public static int getY() {
		return y;
	}

	public static void setY(int y) {
		GameObjects.y = y;
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
	
	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}
	
	public boolean getPickUp() {
		return pickUp;
	}
	
	
	
}//end of class
