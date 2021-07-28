package objects;

import java.awt.Color;
import java.awt.Graphics;
//import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.util.LinkedList;

import framework.ObjectID;
import framework.gameObject;
import mainGame.Handler;
import mainGame.gameWindow;
import mainGame.mainGame;


public class Player extends framework.gameObject {

	
	public float width=32,hieght=64;	
	private float gravity = 1f;
	private final float MAX_SPEED = 10;
	private Handler handler;
	
	
 

	
	

	public Player(float x, float y, Handler handler, mainGame mainGame, ObjectID ID) {
		super(x, y,ID);
		this.handler = handler;	
	}

	

	public void tick(LinkedList<gameObject> object) {
		x+=VelX;
		y+=VelY;
		
		if(falling||jumping){
			VelY+=gravity;
			if(VelY>MAX_SPEED) {
				VelY = MAX_SPEED;
			}
		}

		Collision(object);
//		bulletHit(object);
	}
/*
	private void bulletHit(LinkedList<gameObject> object){
		for(int i = 0; i< handler.object.size(); i++){
			gameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId()== ObjectID.Bullet){
				if(collidesWithTop(tempObject.getBounds())){
					handler.removeObject(tempObject);
				}
				if(collidesWith(tempObject.getBounds())){
					handler.removeObject(tempObject);
				}
				if(collidesWithRight(tempObject.getBounds())){
					handler.removeObject(tempObject);
				}
				if(collidesWithLeft(tempObject.getBounds())){
					handler.removeObject(tempObject);
				}
			}
			
		}
	}
*/	

	private void Collision(LinkedList<gameObject> object) 
	{
		for(int i = 0; i< handler.object.size(); i++)
		{
			gameWindow.debugLbl.setText("current I "+i);
			
			gameObject tempObject = handler.object.get(i);		

			if(tempObject.getId()==ObjectID.Block || tempObject.getId() == ObjectID.EnemySpawnBlock){
				if(collidesWith((tempObject.getBounds()))){
					VelY=0;
					y = tempObject.getY()-hieght;	 
					falling = false;
					jumping = false;
				}else 
					falling = true;
				}
			if(tempObject.getId()==ObjectID.Block){

				if(collidesWithTop(tempObject.getBounds())){
					y = tempObject.getY()+33;	 
					VelY=0;
				}		
				
				if(collidesWithRight(tempObject.getBounds())){
					x = tempObject.getX()-width;	 
				}
				if(collidesWithLeft(tempObject.getBounds())){
					x = tempObject.getX()+33;	 
				}
			}
		}
	}

	

	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int)x,(int)y,(int)width,(int)hieght);
	
//		Graphics2D g2d = (Graphics2D) g;
		
		g.setColor(Color.yellow);
//		g2d.draw(getBounds());
//		g2d.draw(getBoundsRight());
//		g2d.draw(getBoundsTop());
//		g2d.draw(getBoundsLeft());
		
	
	}
	

	
	public Shape bottomBounds = new Rectangle((int) ((int)x+(width/2)-((width/2)/2)),(int) ((int)y+(hieght/2)),(int)width/2,(int)hieght/2);


	public Shape getBounds() {

		return new Rectangle((int) ((int)x+(width/2)-((width/2)/2)),(int) ((int)y+(hieght/2)),(int)width/2,(int)hieght/2);
	}

	public Shape getBoundsRight() {
		return new Rectangle((int) ((int)x+width-5),(int)y+10,(int)5,(int)hieght-20);
	}

	public Shape getBoundsTop() {

		return new Rectangle((int) ((int)x+(width/2)-((width/2)/2)),(int)y,(int)width/2,(int)hieght/2);
		//(int) ((int)x+(width/2)-((width/2)/2))
	}
	
	public Shape getBoundsLeft() {
		return new Rectangle((int)x,(int)y+10,(int)5,(int)hieght-20);
	}
	public boolean collidesWith(Shape other) {
	    return getBounds().intersects(other.getBounds());
	}
	public boolean collidesWithTop(Shape other) {
	    return getBoundsTop().intersects(other.getBounds());
	}
	public boolean collidesWithRight(Shape other){
	    return getBoundsRight().intersects(other.getBounds());
	}
	public boolean collidesWithLeft(Shape other) {
	    return getBoundsLeft().intersects(other.getBounds());
	}



}
