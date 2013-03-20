package com.bbj.cva.screenobjects.interfaces;

import com.badlogic.gdx.graphics.g2d.Animation;
/**
 * This is for anything that has a melee attack. Could be cheer or arachnid.
 * This is subclassed by ICheerAttacker 
 * @author bbarrand12
 *
 */
public interface IAttacker {
	public abstract Animation getAttackAnimation();
}
