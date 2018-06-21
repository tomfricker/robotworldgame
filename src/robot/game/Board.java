package robot.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Panel;

import javax.swing.JPanel;

public class Board extends GameObjects{

	public Board(int x, int y, ID id) {
		super(x, y, id);
		// the x and y variables do nothing in this class but they are needed for the constructor at the moment.
	}

	@Override
	public void tick() {
		//does nothing
		
	}

	@Override
	public void render(Graphics g) {
		
		//BOARD------------------------------------------------------
				//size of board
				int boardSquares = 6; //number of squares in each column/row
				//^^ doesn't handle odd numbers properly due to switching of space boolean. Keep this below 42 for the current size of the game or it gets buggy. Tried it at 2000 and it slowed it down A LOT!
				int squareCornerY = 0; //tracks where the squares should start height wise
				boolean space = false; //switches between the square colours
				
				//the board is painted in rows by this for loop.
				for(int i = 0; i<boardSquares+1; i++) {
					
					int squareCornerX = 0; //tracks the start of the squares width wise
					//this for loop paints each square along the current row, switching colour after each is painted
					for(int o = 0; o<boardSquares+1; o++) {
						//changes square colour
						if(space) {g.setColor(Color.white);}
						else{g.setColor(Color.black);}
						//
						g.fillRect(squareCornerX,squareCornerY,(Game.WIDTH-Window.SIDEBARWIDTH)/boardSquares,Game.HEIGHT/boardSquares);
						space = !space; //as the space boolean changes at the end of a row, the next row starts in the opposite colour, hence odd square numbers not working. 
						squareCornerX += (Game.WIDTH-Window.SIDEBARWIDTH)/boardSquares;
					}
				
					squareCornerY += Game.HEIGHT/boardSquares;
					
				}
				//END OF BOARD-----------------------------------------------
		
		//sorry for overriding this Dessy, I've left it in case you want to try and reinstate it in the render method but I wrote
		//the above when my GIT was messing about and I didn't have your board part working properly. 
		/**
		
		 //Make the centre component big.     
        Panel board = new Panel();
        board.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        frame.add(board, BorderLayout.CENTER);
        
	    board.setLayout(new GridLayout(6, 6));
	    
	    //set the separate grids black and white
	    for (int i = 0; i < 6; i++) {
	        for (int j = 0; j < 6; j++) {
	            squares[i][j] = new JPanel();

	            if ((i + j) % 2 == 0) {
	                squares[i][j].setBackground(Color.blue);
	            } 
	            else {
	                squares[i][j].setBackground(Color.white);
	            }   
	            board.add(squares[i][j]);
	        	}
	    	}
		
	}
	
	*/

}
	
}//end of class
