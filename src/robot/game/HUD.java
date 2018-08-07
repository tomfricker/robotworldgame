package robot.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 * The HUD (Heads Up Display) has both front end and back end uses.
 * On the front end it displays to the user what stage of what particular
 * level they are playing. It also shows them their score for that level.
 * 
 * On the back end it holds the number of interactions that user has made with
 * NPCs to progress the stage within the Spawner class. It also holds boolean
 * values which will either end the stage or level that a player which can be
 * accessed via getter and setter methods.
 * 
 * @author Robot World Group
 *
 */
public class HUD {
	
	//Fields to display and store information on the score/level/stage
	private int score = 0;
	private int level = 0;
	private int stage = 0;
	
	//Fields to hold interactions and whether a level or stage has ended
	private int interactions = 0;
	private boolean levelEnd = false;
	private boolean stageEnd = false;
	
	/**
	 * Method to continually update the HUD from the Game class.
	 */
	public void tick() {
		
	}
	
	/**
	 * Draws the front end of the HUD so that the user can see their
	 * level stage and score.
	 * @param g
	 */
	public void render(Graphics g) {
		//set background
		int y = (Game.HEIGHT - 40);
		int x = 8;
		g.setColor(Color.BLUE);
		g.fillRect(x, y, 225, 30);

		g.setColor(Color.WHITE);
		g.drawRect(x, y, 225, 30);
		
		//draw score and level
		y += 20;
		x += 10;
		Font font = new Font("Arial", Font.BOLD, 14);
		g.setFont(font);
		g.setColor(Color.WHITE);
		g.drawString("Level: " + level, x, y);
		x += 70;
		g.drawString("Stage: " + stage, x, y);
		x += 70;
		g.drawString("Score: " + score, x, y);

	}
			
	//All getter and setter methods for fields of this class
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public void setScore(int score) {
		this.score = score;
	}

	public int getScore() {
		return score;
	}
	
	public boolean isStageEnd() {
		return stageEnd;
	}

	public void setStageEnd(boolean stageEnd) {
		this.stageEnd = stageEnd;
	}

	public boolean isLevelEnd() {
		return levelEnd;
	}

	public void setLevelEnd(boolean levelEnd) {
		this.levelEnd = levelEnd;
	}

	public int getStage() {
		return stage;
	}

	public void setStage(int stage) {
		this.stage = stage;
	}

	public int getLevel() {
		return level;
	}

	public int getInteractions() {
		return interactions;
	}

	public void setInteractions(int interactions) {
		this.interactions = interactions;
	}

}