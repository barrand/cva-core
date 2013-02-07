package com.bbj.cva.events;

import com.bbj.cva.screenobjects.ScreenObject;

public class UnitTypeEvent
{
	public ScreenObject screenObject;
	public UnitTypeEvent(ScreenObject screenObject) 
	{
		this.screenObject = screenObject;
	}
}
