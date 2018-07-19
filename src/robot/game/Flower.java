package robot.game;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.imageio.ImageIO;

public class Flower extends GameObjects {
	
	Manager manager;
	SidePanel side;
	
	ArrayList<String> flowers = new ArrayList<String>();
	static LinkedList<GameObjects> flowerList = new LinkedList<>();

	public Flower(int x, int y, ID id, Manager manager, SidePanel side) {
		super(x, y, id);

		this.manager = manager;
		this.side = side;
		
		addFlowerURL();
	}

	@Override
	public void tick() {

		
	}

	@Override
	public void render(Graphics g) {
		File imageFile = new File(flowers.get(1));
		try {
			Image flower = ImageIO.read(imageFile);
			g.drawImage(flower, x, y, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void addFlowerURL() {
		flowers.add("C:/Users/MissH/git/RobotWorld/src/robot/game/Flower.png");
		flowers.add("C:/Users/MissH/git/RobotWorld/src/robot/game/flower2.png");
		flowers.add("C:/Users/MissH/git/RobotWorld/src/robot/game/flower3.png");
	}
	
	public void chooseFlower() {
		for(String flower : flowers) {
			flowers.indexOf(flower);
		}
	}
	
	public void addFlower(GameObjects flower) {
		flowerList.add(flower);
	}
	
	public static void removeFlower(GameObjects flower) {
		flowerList.remove(flower);
	}


}
