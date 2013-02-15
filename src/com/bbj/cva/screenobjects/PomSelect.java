package com.bbj.cva.screenobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bbj.cva.model.CvaModel;

public class PomSelect extends ScreenObject 
{
	public PomSelect(float x, float y)
	{
		super(x,y);
		type = CvaModel.Unit.POM;
		texture = CvaModel.pomSelect;
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
	public float getWidth() {
		return 128;
	}

	@Override
	public float getHeight() {
		return 135;
	}
}