package robot.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import npcs.CollectionNPC;
import npcs.OperatorsNPC;
import npcs.MessageNPC;
import npcs.PICKUPID;
import npcs.Pickup;
import npcs.StageEndNPC;
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
	private String[] levelNames = {"Data Types", "Conditional Statements", "Collections", "Loops and Methods", "Classes and Objects"};
	
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
	 * user has clicked on the button to go to next Level 
	 * and change the gameState accordingly. If the gameState is 
	 * End of Level 2, then an additional button will appear,
	 * which will display the source code of a class.
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
				int level = HUD.getLevel();
				if(level != 5) {
					resetHUD();
					int newLevel = HUD.getLevel()+1;
					createLevel(newLevel);
				}
			}
			else if(mouseOver(mouseX, mouseY, 100, 284, 440, 64)) {
					int currentLevel = HUD.getLevel();
					if(currentLevel == 5) {
						new SourceCodeWindow();
						
					}
			}
			else {
				Game.gameState = STATE.Menu;
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
						+ "~Good luck and enjoy your adventure in Robot World!\n\n"
						+ "~If you get stuck use the Help Button.\n\n");
				break;
			}
			
			case 2 : {
				//add objects to start of level 2
				hud.setLevel(2);
				manager.addObject(new Player(Cells.A, Cells.A, ID.Player));
				//displays message when player interacts with an OperatorsNPC	
				String npcMessage = "Hi! Java uses relational operators to compare two values.\n\n"
						+ "It then checks to see if they are equal\n\n"
						+ "or if a value is greater than, less than or not equal to another value.\n\n"
						+ "The result of this comparison will be a boolean value which is either: True or False.\n\n"
						+ "We will learn more about Booleans in the next stage.\n\n"
						+ "Use the right arrow key to speak to Robbie's friend. They will be happy to teach you more!\n\n"
						+ "See you in the next stage!\n\n";
				manager.addObject(new MessageNPC(Cells.B, Cells.A, ID.NPC, manager, side, hud, npcMessage));
		        String npcMessage2 = "Hi! I'm Greg the Great. I love the > operator.\n\n"
		        		+ "make Robbie find all the values that are greater than 5 and bring them back to me\n\n"
		        		+ "Once you've brought them back, press the down key to visit Lessley!\n\n"
		        		+ "Hint: This means any number higher than 5.\n\n";
		         manager.addObject(new OperatorsNPC(Cells.H, Cells.A, ID.NPC,"GreaterThan", side,hud, npcMessage2));
		       String npcMessage3 = "Hi I'm Lessley, I am looking for all the values less than 5.\n\n"
		       		+ "Collect all the values less than 5 and bring them back to me!\n\n"
		       		+ "Once you've done that press the left key to visit Eddie Equal!\n\n"
		          + "Hint: This means any number lower than 5.\n\n";
				manager.addObject(new OperatorsNPC(Cells.H, Cells.H, ID.NPC,"LessThan", side, hud, npcMessage3));
			  String npcMessage4 = "Hi I'm Eddie Equal, I am looking for all the values that are equal to 5.\n\n"
			  		+ "Collect all the values equal to 5 and bring them back to me\n\n"
			  		+ "Once you've done that press the up arrow to visit Nora NotEqual!\n\n"
				 + "Hint: There is only 1 value.\n\n";
				manager.addObject(new OperatorsNPC(Cells.A, Cells.H, ID.NPC,"Equal", side, hud, npcMessage4));
		        String question = "Hi I'm Nora NotEqual. In Java when you use the != operator, it means that two\n\n"
		        		+ "values are not the same. Let's see if you understand this. Answer this question to \n\n"
		        		+ "to move onto the next stage:\n"
		        		+ " int robot x = 1;"
		        		+ " int robot y  = 10;\n\n"
		        		+ " System.out.println(x != y);\n"
		        		+ "Type whether you think the above print statement would give true or false\n\n"
		        		+ "Hint: 2 != 2 would give false ";
		        String answer = "true";
		        manager.addObject(new StageEndNPC(Cells.A, Cells.D, ID.NPC, manager, side, hud, question, answer));
		        manager.addObject(new Pickup(Cells.C, Cells.G,ID.NPC, PICKUPID.Less,side, "One"));
		        manager.addObject(new Pickup(Cells.B, Cells.F,ID.NPC, PICKUPID.Less,side, "Two"));
		        manager.addObject(new Pickup(Cells.C, Cells.D,ID.NPC, PICKUPID.Less,side, "Three"));
		        manager.addObject(new Pickup(Cells.E, Cells.F,ID.NPC, PICKUPID.Less,side, "Four"));
		        manager.addObject(new Pickup(Cells.G, Cells.F,ID.NPC, PICKUPID.Equal,side, "Five"));
		        manager.addObject(new Pickup(Cells.B, Cells.C,ID.NPC, PICKUPID.Greater,side, "Six"));
		        manager.addObject(new Pickup(Cells.D, Cells.B,ID.NPC, PICKUPID.Greater,side, "Seven"));
		        manager.addObject(new Pickup(Cells.D, Cells.D,ID.NPC, PICKUPID.Greater,side, "Eight"));
		        manager.addObject(new Pickup(Cells.H, Cells.E,ID.NPC, PICKUPID.Greater,side, "Nine"));
		        manager.addObject(new Pickup(Cells.H, Cells.C,ID.NPC, PICKUPID.Greater,side, "Ten"));
		         	side.setText("Welcome to level 2 of Robot World!\n\n"
						+ "~Booleans and Conditional Statements are very important in Java\n\n"
						+ "~But first you need to know about Relational Operators before you can understand them.\n\n"
						+ "~By the end of this level you will know about:\n\n"
						+ "*Greater Than (>)\n\n"
						+ "*Less than (<)\n\n"
						+ "*Equal to (==)\n\n"
						+ "*And finally Not equal to operators. (!=)\n\n"
						+ "Press the right arrow key to read about them.\n\n"
						+ "~If you need help use the Help Button on the left hand side.\n\n");
	
			
				
				
				break;
			}
			case 3: {
				hud.setLevel(3);
				//add objects to start of level 3
				manager.addObject(new Player(Cells.B,Cells.A,ID.Player));
				manager.addObject(new CollectionNPC(Cells.B,Cells.G,ID.NPC,"Int",side,hud));//
				manager.addObject(new CollectionNPC(Cells.H,Cells.E,ID.NPC,"Char",side,hud));//
				manager.addObject(new CollectionNPC(Cells.H,Cells.A,ID.NPC,"Boolean",side,hud));//
				manager.addObject(new Pickup(Cells.A,Cells.G,ID.NPC, PICKUPID.Boolean,side, "true"));//
				manager.addObject(new Pickup(Cells.D,Cells.C,ID.NPC, PICKUPID.Char,side, "char1"));//
				manager.addObject(new Pickup(Cells.F,Cells.A,ID.NPC, PICKUPID.Char,side, "char2"));//
				manager.addObject(new Pickup(Cells.F,Cells.D,ID.NPC, PICKUPID.Int,side, "int1"));//
				manager.addObject(new Pickup(Cells.H,Cells.H,ID.NPC, PICKUPID.Int,side, "int2"));//
				side.setText("~Welcome to level 3 of Robot World - Time to learn about Primitive Types and Collections!\n\n"
						+ "~These are collections. They are a useful way of organising data.\n\n"
						+"try talking to them to work out how to get the messy data into an organised state.\n\n ");
				
				break;
			}
			case 4 : {
				hud.setLevel(4);
				//add objects to start of level 4
				manager.addObject(new Player(Cells.D, Cells.D, ID.Player));
				String npcMessage = "Well Done! Now try moving up.";
				manager.addObject(new MessageNPC(Cells.E, Cells.D, ID.NPC, manager, side, hud, npcMessage));
				side.setText("~Welcome to level 4 of Robot World!\n\n"
						+ "~Try moving the Robot around the board by typing in code.\n\n"
						+ "~First type robot.move(right); and then press the Run Code button.\n\n"
						+ "~Go to each message robot as it appears to talk to it.\n\n"
						+ "~To learn how to use the input panel press the Help button or type help.\n\n");
				break;
			}
			case 5 : {
				hud.setLevel(5);
				//add objects to start of level 5
				manager.addObject(new Player(Cells.A, Cells.A, ID.Player));
				String npcMessage = "Hello! I will be your robot buddy and guide you through this adventure. " 
						+ "I hope we can learn together!\n\nNow go to the apple!\n\n";
				manager.addObject(new MessageNPC(Cells.D, Cells.A, ID.NPC, manager, side, hud, npcMessage));
				side.setText("~Welcome to Level 5 of Robot World - All about Classes and Objects!\n\n"
						+ "~Try moving the Robot to the other Robot.\n\n");
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
	 * or End Game screens dependent on the gameState.
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
			if(HUD.getLevel() != 5)
				g.drawString("Click here for next level", 120, 150);
			else
				g.drawString("Click here for Main Menu", 120, 150);
			
			//display user score
			g.drawString("Your score was: " + hud.getScore() + "/" + hud.getMaxScore(HUD.getLevel()), 160, 245);
			
			//display button for Robot source code at end of level 5
			if (HUD.getLevel()==5) {
				g.setColor(Color.BLACK);
				g.fillRect(100, 284, 440, 64);
				g.setColor(Color.WHITE);
				g.drawRect(100, 284, 440, 64);
				
				font = new Font("Monospaced", Font.BOLD, 25);
				g.setFont(font);
				g.setColor(Color.WHITE);
				g.drawString("See Robot Class Source Code", 120, 329);
			}
			
		}
	}
	
}
