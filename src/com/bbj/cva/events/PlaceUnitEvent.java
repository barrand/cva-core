package com.bbj.cva.events;

import com.bbj.cva.model.CvaModel;

public class PlaceUnitEvent extends Object
{
	public float x;
	public float y;
	public CvaModel.Unit unitType;
	
	public PlaceUnitEvent(float nx, float ny, CvaModel.Unit ut)
	{
		x = nx;
		y = ny;
		this.unitType = ut;
	}
}
