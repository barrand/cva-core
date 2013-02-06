package com.bbj.cva.screenobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bbj.cva.model.CvaModel;
import com.bbj.cva.screenobjectsdata.SpawnUnitData;

public class Pom extends AnimatedScreenObject {

	public Pom()
	{
		super();
		
	}

	@Override
	public void render(SpriteBatch spriteBatch) {
		super.render(spriteBatch);
	}

	@Override
	public void loadTextureIfNeeded() {
		if(CvaModel.pomWalk == null){
			CvaModel.pomWalk = new Texture(Gdx.files.internal("data/spriteSheets/pomWalk.png")); // #9
		}
		texture = CvaModel.pomWalk;
	}

	@Override
	public int getFrameCols() {
		return 5;
	}

	@Override
	public int getFrameRows() {
		return 4;
	}

	@Override
	public float getAnimationSpeed() {
		return 0.05f;
	}

	@Override
	public float getSpeedX() {
		return -4f;
	}

	@Override
	public float getSpeedY() {
		return 0f;
	}

}