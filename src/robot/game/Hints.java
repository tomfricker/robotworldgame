package robot.game;

import java.util.HashMap;

/**
 * This class creates possible hint for stated incorrect answers.
 * It then compares the users input with the possible incorrect answers to find a relevant hint and if one is available returns the hint.
 * 
 * @author MissH
 *
 */

public class Hints {
	
	//contains all available hints
	private HashMap<String, String> hintMap;
	
	/**
	 * Constructor for Hints
	 */
	public Hints() {
		hintMap = new HashMap<>();
		chooseHintMap();
	}
	
	/**
	 * Returns chosen hint if available, if not returns message
	 * @param input The users answer
	 * @return The hint or the given message
	 */
	public String giveHint(String input) {
		String chosen = hintMap.get(input);
		if(chosen != null) {
			return chosen;
		}
		return chooseGeneralHint();
	}
	
	/**
	 * Adds possible incorrect answers and their corresponding hint
	 */
	private void chooseHintMap() {
//LEVEL 1
		//level 1 stage 2
		hintMap.put("robots = 8;", 
				"Don't forget to declare a data type!");
		hintMap.put("int robots = 8", 
				"I think you forgot something on the end;");
		hintMap.put("int Robots = 8;", 
				"Robots or robots?");
		
		//level 1 stage 3
		hintMap.put("name = \"Robbie\";", 
				"Don't forget to declare a data type!");
		hintMap.put("String name = \"Robbie\"", 
				"I think you forgot something on the end;");
		hintMap.put("String name = Robbie;", 
				"A String is alway contained in inverted commas!");

		
//LEVEL 2
		//level 2 stage 1
		hintMap.put("eat(apple);", 
				"Who is eating the apple?");
		hintMap.put("robot.eat(apple)", 
				"I think you forgot something on the end;");
		
		hintMap.put("eat(banana);", 
				"Who is eating the apple?");
		hintMap.put("robot.eat(banana)", 
				"I think you forgot something on the end;");
		
		hintMap.put("Robot buddy = Robot();", 
				"Don't forget you are creating something new!");
		hintMap.put("Robot buddy = new Robot()", 
				"I think you forgot something on the end;");
		hintMap.put("buddy = new Robot();", 
				"First you have to say what buddy is going to be!");
		hintMap.put("Robot buddy = new Robot;", 
				"Don't forget the brackets on the end!");
		hintMap.put("Robot robot = Buddy;", 
				"Think about the form you were given: ClassName ObjectName = new ClassName();");
		hintMap.put("Robot = buddy;", 
				"Don't forget you are creating something new! Think about the form you were given: ClassName ObjectName = new ClassName();");
		
		//level 2 stage 2
		hintMap.put("private int age", 
				"I think you forgot something on the end;");
		hintMap.put("private age;", 
				"Don't forget to declare a data type!");
		hintMap.put("int age;", 
				"This field should be private.");
		hintMap.put("private int Robot age", 
				"Think about the form you were given: private DataType nameOfField;;");
		hintMap.put("private Robot age", 
				"Think about the form you were given: private DataType nameOfField;;");
		
		hintMap.put("private Item hat", 
				"I think you forgot something on the end;");
		hintMap.put("private hat;", 
				"This hat is an Item.");
		hintMap.put("Item hat;", 
				"This field should be private.");
		
		hintMap.put("private String name; private boolean hasBuddy", 
				"I think you forgot something on the end of one of your statements;");
		hintMap.put("private String name, private boolean hasBuddy", 
				"I think you forgot something on the end;");
		hintMap.put("private String name, private boolean hasBuddy;", 
				"I think you forgot something on the end of one of your statements;");
		hintMap.put("private String name;", 
				"There should be two statements.");
		hintMap.put("private String name; private hasBuddy;", 
				"hasBuddy will either be true or false.");
		
		//level 2 stage 3
		hintMap.put("Robot robot = new Robot(\"Berty\", 17)", 
				"I think you forgot something on the end;");
		hintMap.put("Robot robot = new Robot(Berty, 17);", 
				"A String is alway contained in inverted commas!");
		hintMap.put("Robot robot = new Robot(\"Berty\");", 
				"You can have two parameters in the brackets, i.e. (name, age).");
		hintMap.put("Robot robot = Robot(\"Berty\", 17);", 
				"Don't forget you are creating something new!");
		hintMap.put("robot = new Robot(\"Berty\", 17);", 
				"First you have to say what robot is going to be!");
		hintMap.put("public Robot(String fullName, int robotAge) {name = \"Berty\", age = 17;}", 
				"You only need to set the robots name and age, not define the method.");
		
		//level 2 stage 4
		hintMap.put("robot.changeName(\"Robbie\")", 
				"I think you forgot something on the end;");
		hintMap.put("changeName(\"Robbie\");", 
				"Whos name are you changing?");
		hintMap.put("robot.changeName(Robbie)", 
				"A String is alway contained in inverted commas!");
		hintMap.put("Robot robot (String = newName(\"Robbie\");", 
				"You want to use the method: changeName()");
		hintMap.put("Robot name = newName(\"Robbie\");", 
				"You want to use the method: changeName()");
		hintMap.put("public void changeName(String newName) {name = \"Robbie\";}", 
				"You only need to change the robots name, not define the method.");
		
		//level 2 stage 5
		hintMap.put("public void setAge(int newAge) {age = newAge}", 
				"I think you forgot something on the end;");
		hintMap.put("public void setAge(int newAge)", 
				"You are writing a method, you should have what you want the method to do in curly brackets.");
		hintMap.put("setAge(int newAge) {age = newAge;}", 
				"You are writing a method, start with: public void");
//LEVEL 3
		
//LEVEL 4
		//level 4 stage 1
		hintMap.put("robot.pickup(flower)", 
				"I think you forgot something on the end;");
		hintMap.put("pickup(flower);", 
				"Who is picking up the flower?");
		hintMap.put("robot.pick(flower);", 
				"The method is: pickup");
		hintMap.put("robot.pickUp(flower);", 
				"The method is: pickup");
		
		//level 4 stage 2
		hintMap.put("rose, tulip, lily", 
				"The print method prints everything in the brackets (including commas and spaces on the end).");
		hintMap.put("rose, tulip, lily,", 
				"The print method prints everything in the brackets (including spaces on the end).");
		
		//level 4 stage 5
		hintMap.put("int i = 1", 
				"I think you forgot something on the end;");
		
		
		
		
	}
	
	/**
	 * Returns general hints based on level, stage and number of interactions
	 * @return
	 */
	private String chooseGeneralHint() {
		if(HUD.getLevel() == 1) {
			if(HUD.getStage() == 2) {
				return "Your answer should be in this form: DataType Name = Assignment;";
			}
			else if(HUD.getStage() == 3) {
				return "Your answer should be in this form: DataType Name = Assignment;";
			}
		}
		else if(HUD.getLevel() == 2) {
			if(HUD.getStage() == 1) {
				if(HUD.getInteractions() == 2) {
					return "Replace fruit with the name of the fruit you want the robot to eat.";
				}
				else if(HUD.getInteractions() == 6) {
					return "Replace fruit with the name of the fruit you want the robot to eat.";
				}
				else if(HUD.getInteractions() == 0) {
					return "Your answer should be in this form: NameOfClass NameOfObject = new NameOfClass();";
				}
			}
			else if(HUD.getStage() == 2) {
				if(HUD.getInteractions() == 4) {
					return "Replace DataType and NameOfField: private DataType NameOfField;";
				}
				else if(HUD.getInteractions() == 6) {
					return "Replace ClassName and NameOfField: private ClassName NameOfField;";
				}
				else if(HUD.getInteractions() == 0) {
					return "Your answer should be in this form: private DataType NameOfField; private DataType NameOfField;";
				}
			}
			else if(HUD.getStage() == 3) {
				return "Your answer should be in this form: NameOfClass NameOfObject = new NameOfClass(String, int);";
			}
			else if(HUD.getStage() == 4) {
				return "Replace MethodName and String: robot.MethodName(String);";
			}			
		}
		else if(HUD.getLevel() == 4) {
			if(HUD.getStage() == 1) {
				return "The method you should use is pickup: robot.MethodName(ObjectName);";
			}
			else if(HUD.getStage() == 2) {
				return "Your answer should be in this form: flowerName, flowerName, flowerName, ";
			}
			else if(HUD.getStage() == 3) {
				return "The flowers are numbered like this: 0) rose, 1) tulip, 2) lily";
			}
			else if(HUD.getStage() == 4) {
				return "The flowers are numbered like this: 0) rose, 1) tulip, 2) lily";
			}
		}
		return "That's not quite right, try again.";
	}

}
