package npcs;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import robot.game.Game;
import robot.game.GameObjects;
import robot.game.HUD;
import robot.game.Hints;
import robot.game.ID;
import robot.game.Manager;
import robot.game.SidePanel;

/**
 * NPC stands for non playable character.
 * 
 * This class extends GameObjects so that it can be easily stored within the Manager
 * class' list to be iterated over. It's function within the game is to ask the 
 * the user a question and validate the answer.
 * 
 * It will ask it's question within a pop up window. It then waits for the user
 * to input an answer or cancel it's interaction. If the user inputs an incorrect
 * answer it will deduct points from their score and display the answer they put
 * and an incorrect message to the SidePanel. If they are correct they are 
 * awarded points and told they are correct within the SidePanel. 
 * 
 * @author Robot World Group
 *
 */
public class QuestionNPC extends GameObjects {
	
	//Fields of the LevelEndNPC
	private HUD hud;
	boolean interacted;
	private String question, answer;
	private String picture;
	private Hints hints;

	/**
	 * Constructor for the QuestionNPC
	 * @param x
	 * @param y
	 * @param id
	 * @param manager
	 * @param side
	 * @param hud
	 * @param question
	 * @param answer
	 */
	public QuestionNPC(int x, int y, ID id, Manager manager, SidePanel side, HUD hud, String question, String answer, String picture) {
		super(x, y, id);
		this.hud = hud;
		interacted = false;
		this.question = question;
		this.answer = answer;
		this.picture = picture;
		hints = new Hints();
	}

	/**
	 * Having the interact method within tick will ensure that if the player comes across the NPC in the game it
	 * will display it's question to the user.
	 */
	public void tick() {
		if (!interacted)
			interact();
	}

	/**
	 * Displays the image of the NPC on the board at the specified position.
	 */
	public void render(Graphics g) {
		if(interacted == false) {
			File imageFile = new File(picture);
			try {
				Image robot = ImageIO.read(imageFile);
				g.drawImage(robot, x, y, null);
			} catch (IOException e) {
				e.printStackTrace();
			}
			}
			else {
				File imageFile = new File("pictures\\walleTick.png");
				try {
					Image robot = ImageIO.read(imageFile);
					g.drawImage(robot, x, y, null);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
	}
	
	
	/**
	 * This method sets up the interactions between the player object and the NPC.
	 * This character asks for the answer to a question then displays both correct and incorrect answers typed in the side panel.
	 */
	public void interact() {
		for(GameObjects gameObject : Manager.objectList) {
			if(gameObject.getId() == ID.Player) {
				if(gameObject.getX() == x && gameObject.getY() == y) {
					String input = JOptionPane.showInputDialog(null, question, "Open Answer Question", JOptionPane.QUESTION_MESSAGE);
					if(input == null || input.length() == 0) {
						gameObject.setX(gameObject.getX()-Game.boardIndex);
					}
					//if the answer is incorrect the player can continue to move round the board and the interactions can still take place
					else if (!input.equals(answer)) {
						SidePanel.addText("~" + input + "\n");
						SidePanel.addText("~incorrect\n");
						SidePanel.addText("~You lost 10 points\n\n");
						//gives a hint
						String hint = hints.giveHint(input);
						SidePanel.addText("~" + hint + "\n\n");
						
						gameObject.setX(gameObject.getX()-Game.boardIndex);
						int currentScore = hud.getScore();
						if(currentScore > 0)
							hud.setScore(currentScore - 10);
					}
					//if the player is correct increase interactions and move on
					else if(input.equals(answer)) {
						hud.setScore(hud.getScore() + 50);
						SidePanel.addText("~" + question + "\n\n"
							+ "~" + input + "\n"
							+ "~correct\n\n");
						interacted = true;
						//hud.setLevelEnd(true);
						HUD.setInteractions(HUD.getInteractions() + 1);
						
						
					}
				}
			}
		}
	}
}
