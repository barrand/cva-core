package com.bbj.cva.screenobjects;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bbj.cva.events.RemoveScreenObjectEvent;
import com.bbj.cva.model.CvaModel;
import com.bbj.cva.screenobjects.interfaces.IAnimated;
import com.bbj.cva.screenobjects.interfaces.IAttacker;
import com.bbj.cva.screenobjects.interfaces.IDier;
import com.bbj.cva.screenobjects.interfaces.IHitAreaObject;

public class Pom extends ScreenObject implements IHitAreaObject, IAttacker, IDier,
		IAnimated {

	public Pom(float x, float y) {
		super(x, y, CvaModel.Unit.POM);
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

	@Override
	public ArrayList<IHitAreaObject> getInteractables() {
		return CvaModel.thingsCheerborgsInteractWith;
	}

	@Override
	public void handleCollision(IHitAreaObject o) {
		super.handleCollision(o);
//		if (o instanceof IProjectile) {
//			Gdx.app.log("cva", this.toString());
//			stateTime = 0f;
//			currentAnim = getDieAnimation();
//			stats.speedX = 0f;
//			checkForInteractions = false;
//			loop = false;
//			CvaModel.eventBus
//					.post(new RemoveScreenObjectEvent((ScreenObject) o));
//		}
//		if (o instanceof ICheerAttackable) {
//			currentAnim = getAttackAnimation();
//			stats.speedX = 0f;
//		}
	}

	@Override
	public Animation getDieAnimation() {
		return CvaModel.pomDie;
	}

	@Override
	public Animation getAttackAnimation() {
		return CvaModel.pomAttack;
	}
}