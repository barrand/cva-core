package com.bbj.cva.screenobjects.interfaces;

import java.util.ArrayList;

public interface IHitAreaObject {
	public ArrayList<IHitAreaObject> getInteractables();
	public void handleCollision(IHitAreaObject o);
}
