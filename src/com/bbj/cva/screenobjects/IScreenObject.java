package com.bbj.cva.screenobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface IScreenObject {

	public void create();
	public void destroy();
	public void render(SpriteBatch spriteBatch);
	public void setX(float x);
	public void setY(float y);
	public float getWidth();
	public float getHeight();
	public void loadTextureIfNeeded();
	public float getSpeedX();
	public float getSpeedY();
}
