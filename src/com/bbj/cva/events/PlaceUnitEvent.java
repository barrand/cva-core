package com.bbj.cva.events;

import com.bbj.cva.screenobjects.ScreenObject;

public class PlaceUnitEvent extends Object
{
	public ScreenObject screenObject;
	
	public PlaceUnitEvent(ScreenObject screenObject)
	{
		this.screenObject = screenObject;
	}
}
