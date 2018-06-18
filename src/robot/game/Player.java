package robot.game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Player extends GameObjects {
	
	
	
	protected Player(int x, int y, ID id) {
		super(x, y, id);
		
	}


	
	public void tick() {
		if(id == ID.cat) {
			Random rand = new Random(); 
			int randmove = rand.nextInt(2);
			System.out.println(randmove);
			
			
		
		}
		
		if(id == ID.mouse) {
			Random rand = new Random(); 
			int direction = rand.nextInt(1);
			if(direction == 0) {
				x = x + (rand.nextInt(1)*2);
			}
			if(direction == 1) {
				x = x - (rand.nextInt(1)*2);
			}
		
		}
		
	}

	
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x,y,32,32);
		
	}


	

}
