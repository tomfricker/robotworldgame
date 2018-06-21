package robot.game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {

	
	private static final long serialVersionUID = 5246413982503020397L;
	public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
	private Thread thread;
	private boolean running = false;
	static Manager manager;
	
	public Game() {
		//creates the window for the game to run in and the manager to control objects in the game. 
		new Window(WIDTH, HEIGHT, "Robot World", this );
		manager  = new Manager();
		
		//creates a level (there's only 1 now)
		// to do: I'm thinking this should be moved into manager.
		Level level = new Level(1);
	
		
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
				//the game then draws all of it's objects into the game. 
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
		
		g.setColor(Color.blue);
		g.fillRect(0,0,WIDTH,HEIGHT);
		
		manager.render(g);
		
		g.dispose();
		bs.show();
	}

	public static void main(String args[]) {
		new Game();
		}
	
	
	} //end of class
	



