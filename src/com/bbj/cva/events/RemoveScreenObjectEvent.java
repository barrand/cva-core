package com.bbj.cva.events;

import com.bbj.cva.screenobjects.ScreenObject;

public class RemoveScreenObjectEvent extends Object
{
	public ScreenObject screenObject;
	public RemoveScreenObjectEvent(ScreenObject screenObject) {
		this.screenObject = screenObject;
	}

}
