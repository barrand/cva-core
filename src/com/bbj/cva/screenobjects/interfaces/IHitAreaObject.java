package com.bbj.cva.screenobjects.interfaces;

import com.badlogic.gdx.math.Rectangle;

public interface IHitAreaObject extends IScreenObject{
	public float getHitAreaWidth();//the "body" of the object, where the character can get hit
	public float getHitAreaHeight();
	public Rectangle getHitArea();
}
