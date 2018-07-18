package robot.game;

import java.awt.Canvas;
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
	
	public Game() {
		//creates the window for the game to run in and the manager to control objects in the game. 
		manager = new Manager();
		this.addKeyListener(new KeyInput(manager));
		this.addMouseListener(new MouseInput(manager));
		SidePanel side = new SidePanel();
		new Window(WIDTH, HEIGHT, TITLE, this, side);
		
		//creates board index values (testing)
		boardIndex = HEIGHT/8;
		
		//add the player to the game objects and display it on the board
		manager.addObject(new Board(0,0,ID.background));
		manager.addObject(new Player(Cells.A, Cells.A, ID.Player));
		manager.addObject(new LevelHelenNPC(Cells.B, Cells.A, ID.NPC, manager, side));
		
		side.setText("~Welcome to level Helen of Robot World!\n\n"
				+ "~Please move the Robot around the board by typing in code, e.g. robot.move(right);\n\n"
				+ "~Good luck and enjoy your adventure in Robot World!\n\n");
		
		//Level level = new Level(1);
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
		int frames = 0;
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
				frames++;
			}
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}
	
	private void tick() {
		manager.tick();
	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		manager.render(g);
		
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
	



