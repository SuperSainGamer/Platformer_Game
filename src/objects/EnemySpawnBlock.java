package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Shape;
import java.util.LinkedList;

import framework.ObjectID;
import framework.gameObject;
import mainGame.Handler;

public class EnemySpawnBlock extends framework.gameObject {
	
	private Handler handler;
	
	
	public EnemySpawnBlock(float x, float y,Handler handler, ObjectID ID) {
		super(x, y, ID);
		this.handler= handler;
	}

	
	public void tick(LinkedList<gameObject> object) {
		
	}

	
	public void render(Graphics g) {
		g.setColor(Color.blue);
			g.fillRect((int)x,(int) y, 32, 32);
	}

	
	public Shape getBounds() {
		return new Rectangle((int)x,(int)y, 32	,32);
	}

}
