package com.bbj.cva.screenobjects.interfaces;

import java.util.ArrayList;

import com.badlogic.gdx.math.Rectangle;

public interface IHitAreaObject {
	public float getHitAreaWidth();//the "body" of the object, where the character can get hit
	public float getHitAreaHeight();
	public Rectangle getHitArea();
	public ArrayList<IHitAreaObject> getInteractables();
	public void handleCollision(IHitAreaObject o);
}
