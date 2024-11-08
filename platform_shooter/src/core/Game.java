package core;

import java.awt.Canvas;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import model.OtherPlayer;
import view.Window;
import view.tile.TileManager;


public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = -5505267217615912489L;

	private Thread thread;
	private boolean running = false;
	
	public static final int WIDTH = 16 * 48, HEIGHT = 12 * 48;
	
	public static final int TILE_SIZE = 24;
	
	public static final int MAP_COL = 106;
	public static final int MAP_ROW = 106;
	
	public static final Font DEFAULT_FONT = new Font("Lucida Grande", Font.PLAIN, 13);
	public static final Font DEFAULT_FONT_LARGE = new Font("Lucida Grande", Font.PLAIN, 26);
	
	public static PlayerHandler playerHandler;
	
	public static TileManager tileManager;
	
	public static Input input;

	
	public static GameState gamestate = GameState.MainMenu;
	
	public enum GameState {
		Paused(),
		Running(),
		MainMenu()
	}
	
	public static void main(String[] args) 
	{
		new Game();
		
	}
	
	public Game() {
		playerHandler = new PlayerHandler();

		gamestate = GameState.Running;
		
		input = new Input();
		
		this.addKeyListener(input);
		this.addMouseListener(input);
		this.addMouseMotionListener(input);
		
		tileManager = new TileManager();
		
		new Window(WIDTH, HEIGHT, "RUSTFALL", this);
	}
	
	/**
     * Starts the game loop by creating and starting a new thread.
     */
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	/**
     * Stops the game loop by joining the thread.
     */
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				delta--;
			}
			if(running)
				render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				System.out.println("FPS: " + frames);
				timer += 1000;
				frames = 0;
			}
			
		}
		stop();
		
	}
	
	private void tick() {
		playerHandler.tick();
	}
	
	/**
     * Renders in-game objects on the screen during the game.
     * Manages different rendering states based on the game state.
     */
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		if(gamestate == GameState.Running) {
			//rendering the tilemanager renders the background map
			tileManager.render(g);
			
			playerHandler.render(g);
			
			//hud.render(g);
		}
		/*if(gamestate == GameState.MainMenu) {
			MainMenu.render(g);
		}
		if(gamestate == GameState.Paused) {
			PauseMenu.render(g);
		}*/
		g.dispose();
		bs.show();
	}

}
