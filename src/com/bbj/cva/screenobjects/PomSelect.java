package com.bbj.cva.screenobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bbj.cva.model.CvaModel;

public class PomSelect extends ScreenObject 
{
	public PomSelect()
	{
		super();
		type = CvaModel.Unit.POM;
	}

	@Override
	public void render(SpriteBatch spriteBatch) 
	{
		super.render(spriteBatch);
	}

	@Override
	public void loadTextureIfNeeded() 
	{
		if(CvaModel.pomSelect == null){
			CvaModel.pomSelect = new Texture(Gdx.files.internal("data/spriteSheets/pomSelect.png"));
		}
		texture = CvaModel.pomSelect;
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