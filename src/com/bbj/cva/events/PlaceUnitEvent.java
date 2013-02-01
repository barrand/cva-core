package com.bbj.cva.events;

public class PlaceUnitEvent extends Object{
	public float x;
	public float y;
	
	public PlaceUnitEvent() {
		x = 0;
		y = 0;
	}
	
	public PlaceUnitEvent(float nx, float ny) {
		x = nx;
		y = ny;
	}
}
