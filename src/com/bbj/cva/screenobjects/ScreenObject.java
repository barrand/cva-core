package com.bbj.cva.screenobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.bbj.cva.model.CvaModel;

public abstract class ScreenObject implements IScreenObject {
	Rectangle unitRect;
	public static float SPEEDX;
	public static float SPEEDY;
	public float speedXModifier;
	public float speedYModifier;
	protected Texture texture;
	public CvaModel.Unit type;

	public ScreenObject(float x, float y) {
		unitRect = new Rectangle();
		unitRect.width = getWidth();
		unitRect.height = getHeight();
		unitRect.x = x;
		unitRect.y = y;
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
	public float getX() 
	{
		return unitRect.x;
	}

	@Override
	public float getY()
	{
		return unitRect.y;
	}
	
	@Override
	public void destroy() {
		//
	}
}
