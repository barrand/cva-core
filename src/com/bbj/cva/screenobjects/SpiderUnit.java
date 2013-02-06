package com.bbj.cva.screenobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.bbj.cva.model.CvaModel;

public class SpiderUnit extends ScreenObject implements IScreenObject {
	public SpiderUnit()
	{
		super();
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
}
