package com.bbj.cva.screenobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.bbj.cva.model.CvaModel;
import com.bbj.cva.screenobjects.interfaces.IScreenObject;

public abstract class ScreenObject implements IScreenObject {
	public static float SPEEDX;
	public static float SPEEDY;
	public float speedXModifier;
	public float speedYModifier;
	protected Texture texture;
	public CvaModel.Unit type;
	public float x,y;

	public ScreenObject(float x, float y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public void create() {
	}

	@Override
	public void render(SpriteBatch spriteBatch) {
		x += getSpeedX() + speedXModifier;
		y += getSpeedY() + speedYModifier;
		spriteBatch.draw(texture, x - getSpriteWidth()/2 , y);
	}
	
	@Override
	public void destroy() {
		//
	}
}
