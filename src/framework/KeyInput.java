package framework;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.BitSet;

import mainGame.Handler;

public class KeyInput extends KeyAdapter{
	
	Handler handler;
	private final static BitSet keys = new BitSet();
	public static BitSet getKeys() {
		return keys;
	}	
	
	
	
		public KeyInput(Handler handler)
		{
			this.handler = handler;
		}
	

	public void keyPressed(KeyEvent e) 
	{
		int key = e.getKeyCode();		

        keys.set(e.getKeyCode());

		
		
//		for(int i = 0; i<handler.object.size(); i++) 
//		{
//			gameObject tempObject = handler.object.get(i);
//			
//			if(tempObject.getId()==ObjectID.Player)
//			{
//				if(key == KeyEvent.VK_D) tempObject.setVelX(5);
//				if(key == KeyEvent.VK_A) tempObject.setVelX(-5);
//				if(key == KeyEvent.VK_W && !tempObject.isJumping()) 
//					{
//					System.out.println("is Jumping"+tempObject.isJumping());
//					tempObject.setJumping(true);
//					tempObject.setVelY(-20);
//					}
//				if(key == KeyEvent.VK_S) tempObject.setVelY(10);
//
//
//			}
//		}
		
		
		
		
		if(key == KeyEvent.VK_ESCAPE ) 
		{
			System.exit(1);
		}
		
		
		
	}
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();		
		
		keys.clear(key);
		for(int i = 0; i<handler.object.size(); i++) 
		{
			gameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId()==ObjectID.Player)
			{
				if(key == KeyEvent.VK_D) tempObject.setVelX(0);
				if(key == KeyEvent.VK_A) tempObject.setVelX(0);
				if(key ==KeyEvent.VK_W) tempObject.setVelY(0);
				if(key ==KeyEvent.VK_S) tempObject.setVelY(0);

			}
		
	}
	
	}
}
