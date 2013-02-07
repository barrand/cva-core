package com.bbj.cva.screenobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface IScreenObject {

	public void create();
	public void destroy();
	public void render(SpriteBatch spriteBatch);
<<<<<<< HEAD
	public void setX(float x);
	public void setY(float y);
=======
	public void setX(int x);
	public void setY(int y);
	public int getX();
	public int getY();
>>>>>>> Refined the selection objects/heirarchy
	public float getWidth();
	public float getHeight();
	public void loadTextureIfNeeded();
	public float getSpeedX();
	public float getSpeedY();
}
