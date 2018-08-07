package robot.game;

import java.io.File;

import npcs.Drawing;
import npcs.Flower;
import npcs.MessageNPC;
import npcs.MultipleQuestionNPC;
import npcs.QuestionNPC;
import npcs.StageEndNPC;

/**
 * This class constantly checks the HUD class to see if certain criteria have been met
 * in order to add objects to the current level and stage that the user is playing.
 * 
 * @author Robot World Group
 *
 */
public class Spawner {
	
	//Fields of Spawner class
	private Manager manager;
	private HUD hud;
	private SidePanel side;
	
	/**
	 * Constructor creates the Spawner class.
	 * @param manager
	 * @param hud
	 * @param side
	 */
	public Spawner(Manager manager, HUD hud, SidePanel side) {
		this.manager = manager;
		this.hud = hud;
		this.side = side;
	}
	
	/**
	 * This method constantly checks to see what level the user is on and,
	 * depending on what stage they are on, execute the corresponding method
	 * within this class.
	 */
	public void tick() {
		//Get the stage the user is on
		int stage = hud.getStage();
		//Get the level the user is on
		switch(hud.getLevel()) {
			//Execute the method for the correct level/stage
			//level 1
			case 1: if(stage == 1) levelOneStageOne();
			else if(stage == 2) levelOneStageTwo();
			else if(stage == 3) levelOneStageThree();
			else if(stage == 4) levelOneStageFour();

			//level 2
			case 2: if(stage == 1) levelTwoStageOne();
			else if(stage == 2) levelTwoStageTwo();
			else if(stage == 3) levelTwoStageThree();
			else if(stage == 4) levelTwoStageFour();
			else if(stage == 5) levelTwoStageFive();
			
			//level 3
			case 3: if(stage == 1) levelThreeStageOne();
			else if(stage == 2) levelThreeStageTwo();
			else if(stage == 3) levelThreeStageThree();
			else if(stage == 4) levelThreeStageFour();
			else if(stage == 5) levelThreeStageFive();
			
			//level 4
			//case 4:
			//level 5
			//case 5:
		}
	}
	
	/**
	 * Creates the objects for the first stage of level 1 as you progress in the level.
	 * The number of interactions is executed using odd numbers to ensure that an
	 * object is only added once to the manager.
	 */
	public void levelOneStageOne() {
		//Get the number of interactions
		int interactions = hud.getInteractions();
		//Add the second NPC once the first has been interacted with
		if(interactions == 1) {
			String message = "There are many different types of data types including, int, double, float, "
					+ "long, boolean and char.";
			manager.addObject(new MessageNPC(Cells.E, Cells.A, ID.NPC, manager, side, hud, message));
			//Add an extra interaction to stop the addition of further objects from this method
			hud.setInteractions(interactions + 1);
		}
		//Add the NPCs one at a time for the rest of the stage
		else if(interactions == 3) {
			String message = "To use data within our program we need to store it in a variable.";
			manager.addObject(new MessageNPC(Cells.G, Cells.A, ID.NPC, manager, side, hud, message));
			hud.setInteractions(interactions + 1);
		}
		else if(interactions == 5) {
			String message = "We decide what name to give our variable and begin with a lowercase letter.\n\n"
					+ "e.g. robots, squares, x or y.";
			manager.addObject(new MessageNPC(Cells.H, Cells.C, ID.NPC, manager, side, hud, message));
			hud.setInteractions(interactions + 1);
		}
		else if(interactions == 7) {
			String message = "To assign data to our variable we use =";
			manager.addObject(new MessageNPC(Cells.F, Cells.C, ID.NPC, manager, side, hud, message));
			hud.setInteractions(interactions + 1);
		}
		else if(interactions == 9) {
			String message = "Finally we use ; to finish our line of code.";
			manager.addObject(new MessageNPC(Cells.D, Cells.C, ID.NPC, manager, side, hud, message));
			hud.setInteractions(interactions + 1);
		}
		else if(interactions == 11) {
			String question = "In maths, an integer is any whole number. Which data type\n"
					+ "do you think we would use to store an integer?\n\n"
					+ "int, double, float, long, boolean or char.";
			String answer = "int";
			manager.addObject(new StageEndNPC(Cells.B, Cells.C, ID.NPC, manager, side, hud, question, answer));
			hud.setInteractions(0);
		}
	}
	
	/**
	 * Creates the objects for the first stage of level 1 as you progress in the level.
	 * The number of interactions is executed using odd numbers to ensure that an
	 * object is only added once to the manager.
	 */
	public void levelOneStageTwo() {
		int interactions = hud.getInteractions();
		if(hud.isStageEnd() == true) {
			Manager.clearAll();
			SidePanel.addText("~Congratulations you completed Stage 1\n\n"
					+ "~Now try stage 2.\n\n"
					+ "~Good luck!\n\n");
			manager.addObject(new Player(Cells.A, Cells.A, ID.Player));
			String message1 = "In the last stage we learnt about data types, now we are going try our first line of code.";
			manager.addObject(new MessageNPC(Cells.A, Cells.D, ID.NPC, manager, side, hud, message1));
			hud.setStageEnd(false);
		}
		if(interactions == 1) {
			String message = "Remeber we need a data type, a variable name, an assignment =, the data and finally a ;";
			manager.addObject(new MessageNPC(Cells.A, Cells.F, ID.NPC, manager, side, hud, message));
			//Add an extra interaction to stop the addition of further objects from this method
			hud.setInteractions(interactions + 1);
		}
		//Add the NPCs one at a time for the rest of the stage
		else if(interactions == 3) {
			String message = "An example of a number variable declaration is:\n\n"
					+ "int squares = 64;";
			manager.addObject(new MessageNPC(Cells.B, Cells.G, ID.NPC, manager, side, hud, message));
			hud.setInteractions(interactions + 1);
		}
		else if(interactions == 5) {
			for(int i = 0; i < 8; i++) {
				int cell = 8 + Game.boardIndex*i;
				manager.addObject(new MessageNPC(cell, Cells.A, ID.NPC, manager, side, hud, null));
			}
			String question = "Please declare the number of robots on the top line of the board.\n"
					+ "I need to be declared in a variable called robots";
			String answer = "int robots = 8;";
			manager.addObject(new StageEndNPC(Cells.D, Cells.G, ID.NPC, manager, side, hud, question, answer));
			hud.setInteractions(0);
		}
	}
	
	/**
	 * Sets up stage 3 of level 1 and adds NPCs as necessary. Works in the same way as
	 * levelOneStageOne()
	 */
	public void levelOneStageThree() {
		int interactions = hud.getInteractions();
		if(hud.isStageEnd() == true) {
			Manager.clearAll();
			SidePanel.addText("~Congratulations you completed Stage 2\n\n"
					+ "~Now try stage 3.\n\n"
					+ "~Good luck!\n\n");
			manager.addObject(new Player(Cells.A, Cells.A, ID.Player));
			String message1 = "In Java, and most programming languages, a word, sentence or bunch of characters is called a String.\n\n(HINT: Notice the capital letter)";
			manager.addObject(new MessageNPC(Cells.C, Cells.C, ID.NPC, manager, side, hud, message1));
			hud.setStageEnd(false);
		}
		if(interactions == 1) {
			String message2 = "When writing a String you must wrap it with inverted commas like:\n\n"
					+ "\"This is a string\"";
			manager.addObject(new MessageNPC(Cells.E, Cells.E, ID.NPC, manager, side, hud, message2));
			hud.setInteractions(interactions + 1);
		}
		else if(interactions == 3) {
			String message3 = "Just like an int a String must have a variable name";
			manager.addObject(new MessageNPC(Cells.G, Cells.G, ID.NPC, manager, side, hud, message3));
			hud.setInteractions(interactions + 1);
		}
		else if(interactions == 5) {
			String message4 = "Remember the order, data type, variable name, assignment, data and ;";
			manager.addObject(new MessageNPC(Cells.F, Cells.H, ID.NPC, manager, side, hud, message4));
			hud.setInteractions(interactions + 1);
		}
		else if(interactions == 7) {
			String question = "Please declare a String called robot that is \"Robot\"";
			String answer = "String robot = \"Robot\";";
			manager.addObject(new StageEndNPC(Cells.D, Cells.H, ID.NPC, manager, side, hud, question, answer));
			hud.setInteractions(0);
		}
	}
	
	/**
	 * Sets up stage 2 of level 1 and adds NPCs as necessary. Works in the same way as
	 * levelOneStageOne()
	 */
	public void levelOneStageFour() {
		int interactions = hud.getInteractions();
		if(hud.isStageEnd() == true) {
			Manager.clearAll();
			SidePanel.addText("~Congratulations you completed Stage 3\n\n"
					+ "~Now try stage 4.\n\n"
					+ "~Good luck!\n\n");
			manager.addObject(new Player(Cells.A, Cells.A, ID.Player));
			String message1 = "If we want to do arithmetic on numbers we can use + - / or * on two variables, such as:\n\n"
					+ "int squares = rows * columns;";
			manager.addObject(new MessageNPC(Cells.C, Cells.C, ID.NPC, manager, side, hud, message1));
			hud.setStageEnd(false);
		}
		if(interactions == 1) {
			String message = "This is the final stage of level 1.\n"
					+ "Let's see what you can remember.";
			manager.addObject(new MessageNPC(Cells.D, Cells.E, ID.NPC, manager, side, hud, message));
			hud.setInteractions(interactions + 1);
		}
		//final quiz for end of level 1
		else if(interactions == 3) {
			String[] questions = {"Which of the following is not a data type?\n\n"
					+ "1. int\n"
					+ "2. boolean\n"
					+ "3. sausage\n"
					+ "4. long\n", 
					"Which of these is a correct variable declaration in Java?\n\n"
					+ "1. int 30 = elephants;\n"
					+ "2. 30 elephants = int;\n"
					+ "3. elephants int = 30;\n"
					+ "4. int elephants = 30;",
					"What is a String?\n\n"
					+ "1. A whole number\n"
					+ "2. A series of letters, numbers or characters\n"
					+ "3. A decimal number\n"
					+ "4. A statement of true or false\n",
					"If x is 3 and y is 7 and we want to store the value of the\n"
					+ "two variables added together which of the following statements would we use?\n\n"
					+ "1. int z = x + y;\n"
					+ "2. int z = 10;\n"
					+ "3. int z = x;\n"
					+ "4. int z = y;"};
			String[] answers = {"3", "4", "2", "1"};
			manager.addObject(new MultipleQuestionNPC(Cells.D, Cells.F, ID.NPC, manager, side, hud, questions, answers));
			hud.setInteractions(0);
		}
	}
	
	/**
	 * Sets up stage 1 of level 2 and adds NPCs as necessary. Works in the same way as
	 * levelOneStageOne()
	 */
	public void levelTwoStageOne() {
		int interactions = hud.getInteractions();
		if(interactions == 1) {
			String message = "Well Done! Lets see if you can pickup the flower.\n"
					+ "Try typing robot.move(up, 2); to get to it.";
			manager.addObject(new MessageNPC(Cells.E, Cells.C, ID.NPC, manager, side, hud, message));
			hud.setInteractions(interactions + 1);
		 }
		else if(interactions == 3) {
			File picture = new File("pictures\\Flower.png");
			manager.addFlower(new Flower(Cells.E, Cells.A, ID.Flower, manager, side, hud, picture));
			String message = "Now you can move more than one square at a time!";
			manager.addObject(new MessageNPC(Cells.F, Cells.A, ID.NPC, manager, side, hud, message));
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
			File picture = new File("pictures\\flower2.png");
			manager.addFlower(new Flower(Cells.A, Cells.D, ID.Flower, manager, side, hud, picture));
			hud.setInteractions(interactions + 1);
		}
		else if(interactions == 3) {
			String message3 = "The for each loop has the following syntax:\n" 
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
			File picture = new File("pictures\\flower3.png");
			manager.addFlower(new Flower(Cells.C, Cells.F, ID.Flower, manager, side, hud, picture));
			hud.setInteractions(interactions + 1);
		}
		else if(interactions == 7) {
			String message5 = "The for each loop is used for collections only!";
			manager.addObject(new MessageNPC(Cells.G, Cells.E, ID.NPC, manager, side, hud, message5));
			hud.setInteractions(interactions + 1);
		}
		else if(interactions == 9) {
			String question = "What would the following for each loop print for the collection flower = {rose, tulip, lily}?\n"
					+ "for(Flower flower : flowers) {\n"
					+ "print(flower ,);\n"
					+ "}";
			String answer = "rose, tulip, lily ,";
			manager.addObject(new StageEndNPC(Cells.E, Cells.D, ID.NPC, manager, side, hud, question, answer));
			hud.setInteractions(0);
		}
	}
	
	/**
	 * Sets up stage 3 of level 2 and adds NPCs as necessary
	 */
	public void levelTwoStageThree() {
		int interactions = hud.getInteractions();
		if(hud.isStageEnd() == true) {
			Manager.clearAll();
			SidePanel.addText("~Congratulations you completed Stage 2\n\n"
					+ "~Now try stage 3.\n\n"
					+ "~Good luck!\n\n");
			manager.addObject(new Player(Cells.A, Cells.A, ID.Player));
			String message1 = "Note that ++ is short hand for adding 1.";
			manager.addObject(new MessageNPC(Cells.C, Cells.D, ID.NPC, manager, side, hud, message1));
			File picture = new File("pictures\\Flower.png");
			manager.addFlower(new Flower(Cells.B, Cells.D, ID.Flower, manager, side, hud, picture));
			hud.setStageEnd(false);
		}
		else if(interactions == 1) {
			String message2 = "The while loop has the following syntax:\n" 
					+ "while(boolean condition) {\n"
					+ "loop body\n"
					+ "}";
			manager.addObject(new MessageNPC(Cells.B, Cells.F, ID.NPC, manager, side, hud, message2));
			hud.setInteractions(interactions + 1);
		}
		else if(interactions == 3) {
			String message3 = "For example while the flowers position in the collection of flowers is less than the size of the collection, pick up the flower.\n"
					+ "int flowerIndex = 0;\n"
					+ "while(flowerIndex < flowers.size()) {\n"
					+ "pickup(flower);\n"
					+ "flowerIndex ++;\n"
					+ "}";
			manager.addObject(new MessageNPC(Cells.G, Cells.H, ID.NPC, manager, side, hud, message3));
			File picture = new File("pictures\\flower3.png");
			manager.addFlower(new Flower(Cells.E, Cells.C, ID.Flower, manager, side, hud, picture));
			hud.setInteractions(interactions + 1);
		}
		else if(interactions == 5) {
			String message4 = "A while loop is used when you have an unknown number of iterations.";
			manager.addObject(new MessageNPC(Cells.C, Cells.B, ID.NPC, manager, side, hud, message4));
			hud.setInteractions(interactions + 1);
		}
		else if(interactions == 7) {
			String question = "Which flowers would be left unpicked after the following while loop for the collection flower = {rose, tulip, lily}?\n"
					+ "int flowerIndex = 0;\n"
					+ "while(flowerIndex < 2) {\n"
					+ "pickup(flower);\n"
					+ "flowerIndex ++;\n"
					+ "}";
			String answer = "lily";
			manager.addObject(new StageEndNPC(Cells.E, Cells.D, ID.NPC, manager, side, hud, question, answer));
			hud.setInteractions(0);
		}
	}
	
	/**
	 * Sets up stage 4 of level 2 and adds NPCs as necessary
	 */
	public void levelTwoStageFour() {
		int interactions = hud.getInteractions();
		if(hud.isStageEnd() == true) {
			Manager.clearAll();
			SidePanel.addText("~Congratulations you completed Stage 3\n\n"
					+ "~Now try stage 4.\n\n"
					+ "~Good luck!\n\n");
			manager.addObject(new Player(Cells.A, Cells.A, ID.Player));
			String message1 = "The for loop is used to execute a block of statements a specified number of times.";
			manager.addObject(new MessageNPC(Cells.D, Cells.A, ID.NPC, manager, side, hud, message1));
			hud.setStageEnd(false);
		}
		if(interactions == 1) {
			String message3 = "The for loop has the following syntax:\n" 
					+ "for(initialization; loop condition; increment/decrement) {\n"
					+ "loop body\n"
					+ "}";
			manager.addObject(new MessageNPC(Cells.B, Cells.F, ID.NPC, manager, side, hud, message3));
			File picture = new File("pictures\\flower3.png");
			manager.addFlower(new Flower(Cells.B, Cells.G, ID.Flower, manager, side, hud, picture));
			hud.setInteractions(interactions + 1);
		}
		else if(interactions == 3) {
			String message2 = "The loop condition must resolve to a boolean value.";
			manager.addObject(new MessageNPC(Cells.F, Cells.B, ID.NPC, manager, side, hud, message2));
			hud.setInteractions(interactions + 1);
		}
		else if(interactions == 5) {
			String message4 = "For example for i starting at 0, if i is less than the number of flowers, pick up the flower.\n"
					+ "for(int i = 0; i < flowers.size(); i++) {\n"
					+ "pickup(flower);\n"
					+ "}";
			manager.addObject(new MessageNPC(Cells.G, Cells.H, ID.NPC, manager, side, hud, message4));
			File picture = new File("pictures\\Flower.png");
			manager.addFlower(new Flower(Cells.A, Cells.E, ID.Flower, manager, side, hud, picture));
			hud.setInteractions(interactions + 1);
		}
		else if(interactions == 7) {
			String message5 = "You might have noticed that the while and for loops are similar.\n" 
					+ "A for loop is better suited for a known number of iterations.";
			manager.addObject(new MessageNPC(Cells.F, Cells.E, ID.NPC, manager, side, hud, message5));
			hud.setInteractions(interactions + 1);
		}
		else if(interactions == 9) {
			String question = "Which flowers would be picked up after the following for loop for the collection flower = {rose, tulip, lily}?\n"
					+ "for(int i = 0; i < 1; i++) {\n"
					+ "pickup(flower);\n"
					+ "}";
			String answer = "rose";
			manager.addObject(new StageEndNPC(Cells.E, Cells.D, ID.NPC, manager, side, hud, question, answer));
			hud.setInteractions(0);
		}
	}
	
	/**
	 * Sets up stage 5 of level 2 and adds NPCs as necessary
	 */
	public void levelTwoStageFive() {
		int interactions = hud.getInteractions();
		if(hud.isStageEnd() == true) {
			Manager.clearAll();
			SidePanel.addText("~Congratulations you completed Stage 4\n\n"
					+ "~Now try stage 5.\n\n"
					+ "~Good luck!\n\n");
			manager.addObject(new Player(Cells.A, Cells.A, ID.Player));
			String message = "Lets use loops to draw a flower! Try drawFlower();";
			manager.addObject(new MessageNPC(Cells.B, Cells.B, ID.NPC, manager, side, hud, message));
			manager.addObject(new Drawing(interactions, interactions, null, manager, side, hud));
			File picture = new File("pictures\\flower2.png");
			manager.addFlower(new Flower(Cells.B, Cells.D, ID.Flower, manager, side, hud, picture));
			hud.setStageEnd(false);
		}
		if(interactions == 1) {
			String[] questions = {"Which loop should we use: for each, while or for?",
					"Correct the following for loop:\n"
					+ "for(i = 1; i < 6; i++) {...\n",
			"What is wrong with the following:\n"
					+ "for(int i = 1; i = 6; i++) {...\n"
					+ "1. inital condition is not instantiated\n"
					+ "2. loop condition does not resolve to boolean\n"
					+ "3. nothing",
					"How many petals will this for loop draw?\n"
					+ "for(int i = 1; i < 6; i++) {\n"
					+ "petal.rotate(72);\n"
					+ "draw(petal);\n"
					+ "}"};
			String[] answers = {"for", "int i = 1;", "2", "5"};
			manager.addObject(new MultipleQuestionNPC(Cells.E, Cells.D, ID.NPC, manager, side, hud, questions, answers));
			manager.addObject(new Drawing(Cells.D, Cells.D, ID.NPC, manager, side, hud));
			hud.setInteractions(0);
			
		}
		
	}
	
	/**
	 * Sets up stage 1 of level 3 and adds NPCs as necessary.
	 */
	public void levelThreeStageOne() {
		//Get the number of interactions
		int interactions = hud.getInteractions();
				//Add the second NPC once the first has been interacted with
				if(interactions == 1) {
					String message = "Your robot is hungry, feed him by getting him to eat the apple.\n"
							+ "You can use something like robot.eat(fruit). Now try it yourself:";
					String answer = "robot.eat(apple);";
					manager.addObject(new QuestionNPC(Cells.G, Cells.A, ID.NPC, manager, side, hud, message, answer, "pictures/apple.png"));
					//Add an extra interaction to stop the addition of further objects from this method
					hud.setInteractions(interactions + 1);
					
				}
				//Add the NPCs one at a time for the rest of the stage
				else if(interactions == 3) {
					String message = "Here we have two classes: the Robot and the Item classes. \n" 
							+"Robbie is an Object from the Robot Class. This means he has characteristics (Fields) that are taken from (belong to) the Robot Class. \n"
							+"We’ll discuss those in detail later. Continue to the next fruit.";
					manager.addObject(new MessageNPC(Cells.G, Cells.C, ID.NPC, manager, side, hud, message));
					hud.setInteractions(interactions + 1);
				}
				else if(interactions == 5) {
					String message = "Eat the banana to unlock more information about Classes and Objects.";
					String answer = "robot.eat(banana);";
					manager.addObject(new QuestionNPC(Cells.C, Cells.C, ID.NPC, manager, side, hud, message, answer, "pictures/banana.png"));
					hud.setInteractions(interactions + 1);
				}
				else if(interactions == 7) {
					String message = "Classes start with a capital letter, while Objects are written with a small letter. \n\n"
							+ "Write all the Objects you have seen so far in order of introduction!";
					String answer = "robot, apple, banana";
					manager.addObject(new QuestionNPC(Cells.B, Cells.C, ID.NPC, manager, side, hud, message, answer, "pictures/walle.png"));
					hud.setInteractions(interactions + 1);
				}
				else if(interactions == 9) {
					String message = "Classes have Methods that help you make an action, but only an Object can “invoke” that method.\n\n" + 
							"Write the name of the Method you've seen so far!";
					String answer = "eat";
					manager.addObject(new QuestionNPC(Cells.A, Cells.D, ID.NPC, manager, side, hud, message, answer, "pictures/walle.png"));
					hud.setInteractions(interactions + 1);
				}
				else if(interactions == 11) {
					String message = "To create a new object from a class you need to type it in this form: \n\n" + 
							"ClassName ObjectName = new ClassName(); \n\n" + 
							"(Don’t forget to put capital letters where needed!)";
					manager.addObject(new MessageNPC(Cells.D, Cells.D, ID.NPC, manager, side, hud, message));
					hud.setInteractions(interactions + 1);
				}
				else if(interactions == 13) {
					String question = "Now try to create a friend for your robot from the class Robot and name it buddy!";
					String answer = "Robot buddy = new Robot();";
					manager.addObject(new StageEndNPC(Cells.F, Cells.F, ID.NPC, manager, side, hud, question, answer));
					hud.setInteractions(0);
				}
	}
	
	/**
	 * Sets up stage 2 of level 3 and adds NPCs as necessary.
	 */
	public void levelThreeStageTwo() {
		int interactions = hud.getInteractions();
		if(hud.isStageEnd() == true) {
			Manager.clearAll();
			SidePanel.addText("~Well done! The second part of this statement is called the Constructor of a class. "
					+ "We will have a look at it at a later Stage.\n\n"
					+ "~Now try stage 2.\n\n"
					+ "~Good luck!\n\n");
			manager.addObject(new Player(Cells.A, Cells.A, ID.Player));
			String message = "A Class in Java has 3 main components: Fields (Objects’ state), Methods (Objects’ behavior) "
					+ "and Constructor (Objects’ creation).";
			manager.addObject(new MessageNPC(Cells.A, Cells.E, ID.NPC, manager, side, hud, message));
			hud.setStageEnd(false);
		}
		if(interactions == 1) {
			String message = "Fields can be primitive or reference type: an int, String or of class Item or Robot.\n\n" + 
					"We define them like so:\n\n" + 
					"private DataType/ClassName nameOfField;";
			manager.addObject(new MessageNPC(Cells.D, Cells.F, ID.NPC, manager, side, hud, message));
			//Add an extra interaction to stop the addition of further objects from this method
			hud.setInteractions(interactions + 1);
		}
		//Add the NPCs one at a time for the rest of the stage
		else if(interactions == 3) {
			String message = "Define an age field for the Robot class.";
			String answer = "private int age;";
			manager.addObject(new QuestionNPC(Cells.E, Cells.C, ID.NPC, manager, side, hud, message, answer, "pictures/walle.png"));
			hud.setInteractions(interactions + 1);
		}
		else if(interactions == 5) {
			String message = "Well done! Now define a hat field that is an Object of Item Class.";
			String answer = "private Item hat;";
			manager.addObject(new QuestionNPC(Cells.F, Cells.A, ID.NPC, manager, side, hud, message, answer, "pictures/walle.png"));
			hud.setInteractions(interactions + 1);
		}
		else if(interactions == 7) {
			String message = "As you can see Fields carry the characteristics of the Class: "
					+ "Objects from the Robot Class will have age and a hat. ";
			manager.addObject(new MessageNPC(Cells.G, Cells.D, ID.NPC, manager, side, hud, message));
			hud.setInteractions(interactions + 1);
		}
		else if(interactions == 9) {
			String question = "Now add a name and hasBuddy Fields to the Robot class.\n\n"
					+ "(Think carefully what data types those Fields will be!)";
			String answer = "private String name; private boolean hasBuddy;";
			manager.addObject(new StageEndNPC(Cells.H, Cells.G, ID.NPC, manager, side, hud, question, answer));
			hud.setInteractions(0);
		}
	}
	
	/**
	 * Sets up stage 3 of level 3 and adds NPCs as necessary.
	 */
	public void levelThreeStageThree() {
		int interactions = hud.getInteractions();
		if(hud.isStageEnd() == true) {
			Manager.clearAll();
			SidePanel.addText("~Well done! Welcome to Stage 3, where we will look at the Class Constructor.\n\n"
						+ "~Good luck!\n\n");
			manager.addObject(new Player(Cells.A, Cells.A, ID.Player));
			String message = "Constructors are used when a new Object of a Class is created.";
			manager.addObject(new MessageNPC(Cells.B, Cells.B, ID.NPC, manager, side, hud, message));
			hud.setStageEnd(false);
		}
		if(interactions == 1) {
			String message = "They have the same name as the Class and initialise the Object by "
					+ "storing initial values for the Fields.";
			manager.addObject(new MessageNPC(Cells.C, Cells.D, ID.NPC, manager, side, hud, message));
			//Add an extra interaction to stop the addition of further objects from this method
			hud.setInteractions(interactions + 1);
		}
		//Add the NPCs one at a time for the rest of the stage
		else if(interactions == 3) {
			String message = "These values can be passed by parameters or hard coded in the Constructor’s body.\n\n" + 
					"Examples of both:\n\n" + 
					"public Robot(String fullName, int robotAge) {\n" + 
					"//this is a comment in Java: here we equate the Fields we created earlier to the parameters above\n" + 
					"		name = fullName;\n" + 
					"		age = robotAge;\n" + 
					"}\n\n" + 
					"public Robot() {\n" + 
					"		name = “Robbie”;\n" + 
					"		age = 15;\n" + 
					"}";
			manager.addObject(new MessageNPC(Cells.E, Cells.E, ID.NPC, manager, side, hud, message));
			hud.setInteractions(interactions + 1);
		}
		else if(interactions == 5) {
			String message = "As you can see, a Class can have multiple Constructors, as long as they differ in parameter signatures.";
			manager.addObject(new MessageNPC(Cells.G, Cells.B, ID.NPC, manager, side, hud, message));
			hud.setInteractions(interactions + 1);
		}
		else if(interactions == 7) {
			String question = "Write the code to create a Robot Object called robot with name Berty and age 17.\n\n" + 
					"(Be careful how you write the parameters considering their data type!) ";
			String answer = "Robot robot = new Robot(\"Berty\", 17);";
			manager.addObject(new StageEndNPC(Cells.H, Cells.E, ID.NPC, manager, side, hud, question, answer));
			hud.setInteractions(0);
		}
			
	}
	
	/**
	 * Sets up stage 4 of level 3 and adds NPCs as necessary.
	 */
	public void levelThreeStageFour() {
		int interactions = hud.getInteractions();
		if(hud.isStageEnd() == true) {
			Manager.clearAll();
			SidePanel.addText("~Welcome to Stage 4, All about Methods!\n\n"
						+ "~Good luck!\n\n");
			manager.addObject(new Player(Cells.A, Cells.A, ID.Player));
			String message = "Methods specify an action that is performed BY or ON an Object.";
			manager.addObject(new MessageNPC(Cells.B, Cells.B, ID.NPC, manager, side, hud, message));
			hud.setStageEnd(false);
		}
		if(interactions == 1) {
			String message = "Each Class has certain Methods, which its Objects can perform.";
			manager.addObject(new MessageNPC(Cells.G, Cells.A, ID.NPC, manager, side, hud, message));
			//Add an extra interaction to stop the addition of further objects from this method
			hud.setInteractions(interactions + 1);
		}
		
		else if(interactions == 3) {
			String message = "Here is an example of how a Method is defined within a Class:\n\n" + 
					"public returnType methodName (parameters) {\n" + 
					"\t\t\t action;\n" + 
					"\t\t\t action;\n" + 
					"\t\t\t …\n" + 
					"}";
			manager.addObject(new MessageNPC(Cells.G, Cells.C, ID.NPC, manager, side, hud, message));
			hud.setInteractions(interactions + 1);
		}
		else if(interactions == 5) {
			String message = "Methods that RETURN an Object have the same returnType as Object (e.g. String, int, Robot) "
					+ "and are called ACCESSOR Methods";
			manager.addObject(new MessageNPC(Cells.C, Cells.C, ID.NPC, manager, side, hud, message));
			hud.setInteractions(interactions + 1);
		}
		else if(interactions == 7) {
			String message = "Methods that change the state of an Object and do not have a RETURN statement have a VOID "
					+ "returnType and are called MUTATOR Methods.";
			manager.addObject(new MessageNPC(Cells.B, Cells.C, ID.NPC, manager, side, hud, message));
			hud.setInteractions(interactions + 1);
		}
		else if(interactions == 9) {
			String message = "Which type of Method is the following: \n\n" + 
					"public String getName() {\n" + 
					"		return name;\n" + 
					"}\n";
			String answer = "accessor";
			manager.addObject(new QuestionNPC(Cells.D, Cells.D, ID.NPC, manager, side, hud, message, answer, "pictures/walle.png"));
			hud.setInteractions(interactions + 1);
		}
		else if(interactions == 11) {
			String message = "Which type of Method is the following:\n\n" + 
					"public void changeName(String newName) {\n" + 
					"		name = newName;\r\n" + 
					"}\n";
			String answer = "mutator";
			manager.addObject(new QuestionNPC(Cells.A, Cells.D, ID.NPC, manager, side, hud, message, answer, "pictures/walle.png"));
			hud.setInteractions(interactions + 1);
		}
		else if(interactions == 13) {
			String question = "Write the code to change robot’s name to Robbie!";
			String answer = "robot.changeName(\"Robbie\");";
			manager.addObject(new StageEndNPC(Cells.F, Cells.F, ID.NPC, manager, side, hud, question, answer));
			hud.setInteractions(0);
		}
		
	}
	
	public void levelThreeStageFive() {
		int interactions = hud.getInteractions();
		if(hud.isStageEnd() == true) {
			Manager.clearAll();
			SidePanel.addText("~Congratulations you completed all 4 Stages!\n\n"
					+ "~Go to your last buddy to see what he has for you!\n\n");
			manager.addObject(new Player(Cells.A, Cells.A, ID.Player));
			String message1 = "Now take the final quiz to complete the Level!\n\n~Good luck!\\n\\n";
			manager.addObject(new MessageNPC(Cells.C, Cells.C, ID.NPC, manager, side, hud, message1));
			hud.setStageEnd(false);
		}
		if(interactions == 1) {
			String[] questions = {"Which of the following can be a Class Name?\n\n"
					+ "1. int\n"
					+ "2. Boolean\n"
					+ "3. Message\n"
					+ "4. triangle\n",
					"What are Fields for?\n\n"
					+ "1. For storing values within Objects\n"
					+ "2. To allow Objects to do actions\n"
					+ "3. To ease Class creation\n"
					+ "4. To add parameters\n",
					"Can a Class have more than one Constuctor?\n\n"
					+ "1. Yes\n"
					+ "2. No\n"
					+ "3. Yes, but not with the same parameter signatures\n"
					+ "4. Yes, but only if it has the same parameters\n",
					"Write a Method to set the age in the Robot Class!\n\n (HINT: Call it setAge!)"
					};
			String[] answers = {"3", "1", "3", "public void setAge(int newAge) {age = newAge;}"};
			manager.addObject(new MultipleQuestionNPC(Cells.D, Cells.F, ID.NPC, manager, side, hud, questions, answers));
			hud.setInteractions(0);
		}
	}

}

