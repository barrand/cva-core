package com.bbj.cva.screenobjects.interfaces;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.bbj.cva.screenobjects.projectiles.IProjectile;

public interface IShooter {
	public abstract IProjectile getProjectile();
	public abstract TextureRegion getShootingFrame();
	public abstract String getShootingRegionName();
	public abstract int getShootingNumFrames();
}
