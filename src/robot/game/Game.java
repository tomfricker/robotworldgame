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
	private Manager manager;
	
	public Game() {
		//creates the window for the game to run in and the manager to control objects in the game. 
		new Window(WIDTH, HEIGHT, "Robot World", this );
		manager  = new Manager();
		
		//adds players into the game
		manager.addObject(new Board(0,0,ID.background));
		manager.addObject(new Player(100,100,ID.cat));
		manager.addObject(new Player(400,400, ID.mouse));
		
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
				tick();
				delta--;
			}
			if(running) {
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
	
	//testing a commit due to issues on my end (James)



