package com.bbj.cva.screenobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class AnimatedScreenObject extends ScreenObject {

	protected Animation walkAnim;
	protected Animation noActionAnim;
	protected Animation dieAnim;
	protected Animation attackAnim;
	protected Animation currentAnim;

	protected TextureAtlas textureAtlas;

	SpriteBatch spriteBatch; // #6
	TextureRegion currentFrame; // #7
	public boolean loop = true;

	float stateTime; // #8

	public AnimatedScreenObject(float x, float y) {
		super(x, y);
		stateTime = 0f;
	}

	@Override
	public void render(SpriteBatch spriteBatch) {
		x += getSpeedX() + speedXModifier;
		y += getSpeedY() + speedYModifier;
		stateTime += Gdx.graphics.getDeltaTime();
		AtlasRegion currentFrame = (AtlasRegion) currentAnim.getKeyFrame(
				stateTime, loop);
		if (!loop && currentAnim.isAnimationFinished(stateTime)) {
			onAnimationEnd();
		}
		spriteBatch.draw(currentFrame, x + currentFrame.offsetX, y
				+ currentFrame.offsetY);
	}

	protected void onAnimationEnd() {
		// override me if needed
	}

}
