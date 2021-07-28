package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Shape;
import java.util.LinkedList;

import framework.ObjectID;
import framework.gameObject;
import mainGame.Handler;

public class Bullet extends framework.gameObject {


	Handler handler;
	private int direction=2;
	
	public Bullet(float x, float y,int dir, Handler handler, ObjectID ID) {
		super(x, y, ID);
		this.handler = handler;
		this.direction=dir;
	}

	public void tick(LinkedList<gameObject> object) {
//		this.x+=VelX;
//		this.y+=VelY;
		bulletCollison(object);
		if(direction==0) {
			this.y-=15f;
		}
		if(direction==1) {
			this.x-=15f;
		}
		if(direction==2) {
			this.x+=15f;
		}
		if(direction==3) {
			this.y+=15f;
		}
		
	}

	
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		g.setColor(Color.green);
		g2d.fill(getBounds());
		//Oval((int)x,(int) y, 16, 16);
		g.setColor(Color.yellow);
		
	}

	public Shape getBounds() {
		if(direction==0) {
			return new Polygon(new int[] {(int)x,(int)x+10,(int)x+5},new int[] {(int)y+22,(int)y+22,(int)y-8},3);
		}
		if(direction==1) {
			return new Polygon(new int[] {(int)x+15,(int)x+15,(int)x-10},new int[] {(int)y,(int)y+10,(int)y+5},3);
		}
		if(direction==3) {
			return new Polygon(new int[] {(int)x,(int)x+10,(int)x+5},new int[] {(int)y-22,(int)y-22,(int)y+8},3);
		}
		else
			return new Polygon(new int[] {(int)x+1,(int)x+1,(int)x+25},new int[] {(int)y,(int)y+10,(int)y+5},3);

				//Ellipse2D.Float(x, y, 16, 16);
	}

	private boolean colidesWith(Shape other) {
			return this.getBounds().intersects(other.getBounds());	
	}
	
	private void bulletCollison(LinkedList<gameObject> ovject) {
		for(int i = 0; i< handler.object.size(); i++)
		{
			gameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId()==ObjectID.Block&&this.colidesWith(tempObject.getBounds())) {
				handler.removeObject(this);
			}
			
		}
	}





}
