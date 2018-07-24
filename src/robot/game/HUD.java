package robot.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class HUD {
	
	private int score = 0;
	private int level = 0;
	
	public int getLevel() {
		return level;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		Font font = new Font("Monospaced", Font.PLAIN, 12);
		g.setFont(font);
		g.setColor(Color.BLACK);
		g.drawString("Score: " + score, 10, 30);
		g.drawString("Level: " + level, 10, 45);
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