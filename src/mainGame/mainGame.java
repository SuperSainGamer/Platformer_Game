package mainGame;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Random;

import framework.KeyInput;
import framework.MouseInput;
import framework.ObjectID;
import framework.gameObject;
import objects.Block;
import objects.Enemy;
import objects.EnemySpawnBlock;
import objects.Player;

public class mainGame extends Canvas implements Runnable {

	private static final long serialVersionUID = -227384587330048025L;

	private boolean running = false;
	private Thread thread;
	public static int WIDTH, HEIGHT;
	private BufferedImage level=null;
	
	
//local player variables
	public static int direction = 2;
//	private static int lastDir=0;
	
//Enemy local variables
	private long currentTime = System.currentTimeMillis();	
	private int location = 0;
	private long nextSpawn = 0;
	private long spawnDelay=500;
	
	public static int HEALTH =100;

	
	
/***********************************/
	
	// obj

	
	Handler handler;
	Camera cam;	
	Random rand = new Random();

	private void init() {
		WIDTH = getWidth();
		HEIGHT = getHeight();
		
		bufferedImageLoader loader = new bufferedImageLoader();
		level = loader.loadImage("/TimeJamLevel.png");//loading the level

		handler = new Handler();

		//handler.addObject(new Player(100, 100, handler, ObjectID.Player));

		//handler.creatlvl();
		
		cam = new Camera(0,0);

		loadImageLvl(level);
		
		this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(new MouseInput(handler,this));
		this.addMouseMotionListener(new MouseInput(handler,this));
	}

	public synchronized void start() {
		if (running)
			return;

		running = true;
		thread = new Thread(this);
		thread.start();
	}

	public void run() {
		init();
		this.requestFocus();

		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames + " TICKS: " + updates);
				//System.out.println("Key pressed"+KeyInput.getKeys());
				frames = 0;
				updates = 0;
			}
		}

		System.out.println("Thread has begun");
	}

	private void tick() {
		handler.tick();
//	    System.out.println("Key pressed"+KeyInput.getKeys());
//		System.out.println("X: "+MouseInput.mx+" Y: "+MouseInput.my);
		gameWindow.mouseXlbl.setText("Direction "+direction+" X: "+MouseInput.mx+" Y: "+MouseInput.my);
		

		

		
	for(int i=0; i<handler.object.size();i++) {
		gameObject tempObject = handler.object.get(i);
		
		if(tempObject.getId()==ObjectID.EnemySpawnBlock) {
			currentTime= System.currentTimeMillis();

			
			
			if(KeyInput.getKeys().get(88)) {
//				handler.addObject(new Enemy(tempObject.getX(),tempObject.getY(),handler,ObjectID.Enemy));
				if(nextSpawn<currentTime){
					handler.addObject(new Enemy(tempObject.getX(),tempObject.getY()-60,handler,ObjectID.Enemy));
		        	nextSpawn =  currentTime+spawnDelay;
		    	}
			}
		}
		if(tempObject.getId()==ObjectID.Enemy) {
			if(KeyInput.getKeys().get(90)) {
			}
			//	tempObject.setAttackVelX(-2f);
		}
		
		if(tempObject.getId()==ObjectID.Bullet) {

			tempObject.setAttackVelX(MouseInput.run);
			tempObject.setAttackVelY(MouseInput.rise);
		}
		
		if(tempObject.getId()== ObjectID.Player)
			cam.tick(tempObject);
		
			//Left
			if(KeyInput.getKeys().get(65)) 
			{
				tempObject.setVelX(-5);
				direction=1;
			}
			//Right
			if(KeyInput.getKeys().get(68))
			{
				tempObject.setVelX(5);
				direction=2;
			}
			//Space
			if (KeyInput.getKeys().get(32) && !tempObject.isJumping()) 
			{
				tempObject.setJumping(true);
				tempObject.setVelY(-20);
			}
			//Up
			if(KeyInput.getKeys().get(87)){
				direction=0;
			}
			//Down shot
			if(KeyInput.getKeys().get(32)){
				if(KeyInput.getKeys().get(83)){
					direction=3;
				}
			}	
	}
}
	
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;


/**************************************************************/		
		g.setColor(Color.black);
		g.fillRect(0, 0, getWidth(), getHeight());	
		
		for(int i=0; i<handler.object.size();i++) {
			gameObject tempObject = handler.object.get(i);
			if(tempObject.getId()== ObjectID.Player) {	
				
				g.setColor(Color.white);
				//g2d.drawLine((int)tempObject.getX(),(int)tempObject.getY(), MouseInput.run, MouseInput.rise);
				
			}
		}	
		
		g2d.translate(cam.getX(), cam.getY());
		handler.render(g);
		g2d.translate(-cam.getX(), -cam.getY());

/**************************************************************/
		g.dispose();
		bs.show();
	}


	private void loadImageLvl(BufferedImage image) {
		int w = image.getWidth();
		int h = image.getHeight();
		
		System.out.println("Width, Height "+w+" "+h);
		for(int xx=0;xx<h;xx++) {
			for(int yy=0; yy<w;yy++) {
				
				int pixel = image.getRGB(xx, yy);
				int red= (pixel >> 16) & 0xff;
				int green= (pixel >> 8) & 0xff;
				int blue= (pixel) & 0xff;
				
				if(red == 255 && green == 255 && blue == 255) {
					handler.addObject(new Block(xx*32, yy*32, ObjectID.Block));
				}
				
				if(red == 237 && green == 28 && blue == 36) {
					handler.addObject(new Player(xx*16, yy*32, handler,this, ObjectID.Player));
				}
				if(red == 230 && green == 150 && blue == 30) {
					handler.addObject(new Enemy(xx*32, yy*32,handler, ObjectID.Enemy));
				}	
				// r = 250 g = 100 b = 30
				if(red ==250 && green == 100 && blue == 30) {
					handler.addObject(new EnemySpawnBlock(xx*32, yy*32,handler, ObjectID.EnemySpawnBlock));
				}
				
			}
		}
	}
	
	
	
	
	
	public static void main(String[]args) {
		new gameWindow(800, 600, "Time Jam", new mainGame());
	}

}
