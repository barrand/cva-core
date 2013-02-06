package com.bbj.cva.screenobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.bbj.cva.model.CvaModel;

public class SpiderUnit extends ScreenObject implements IScreenObject {
	public SpiderUnit(float x, float y)
	{
		super(x,y);
	}

	@Override
	public void loadTextureIfNeeded() {
		if(CvaModel.spider== null){
			CvaModel.spider = new Texture(Gdx.files.internal("data/spriteSheets/spider.png")); // #9
		}
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
		return 50;
	}

	@Override
	public float getHeight() {
		return 50;
	}
}
