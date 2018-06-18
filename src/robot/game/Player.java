package robot.game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Player extends GameObjects {
	
	
	
	protected Player(int x, int y, ID id) {
		super(x, y, id);
		
	}


	
	public void tick() {
		if(id == ID.mouse) {
			Random rand = new Random(); 
			int randmove1 = rand.nextInt(3);
			int randmove2 = rand.nextInt(3);
			int moveVal1 = (1 -randmove1) * 10;
			int moveVal2 = (1 - randmove2) * 10;
			if(y + moveVal1 < 0) {y = 1;}
			else if(y + 32 +moveVal1 > Game.HEIGHT ) {y = Game.HEIGHT - 32;}
			else{y += moveVal1;}
			
			if(x + moveVal2 < 0) {x = 1;}
			else if(x + 32 + moveVal1 > Game.WIDTH ) {x = Game.WIDTH - 32;}
			else{x += moveVal2;}
			
		}
		
		if(id == ID.cat) {
			Random rand = new Random(); 
			int randmove1 = rand.nextInt(3);
			int randmove2 = rand.nextInt(3);
			int moveVal1 = (1 -randmove1) * 3;
			int moveVal2 = (1 - randmove2) * 3;
			if(y + moveVal1 < 0) {y = 1;}
			else if(y + 32 +moveVal1 > Game.HEIGHT ) {y = Game.HEIGHT - 32;}
			else{y += moveVal1;}
			
			if(x + moveVal2 < 0) {x = 1;}
			else if(x + 32 + moveVal1 > Game.WIDTH ) {x = Game.WIDTH - 32;}
			else{x += moveVal2;}
		
		}
		
	}

	
	public void render(Graphics g) {
		if(id ==ID.mouse) {
		g.setColor(Color.white);}
		if(id ==ID.cat) {
			g.setColor(Color.black);}
		g.fillRect(x,y,32,32);
		
	}


	

}
