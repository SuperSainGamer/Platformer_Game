package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.util.LinkedList;

import framework.ObjectID;
import framework.gameObject;

public class enemyAttack extends framework.gameObject{

	public enemyAttack(float x, float y, ObjectID ID) {
		super(x, y, ID);
	}

	public void tick(LinkedList<gameObject> object) {
		this.x+= getAttackVelX();
		this.y+= getAttackVelY();
	}

	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.red);
		g2d.fill(getBounds());
	}

	@Override
	public Shape getBounds() {
		return new Ellipse2D.Float(x, y, 16, 16);
	}

}
