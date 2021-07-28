package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Shape;
import java.util.LinkedList;

import framework.ObjectID;
import framework.gameObject;

public class Block extends framework.gameObject {



	public Block(float x, float y, ObjectID ID) {
		super(x, y, ID);
	}

	public void tick(LinkedList<gameObject> object) {
	}

	public void render(Graphics g) {
		g.setColor(Color.white);
		g.drawRect((int)x,(int) y, 32, 32);
		
	}
	
	public Shape getBounds() {
		return new Rectangle((int)x,(int)y, 32	,32);
	}

	

}
