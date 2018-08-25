package robot.game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {

	/**
	 * The Game class is an important class for the most basic functionality of the RobotWorld game.
	 * Game sets up the window in which the game is run as well as important methods for progressing 
	 * the game loop.
	 * 
	 * This class controls the game's overall state and the updating and rendering of game objects. 
	 * the update (tick) and render methods rely heavily on the Manager class as it is called to 
	 * carry out updating and rendering during the game loop. 
	 * 
	 * This class also contains the background for each level in the game. in the render method and
	 * ensures that all moving objects within the game are contained within the boundaries of the 
	 * game's window
	 * 
	 * @author RobotWorld Group 
	 */
	private static final long serialVersionUID = 5246413982503020397L;
	public static final int WIDTH = 860, HEIGHT = 640;
	private Thread thread;
	private boolean running = false;
	static Manager manager;
	private static final String TITLE = "Robot World";
	public static int boardIndex;
	static SidePanel side;
	private MainMenu menu;
	private HUD hud;
	private Spawner spawner;
	
	public enum STATE {
		Menu,
		Game,
		End
	};
	
	public static STATE gameState = STATE.Menu;
	
	public Game() {
		//creates the window for the game to run in and the manager to control objects in the game. 
		manager = new Manager();
		SidePanel side = new SidePanel();
		CodePanel code = new CodePanel();
		hud = new HUD();
		menu = new MainMenu(this, manager, side, hud, code);
		spawner = new Spawner(manager, hud, side);
		this.addKeyListener(new KeyInput(manager));
		//this.addMouseListener(new MouseInput(manager));
		this.addMouseListener(menu);
		
		new Window(WIDTH, HEIGHT, TITLE, this, side, code);
		
		//creates board index values (testing)
		boardIndex = HEIGHT/8;
		
		side.setText("Welcome to RobotWorld Game! Please check the Help Menu for instructions!");
		//level = new Level(manager, side, 1);
		//level = new Level(manager, side, 2);
	}
	
	/**
	 * Creates a new thread and allows the game to be run.
	 */
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	/**
	 * Ends the thread started by start() and stops the game running.
	 */
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		}catch(Exception e){
			e.printStackTrace();
		}
	}
		
	/**
	 * This method contains the game loop. Whilst the game is running, it progresses a timer and,
	 * when time has progressed, it calls the game to update itself. It does so by calling tick()
	 * (update all game objects in the manager) and render to then show the updated GameObjects objects.
	 * This is only carried out if the game is running however. Otherwise, the game is stopped. 
	 */
	public void run() {
		//ensures that the board is in focus at start of game
		this.requestFocus();
		long lastTime =System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime)/ns;
			lastTime = now;
			while(delta >=1) {
				tick(); //this is the method call that updates objects presently in the game. 
				delta--;
			}
			if(running) {
				//the game then draws all of its objects into the game. 
				render();
			}
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
			}
		}
		stop();
	}
	
	/**
	 * Calls for the game to update itself. This calls the Manager to iterate over a linkedList of 
	 * GameObjects and update each in turn. If the Game is in the STATE.Game enum state, 
	 * the Hud and the Spawner update. This also calls the Manager to reset all GameObjects if the Hud
	 * detects the end of a level.
	 * 
	 *  If the game is in the STATE.Menu enum state, the tick method is responsible for calling the menu 
	 *  to update
	 * 
	 */
	private void tick() {
		manager.tick();
		
		if(gameState == STATE.Game) {
			hud.tick();
			spawner.tick();
			
			if(hud.isLevelEnd() == true) {
				Manager.clearAll();
				gameState = STATE.End;
			}
		}
		else if(gameState == STATE.Menu || gameState == STATE.End) {
			menu.tick();
		}
	}

	/**
	 * Render uses a buffer strategy to manage the rendering of all objects in the game. 
	 * 
	 * The method first renders a chess board using black and white squares which serves 
	 * as the background for each level of the game. 
	 * Secondly, the Manager is called to render all of the objects stored in its objects LinkedList.
	 * 
	 * Finally, the Method renders the hud or the menu depending on which state the game is in. 
	 * 
	 */
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		//calculates the correct dimensions for squares on a chessboard
		int x = 0;
		int y = 0;
		int squareHeight = HEIGHT/8;
		int squareWidth = squareHeight;
				
		//sets the background to a chessboard pattern
		for(int j = 0; j < 8; j++) {
			for(int i = 0; i < 8; i++) {
				if((i + j) % 2 == 0) {
					g.setColor(Color.white);
					g.fillRect(x, y, squareWidth, squareHeight);
				}
				else {
					g.setColor(Color.black);
					g.fillRect(x, y, squareWidth, squareHeight);
				}
				x += squareWidth;
			}
			y += squareHeight;
			x = 0;
		}
		
		manager.render(g);
		
		if(gameState == STATE.Game) {
			hud.render(g);
		}
		else if(gameState == STATE.Menu || gameState == STATE.End) {
			menu.render(g);
		}
		
		g.dispose();
		bs.show();
	}
	
	/**
	 * This method will return an integer within the bounds set via the parameters
	 * passed in. This is useful for setting boundaries that players and other
	 * characters must adhere to.
	 * @param var
	 * @param min
	 * @param max
	 * @return int
	 */
	public static int clamp(int var, int min, int max) {
		if(var >= max)
			return max;
		if(var <= min)
			return min;
		else
			return var;
	}

	/**
	 * Creates a new game
	 */
	public static void main(String args[]) {
		new Game();
	}
	
	
	
}
	



