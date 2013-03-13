package com.bbj.cva.events;

import com.badlogic.gdx.math.Rectangle;

public class UnitTypeSelectEvent extends Object
{
	public Rectangle selection;

	public UnitTypeSelectEvent(Rectangle rect)
	{
		selection = rect;
	}
}
