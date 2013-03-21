package com.bbj.cva.screenobjects.interfaces;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
/**
 * This is for anything that has a melee attack. Could be cheer or arachnid.
 * @author bbarrand12
 *
 */
public interface IAttacker {
	public abstract Animation getAttackAnimation();
	public abstract AtlasRegion getAttackingFrame();
}
