package com.bbj.cva.events;

import com.bbj.cva.model.CvaModel;

public class UnitTypeEvent
{
	public CvaModel.Unit unitType;
	public UnitTypeEvent(CvaModel.Unit ut) 
	{
		this.unitType = ut;
	}
}
