package com.bbj.cva.screenobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.bbj.cva.events.PlaceUnitEvent;
import com.bbj.cva.model.CvaModel;
import com.bbj.cva.screenobjects.interfaces.IAnimated;
import com.bbj.cva.screenobjects.interfaces.IAttacker;
import com.bbj.cva.screenobjects.interfaces.IHitAreaObject;
import com.bbj.cva.screenobjects.interfaces.INonAnimated;
import com.bbj.cva.screenobjects.interfaces.IScreenObject;
import com.bbj.cva.screenobjects.interfaces.IShooter;

public abstract class ScreenObject implements IScreenObject {

	protected Animation walkAnim;
	protected Animation noActionAnim;
	protected Animation dieAnim;
	protected Animation attackAnim;
	protected Animation currentAnim;

	protected Texture texture; // used in non animated objects instead of the
								// animations
	protected TextureAtlas textureAtlas;

	SpriteBatch spriteBatch; // #6
	TextureRegion currentFrame; // #7
	public boolean loop = true;

	protected Rectangle hitArea;
	protected boolean checkForInteractions = true;

	protected boolean alreadyShot = false;

	protected Rectangle attackArea;
	protected Animation attackingAnim;

	public static float SPEEDX;
	public static float SPEEDY;
	public float speedXModifier;
	public float speedYModifier;
	public CvaModel.Unit type;
	public float x, y;

	float stateTime; // #8

	public ScreenObject(float x, float y) {
		stateTime = 0f;

		this.x = x;
		this.y = y;
		
		if (this instanceof IAttacker) {
			attackArea = new Rectangle();
			attackArea.width = ((IAttacker) this).getAttackAreaWidth();
			attackArea.height = ((IAttacker) this).getAttackAreaHeight();
			attackArea.x = x;
			attackArea.y = y;
		}

		if (this instanceof IHitAreaObject) {
			hitArea = new Rectangle();
			hitArea.width = ((IHitAreaObject) this).getHitAreaWidth();
			hitArea.height = ((IHitAreaObject) this).getHitAreaHeight();
			hitArea.x = x;
			hitArea.y = y;
		}
	}

	public void create() {

	}

	public void render(SpriteBatch spriteBatch) {
		x += getSpeedX() + speedXModifier;
		y += getSpeedY() + speedYModifier;

		AtlasRegion currentFrame = null;
		
		if (this instanceof IAnimated) {
			stateTime += Gdx.graphics.getDeltaTime();
			currentFrame = (AtlasRegion) currentAnim.getKeyFrame(
					stateTime, loop);
		}
		
		if (this instanceof IHitAreaObject) {
			hitArea.x = x - hitArea.width / 2;
			hitArea.y = y + hitArea.height / 2;
			if (CvaModel.DEBUG) {
				spriteBatch.setColor(200, 200, 200, 240);
				spriteBatch
						.draw(CvaModel.blue, hitArea.x, hitArea.y,
								hitArea.width, hitArea.height, 0, 0, 0, 0,
								false, false);
				spriteBatch.setColor(CvaModel.defaultColor);
			}

			if (checkForInteractions
					&& ((IHitAreaObject) this).getInteractables() != null) {

				for (IHitAreaObject o : ((IHitAreaObject) this)
						.getInteractables()) {
					if (o.getHitArea().overlaps(hitArea)) {
						((IHitAreaObject) this).handleCollision(o);
					}
				}
			}
		}

		if (this instanceof IAttacker) {
			if (((IAttacker) this).attacksToTheLeft()) {
				attackArea.x = x - attackArea.width;
			} else {
				attackArea.x = x + attackArea.width;
			}
			attackArea.y = y + attackArea.height / 2;
			if (CvaModel.DEBUG) {
				spriteBatch.setColor(255, 0, 0, 240);
				spriteBatch.draw(CvaModel.blue, attackArea.x, attackArea.y,
						attackArea.width, attackArea.height, 0, 0, 0, 0, false,
						false);
				spriteBatch.setColor(CvaModel.defaultColor);
			}
		}


		if (this instanceof IAnimated) {
			if (!loop && currentAnim.isAnimationFinished(stateTime)) {
				onAnimationEnd();
			}
			spriteBatch.draw(currentFrame, x + currentFrame.offsetX, y
					+ currentFrame.offsetY);
		}else if(this instanceof INonAnimated){
			spriteBatch.draw(texture, x, y);
		}

		if (this instanceof IShooter) {
			// only shoot once per animation cycle and reset when going to a
			// different animation
			AtlasRegion shootingFrame = ((IShooter) this).getShootingFrame();
			if (currentFrame == ((IShooter) this).getShootingFrame()
					&& !alreadyShot) {
				ScreenObject projectile = (ScreenObject) ((IShooter) this)
						.getProjectile();
				CvaModel.eventBus.post(new PlaceUnitEvent(projectile.x,
						projectile.y, CvaModel.Unit.BOLA));
				alreadyShot = true;
			} else if (alreadyShot
					&& currentFrame != ((IShooter) this).getShootingFrame()) {
				alreadyShot = false;
			}
		}
	}

	protected void onAnimationEnd() {
		// override me if needed
	}

	public void destroy() {

	}

}
