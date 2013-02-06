package com.bbj.cva.screenobjectsdata;

import com.bbj.cva.screenobjects.ScreenObject;

public class SpawnUnitData
{
	public float dx, dy;
	public ScreenObject screenObject;

	public SpawnUnitData(ScreenObject screenObject, float x, float y)
	{
		this.screenObject = screenObject;
		dx = x;
		dy = y;
	}
}
