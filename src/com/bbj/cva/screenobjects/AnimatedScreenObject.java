package com.bbj.cva.screenobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.bbj.cva.model.CvaModel;
import com.bbj.cva.screenobjects.interfaces.IAnimatedAttacker;
import com.bbj.cva.screenobjects.interfaces.IAnimatedDier;
import com.bbj.cva.screenobjects.interfaces.IAnimatedWalker;

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
	}

	@Override
	public void create() {
		super.create();
		textureAtlas = new TextureAtlas(
				Gdx.files.internal(getTextureAtlasUrl()));

		stateTime = 0f;
		if (this instanceof IAnimatedWalker) {
			walkAnim = createAnimation(walkAnim,
					((IAnimatedWalker) this).getWalkNumFrames(),
					((IAnimatedWalker) this).getWalkRegionName());
			walkAnim.setPlayMode(Animation.LOOP);
		}
		if (this instanceof IAnimatedDier) {
			dieAnim = createAnimation(dieAnim,
					((IAnimatedDier) this).getDieNumFrames(),
					((IAnimatedDier) this).getDieRegionName());
			dieAnim.setPlayMode(Animation.NORMAL);
		}
		if (this instanceof IAnimatedAttacker) {
			attackAnim = createAnimation(dieAnim,
					((IAnimatedAttacker) this).getAttackNumFrames(),
					((IAnimatedAttacker) this).getAttackRegionName());
			attackAnim.setPlayMode(Animation.LOOP);
		}
	}

	abstract protected String getTextureAtlasUrl();

	private Animation createAnimation(Animation anim, int numberOfFrames,
			String fileNamePrefix) {
		AtlasRegion[] walkAtlases = new AtlasRegion[numberOfFrames];
		for (int ct = 1; ct < numberOfFrames + 1; ct++) {
			String name = fileNamePrefix + String.format("%04d", ct);
			Gdx.app.log("cva", "name " + name);
			walkAtlases[ct - 1] = textureAtlas.findRegion(name);
		}
		return new Animation(getAnimationSpeed(), walkAtlases);
	}

	protected float getAnimationSpeed() {
		return 0.03f;
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
