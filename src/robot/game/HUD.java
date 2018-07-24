package robot.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class HUD {
	
	private int score = 0;
	private int level = 0;
	private int stage = 0;
	
	public int getStage() {
		return stage;
	}

	public void setStage(int stage) {
		this.stage = stage;
	}

	
	public int getLevel() {
		return level;
	}
	
	public void tick() {
		
	}
	
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
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public void setScore(int score) {
		this.score = score;
	}

	public int getScore() {
		return score;
	}

}