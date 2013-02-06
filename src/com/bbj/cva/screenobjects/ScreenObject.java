package com.bbj.cva.screenobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public abstract class ScreenObject implements IScreenObject {
	Rectangle unitRect;
	public static float SPEEDX;
	public static float SPEEDY;
	public float speedXModifier;
	public float speedYModifier;
	protected Texture texture;

	public ScreenObject() {
		unitRect = new Rectangle();
		unitRect.width = getWidth();
		unitRect.height = getHeight();
	}

	@Override
	public void create() {
		loadTextureIfNeeded();
	}

	@Override
	public void render(SpriteBatch spriteBatch) {
		unitRect.x += getSpeedX() + speedXModifier;
		unitRect.y += getSpeedY() + speedYModifier;
		spriteBatch.draw(texture, unitRect.x - unitRect.getWidth()/2 , unitRect.y);
	}

	@Override
	public void setX(float x) {
		unitRect.x = x;
	}

	@Override
	public void setY(float y) {
		unitRect.y = y;
	}
	
	@Override
	public void destroy() {
		//
	}
}
