package robot.game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {

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
		
		//level = new Level(manager, side, 1);
		//level = new Level(manager, side, 2);
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		}catch(Exception e){
			e.printStackTrace();
		}
	}
		
	public void run() {
		//ensures that the board is in focus at start of game
		this.requestFocus();
		//this part handles the frame counter and I'm not sure it's necessary for the game to actually run. I haven't bothered to check yet. 
		//have since checked.... just leave it here. 
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
	 * This method will return an integer within the bounds set ia the parameters
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
	
	
	
} //end of class
	



