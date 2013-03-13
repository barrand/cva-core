package com.bbj.cva.screenobjects.interfaces;

import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.bbj.cva.screenobjects.projectiles.IProjectile;

public interface IShooter {
	public abstract IProjectile getProjectile();
	public abstract AtlasRegion getShootingFrame();
	public abstract String getShootingRegionName();
	public abstract int getShootingNumFrames();
}
