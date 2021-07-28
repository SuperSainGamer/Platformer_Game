package framework;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import mainGame.Handler;
import mainGame.mainGame;
import objects.Bullet;



public class MouseInput extends MouseAdapter implements MouseMotionListener {
	
	public static int mx;
	public static int my;
	public static int run;
	public static int rise;
	
	Handler handler;
	public static Point mousePos;

	public MouseInput(Handler handler, mainGame mainGame) {
		this.handler=handler;
	}
	
	public void mousePressed(MouseEvent e) {
		mx=e.getX();
		my=e.getY();
		//handler.addObject(new Bullet(mx,my,handler,ObjectID.Bullet));
		//System.out.println("Direction "+ mainGame.direction);
		
		//handler.addObject(new Enemy(mx, my, handler, ObjectID.Enemy));
		
		for(int i = 0; i<handler.object.size(); i++) 
		{
			gameObject tempObject = handler.object.get(i);
			
			
			
			
			if(tempObject.getId()==ObjectID.Player)
			{

				rise = (int) ((int) my-tempObject.getY());
				run = (int)((int)mx-tempObject.getX());
			//	System.out.println("X: "+mx+" Y: "+my);
			//	System.out.println("Run: "+run+" Y:"+rise);
				
				
				if(mainGame.direction==0) {
//					handler.addObject(new Bullet(tempObject.getX()-8,tempObject.getY()-20,mainGame.direction,handler,ObjectID.Bullet));
					handler.addObject(new Bullet(tempObject.getX()+8,tempObject.getY()-25,mainGame.direction,handler,ObjectID.Bullet));
//					handler.addObject(new Bullet(tempObject.getX()+24,tempObject.getY()-20,mainGame.direction,handler,ObjectID.Bullet));	
				}
				if(mainGame.direction==1) {
					handler.addObject(new Bullet(tempObject.getX()-16,tempObject.getY()+16,mainGame.direction,handler,ObjectID.Bullet));
				}
				if(mainGame.direction==2) {
					handler.addObject(new Bullet(tempObject.getX()+33,tempObject.getY()+16,mainGame.direction,handler,ObjectID.Bullet));
				}
				if(mainGame.direction==3) {
					handler.addObject(new Bullet(tempObject.getX()+8,tempObject.getY()+65,mainGame.direction,handler,ObjectID.Bullet));
				}
			}
		}
		
		
	}
	
	
	@Override
	public void mouseDragged(MouseEvent e) {
		
	}
	
	public void mouseEntered(MouseEvent e) {
		mx=e.getX();
		my=e.getY();
	}
	
	public void mouseMoved(MouseEvent e) {
		mx=e.getX();
		my=e.getY();
		
	}

	
	
}
