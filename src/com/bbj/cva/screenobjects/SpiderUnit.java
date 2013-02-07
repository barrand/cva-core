package com.bbj.cva.screenobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bbj.cva.model.CvaModel;

public class SpiderUnit extends ScreenObject {
	public SpiderUnit(float x, float y)
	{
		super(x,y);
		type = CvaModel.Unit.SPIDER;
	}

	@Override
	public void render(SpriteBatch spriteBatch) {
		super.render(spriteBatch);
	}
	
	@Override
	public void loadTextureIfNeeded() {
		if(CvaModel.spider== null){
			CvaModel.spider = new Texture(Gdx.files.internal("data/spriteSheets/spider.png")); // #9
		}
		texture = CvaModel.spider;
	}

	@Override
	public float getSpeedX() {
		return 0;
	}
	@Override
	public float getSpeedY() {
		return 0;
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
