package com.bbj.cva.screenobjectsdata;

import com.bbj.cva.screenobjects.ScreenObjectBase;

public class SpawnUnitData
{
	public float dx, dy;
	public ScreenObjectBase screenObject;

	public SpawnUnitData(ScreenObjectBase screenObject, float x, float y)
	{
		this.screenObject = screenObject;
		dx = x;
		dy = y;
	}
}
