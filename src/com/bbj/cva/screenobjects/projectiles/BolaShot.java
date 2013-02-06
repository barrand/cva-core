package com.bbj.cva.screenobjects.projectiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.bbj.cva.model.CvaModel;
import com.bbj.cva.screenobjects.ScreenObject;

public class BolaShot extends ScreenObject implements IProjectile {
	
	public BolaShot() {
		super();
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
	public void loadTextureIfNeeded() {
		if(CvaModel.bolaShot== null){
			CvaModel.bolaShot = new Texture(Gdx.files.internal("data/spriteSheets/webshot.png")); // #9
		}
		texture = CvaModel.bolaShot;
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
	public float getWidth() {
		return 40;
	}

	@Override
	public float getHeight() {
		return 40;
	}
}