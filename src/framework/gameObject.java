package framework;

import java.awt.Graphics;
import java.awt.Shape;
import java.util.LinkedList;

public abstract class gameObject {
	
	protected float x,y;
	protected float VelX = 0, VelY = 0;
	protected float attackVelX=-2f, attackVelY=0;

	protected ObjectID ID;
	protected boolean falling = true;
	protected boolean jumping = false;
	
	
	
	public  gameObject(float x,float y, ObjectID ID) {
		
		this.x= x;
		this.y= y; 
		this.ID = ID;

	}
	
	public abstract void tick(LinkedList<gameObject> object); 
	public abstract void render(Graphics g);	
	public abstract Shape getBounds();
	
	
	public  float getX() {
		return x;
	}
	public  float getY() {
		return y;
	}
	public  void setX(float x) {
		this.x=x;
	}
	public  void setY(float y) {
		this.y=y;
	}

	public  float getVelX() {
		return VelX;
	}
	public  float getVelY() {
		return VelY;
	}
	public  void setVelX(float Velx) {
		this.VelX=Velx;
	}
	public  void setVelY(float Vely) {
		this.VelY=Vely;
	}
	
	public boolean isFalling() {
		return falling;
	}

	public void setFalling(boolean falling) {
		this.falling = falling;
	}

	public boolean isJumping() {
		return jumping;
	}

	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}

	public float getAttackVelX() {
		return attackVelX;
	}

	public void setAttackVelX(float attackVelX) {
		this.attackVelX = attackVelX;
	}

	public float getAttackVelY() {
		return attackVelY;
	}

	public void setAttackVelY(float attackVelY) {
		this.attackVelY = attackVelY;
	}
	
	public  ObjectID getId() {
		return ID;
	}
}
