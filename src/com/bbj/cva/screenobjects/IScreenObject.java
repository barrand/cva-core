package com.bbj.cva.screenobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface IScreenObject {

	public void create();
	public void render(SpriteBatch spriteBatch);

	public void setX(int x);

	public void setY(int y);
	public void loadTextureIfNeeded();
	public float getSpeedX();
	public float getSpeedY();
}
