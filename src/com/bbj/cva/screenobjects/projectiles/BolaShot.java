package com.bbj.cva.screenobjects.projectiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.bbj.cva.model.CvaModel;
import com.bbj.cva.screenobjects.IScreenObject;
import com.bbj.cva.screenobjects.ScreenObject;
import com.bbj.cva.screenobjectsdata.SpawnUnitData;

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
		return 3f;
	}

	@Override
	public float getSpeedY() {
		return 0;
	}
}
