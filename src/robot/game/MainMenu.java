package robot.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

//import DessyLevel.LevelDessyNPC;
//import DessyLevel.LevelDessyNPC1;
import HelenLevel.LevelHelenNPC;
import HelenLevel.LevelHelenNPC1;
import HelenLevel.LevelHelenNPC2;
import TomLevel.LevelTomNPC;
import TomLevel.LevelTomNPC1;
import TomLevel.LevelTomNPC2;
import TomLevel.LevelTomNPC3;

import robot.game.Game.STATE;

public class MainMenu extends MouseAdapter{
	
	private Game game;
	private Manager manager;
	private SidePanel side;
	private HUD hud;
	
	public MainMenu(Game game, Manager manager, SidePanel side, HUD hud, ButtonBar buttons) {
		this.game = game;
		this.manager = manager;
		this.side = side;
		this.hud = hud;
	}
	
	public void mousePressed(MouseEvent e) {
		//get coordinates of the mouse when clicked
		int mouseX = e.getX();
		int mouseY = e.getY();
		
		if(game.gameState == STATE.Menu) {
			hud.setScore(0);
			int y = 200;
			//level 1 button
			if(mouseOver(mouseX, mouseY, 100, y, 440, 64)) {
				game.gameState = STATE.Game;
				hud.setLevel(1);
				manager.addObject(new Player(Cells.B, Cells.A, ID.Player));
				manager.addObject(new LevelTomNPC(Cells.E, Cells.D, ID.NPC, manager, side, hud));
				manager.addObject(new LevelTomNPC1(Cells.H, Cells.A, ID.NPC, manager, side, hud));
				manager.addObject(new LevelTomNPC2(Cells.A, Cells.H, ID.NPC, manager, side, hud));
				manager.addObject(new LevelTomNPC3(Cells.H, Cells.H, ID.NPC, manager, side, hud));
				side.setText("~Welcome to level 1 of Robot World!\n\n"
						+ "~Please move Robbie the Robot around the board using the arrow keys.\n\n"
						+ "~Good luck and enjoy your adventure in Robot World!\n\n");
						
			}
			y += 84;
			//level 2 button
			if(mouseOver(mouseX, mouseY, 100, y, 440, 64)) {
				game.gameState = STATE.Game;
				hud.setLevel(2);
				//add objects to start of level 2
				manager.addObject(new Player(Cells.D, Cells.D, ID.Player));
				manager.addObject(new LevelHelenNPC(Cells.D, Cells.A, ID.NPC, manager, side, hud));
				manager.addObject(new LevelHelenNPC1(Cells.E, Cells.D, ID.NPC, manager, side, hud));
				manager.addObject(new LevelHelenNPC2(Cells.E, Cells.C, ID.NPC, manager, side, hud));
				side.setText("~Welcome to level Helen of Robot World!\n\n"
						+ "~Try moving the Robot around the board by typing in code, e.g. robot.move(right);\n\n");
			}
			
			y += 84;
			//level 3 button
			if(mouseOver(mouseX, mouseY, 100, y, 440, 64)) {
				game.gameState = STATE.Game;
				hud.setLevel(3);
				//add objects to start of level 3
				manager.addObject(new Player(Cells.B, Cells.A, ID.Player));
				//manager.addObject(new LevelDessyNPC(Cells.H, Cells.B, ID.NPC, manager, side, hud));
				//manager.addObject(new LevelDessyNPC1(Cells.E, Cells.E, ID.NPC, manager, side, hud));
				side.setText("~Welcome to level 3 of Robot World - All about Classes and Objects!\n\n"
						+ "~Try moving the Robot to the apple.\n\n");
			}
			
			y += 84;
			//level 4 button
			if(mouseOver(mouseX, mouseY, 100, y, 440, 64)) {
				game.gameState = STATE.Game;
				hud.setLevel(4);
				//add objects to start of level 4
			}
			
			y += 84;
			//level 5 button
			if(mouseOver(mouseX, mouseY, 100, y, 440, 64)) {
				game.gameState = STATE.Game;
				hud.setLevel(5);
				//add objects to start of level 5
			}
		}
		else if(game.gameState == STATE.End) {
			if(mouseOver(mouseX, mouseY, 70, 45, 500, 120)) {
				game.gameState = STATE.Menu;
			}
		}
		
		
	}
	
	/**
	 * Checks to see if mouse is over a set menu item rectangle
	 * @param mouseX
	 * @param mouseY
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @return
	 */
	private boolean mouseOver(int mouseX, int mouseY, int x, int y, int width, int height) {
		if(mouseX > x && mouseX < x + width) {
			if(mouseY > y && mouseY < y + height) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		//set the starting coordinates for each element of the menu
		int x = 70;
		int y = 45;
		
		if(game.gameState == STATE.Menu) {
			//draw title background
			g.setColor(Color.BLACK);
			g.fillRect(x, y, 500, 120);
			g.setColor(Color.WHITE);
			g.drawRect(x, y, 500, 120);
			
			//draw menu item boxes
			y = 200;
			for(int i = 0; i < 5; i++) {
				g.setColor(Color.BLACK);
				g.fillRect(100, y, 440, 64);
				g.setColor(Color.WHITE);
				g.drawRect(100, y, 440, 64);
				y += 64 + 20;
			}
			
			//draw menu title
			Font font = new Font("Monospaced", Font.BOLD, 70);
			g.setFont(font);
			g.setColor(Color.BLUE);
			g.drawString("Robot World", 90, 100);
			
			//draw menu subtitle
			font = new Font("Monospaced", Font.BOLD, 50);
			g.setFont(font);
			g.setColor(Color.WHITE);
			g.drawString("Level Select", 140, 150);
			
			//draw menu item strings
			font = new Font("Monospaced", Font.BOLD, 40);
			g.setFont(font);
			g.setColor(Color.RED);
			y = 245;
			for(int i = 0; i < 5; i++) {
				g.drawString("Level " + (i+1), 240, y);
				y += 64 + 20;
			}
		}
		else if(game.gameState == STATE.End) {
			//draw background
			g.setColor(Color.BLACK);
			g.fillRect(x, y, 500, 120);
			g.setColor(Color.WHITE);
			g.drawRect(x, y, 500, 120);
			
			y = 200;
			g.setColor(Color.BLACK);
			g.fillRect(100, y, 440, 64);
			g.setColor(Color.WHITE);
			g.drawRect(100, y, 440, 64);
			
			//draw text
			Font font = new Font("Monospaced", Font.BOLD, 70);
			g.setFont(font);
			g.setColor(Color.BLUE);
			g.drawString("YOU WIN!", 160, 100);
			
			font = new Font("Monospaced", Font.BOLD, 35);
			g.setFont(font);
			g.setColor(Color.WHITE);
			g.drawString("Return to Main Menu", 120, 150);
			
			g.drawString("Your score was : " + hud.getScore(), 120, 245);
			
		}
	}
	
}
