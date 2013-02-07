package com.bbj.cva.screenobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bbj.cva.model.CvaModel;

public class SpiderSelect extends ScreenObject 
{
	public SpiderSelect(float x, float y)
	{
		super(x,y);
		type = CvaModel.Unit.SPIDER;
	}

	@Override
	public void render(SpriteBatch spriteBatch) 
	{
		super.render(spriteBatch);
	}

	@Override
	public void loadTextureIfNeeded() 
	{
		if(CvaModel.spiderSelect == null){
			CvaModel.spiderSelect = new Texture(Gdx.files.internal("data/spriteSheets/spiderSelect.png"));
		}
		texture = CvaModel.spiderSelect;
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