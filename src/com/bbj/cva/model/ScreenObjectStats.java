package com.bbj.cva.model;

//This class should hold the stats for the objects. I don't think it should hold animations, only simple stats so that the scereen objects can be upgraded
public class ScreenObjectStats {
	public int maxHealth;
	public int attackStrength;
	public float speedX;
	public float speedY = 0f;
	public float spriteWidth;
	public float spriteHeight;
	public float hitAreaWidth;
	public float hitAreaHeight;
	public float attackAreaWidth;
	public float attackAreaHeight;
	public boolean attacksToTheLeft = true;

}
