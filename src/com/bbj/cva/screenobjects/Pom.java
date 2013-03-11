package com.bbj.cva.screenobjects;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.bbj.cva.events.RemoveScreenObjectEvent;
import com.bbj.cva.model.CvaModel;
import com.bbj.cva.screenobjects.interfaces.IAnimated;
import com.bbj.cva.screenobjects.interfaces.IAttacker;
import com.bbj.cva.screenobjects.interfaces.IHitAreaObject;
import com.bbj.cva.screenobjects.projectiles.IProjectile;

public class Pom extends ScreenObject implements IHitAreaObject, IAttacker,
		IAnimated {

	private float speedX = -1.2f;

	public Pom(float x, float y) {
		super(x, y);
		type = CvaModel.Unit.POM;
	}

	@Override
	public void create() {
		super.create();
		currentAnim = CvaModel.pomWalk;
	}

	@Override
	public void render(SpriteBatch spriteBatch) {
		super.render(spriteBatch);
	}

	@Override
	public void onAnimationEnd() {
		CvaModel.eventBus.post(new RemoveScreenObjectEvent(this));
	}

	// smaller is faster
	protected float getAnimationSpeed() {
		return 0.03f;
	}

	// larger is faster
	@Override
	public float getSpeedX() {
		return speedX;
	}

	@Override
	public float getSpeedY() {
		return 0f;
	}

	@Override
	public float getSpriteWidth() {
		return 300;
	}

	@Override
	public float getSpriteHeight() {
		return 300;
	}

	@Override
	public float getHitAreaWidth() {
		return 80;
	}

	@Override
	public float getHitAreaHeight() {
		return 130;
	}

	@Override
	public float getAttackAreaWidth() {
		return 100;
	}

	@Override
	public float getAttackAreaHeight() {
		return 130;
	}

	@Override
	public boolean attacksToTheLeft() {
		return true;
	}

	@Override
	public ArrayList<IHitAreaObject> getInteractables() {
		return CvaModel.thingsCheerborgsInteractWith;
	}

	@Override
	public void handleCollision(IHitAreaObject o) {
		if (o instanceof IProjectile) {
			Gdx.app.log("cva", this.toString());
			stateTime = 0f;
			currentAnim = dieAnim;
			speedX = 0f;
			checkForInteractions = false;
			loop = false;
			CvaModel.eventBus
					.post(new RemoveScreenObjectEvent((ScreenObject) o));
		}
	}

	@Override
	public Rectangle getHitArea() {
		return hitArea;
	}
}