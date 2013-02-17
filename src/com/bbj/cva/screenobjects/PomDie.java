package com.bbj.cva.screenobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bbj.cva.events.RemoveScreenObjectEvent;
import com.bbj.cva.model.CvaModel;

public class PomDie extends AnimatedScreenObject {

	public PomDie(float x, float y)
	{
		super(x,y);
		this.loop = false;
		type = CvaModel.Unit.POM_DIE;
		texture = CvaModel.pomDie;
	}

	@Override
	public void render(SpriteBatch spriteBatch) {
		super.render(spriteBatch);
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
		return 7;
	}

	@Override
	public float getAnimationSpeed() {
		return 0.017f;
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
		return 50;
	}

	@Override
	public float getSpriteHeight() {
		return 125;
	}

}