package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.util.LinkedList;

import framework.ObjectID;
import framework.gameObject;
import mainGame.Handler;

public class Enemy extends framework.gameObject{
	
	private final float width=32,hieght=64;
	private float x,y;
	private float gravity = 2f;
	private final float MAX_SPEED= 10f;
	
	private Handler handler;
	
	public Enemy(float x, float y,Handler handler, ObjectID ID) {
		super(x, y, ID);
		this.x=x;
		this.y=y;
		this.handler=handler;
	}

	public void tick(LinkedList<gameObject> object) {
		y+=getAttackVelY();
		enemyHit(object);
		if(falling){
			setAttackVelY(gravity);
			if(getAttackVelY()>MAX_SPEED) {
				y= MAX_SPEED;
			}
		}
		
		enemyWallHit(object);
		this.x+=getAttackVelX();
	}

	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.orange);
		g.fillRect((int)x,(int) y,(int) width,(int) hieght);
		g.setColor(Color.red);
		g2d.draw(getBounds());
		g.setColor(Color.red);
		g2d.draw(getBoundsLeft());
		g.setColor(Color.red);
		g2d.draw(getBoundsRight());
	}

	
	public Shape getBounds() {
		return new Rectangle((int) ((int)x+(width/2)-((width/2)/2)),(int) ((int)y),(int)width/2,(int)hieght);
	}
	public Shape getBoundsLeft() {
		return new Rectangle((int)x,(int)y+10,(int)5,(int)hieght-20);
	}
	
	public Shape getBoundsRight() {
		return new Rectangle((int) ((int)x+width-5),(int)y+10,(int)5,(int)hieght-20);
	}

	private void enemyWallHit(LinkedList<gameObject> object) {
		for(int i = 0; i< handler.object.size(); i++){
			gameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId()==ObjectID.Block||tempObject.getId()==ObjectID.EnemySpawnBlock) {
				if(colidesWith(tempObject.getBounds())) {
					setAttackVelY(0);
					y = tempObject.getY()-64;	 
					falling = false;
				}
				if(this.colidesWithLeft(tempObject.getBounds())){
					if(this.getAttackVelX()<=-1) {
						this.setAttackVelX(2f);
						return;
					}	
				}
				if(this.colidesWithRight(tempObject.getBounds())) {
					if(this.getAttackVelX()>=1) {
						this.setAttackVelX(-2f);
						return;
					}
				}
			}
		}
	}
	
	private void enemyHit(LinkedList<gameObject> object){
		for(int i = 0; i< handler.object.size(); i++)
		{
			gameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId()==ObjectID.Bullet&&this.colidesWith(tempObject.getBounds())) {
				handler.removeObject(tempObject);
				handler.removeObject(this);
			}
			if(tempObject.getId()==ObjectID.Bullet&&this.colidesWithLeft(tempObject.getBounds())) {
				handler.removeObject(tempObject);
				handler.removeObject(this);
			}
			if(tempObject.getId()==ObjectID.Bullet&&this.colidesWithRight(tempObject.getBounds())) {
				handler.removeObject(tempObject);
				handler.removeObject(this);
			}
		}
	}
	
	
	
	
	private boolean colidesWith(Shape other) {
		return this.getBounds().intersects(other.getBounds());	
	}
	
	private boolean colidesWithLeft(Shape other) {
		return this.getBoundsLeft().intersects(other.getBounds());	
	}
	
	private boolean colidesWithRight(Shape other) {
		return this.getBoundsRight().intersects(other.getBounds());	
	}
	
	
	
	
	
	
	
}//end of class
