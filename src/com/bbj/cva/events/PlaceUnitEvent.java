package com.bbj.cva.events;

import com.bbj.cva.screenobjects.ScreenObject;

public class PlaceUnitEvent extends Object
{
	public float x;
	public float y;
	public ScreenObject screenObject;  // FIXME: Should just change this to be the Unit enum...
	
	public PlaceUnitEvent(ScreenObject screenObject)
	{
		this.screenObject = screenObject;
		x = screenObject.x;
		y = screenObject.y;
	}
	
	public PlaceUnitEvent(float nx, float ny, ScreenObject so)
	{
		x = nx;
		y = ny;
		this.screenObject = so;
	}
}
