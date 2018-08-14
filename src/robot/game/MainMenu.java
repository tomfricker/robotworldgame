package robot.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import npcs.CollectionNPC;
import npcs.MessageNPC;
import npcs.PICKUPID;
import npcs.Pickup;
import robot.game.Game.STATE;

/**
 * This class displays the options for the 5 different levels of game.
 * It extends MouseAdapter so that the different buttons are clickable within
 * the main game window.
 * 
 * It also sets up the initial state of the level once it has been selected
 * by the user.
 * 
 * It also shows the end level screen where the user sees their score and
 * has the option to go back to the main menu to select another level.
 * 
 * @author Robot World Group
 *
 */
public class MainMenu extends MouseAdapter{
	
	//Fields of the MainMenu class
	private Manager manager;
	private SidePanel side;
	private HUD hud;
	private String[] levelNames = { "Data Types", "Classes and Objects", "Collections", "Loops and Methods", "Conditional Statements" };
	
	/**
	 * Constructor of the MainMenu
	 * @param game
	 * @param manager
	 * @param side
	 * @param hud
	 * @param code
	 */
	public MainMenu(Game game, Manager manager, SidePanel side, HUD hud, CodePanel code) {
		this.manager = manager;
		this.side = side;
		this.hud = hud;
	}
	
	/**
	 * This method checks if the gameState is either Menu or End.
	 * If it is in Menu it will check if a certain level button
	 * has been clicked on and set up the objects that begin that
	 * particular level.
	 * 
	 * If the gameState is End then it will check to see if the 
	 * user has clicked on the button to return to the MainMenu
	 * and change the gameState accordingly.
	 */
	public void mousePressed(MouseEvent e) {
		//get coordinates of the mouse when clicked
		int mouseX = e.getX();
		int mouseY = e.getY();
		
		//Check to see if the gameState is Menu
		if(Game.gameState == STATE.Menu) {
			//set up the initial state of the HUD before a level begins
			hud.setLevel(0);
			resetHUD();
			//Starting co-ordinates of the Y-axis for Level 1 button
			int y = 200;
			//level 1 button
			if(mouseOver(mouseX, mouseY, 100, y, 440, 64)) {
				createLevel(1);
			}
			
			//level 2 button
			y += 84;
			if(mouseOver(mouseX, mouseY, 100, y, 440, 64)) {
				createLevel(2);
			}
			
			//level 3 button
			y += 84;
			if(mouseOver(mouseX, mouseY, 100, y, 440, 64)) {
				createLevel(3);
			}
			
			//level 4 button
			y += 84;
			if(mouseOver(mouseX, mouseY, 100, y, 440, 64)) {
				createLevel(4);
			}
			
			//level 5 button
			y += 84;
			if(mouseOver(mouseX, mouseY, 100, y, 440, 64)) {
				createLevel(5);
			}
		}
		else if(Game.gameState == STATE.End) {
			if(mouseOver(mouseX, mouseY, 70, 45, 500, 120)) {
				int level = hud.getLevel();
				if(level != 5) {
					resetHUD();
					int newLevel = hud.getLevel()+1;
					createLevel(newLevel);
				}
				else {
					Game.gameState = STATE.Menu;
				}
			}
		}
		
		
	}
	
	/**
	 * Resets the HUD so that a new level can be set up correctly.
	 */
	public void resetHUD() {
		hud.setScore(0);
		hud.setStage(1);
		hud.setLevelEnd(false);
	}
	
	/**
	 * Sets up the beginning state for the appropriate level
	 */
	public void createLevel(int level) {
		//set the game state
		Game.gameState = STATE.Game;
		//create the level
		switch (level) {
			case 1 : {
				hud.setLevel(1);
				manager.addObject(new Player(Cells.A, Cells.A, ID.Player));
				String npcMessage = "In this first level we are going to learn about data types.";
				manager.addObject(new MessageNPC(Cells.C, Cells.A, ID.NPC, manager, side, hud, npcMessage));
				side.setText("~Welcome to level 1 of Robot World!\n\n"
						+ "~Please move Robbie the Robot around the board using the arrow keys.\n\n"
						+ "~Good luck and enjoy your adventure in Robot World!\n\n");
				break;
			}
			case 2 : {
				hud.setLevel(2);
				//add objects to start of level 2
				manager.addObject(new Player(Cells.A, Cells.A, ID.Player));
				String npcMessage = "Hello! I will be your robot buddy and guide you through this adventure. " 
						+ "I hope we can learn together!\n\nNow go to the apple!\n\n";
				manager.addObject(new MessageNPC(Cells.D, Cells.A, ID.NPC, manager, side, hud, npcMessage));
				side.setText("~Welcome to level 2 of Robot World - All about Classes and Objects!\n\n"
						+ "~Try moving the Robot to the other Robot.\n\n");
				break;
			}
			case 3: {
				hud.setLevel(3);
				//add objects to start of level 3
				manager.addObject(new Player(Cells.B,Cells.A,ID.Player));
				manager.addObject(new CollectionNPC(Cells.A,Cells.H,ID.NPC,"Int",side,hud,1));
				manager.addObject(new CollectionNPC(Cells.H,Cells.H,ID.NPC,"Char",side,hud,2));
				manager.addObject(new CollectionNPC(Cells.H,Cells.A,ID.NPC,"Boolean",side,hud,1));
				manager.addObject(new Pickup(Cells.A,Cells.F,ID.NPC, PICKUPID.Boolean,side, "true"));
				manager.addObject(new Pickup(Cells.B,Cells.G,ID.NPC, PICKUPID.Char,side, "char1"));
				manager.addObject(new Pickup(Cells.A,Cells.A,ID.NPC, PICKUPID.Char,side, "char2"));
				manager.addObject(new Pickup(Cells.A,Cells.G,ID.NPC, PICKUPID.Int,side, "int1"));
				manager.addObject(new Pickup(Cells.A,Cells.D,ID.NPC, PICKUPID.Int,side, "int2"));
				side.setText("~Welcome to level 3 of Robot World - Time to learn about Primitive Types and Collections!\n\n"
						+ "~These are collectors. Try talking to them to see what they want\n\n");
				
				break;
			}
			case 4 : {
				hud.setLevel(4);
				//add objects to start of level 4
				manager.addObject(new Player(Cells.D, Cells.D, ID.Player));
				String npcMessage = "Well Done! Now try moving up.";
				manager.addObject(new MessageNPC(Cells.E, Cells.D, ID.NPC, manager, side, hud, npcMessage));
				side.setText("~Welcome to level 4 of Robot World!\n\n"
						+ "~Try moving the Robot around the board by typing in code, e.g. robot.move(right);, "
						+ "press the enter key and then the Run Code button.\n\n");
				break;
			}
			case 5 : {
				hud.setLevel(5);
				//add objects to start of level 5
				break;
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
	
	/**
	 * Ensures that the MainMenu is constantly being updated from the Game Class
	 */
	public void tick() {
		
	}
	
	/**
	 * Draws all the rectangles and Strings contained within either the Menu screen
	 * or End Game screens dependant on the gameState.
	 * @param g
	 */
	public void render(Graphics g) {
		//set the starting coordinates for each element of the menu
		int x = 70;
		int y = 45;
		
		if(Game.gameState == STATE.Menu) {
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
			font = new Font("Monospaced", Font.BOLD, 22);
			g.setFont(font);
			g.setColor(Color.RED);
			y = 240;
			for(int i = 0; i < 5; i++) {
				g.drawString("Level " + (i+1) + " - " + levelNames[i], 110, y);
				y += 64 + 20;
			}
		}
		else if(Game.gameState == STATE.End) {
			//draw rectangles
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
			
			font = new Font("Monospaced", Font.BOLD, 26);
			g.setFont(font);
			g.setColor(Color.RED);
			if(hud.getLevel() != 5)
				g.drawString("Click here for next level", 120, 150);
			else
				g.drawString("Click here for Main Menu", 120, 150);
			
			//display user score
			g.drawString("Your score was : " + hud.getScore(), 160, 245);
			
		}
	}
	
}
