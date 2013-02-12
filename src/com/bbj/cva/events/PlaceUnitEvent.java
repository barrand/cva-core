package com.bbj.cva.events;

import com.bbj.cva.screenobjects.ScreenObject;
import com.mangecailloux.pebble.event.Event;

public class PlaceUnitEvent extends Event
{
	public float x;
	public float y;
	public ScreenObject screenObject;  // FIXME: Should just change this to be the Unit enum...
	
	public PlaceUnitEvent(ScreenObject screenObject)
	{
		this.screenObject = screenObject;
		x = screenObject.getX();
		y = screenObject.getY();
	}
	
	public PlaceUnitEvent(float nx, float ny, ScreenObject so)
	{
		x = nx;
		y = ny;
		this.screenObject = so;
	}

	@Override
	public void reset() {
		x = 0;
		y = 0;
		screenObject = null;
	}
}
