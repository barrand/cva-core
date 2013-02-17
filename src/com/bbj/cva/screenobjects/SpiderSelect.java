package com.bbj.cva.screenobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bbj.cva.model.CvaModel;

public class SpiderSelect extends ScreenObject 
{
	public SpiderSelect(float x, float y)
	{
		super(x,y);
		type = CvaModel.Unit.SPIDER;
		texture = CvaModel.spiderSelect;
	}

	@Override
	public void render(SpriteBatch spriteBatch) 
	{
		super.render(spriteBatch);
	}

	@Override
	public float getSpeedX() {
		return 0f;
	}

	@Override
	public float getSpeedY() {
		return 0f;
	}
	
	@Override
	public float getSpriteWidth() {
		return 128;
	}

	@Override
	public float getSpriteHeight() {
		return 135;
	}
}