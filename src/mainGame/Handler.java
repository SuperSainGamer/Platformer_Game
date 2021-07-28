package mainGame;
import java.awt.Graphics;
import java.util.LinkedList;

import framework.ObjectID;
import framework.gameObject;
import objects.Block;

public class Handler {

	public LinkedList<gameObject> object = new LinkedList<gameObject>();

	private gameObject TempObject;

	public void tick() {
		for (int i = 0; i < object.size(); i++) {
			TempObject = object.get(i);
			TempObject.tick(object);
		}
	}

	public void render(Graphics g) {
		for (int i = 0; i < object.size(); i++) {
			TempObject = object.get(i);

			TempObject.render(g);
		}
	}

	public void addObject(gameObject object) {
		this.object.add(object);
	}

	public void removeObject(gameObject object) {
		this.object.remove(object);
	}

	public void creatlvl() {
		
//		for(int yy=0; yy<mainGame.HEIGHT+32; yy+=32)
//		{
//			addObject(new Block(0, yy-8, ObjectID.Block));
//		}
//		for(int yy=0;yy<mainGame.HEIGHT-32;yy+=32)
//		{
//			addObject(new Block(mainGame.WIDTH-32,yy-8, ObjectID.Block));
//		}	
//		for (int xx = 0; xx < mainGame.WIDTH*2 + 32; xx += 32) {
//			addObject(new Block(xx, mainGame.HEIGHT - 32, ObjectID.Block));
//		}
//		for (int xx = 0; xx < mainGame.WIDTH*2 + 32; xx += 32) {
//			addObject(new Block(xx, mainGame.HEIGHT - 608, ObjectID.Block));
//		}
//		for(int xx=200;xx<600;xx+=32)
//		{
//			addObject(new Block(xx, 400, ObjectID.Block));
//		}

	}

}
