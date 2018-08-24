package robot.game;

/**
 * This class sets squares to A-H.
 * It also holds the methods to increase and decrease a square.
 * 
 * @author MissH
 *
 */
public class Cells {

	/**
	 * Sets cells of board to letters
	 */
	static int A = 8 + Game.boardIndex*0;
	static int B = 8 + Game.boardIndex*1;
	static int C = 8 + Game.boardIndex*2;
	static int D = 8 + Game.boardIndex*3;
	static int E = 8 + Game.boardIndex*4;
	static int F = 8 + Game.boardIndex*5;
	static int G = 8 + Game.boardIndex*6;
	static int H = 8 + Game.boardIndex*7;
	
	/**
	 * Gives the letter after the currentLetter
	 * @param currentLetter Current x or y-axis
	 * @return forward one letter
	 */
	public static int incrementCell(int currentLetter) {
		int nextLetter = 0;
		if (currentLetter == A) {
			nextLetter = B;
		}
		else if(currentLetter == B) {
			nextLetter = C;
		}
		else if(currentLetter == C) {
			nextLetter = D;
		}
		else if(currentLetter == D) {
			nextLetter = E;
		}
		else if(currentLetter == E) {
			nextLetter = F;
		}
		else if(currentLetter == F) {
			nextLetter = G;
		}
		else if(currentLetter == G) {
			nextLetter = H;
		}
		else if(currentLetter == H) {
			nextLetter = H;
			SidePanel.addText("~Don't go off the board! \n\n");
		}
		return nextLetter;
	}
	
	/**
	 * Gives the letter before the currentLetter
	 * @param currentLetter Current x or y-axis
	 * @return back one letter
	 */
	public static int decrementCell(int currentLetter) {
		int beforeLetter = 0;
		if (currentLetter == A) {
			beforeLetter = A;
			SidePanel.addText("~Don't go off the board! \n\n");
		}
		else if(currentLetter == B) {
			beforeLetter = A;
		}
		else if(currentLetter == C) {
			beforeLetter = B;
		}
		else if(currentLetter == D) {
			beforeLetter = C;
		}
		else if(currentLetter == E) {
			beforeLetter = D;
		}
		else if(currentLetter == F) {
			beforeLetter = E;
		}
		else if(currentLetter == G) {
			beforeLetter = F;
		}
		else if(currentLetter == H) {
			beforeLetter = G;
		}
		return beforeLetter;
	}
	
	
}
