package com.bbj.cva.screenobjects;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.bbj.cva.model.CvaModel;

public abstract class AttackingHitableAnimated extends
		HitableAnimatedScreenObject {

	protected Rectangle attackArea;

	protected Animation attackingAnim;

	public AttackingHitableAnimated(float x, float y) {
		super(x, y);
		// TODO Auto-generated constructor stub
		attackArea = new Rectangle();
		attackArea.width = getAttackAreaWidth();
		attackArea.height = getAttackAreaHeight();
		attackArea.x = x;
		attackArea.y = y;
	}

	protected abstract float getAttackAreaWidth();

	protected abstract float getAttackAreaHeight();

	protected abstract boolean attacksToTheLeft();

	@Override
	public void render(SpriteBatch spriteBatch) {
		super.render(spriteBatch);
		if (attacksToTheLeft()) {
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

	public Rectangle getAttackArea() {
		return attackArea;
	}

}
