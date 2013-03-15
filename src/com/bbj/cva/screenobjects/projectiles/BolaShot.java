package com.bbj.cva.screenobjects.projectiles;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.bbj.cva.model.CvaModel;
import com.bbj.cva.screenobjects.ScreenObject;
import com.bbj.cva.screenobjects.interfaces.IHitAreaObject;
import com.bbj.cva.screenobjects.interfaces.INonAnimated;

public class BolaShot extends ScreenObject implements IProjectile, INonAnimated, IHitAreaObject {

	public BolaShot(float x, float y) {
		super(x, y);
		type = CvaModel.Unit.BOLA;
		texture = CvaModel.bolaShot;
	}

	public int speedModifier;// should put this in a super class

	@Override
	public int getDamage() {
		return 5;
	}

	@Override 
	public void render(SpriteBatch spriteBatch){
		super.render(spriteBatch);
//		Gdx.app.log("cva bola shot", Float.toString(hitArea.x) + " - width - " + Float.toString(hitArea.width));
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
		return 8;
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
	public void destroy(){
		super.destroy();
		CvaModel.thingsCheerborgsInteractWith.remove(this);
	}

	@Override
	public ArrayList<IHitAreaObject> getInteractables() {
		return null;
	}

	@Override
	public void handleCollision(IHitAreaObject o) {
	}
}
