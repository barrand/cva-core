package com.bbj.cva.screenobjects.interfaces;

import com.badlogic.gdx.graphics.g2d.Animation;

public interface IAttacker {
	public abstract float getAttackAreaWidth();
	public abstract float getAttackAreaHeight();
	public abstract Animation getAttackAnimation();
	public abstract boolean attacksToTheLeft();
}
