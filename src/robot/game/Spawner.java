package robot.game;

import npcs.Flower;
import npcs.LevelEndNPC;
import npcs.MessageNPC;
import npcs.StageEndNPC;

public class Spawner {
	
	private Manager manager;
	private HUD hud;
	private SidePanel side;
	
	public Spawner(Manager manager, HUD hud, SidePanel side) {
		this.manager = manager;
		this.hud = hud;
		this.side = side;
	}
	
	public void tick() {
		//get the level and create objects once certain criteria is met
		int stage = hud.getStage();
		switch(hud.getLevel()) {
			//create the objects of level 1 once you reach a certain score
			case 1: if(stage == 1) levelOneStageOne();
			else if(stage == 2) levelOneStageTwo();

			//level 2
			case 2: if(stage == 1) levelTwoStageOne();
			else if(stage == 2) levelTwoStageTwo();
			else if(stage == 3) levelTwoStageThree();
			
			//level 3
			//case 3:
			//level 4
			//case 4:
			//level 5
			//case 5:
		}
	}
	
	/**
	 * Creates the objects for the first stage of level 1 as you progress in the level
	 */
	public void levelOneStageOne() {
		int interactions = hud.getInteractions();
		if(interactions == 1) {
			String message = "A line of code usually ends with ;";
			manager.addObject(new MessageNPC(Cells.A, Cells.G, ID.NPC, manager, side, hud, message));
			hud.setInteractions(interactions + 1);
		}
		else if(interactions == 3) {
			String message = "An example of a number variable declaration is:\n\n"
					+ "int y = 12;";
			manager.addObject(new MessageNPC(Cells.H, Cells.G, ID.NPC, manager, side, hud, message));
			hud.setInteractions(interactions + 1);
		}
		else if(interactions == 5) {
			String question = "Please declare the number of robots on the board that look just like me.\n"
					+ "I need to be declared in a variable called x";
			String answer = "int x = 4;";
			manager.addObject(new StageEndNPC(Cells.E, Cells.D, ID.NPC, manager, side, hud, question, answer));
			hud.setInteractions(0);
		}
	}
	
	/**
	 * Sets up stage 2 of level 1 and adds NPCs as necessary
	 */
	public void levelOneStageTwo() {
		int interactions = hud.getInteractions();
		if(hud.isStageEnd() == true) {
			Manager.clearAll();
			SidePanel.addText("~Congratulations you completed Stage 1\n\n"
					+ "~Now try stage 2.\n\n"
					+ "~Good luck!\n\n");
			manager.addObject(new Player(Cells.A, Cells.A, ID.Player));
			String message1 = "In Java, and most programming languages, a word, sentence or bunch of characters is called a String.\n\n(HINT: Notice the capitol letter)";
			manager.addObject(new MessageNPC(Cells.H, Cells.A, ID.NPC, manager, side, hud, message1));
			hud.setStageEnd(false);
		}
		if(interactions == 1) {
			String message2 = "When writing a String you must wrap it with inverted commas like:\n\n"
					+ "\"This is a string\"";
			manager.addObject(new MessageNPC(Cells.C, Cells.D, ID.NPC, manager, side, hud, message2));
			hud.setInteractions(interactions + 1);
		}
		else if(interactions == 3) {
			String message3 = "Just like an int a String must have a variable name";
			manager.addObject(new MessageNPC(Cells.B, Cells.F, ID.NPC, manager, side, hud, message3));
			hud.setInteractions(interactions + 1);
		}
		else if(interactions == 5) {
			String message4 = "Variables can have any name as long as it starts with a lowercase letter.\n\n"
					+ "e.g int robots = 4;";
			manager.addObject(new MessageNPC(Cells.G, Cells.H, ID.NPC, manager, side, hud, message4));
			hud.setInteractions(interactions + 1);
		}
		else if(interactions == 7) {
			String question = "Please declare a String called robot that is \"Robot\"";
			String answer = "String robot = \"Robot\";";
			manager.addObject(new LevelEndNPC(Cells.E, Cells.D, ID.NPC, manager, side, hud, question, answer));
			hud.setInteractions(0);
		}
	}
	
	public void levelTwoStageOne() {
		int interactions = hud.getInteractions();
		if(interactions == 1) {
			String message = "Well Done! Try picking up the flower.";
			manager.addObject(new MessageNPC(Cells.E, Cells.C, ID.NPC, manager, side, hud, message));
			hud.setInteractions(interactions + 1);
		 }
		else if(interactions == 3) {
			manager.addFlower(new Flower(Cells.E, Cells.A, ID.Flower, manager, side, hud));
			hud.setInteractions(interactions + 1);
		}
		else if(interactions == 5) {
			String question = "What code did you use to make the robot pickup the flower?";
			String answer = "robot.pickup(flower);";
			manager.addObject(new StageEndNPC(Cells.D, Cells.A, ID.NPC, manager, side, hud, question, answer));
			hud.setInteractions(0);
		 }
	}
	
	/**
	 * Sets up stage 2 of level 2 and adds NPCs as necessary
	 */
	public void levelTwoStageTwo() {
		int interactions = hud.getInteractions();
		if(hud.isStageEnd() == true) {
			Manager.clearAll();
			SidePanel.addText("~Congratulations you completed Stage 1\n\n"
					+ "~Now try stage 2.\n\n"
					+ "~Good luck!\n\n");
			manager.addObject(new Player(Cells.A, Cells.A, ID.Player));
			String message1 = "If you want to repeat some code you can use loops.";
			manager.addObject(new MessageNPC(Cells.H, Cells.A, ID.NPC, manager, side, hud, message1));
			hud.setStageEnd(false);
		}
		if(interactions == 1) {
			String message2 = "There are three types of loops: for each, while and for.";
			manager.addObject(new MessageNPC(Cells.C, Cells.D, ID.NPC, manager, side, hud, message2));
			hud.setInteractions(interactions + 1);
		}
		else if(interactions == 3) {
			String message3 = "The for each loop has the following form:\n" 
					+ "for(ElementType element : collection) {\n"
					+ "loop body\n"
					+ "}";
			manager.addObject(new MessageNPC(Cells.B, Cells.F, ID.NPC, manager, side, hud, message3));
			hud.setInteractions(interactions + 1);
		}
		else if(interactions == 5) {
			String message4 = "For example for each element flower of type Flower in the collection of flowers, pick up the flower.\n"
					+ "for(Flower flower : flowers) {\n"
					+ "pickup(flower);\n"
					+ "}";
			manager.addObject(new MessageNPC(Cells.G, Cells.H, ID.NPC, manager, side, hud, message4));
			hud.setInteractions(interactions + 1);
		}
		else if(interactions == 5) {
			String message4 = "MESSAGE";
			manager.addObject(new MessageNPC(Cells.G, Cells.H, ID.NPC, manager, side, hud, message4));
			hud.setInteractions(interactions + 1);
		}
		else if(interactions == 7) {
			String question = "QUESTION";
			String answer = "ANSWER";
			manager.addObject(new StageEndNPC(Cells.E, Cells.D, ID.NPC, manager, side, hud, question, answer));
			hud.setInteractions(0);
		}
	}
	
	public void levelTwoStageThree() {
		int interactions = hud.getInteractions();
		if(hud.isStageEnd() == true) {
			Manager.clearAll();
			SidePanel.addText("~Congratulations you completed Stage 2\n\n"
					+ "~Now try stage 3.\n\n"
					+ "~Good luck!\n\n");
			manager.addObject(new Player(Cells.A, Cells.A, ID.Player));
			String message1 = "MESSAGE";
			manager.addObject(new MessageNPC(Cells.H, Cells.A, ID.NPC, manager, side, hud, message1));
			hud.setStageEnd(false);
		}
		if(interactions == 1) {
			String message2 = "Note that ++ is short hand for adding 1.";
			manager.addObject(new MessageNPC(Cells.C, Cells.D, ID.NPC, manager, side, hud, message2));
			hud.setInteractions(interactions + 1);
		}
		else if(interactions == 3) {
			String message3 = "The for each loop has the following form:\n" 
					+ "while(boolean condition) {\n"
					+ "loop body\n"
					+ "}";
			manager.addObject(new MessageNPC(Cells.B, Cells.F, ID.NPC, manager, side, hud, message3));
			hud.setInteractions(interactions + 1);
		}
		else if(interactions == 5) {
			String message4 = "For example while the flowers position in the collection of flowers is less than the size of the collection, pick up the flower.\n"
					+ "int flowerIndex = 0;\n"
					+ "while(flowerIndex < flowers.size()) {\n"
					+ "pickup(flower);\n"
					+ "flowerIndex ++;\n"
					+ "}";
			manager.addObject(new MessageNPC(Cells.G, Cells.H, ID.NPC, manager, side, hud, message4));
			hud.setInteractions(interactions + 1);
		}
		else if(interactions == 5) {
			String message4 = "MESSAGE";
			manager.addObject(new MessageNPC(Cells.G, Cells.H, ID.NPC, manager, side, hud, message4));
			hud.setInteractions(interactions + 1);
		}
		else if(interactions == 7) {
			String question = "QUESTION";
			String answer = "ANSWER";
			manager.addObject(new LevelEndNPC(Cells.E, Cells.D, ID.NPC, manager, side, hud, question, answer));
			hud.setInteractions(0);
		}
	}


}
