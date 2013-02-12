package com.bbj.cva.screenobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bbj.cva.events.RemoveScreenObjectEvent;
import com.bbj.cva.model.CvaModel;

public class PomDie extends AnimatedScreenObject {

	public PomDie(float x, float y)
	{
		super(x,y);
		this.loop = false;
		type = CvaModel.Unit.POM_DIE;
	}

	@Override
	public void render(SpriteBatch spriteBatch) {
		super.render(spriteBatch);
	}

	@Override
	public void loadTextureIfNeeded() {
		if(CvaModel.pomDie == null){
			CvaModel.pomDie = new Texture(Gdx.files.internal("data/spriteSheets/pomDie.png")); // #9
		}
		texture = CvaModel.pomDie;
	}

	@Override
	protected void onAnimationEnd() {
		CvaModel.eventBus.post(new RemoveScreenObjectEvent(this));
	}
	
	@Override
	public int getFrameCols() {
		return 10;
	}

	@Override
	public int getFrameRows() {
		return 12;
	}

	@Override
	public float getAnimationSpeed() {
		return 0.01f;
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
		return 50;
	}

	@Override
	public float getHeight() {
		return 125;
	}

}