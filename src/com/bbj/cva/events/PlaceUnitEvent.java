package com.bbj.cva.events;

import com.bbj.cva.screenobjects.ScreenObject;

public class PlaceUnitEvent extends Object
{
<<<<<<< HEAD
	public ScreenObject screenObject;
	
	public PlaceUnitEvent(ScreenObject screenObject)
	{
		this.screenObject = screenObject;
=======
	public float x;
	public float y;
	public ScreenObject screenObject;  // FIXME: Should just change this to be the Unit enum...
	
	public PlaceUnitEvent()
	{
		x = 0;
		y = 0;
		this.screenObject = null;
	}

	public PlaceUnitEvent(float nx, float ny, ScreenObject so)
	{
		x = nx;
		y = ny;
		this.screenObject = so;
>>>>>>> Refined the selection objects/heirarchy
	}
}
