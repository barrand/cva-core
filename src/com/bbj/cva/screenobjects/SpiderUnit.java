package com.bbj.cva.screenobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.bbj.cva.model.CvaModel;
import com.bbj.cva.screenobjects.projectiles.BolaShot;

public class SpiderUnit extends Shooter {
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
			CvaModel.spider = new Texture(Gdx.files.internal("data/spriteSheets/spiderShot.png")); // #9
		}
		texture = CvaModel.spider;
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
		return 0.5f;
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
		return 190;
	}

	@Override
	public float getHeight() {
		return 140;
	}

	@Override
	protected ScreenObject getProjectile() {
		return new BolaShot(unitRect.x + unitRect.width, unitRect.y + unitRect.height/2);
	}

	@Override
	protected TextureRegion getShootingFrame() {
		return textureFrames[4];
	}
}
