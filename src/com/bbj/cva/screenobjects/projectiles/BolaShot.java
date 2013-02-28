package com.bbj.cva.screenobjects.projectiles;

import java.util.ArrayList;

import com.badlogic.gdx.math.Rectangle;
import com.bbj.cva.model.CvaModel;
import com.bbj.cva.screenobjects.ScreenObject;
import com.bbj.cva.screenobjects.interfaces.IHitAreaObject;

public class BolaShot extends ScreenObject implements IProjectile {
	
	public BolaShot(float x, float y) 
	{
		super(x, y);
		type = CvaModel.Unit.BOLA;
		texture = CvaModel.bolaShot;
	}

	Rectangle unitRect;//super class
	float x, y; //super class
	public static int SPEED;
	public int speedModifier;//should put this in a super class
		
	@Override
	public int getDamage() {
		return 5;
	}

	@Override
	public int getPushBack() {
		return 0;
	}

	@Override
	public int getSpeedModifier() {
		return 0;
	}

	@Override
	public float getSpeedX() {
		return 10f;
	}

	@Override
	public float getSpeedY() {
		return 0;
	}

	@Override
	public float getSpriteWidth() {
		return 40;
	}

	@Override
	public float getSpriteHeight() {
		return 40;
	}

	@Override
	public float getHitAreaWidth() {
		return 40;
	}

	@Override
	public float getHitAreaHeight() {
		return 40;
	}

	@Override
	public Rectangle getHitArea() {
		return hitArea;
	}

	@Override
	public ArrayList<IHitAreaObject> getInteractables() {
		return null;
	}

	@Override
	public void handleCollision(IHitAreaObject o) {
	}
}
