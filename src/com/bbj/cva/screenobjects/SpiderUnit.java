package com.bbj.cva.screenobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.bbj.cva.model.CvaModel;
import com.bbj.cva.screenobjects.projectiles.BolaShot;

public class SpiderUnit extends Shooter {
	public SpiderUnit(float x, float y)
	{
		super(x,y);
		type = CvaModel.Unit.SPIDER;
		texture = CvaModel.spider;
	}

	@Override
	public void render(SpriteBatch spriteBatch) {
		super.render(spriteBatch);
	}
	

	@Override
	public int getFrameCols() {
		return 5;
	}

	@Override
	public int getFrameRows() {
		return 2;
	}

	@Override
	public float getAnimationSpeed() {
		return 0.2f;
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
		return 190;
	}

	@Override
	public float getSpriteHeight() {
		return 140;
	}

	@Override
	protected ScreenObject getProjectile() {
		return new BolaShot(x + getSpriteWidth(), y + getSpriteHeight()/2);
	}

	@Override
	protected TextureRegion getShootingFrame() {
		return textureFrames[4];
	}
}
