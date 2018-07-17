package robot.game;

import java.awt.Color;
import java.awt.Graphics;

public class Board extends GameObjects {
	
	public Board(int x, int y, ID id) {
		super(x, y, id);
	}
				
	@Override
	public void tick() {
				
	}
	
	@Override
	public void render(Graphics g) {
		//calculates the correct dimensions for squares on a chessboard
		int x = 0;
		int y = 0;
		int squareHeight = Game.HEIGHT/8;		
		int squareWidth = squareHeight;
				
		//sets the background to a chessboard pattern
		for(int j = 0; j < 8; j++) {
			for(int i = 0; i < 8; i++) {
				if((i + j) % 2 == 0) {
					g.setColor(Color.white);
					g.fillRect(x, y, squareWidth, squareHeight);
				}
				else {
					g.setColor(Color.black);
					g.fillRect(x, y, squareWidth, squareHeight);
				}
				x += squareWidth;
			}
			y += squareHeight;
			x = 0;
		}
	}

	
}
