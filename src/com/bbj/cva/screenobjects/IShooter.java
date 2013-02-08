package com.bbj.cva.screenobjects;

import com.bbj.cva.screenobjects.projectiles.IProjectile;

public interface IShooter {
	public IProjectile getProjectile();
	public float getShootingInterval();
}
