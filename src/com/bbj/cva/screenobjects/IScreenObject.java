package com.bbj.cva.screenobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface IScreenObject {

	public void create();
	public void destroy();
	public void render(SpriteBatch spriteBatch);
	public float getSpriteWidth();//the actual width of the sprite graphic
	public float getSpriteHeight();
	public float getSpeedX();
	public float getSpeedY();
}
