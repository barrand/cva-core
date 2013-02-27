package com.bbj.cva.screenobjects.projectiles;

import com.bbj.cva.screenobjects.interfaces.IHitAreaObject;

public interface IProjectile extends IHitAreaObject{
	public int getDamage();
	public int getPushBack();
	public int getSpeedModifier();
}
