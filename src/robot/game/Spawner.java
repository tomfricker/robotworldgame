package robot.game;

import java.io.File;

import npcs.ArrayListNPC;
import npcs.Drawing;
import npcs.Flower;
import npcs.MessageNPC;
import npcs.MultipleChoiceQNPC;
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
		int stage = HUD.getStage();
		//Get the level the user is on
		switch(HUD.getLevel()) {
			//Execute the method for the correct level/stage
			//level 1
			case 1: if(stage == 1) dataTypesIntro();
			else if(stage == 2) dataTypesInt();
			else if(stage == 3) dataTypesString();
			else if(stage == 4) dataTypesQuiz();

			//level 2
			case 2: 
			if(stage == 1) relationalOperators();
		    else if(stage == 2) booleanValues();
		    else if(stage == 3) conditionalStatements();
			
			//level 3
			case 3: if(stage == 1) levelThreeStageOne();
			else if(stage == 2) levelThreeStageTwo();
			//else if(stage == 3) levelThreeStageThree();
			//else if(stage == 4) levelThreeStageFour();
			//else if(stage == 5) levelThreeStageFive();
			
			//level 4
			case 4: if(stage == 1) loopsIntro();
			else if(stage == 2) loopsForeach();
			else if(stage == 3) loopsWhile();
			else if(stage == 4) loopsFor();
			else if(stage == 5) loopsQuiz();
			
			//level 5
			case 5: if(stage == 1) classesObjectsIntro();
			else if(stage == 2) classesObjectsFields();
			else if(stage == 3) classesObjectsConstructor();
			else if(stage == 4) classesObjectsMethods();
			else if(stage == 5) classesObjectsQuiz();
		}
	}
	//LEVEL 1 SETUP
	/**
	 * Creates the objects for the first stage of level 1 as you progress in the level.
	 * The number of interactions is executed using odd numbers to ensure that an
	 * object is only added once to the manager.
	 */
	public void dataTypesIntro() {
		//Get the number of interactions
		int interactions = HUD.getInteractions();
		//Add the second NPC once the first has been interacted with
		if(interactions == 1) {
			String message = "There are many different types of data types including, int, double, float, "
					+ "long, boolean and char.";
			manager.addObject(new MessageNPC(Cells.E, Cells.A, ID.NPC, manager, side, hud, message));
			//Add an extra interaction to stop the addition of further objects from this method
			HUD.setInteractions(interactions + 1);
		}
		//Add the NPCs one at a time for the rest of the stage
		else if(interactions == 3) {
			String message = "To use data within our program we need to store it in a variable.";
			manager.addObject(new MessageNPC(Cells.G, Cells.A, ID.NPC, manager, side, hud, message));
			HUD.setInteractions(interactions + 1);
		}
		else if(interactions == 5) {
			String message = "We decide what name to give our variable and begin with a lowercase letter.\n\n"
					+ "e.g. robots, squares, x or y.";
			manager.addObject(new MessageNPC(Cells.H, Cells.C, ID.NPC, manager, side, hud, message));
			HUD.setInteractions(interactions + 1);
		}
		else if(interactions == 7) {
			String message = "To assign data to our variable we use =";
			manager.addObject(new MessageNPC(Cells.F, Cells.C, ID.NPC, manager, side, hud, message));
			HUD.setInteractions(interactions + 1);
		}
		else if(interactions == 9) {
			String message = "Finally we use ; (semi-colon) to finish our line of code.";
			manager.addObject(new MessageNPC(Cells.D, Cells.C, ID.NPC, manager, side, hud, message));
			HUD.setInteractions(interactions + 1);
		}
		else if(interactions == 11) {
			String question = "In maths, an integer is any whole number. Which data type\n"
					+ "do you think we would use to store an integer?\n\n"
					+ "int, double, float, long, boolean or char.";
			String answer = "int";
			manager.addObject(new StageEndNPC(Cells.B, Cells.C, ID.NPC, manager, side, hud, question, answer));
			HUD.setInteractions(0);
		}
	}
	
	/**
	 * Creates the objects for the first stage of level 1 as you progress in the level.
	 * The number of interactions is executed using odd numbers to ensure that an
	 * object is only added once to the manager.
	 */
	public void dataTypesInt() {
		int interactions = HUD.getInteractions();
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
			String message = "Remember we need a data type, a variable name, an assignment =, the data and finally a ;";
			manager.addObject(new MessageNPC(Cells.A, Cells.F, ID.NPC, manager, side, hud, message));
			//Add an extra interaction to stop the addition of further objects from this method
			HUD.setInteractions(interactions + 1);
		}
		//Add the NPCs one at a time for the rest of the stage
		else if(interactions == 3) {
			String message = "An example of a number variable declaration is:\n\n"
					+ "int squares = 64;";
			manager.addObject(new MessageNPC(Cells.B, Cells.G, ID.NPC, manager, side, hud, message));
			HUD.setInteractions(interactions + 1);
		}
		else if(interactions == 5) {
			for(int i = 0; i < 8; i++) {
				int cell = 8 + Game.boardIndex*i;
				manager.addObject(new MessageNPC(cell, Cells.A, ID.NPC, manager, side, hud, null));
			}
			String question = "Please declare the number of robots on the top line of the board.\n"
					+ "It needs to be declared in a variable called robots.\n\n"
					+ "HINT: Spelling, spaces and upper/lowercase letters are important when writing code!";
			String answer = "int robots = 8;";
			manager.addObject(new StageEndNPC(Cells.D, Cells.G, ID.NPC, manager, side, hud, question, answer));
			HUD.setInteractions(0);
		}
	}
	
	/**
	 * Sets up stage 3 of level 1 and adds NPCs as necessary. Works in the same way as
	 * levelOneStageOne()
	 */
	public void dataTypesString() {
		int interactions = HUD.getInteractions();
		if(hud.isStageEnd() == true) {
			Manager.clearAll();
			SidePanel.addText("~Congratulations you completed Stage 2\n\n"
					+ "~Now try stage 3.\n\n"
					+ "~Good luck!\n\n");
			manager.addObject(new Player(Cells.A, Cells.A, ID.Player));
			String message1 = "In Java, and most programming languages, a word, sentence or group of characters is called a String.\n\n(HINT: Notice the capital letter)";
			manager.addObject(new MessageNPC(Cells.C, Cells.C, ID.NPC, manager, side, hud, message1));
			hud.setStageEnd(false);
		}
		if(interactions == 1) {
			String message2 = "When writing a String you must wrap it with inverted commas like:\n\n"
					+ "\"This is a string\"";
			manager.addObject(new MessageNPC(Cells.E, Cells.E, ID.NPC, manager, side, hud, message2));
			HUD.setInteractions(interactions + 1);
		}
		else if(interactions == 3) {
			String message3 = "Just like an int a String must have a variable name";
			manager.addObject(new MessageNPC(Cells.G, Cells.G, ID.NPC, manager, side, hud, message3));
			HUD.setInteractions(interactions + 1);
		}
		else if(interactions == 5) {
			String message4 = "Remember the order, data type, variable name, assignment, data and ;";
			manager.addObject(new MessageNPC(Cells.F, Cells.H, ID.NPC, manager, side, hud, message4));
			HUD.setInteractions(interactions + 1);
		}
		else if(interactions == 7) {
			String question = "Let's declare a String.\n\n"
					+ "We'll call the String name\n"
					+ "Then assign \"Robbie\" to that variable.";
			String answer = "String name = \"Robbie\";";
			manager.addObject(new StageEndNPC(Cells.D, Cells.H, ID.NPC, manager, side, hud, question, answer));
			HUD.setInteractions(0);
		}
	}
	
	/**
	 * Sets up stage 4 of level 1 and adds NPCs as necessary. Works in the same way as
	 * levelOneStageOne()
	 */
	public void dataTypesQuiz() {
		int interactions = HUD.getInteractions();
		if(hud.isStageEnd() == true) {
			Manager.clearAll();
			SidePanel.addText("~Congratulations you completed Stage 3\n\n"
					+ "~Now try stage 4.\n\n"
					+ "~Good luck!\n\n");
			manager.addObject(new Player(Cells.A, Cells.A, ID.Player));
			String message1 = "If we want to do arithmetic on numbers we can use + - / or *(this means multiply) on two variables, such as:\n\n"
					+ "int rows = 8;\n"
					+ "int columns = 8;\n"
					+ "int squares = rows * columns;\n\n"
					+ "What number do you think squares will store?";
			manager.addObject(new MessageNPC(Cells.C, Cells.C, ID.NPC, manager, side, hud, message1));
			hud.setStageEnd(false);
		}
		if(interactions == 1) {
			String message = "This is the final stage of level 1.\n"
					+ "Let's see what you can remember.";
			manager.addObject(new MessageNPC(Cells.D, Cells.E, ID.NPC, manager, side, hud, message));
			HUD.setInteractions(interactions + 1);
		}
		//final quiz for end of level 1
		else if(interactions == 3) {
			String[] questions = {"Which of the following is not a data type?\n\n", "Which of these is a correct variable declaration in Java?\n\n",
					"What is a String?\n\n", "If x is 3 and y is 7 and we want to store the value of the\n"
					+ "two variables added together which of the following statements would we use?\n\n"};
			String [] question1 = {"","int", "boolean", "sausage", "long"};
			String [] question2 = {"","int 30 = elephants;", "30 elephants = int;", "elephants int = 30;", "int elephants = 30;"};
			String [] question3 = {"","A whole number", "A series of letters, numbers or characters", "A decimal number", "A statement of true or false"};
			String [] question4 = {"","int z = x + y;", "int z = 10;", "int z = x;", "int z = y;"};
			String [][] options = {question1, question2, question3, question4};
			String[] answers = {"sausage", "int elephants = 30;", "A series of letters, numbers or characters", "int z = x + y;"};
			manager.addObject(new MultipleQuestionNPC(Cells.D, Cells.F, ID.NPC, manager, side, hud, questions, answers, options));
			HUD.setInteractions(0);
		}
	}
	
	//LEVEL 2 SETUP 	
	/**
	 * Sets up stage 1 of level 2 
	 */
	//Conditional statements
      public void relationalOperators() {
    	  int interactions = HUD.getInteractions();
    		if(interactions == 1) {
    			
    			HUD.setInteractions(0); // This stops the NPC interaction in other levels from interfering in this level
    			
    		}
      }
      /**
  	 * Sets up stage 2 of level 2 
  	 */
     public void booleanValues() {
    		int interactions = HUD.getInteractions();
    		
    		if(hud.isStageEnd() == true) {
    			Manager.clearAll();
    			SidePanel.addText("~Congratulations you are now on Stage 2\n\n"
    					+ "~In the previous stage, we spoke about how boolean values: True or False\n\n"
    					+ "are used as a result when comparing values or evaluating expressions using relational operators.\n\n"
    					+ "Expressions in java look like this: e.g 5 > 4 ( means that 5 is greater than 4) \n\n."
    					+ "Because 5 is indeed greater than 4, the boolean value true will be the output/answer that Java gives\n\n"
    					+ "Press the right arrow key to see what you need to do to complete this stage.\n\n");
    			manager.addObject(new Player(Cells.A, Cells.A, ID.Player));
    			String message1 = "Robbie loves eating screws but the evil yellow robots have stolen them\n\n"  
    					+ "To get them back answer each boolean question correctly.\n\n";
    			manager.addObject(new MessageNPC(Cells.B, Cells.A, ID.NPC, manager, side, hud, message1));
    			hud.setStageEnd(false);
    //		HUD.setInteractions(interactions + 1);
    	
    		}
    		
    		else if(interactions == 1) {
				String message = "What will be the boolean value be for 3 == 5";
				String answer = "false";
				manager.addObject(new QuestionNPC(Cells.C, Cells.A, ID.NPC, manager, side, hud, message, answer, "pictures/screw.png"));
				HUD.setInteractions(interactions + 1);
    		}
    		
    		else if(interactions == 3) {
				String message = "What will be the boolean value be for 10 < 4";
				String answer = "false";
				manager.addObject(new QuestionNPC(Cells.H, Cells.A, ID.NPC, manager, side, hud, message, answer, "pictures/screw.png"));
				HUD.setInteractions(interactions + 1);
    		
      }
    		else if(interactions == 5) {
				String message = "Java also uses other operators that combine 2 operators into one e.g < = (is less than or equal to)\n\n"
						+ ">= ( is greater than or equal to)\n\n"
						+ "see if you can figure out what boolean value this expression would give:\n\n"
						+ "8 <= 9 ";
				String answer = "true";
				manager.addObject(new QuestionNPC(Cells.H, Cells.H, ID.NPC, manager, side, hud, message, answer, "pictures/screw.png"));
				HUD.setInteractions(interactions + 1);	
     }
     
    		else if(interactions == 7) {
				String message = "What will be the boolean value be for 6 >= 9";
				String answer = "false";
				manager.addObject(new QuestionNPC(Cells.A, Cells.G, ID.NPC, manager, side, hud, message, answer, "pictures/screw.png"));
				HUD.setInteractions(interactions + 1);
     }  	
    		else if(interactions == 9) {
				String message = "What will be the boolean value be for 7 != 1";
				String answer = "true";
				manager.addObject(new QuestionNPC(Cells.D, Cells.A, ID.NPC, manager, side, hud, message, answer, "pictures/screw.png"));
				HUD.setInteractions(interactions + 1);
     }	
    		else if(interactions == 11) {
				String question = "Answer this last question to complete the stage \n\n"
						+ "int robot1 = 5;\n\n"
						+ "int robot2 = 9;\n\n"
						+ "boolean testQuestion = robot1 <= robot2;\n\n"
						+ "System.out.println(testQuestion);\n\n"
						+ "The boolean variable testQuestion is holding an expression, can you figure what boolean value\n\n"
						+ "the System.out.println(testQuestion) statement will print out?\n\n";
				String answer = "true";
				manager.addObject(new StageEndNPC(Cells.D, Cells.D, ID.NPC, manager, side, hud, question, answer));
				HUD.setInteractions(0);
    
    		}
     }	
     /**
 	 * Sets up stage 3 of level 2 
 	 */ 		
      public void conditionalStatements() {
   	  int interactions = HUD.getInteractions();
           if(hud.isStageEnd() == true) {
  			    Manager.clearAll();
  			    SidePanel.addText("Well done! You are now on the final stage of level 2. This stage is very important in Java\n\n"
  			    		+ "because we are going to learn about conditional statements. Without these it is very difficult\n\n"
  			    		+ "to control the flow of your program. To understand this in more detail, visit Robbie's friends.\n\n"
  			    		+ "Make sure you pay attention you never know when you'll be tested by the robots.. \n\n"
  			    		+ "Good luck!");
  			    manager.addObject(new Player(Cells.A, Cells.A, ID.Player));
  			String message1 = "Hi there, have you noticed that we make decisions everyday to do different things?\n\n"
  					+ "In the way that we make decisions,\n\n"
  					+ "computers need conditional statements to help them \n\n"
  					+ "do different things.\n\n"
  					+ "Decisions are written in Java using conditional statements.\n\n";
  			manager.addObject(new MessageNPC(Cells.B, Cells.A, ID.NPC, manager, side, hud, message1));
  			hud.setStageEnd(false);
         
			}
  	         	
             else if(interactions == 1) {
  					String message = "What do computers use to make decisions?\n\n"
  							+ "HINT:Read the panel on the left again!\n\n";
  					String answer = "conditional statements";
  					manager.addObject(new QuestionNPC(Cells.H, Cells.A, ID.NPC, manager, side, hud, message, answer, "pictures/screw.png"));
  					HUD.setInteractions(interactions + 1);
  	  }		
  	         	
  	         	else if(interactions == 3) {
				String message = "These conditional statements are referred to as the following:\n\n"
		                + "if-then\n\n"
						+ "if-else\n\n";
				manager.addObject(new MessageNPC(Cells.H, Cells.H, ID.NPC, manager, side, hud, message));
				HUD.setInteractions(interactions + 1);
  	         	
      }
  	      	else if(interactions == 5) {
				String message = "If-then statements are the most basic ones to use.\n\n"
						+ " They start like this: if(insert condition) {\n\n"
						+ "if the condition in the brackets evaluates to true then java will execute\n\n"
						+ "the code that comes after the bracket.";
				manager.addObject(new MessageNPC(Cells.A, Cells.H, ID.NPC, manager, side, hud, message));
				HUD.setInteractions(interactions + 1);
				
  	      	}
  	    	else if(interactions == 7) {
					String message = "int screw1 = 5;\n\n"
							+ "int screw2 = 9;\n\n"
							+ "if(screw1 <=  screw2){\n\n"
							+ "System.out.println(Robbie is still hungry)\n\n"
							+ "What the above statement means: if the value stored in the variable screw1 is less than\n\n"
							+ "or equal to the value stored in screw2, java needs to print out the sentence\n\n"
							+ "Robbie is still hungry.\n\n"
							+ "What is the condition in the above statement?";
							String [] options = {"","screw1 <= screw2", "int screw1 = 5;", "int screw2 = 9;", "System.out.println(Robbie is still hungry)"};
					String answer = "screw1 <= screw2";
					manager.addObject(new MultipleChoiceQNPC(Cells.A, Cells.D, ID.NPC, manager, side, hud, message, options, answer));
					HUD.setInteractions(interactions + 1);
  	         	
  	      	}	
  	  	else if(interactions == 9) {
			String message = "if-else statements are used to give the computer an alternative decision.\n\n"
	                + "if the condition given in the statement evaluates to false then the computer will \n\n"
	                + "execute a different decision\n\n"
					+ "here is an example: int screw1 = 5;\n\n"
					+ "int screw2 = 9;\n\n"
					+ "if(screw1 <=  screw2){\n\n"
					+ "System.out.println(Robbie is still hungry) }\n\n"
					+ "else { \n\n"
					+ "System.out.println(Robbie is full); }";
			manager.addObject(new MessageNPC(Cells.D, Cells.D, ID.NPC, manager, side, hud, message));
			HUD.setInteractions(interactions + 1);
  	  	}    
  	         	
  	  else if(interactions == 11) {
			String message = "Using the example that you just saw of the if-else statement\n\n"
					+ "what decision do you think will be printed: the first one or the second?\n\n"
					+ "int screw1 = 5;\n\n"
					+ "int screw2 = 9;\n\n"
					+ "if(screw1 <=  screw2){\n\n"
					+ "System.out.println(Robbie is still hungry) }\n\n"
					+ "else {\n\n"
					+ "System.out.println(Robbie is full); }\n\n"
					+ "HINT: Remember relational operators like <= means less than or equals to";
			String [] options = {"","Robbie is still hungry", "Robbie is full"};
			String answer = "Robbie is still hungry";
			manager.addObject(new MultipleChoiceQNPC(Cells.D, Cells.E, ID.NPC, manager, side, hud, message, options, answer));
			HUD.setInteractions(interactions + 1);
		
		
      }	
         //final quiz for end of level 2
         		else if(interactions == 13) {
         			String[] questions = {"What are Relational Operators?", "What is the primitive data type that has 2 values: True and False?\n\n",
         					"Which symbol is used to check the equality of 2 values?\n\n", "Why do we use an if-else statement?\n\n",
         				     "Why are conditional statements used in java?"};
         			String [] question1 = {"","boolean", "they compare values", "variables"};
         			String [] question2 = {"","int", "boolean", "conditional statements"};
         			String [] question3 = {"","=", "<=", "=="};
         			String [] question4 = {"","if a condition evaluates to false", "if a condition evaluates to true", "used to make decisions"};
         			String [] question5 = {"","used to make decisions by checking condition","stores values","compare values"};
         			String [][] options = {question1, question2, question3, question4, question5};
         			String[] answers = {"they compare values", "boolean", "==", "if a condition evaluates to false","used to make decisions by checking condition"};
         			manager.addObject(new MultipleQuestionNPC(Cells.D, Cells.F, ID.NPC, manager, side, hud, questions, answers, options));
         			HUD.setInteractions(0);
      }
      }
      
	//LEVEL 3 SETUP
	
	public void levelThreeStageOne() {
		int interactions = HUD.getInteractions(); 
		if(interactions == 1) {
			
			HUD.setInteractions(0); // This stops the NPC interaction in other levels from interfering in this level
		}		
	}
	
	public void levelThreeStageTwo() {
		if(hud.isStageEnd() == true) {
			Manager.clearAll();
			SidePanel.addText("~ Well Done! Time to look at a Collection a little more closely \n\n" +
								"~ An ArrayList is an ordered list of objects\n\n"+
								"~ You construct an ArrayList using the constructor method ArraytList<Object>(). Object can be any kind of object you want the ArrayList to hold.\n\n" +
								"~ They can be added to with the add() method. Remember how you call methods on an object...object.method()\n\n" +
								"~ try adding each of the vowels (a,e,i,o,u) to this ArrayList<Char> called chars\n\n "+
								"~ Notice how the Collection grows as you add each letter.\n\n");
			manager.addObject(new Player(Cells.A, Cells.A, ID.Player));
			manager.addObject(new ArrayListNPC(Cells.D, Cells.E, ID.Collection,"Char", manager, side,  hud,false));
			hud.setStageEnd(false); }
			
		}
	//third stage of the collections level which was proving to be overly complicated with the commands so it's been left out for now. 
			public void levelThreeStageThree() {
				if(hud.isStageEnd() == true) {
					Manager.clearAll();
					SidePanel.addText("~ Well Done! Now try using the remove() method similarly to get rid of the letter a from the collection");
					manager.addObject(new Player(Cells.A, Cells.A, ID.Player));
					manager.addObject(new ArrayListNPC(Cells.D, Cells.E, ID.Collection,"Char", manager, side,  hud,true));
					hud.setStageEnd(false);
					
				}
			}
			
			
			
		//LEVEL 4 SETUP
		/**
		 * Sets up stage 1 of level 4 and adds NPCs as necessary. Works in the same way as
		 * levelOneStageOne()
		 */
		public void loopsIntro() {
			int interactions = HUD.getInteractions();
			if(interactions == 1) {
				String message = "Well Done! Next try typing robot.move(up, 2); to get to the flower.\n"
						+ "Then try to pickup the flower by typing a new command once you are on the same square.";
				manager.addObject(new MessageNPC(Cells.E, Cells.C, ID.NPC, manager, side, hud, message));
				HUD.setInteractions(interactions + 1);
			 }
			else if(interactions == 3) {
				File picture = new File("pictures\\Flower.png");
				manager.addFlower(new Flower(Cells.E, Cells.A, ID.Flower, manager, side, hud, picture));
				String message = "Now you can move more than one square at a time!";
				manager.addObject(new MessageNPC(Cells.F, Cells.A, ID.NPC, manager, side, hud, message));
				HUD.setInteractions(interactions + 1);
			}
			else if(interactions == 5) {
				String question = "What code did you use to make the robot pickup the flower?";
				String answer = "robot.pickup(flower);";
				manager.addObject(new StageEndNPC(Cells.D, Cells.A, ID.NPC, manager, side, hud, question, answer));
				HUD.setInteractions(0); 
			}
		}
	
	/**
	 * Sets up stage 2 of level 4 and adds NPCs as necessary
	 */
	public void loopsForeach() {
		int interactions = HUD.getInteractions();
		String tab = "   ";
		if(hud.isStageEnd() == true) {
			Manager.clearAll();
			SidePanel.addText("~Congratulations you completed Stage 1\n\n"
					+ "~Now try stage 2.\n\n"
					+ "~Pick up the flowers for extra points!\n\n"
					+ "~Good luck!\n\n");
			manager.addObject(new Player(Cells.A, Cells.A, ID.Player));
			String message1 = "If you want to repeat some code you can use loops.";
			manager.addObject(new MessageNPC(Cells.H, Cells.A, ID.NPC, manager, side, hud, message1));
			hud.setStageEnd(false);
		}
		if(interactions == 1) {
			String message2 = "There are three types of loops: for each, while and for.\n"
					+ "Note: if you ever need more explanation press the help window.";
			manager.addObject(new MessageNPC(Cells.C, Cells.D, ID.NPC, manager, side, hud, message2));
			File picture = new File("pictures\\flower2.png");
			manager.addFlower(new Flower(Cells.A, Cells.D, ID.Flower, manager, side, hud, picture));
			HUD.setInteractions(interactions + 1);
		}
		else if(interactions == 3) {
			String message3 = "The for each loop has the following syntax:\n" 
					+ "for(ElementType element : collection) {\n"
					+ tab + "loop body\n"
					+ "}";
			manager.addObject(new MessageNPC(Cells.B, Cells.F, ID.NPC, manager, side, hud, message3));
			HUD.setInteractions(interactions + 1);
		}
		else if(interactions == 5) {
			String message4 = "For example for each element flower of type Flower in the collection of flowers, pick up the flower.\n"
					+ "for(Flower flower : flowers) {\n"
					+ tab + "pickup(flower);\n"
					+ "}";
			manager.addObject(new MessageNPC(Cells.G, Cells.H, ID.NPC, manager, side, hud, message4));
			File picture = new File("pictures\\flower3.png");
			manager.addFlower(new Flower(Cells.C, Cells.F, ID.Flower, manager, side, hud, picture));
			HUD.setInteractions(interactions + 1);
		}
		else if(interactions == 7) {
			String message5 = "The for each loop is used for collections only!";
			manager.addObject(new MessageNPC(Cells.G, Cells.E, ID.NPC, manager, side, hud, message5));
			HUD.setInteractions(interactions + 1);
		}
		else if(interactions == 9) {
			String question = "What would the following for each loop print for the collection flower = {rose, tulip, lily}?\n"
					+ "for(Flower flower : flowers) {\n"
					+ tab + "//This print method prints out exactly what is in the brackets.\n"
					+ tab + "print(flower, );\n"
					+ "}";
			String answer = "rose, tulip, lily, ";
			manager.addObject(new StageEndNPC(Cells.E, Cells.D, ID.NPC, manager, side, hud, question, answer));
			HUD.setInteractions(0);
		}
	}
	////////
	
	/**
	 * Sets up stage 3 of level 4 and adds NPCs as necessary
	 */
	public void loopsWhile() {
		int interactions = HUD.getInteractions();
		String tab = "   ";
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
					+ tab + "loop body\n"
					+ "}";
			manager.addObject(new MessageNPC(Cells.B, Cells.F, ID.NPC, manager, side, hud, message2));
			HUD.setInteractions(interactions + 1);
		}
		else if(interactions == 3) {
			String message3 = "For example while the flowers position in the collection of flowers is less than the size of the collection, pick up the flower.\n"
					+ "int flowerIndex = 0;\n"
					+ "while(flowerIndex < flowers.size()) {\n"
					+ tab + "pickup(flower);\n"
					+ tab + "flowerIndex ++;\n"
					+ "}";
			manager.addObject(new MessageNPC(Cells.G, Cells.H, ID.NPC, manager, side, hud, message3));
			File picture = new File("pictures\\flower3.png");
			manager.addFlower(new Flower(Cells.E, Cells.C, ID.Flower, manager, side, hud, picture));
			HUD.setInteractions(interactions + 1);
		}
		else if(interactions == 5) {
			String message4 = "A while loop is used when you have an unknown number of iterations.";
			manager.addObject(new MessageNPC(Cells.C, Cells.B, ID.NPC, manager, side, hud, message4));
			HUD.setInteractions(interactions + 1);
		}
		else if(interactions == 7) {
			String question = "Which flowers would be left unpicked after the following while loop for the collection flower = {rose, tulip, lily}?\n"
					+ "int flowerIndex = 0;\n"
					+ "while(flowerIndex < 2) {\n"
					+ tab + "pickup(flower);\n"
					+ tab + "flowerIndex ++;\n"
					+ "}";
			String answer = "lily";
			manager.addObject(new StageEndNPC(Cells.E, Cells.D, ID.NPC, manager, side, hud, question, answer));
			HUD.setInteractions(0);
		}
	}
	
	/**
	 * Sets up stage 4 of level 4 and adds NPCs as necessary
	 */
	public void loopsFor() {
		int interactions = HUD.getInteractions();
		String tab = "   ";
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
					+ tab + "loop body\n"
					+ "}";
			manager.addObject(new MessageNPC(Cells.B, Cells.F, ID.NPC, manager, side, hud, message3));
			File picture = new File("pictures\\flower3.png");
			manager.addFlower(new Flower(Cells.B, Cells.G, ID.Flower, manager, side, hud, picture));
			HUD.setInteractions(interactions + 1);
		}
		else if(interactions == 3) {
			String message2 = "The loop condition must resolve to a boolean value.";
			manager.addObject(new MessageNPC(Cells.F, Cells.B, ID.NPC, manager, side, hud, message2));
			HUD.setInteractions(interactions + 1);
		}
		else if(interactions == 5) {
			String message4 = "For example for i starting at 0, if i is less than 3, pick up the flower.\n"
					+ "for(int i = 0; i < 3; i++) {\n"
					+ tab + "pickup(flower);\n"
					+ "}";
			manager.addObject(new MessageNPC(Cells.G, Cells.H, ID.NPC, manager, side, hud, message4));
			File picture = new File("pictures\\Flower.png");
			manager.addFlower(new Flower(Cells.A, Cells.E, ID.Flower, manager, side, hud, picture));
			HUD.setInteractions(interactions + 1);
		}
		else if(interactions == 7) {
			String message5 = "You might have noticed that the while and for loops are similar.\n" 
					+ "A for loop is better suited for a known number of iterations.";
			manager.addObject(new MessageNPC(Cells.F, Cells.E, ID.NPC, manager, side, hud, message5));
			HUD.setInteractions(interactions + 1);
		}
		else if(interactions == 9) {
			String question = "Which flowers would be picked up after the following for loop for the collection flower = {rose, tulip, lily}?\n"
					+ "for(int i = 0; i < 1; i++) {\n"
					+ tab + "pickup(flower);\n"
					+ "}";
			String answer = "rose";
			manager.addObject(new StageEndNPC(Cells.E, Cells.D, ID.NPC, manager, side, hud, question, answer));
			HUD.setInteractions(0);
		}
	}
	
	/**
	 * Sets up stage 5 of level 4 and adds NPCs as necessary
	 */
	public void loopsQuiz() {
		int interactions = HUD.getInteractions();
		if(hud.isStageEnd() == true) {
			Manager.clearAll();
			SidePanel.addText("~Congratulations you completed Stage 4\n\n"
					+ "~Now try stage 5.\n\n"
					+ "~Good luck!\n\n");
			manager.addObject(new Player(Cells.A, Cells.A, ID.Player));
			String message = "Lets use loops to draw a flower!\n"
					+ "First try typing: drawFlower();";
			manager.addObject(new MessageNPC(Cells.B, Cells.B, ID.NPC, manager, side, hud, message));
			manager.addObject(new Drawing(interactions, interactions, null, manager, side, hud));
			File picture = new File("pictures\\flower2.png");
			manager.addFlower(new Flower(Cells.B, Cells.D, ID.Flower, manager, side, hud, picture));
			hud.setStageEnd(false);
		}
		if(interactions == 1) {
			String[] questions = {"Which loop should we use to draw a flower?",
					"Correct the following for loop:\n"
					+ "for(i = 1; i < 6; i++) {...\n",
					"What is wrong with the following:\n"
					+ "for(int i = 1; i = 6; i++) {...\n",
					"How many petals will this for loop draw?\n"
					+ "for(int i = 1; i < 6; i++) {\n"
					+ "petal.rotate(72);\n"
					+ "draw(petal);\n"
					+ "}"};
			String [] question1 = {"","for each", "while", "for"};
			String [] question2 = {"","i = 0", "int i = 1", "i--"};
			String [] question3 = {"","Inital condition is not instantiated", "Loop condition does not resolve to boolean", "Nothing"};
			String [] question4 = {"","6", "5", "4"};
			String [][] options = {question1, question2, question3, question4};
			String[] answers = {"for", "int i = 1", "Loop condition does not resolve to boolean", "5"};
			manager.addObject(new MultipleQuestionNPC(Cells.E, Cells.D, ID.NPC, manager, side, hud, questions, answers, options));
			manager.addObject(new Drawing(Cells.D, Cells.D, ID.NPC, manager, side, hud));
			HUD.setInteractions(0);
			
		}
		
	}
	
	//LEVEL 5 SETUP
		
		/**
		 * Sets up stage 1 of level 5 and adds NPCs as necessary.
		 */
		public void classesObjectsIntro() {
			//Get the number of interactions
			int interactions = HUD.getInteractions();
					//Add the second NPC once the first has been interacted with
					if(interactions == 1) {
						String message = "Your robot is hungry, feed him by getting him to eat the apple.\n"
								+ "You can use something like robot.eat(fruit).\n (HINT: Find a substitute for the word \"fruit\"!)";
						String answer = "robot.eat(apple);";
						manager.addObject(new QuestionNPC(Cells.G, Cells.A, ID.NPC, manager, side, hud, message, answer, "pictures/apple.png"));
						//Add an extra interaction to stop the addition of further objects from this method
						HUD.setInteractions(interactions + 1);
						
					}
					//Add the NPCs one at a time for the rest of the stage
					else if(interactions == 3) {
						String message = "Here we have two classes: the Robot and the Item classes. \n" 
								+"Robbie is an Object from the Robot Class. This means he has characteristics (Fields) that are taken from (belong to) the Robot Class. \n"
								+"We'll discuss those in detail later. Continue to the next fruit.";
						manager.addObject(new MessageNPC(Cells.G, Cells.C, ID.NPC, manager, side, hud, message));
						HUD.setInteractions(interactions + 1);
					}
					else if(interactions == 5) {
						String message = "Eat the banana to unlock more information about Classes and Objects.";
						String answer = "robot.eat(banana);";
						manager.addObject(new QuestionNPC(Cells.C, Cells.C, ID.NPC, manager, side, hud, message, answer, "pictures/banana.png"));
						HUD.setInteractions(interactions + 1);
					}
					else if(interactions == 7) {
						String message = "Classes start with a capital letter, while Objects are written with a small letter. \n\n"
								+ "Which Objects have you seen so far in order of introduction!";
						String [] options = {"","banana, robot, apple", "Item, Robot, banana", "robot, apple, banana"};
						String answer = "robot, apple, banana";
						manager.addObject(new MultipleChoiceQNPC(Cells.A, Cells.D, ID.NPC, manager, side, hud, message, options, answer));
						HUD.setInteractions(interactions + 1);
					}
					else if(interactions == 9) {
						String message = "Classes have Methods that help you make an action, but only an Object can \"invoke\" that method.\n\n" + 
								"Which Method have you seen so far!";
						String [] options = {"","robot", "eat()", "Item"};
						String answer = "eat()";
						manager.addObject(new MultipleChoiceQNPC(Cells.D, Cells.E, ID.NPC, manager, side, hud, message, options, answer));
						HUD.setInteractions(interactions + 1);
					}
					else if(interactions == 11) {
						String message = "To create a new object from a Class you need to type it in this form: \n\n" + 
								"ClassName ObjectName = new ClassName(); \n\n" + 
								"(Don't forget to put capital letters where needed!)";
						manager.addObject(new MessageNPC(Cells.F, Cells.F, ID.NPC, manager, side, hud, message));
						HUD.setInteractions(interactions + 1);
					}
					else if(interactions == 13) {
						String question = "Now try to create a friend for your robot from the class Robot and name it buddy!\n"
								+ "(HINT: Substitute the key words from the previous Message with actual names!)";
						String answer = "Robot buddy = new Robot();";
						manager.addObject(new StageEndNPC(Cells.D, Cells.H, ID.NPC, manager, side, hud, question, answer));
						HUD.setInteractions(0);
					}
		}
		
		/**
		 * Sets up stage 2 of level 5 and adds NPCs as necessary.
		 */
		public void classesObjectsFields() {
			int interactions = HUD.getInteractions();
			if(hud.isStageEnd() == true) {
				Manager.clearAll();
				SidePanel.addText("~Well done! The second part of this statement is called the Constructor of a class. "
						+ "We will have a look at it at a later Stage.\n\n"
						+ "~Now try stage 2.\n\n"
						+ "~Good luck!\n\n");
				manager.addObject(new Player(Cells.A, Cells.A, ID.Player));
				String message = "A Class in Java has 3 main components: Fields (Objects' state), a Constructor (Objects' creation) "
						+ "and Methods (Objects' behavior).";
				manager.addObject(new MessageNPC(Cells.A, Cells.E, ID.NPC, manager, side, hud, message));
				hud.setStageEnd(false);
			}
			if(interactions == 1) {
				String message = "Fields can be primitive or reference type: an int, String or of class Item or Robot.\n\n" + 
						"We define them like so:\n\n" + 
						"private DataType/ClassName nameOfField;\n\n"
						+ "If you want to learn more about the key word PRIVATE check the Help Section, Level 5, Access Modifier!";
				manager.addObject(new MessageNPC(Cells.D, Cells.F, ID.NPC, manager, side, hud, message));
				//Add an extra interaction to stop the addition of further objects from this method
				HUD.setInteractions(interactions + 1);
			}
			//Add the NPCs one at a time for the rest of the stage
			else if(interactions == 3) {
				String message = "Define an age field for the Robot Class.\n\n"
						+ "(HINT: Subsittute the words above with actual names!)";
				String answer = "private int age;";
				manager.addObject(new QuestionNPC(Cells.E, Cells.C, ID.NPC, manager, side, hud, message, answer, "pictures/walle.png"));
				HUD.setInteractions(interactions + 1);
			}
			else if(interactions == 5) {
				String message = "Well done! Now define a hat field that is an Object of Item Class.";
				String answer = "private Item hat;";
				manager.addObject(new QuestionNPC(Cells.F, Cells.A, ID.NPC, manager, side, hud, message, answer, "pictures/walle.png"));
				HUD.setInteractions(interactions + 1);
			}
			else if(interactions == 7) {
				String message = "As you can see Fields carry the characteristics of the Class: "
						+ "Objects from the Robot Class will have age and a hat. ";
				manager.addObject(new MessageNPC(Cells.G, Cells.D, ID.NPC, manager, side, hud, message));
				HUD.setInteractions(interactions + 1);
			}
			else if(interactions == 9) {
				String question = "Now write 2 more Fields to the Robot class. The first would be called name and the second one: "
						+ "hasBuddy (which has True or False value)\n\n"
						+ "(HINT: Think carefully what data types those Fields will be!)";
				String answer = "private String name; private boolean hasBuddy;";
				manager.addObject(new StageEndNPC(Cells.H, Cells.G, ID.NPC, manager, side, hud, question, answer));
				HUD.setInteractions(0);
			}
		}
		
		/**
		 * Sets up stage 3 of level 3 and adds NPCs as necessary.
		 */
		public void classesObjectsConstructor() {
			int interactions = HUD.getInteractions();
			String tab = "   ";
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
				HUD.setInteractions(interactions + 1);
			}
			//Add the NPCs one at a time for the rest of the stage
			else if(interactions == 3) {
				String message = "These values can be passed by parameters or hard coded in the Constructor's body.\n\n" + 
						"Examples of both:\n\n" + 
						"public Robot(String fullName, int robotAge) {\n" + 
						"//this is a comment in Java: here we equate the Fields we created earlier to the parameters above\n" + 
						tab + "name = fullName;\n" + 
						tab + "age = robotAge;\n" + 
						"}\n\n" + 
						"public Robot() {\n" + 
						tab + "name = \"Robbie\";\n" + 
						tab + "age = 15;\n" + 
						"}";
				manager.addObject(new MessageNPC(Cells.E, Cells.E, ID.NPC, manager, side, hud, message));
				HUD.setInteractions(interactions + 1);
			}
			else if(interactions == 5) {
				String message = "As you can see, a Class can have multiple Constructors, as long as they differ in parameter signatures.";
				manager.addObject(new MessageNPC(Cells.G, Cells.B, ID.NPC, manager, side, hud, message));
				HUD.setInteractions(interactions + 1);
			}
			else if(interactions == 7) {
				String question = "Write the code to create a Robot Object called robot with name Berty and age 17.\n\n" + 
						"(Be careful how you write the parameters considering their data type!) ";
				String answer = "Robot robot = new Robot(\"Berty\", 17);";
				manager.addObject(new StageEndNPC(Cells.H, Cells.E, ID.NPC, manager, side, hud, question, answer));
				HUD.setInteractions(0);
			}
				
		}
		
		/**
		 * Sets up stage 4 of level 5 and adds NPCs as necessary.
		 */
		public void classesObjectsMethods() {
			int interactions = HUD.getInteractions();
			String tab = "   ";
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
				manager.addObject(new MessageNPC(Cells.G, Cells.C, ID.NPC, manager, side, hud, message));
				//Add an extra interaction to stop the addition of further objects from this method
				HUD.setInteractions(interactions + 1);
			}
			
			else if(interactions == 3) {
				String message = "Here is an example of how a Method is defined within a Class:\n\n" + 
						"public returnType methodName (parameters) {\n" + 
						tab + "action;\n" + 
						tab + "action;\n" + 
						tab + "action;\n" + 
						"}";
				manager.addObject(new MessageNPC(Cells.E, Cells.D, ID.NPC, manager, side, hud, message));
				HUD.setInteractions(interactions + 1);
			}
			else if(interactions == 5) {
				String message = "Methods that RETURN an Object have the same returnType as Object (e.g. String, int, Robot) "
						+ "and are called ACCESSOR Methods";
				manager.addObject(new MessageNPC(Cells.B, Cells.E, ID.NPC, manager, side, hud, message));
				HUD.setInteractions(interactions + 1);
			}
			else if(interactions == 7) {
				String message = "Methods that change the state of an Object and do not have a RETURN statement have a VOID "
						+ "returnType and are called MUTATOR Methods.";
				manager.addObject(new MessageNPC(Cells.D, Cells.F, ID.NPC, manager, side, hud, message));
				HUD.setInteractions(interactions + 1);
			}
			else if(interactions == 9) {
				String message = "Which type of Method is the following: \n\n" + 
						"public String getName() {\n" + 
						tab + "return name;\n" + 
						"}\n";
				String [] options = {"","accessor", "mutator"};
				String answer = "accessor";
				manager.addObject(new MultipleChoiceQNPC(Cells.H, Cells.F, ID.NPC, manager, side, hud, message, options, answer));
				HUD.setInteractions(interactions + 1);
			}
			else if(interactions == 11) {
				String message = "Which type of Method is the following:\n\n" + 
						"public void changeName(String newName) {\n" + 
						tab + "name = newName;\r\n" + 
						"}\n";
				String [] options = {"","accessor", "mutator"};
				String answer = "mutator";
				manager.addObject(new MultipleChoiceQNPC(Cells.E, Cells.G, ID.NPC, manager, side, hud, message, options, answer));
				HUD.setInteractions(interactions + 1);
			}
			else if(interactions == 13) {
				String question = "Write the code to change robot's name to Robbie!";
				String answer = "robot.changeName(\"Robbie\");";
				manager.addObject(new StageEndNPC(Cells.A, Cells.G, ID.NPC, manager, side, hud, question, answer));
				HUD.setInteractions(0);
			}
			
		}
		
		/**
		 * Sets up stage 5 of level 5 and adds NPCs as necessary
		 */
		public void classesObjectsQuiz() {
			int interactions = HUD.getInteractions();
			if(hud.isStageEnd() == true) {
				Manager.clearAll();
				SidePanel.addText("~Congratulations you completed all 4 Stages!\n\n"
						+ "~Go to the last robot buddy to see what he has for you!\n\n");
				manager.addObject(new Player(Cells.A, Cells.A, ID.Player));
				String message1 = "Now take the Classes and Objects Quiz to complete the Level!\n\nGood luck!\n\n";
				manager.addObject(new MessageNPC(Cells.C, Cells.C, ID.NPC, manager, side, hud, message1));
				hud.setStageEnd(false);
			}
			if(interactions == 1) {
				String[] questions = {"Which of the following can be a Class Name?\n\n", "What structure does a Class follow?","What are Fields used for?\n\n",
						"Can a Class have more than one Constuctor?\n\n",
						"Write a Method to set the age in the Robot Class!\n(Choose the correct option)"
						};
				String [] question1 = {"","int", "Boolean", "Message", "triangle"};
				String [] question2 = {"","Constructor, Fields, Methods", "Methods, Constructor, Fields", "Fields, Constructor, Methods", "Fields, Methods, Constructor"};
				String [] question3 = {"","For storing values within Objects", "To allow Objects to do actions", "To ease Class creation", "To add parameters"};
				String [] question4 = {"","Yes", "No", "Yes, but not with the same parameter signatures", "Yes, but only if it has the same parameters"};
				String [] question5 = {"","String setAge(String newAge) {age = newAge;}","public void setAge(int newAge) {age = newAge;}", "public setAge(int age) {age = age;}", "public void setAge(int newAge) {newAge = age;}"};
				String [][] options = {question1, question2, question3, question4, question5};
				String[] answers = {"Message", "Fields, Constructor, Methods","For storing values within Objects", "Yes, but not with the same parameter signatures", "public void setAge(int newAge) {age = newAge;}"};
				manager.addObject(new MultipleQuestionNPC(Cells.D, Cells.E, ID.NPC, manager, side, hud, questions, answers, options));
				HUD.setInteractions(0);
			}
		}

}

