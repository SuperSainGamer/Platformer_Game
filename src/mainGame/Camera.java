package mainGame;

import framework.gameObject;

public class Camera {
	
	private float x,y;
	
	public Camera(float x, float y) {
		this.x=x;
		this.y=y;
	}
	
	
	public void tick(gameObject player) {
		
		x= -player.getX()+mainGame.WIDTH/2;
		y= -player.getY()+mainGame.HEIGHT/2;
	}
	public void setX(float x) {
		this.x=x;
	}
	public void setY(float y) {
		this.y=y;
	}
	
	public float getX() {
		return x;
	}
	public float getY() {
		return y;
	}

}
