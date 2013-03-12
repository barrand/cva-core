package com.bbj.cva.screenobjects;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.bbj.cva.events.RemoveScreenObjectEvent;
import com.bbj.cva.model.CvaModel;
import com.bbj.cva.screenobjects.interfaces.IAnimated;
import com.bbj.cva.screenobjects.interfaces.IHitAreaObject;
import com.bbj.cva.screenobjects.interfaces.IShooter;
import com.bbj.cva.screenobjects.projectiles.BolaShot;
import com.bbj.cva.screenobjects.projectiles.IProjectile;

public class SpiderUnit extends ScreenObject implements IShooter,
		IHitAreaObject, IAnimated {
	
	public SpiderUnit(float x, float y) {
		super(x, y);
		type = CvaModel.Unit.SPIDER;
	}

	@Override
	public void create() {
		super.create();
		currentAnim = CvaModel.spiderShoot;
	}

	@Override
	public void render(SpriteBatch spriteBatch) {
		super.render(spriteBatch);
	}

	@Override
	public void onAnimationEnd() {
		CvaModel.eventBus.post(new RemoveScreenObjectEvent(this));
	}

	// smaller is faster
	protected float getAnimationSpeed() {
		return 0.9f;
	}

	@Override
	public float getSpeedX() {
		return 0f;
	}

	@Override
	public float getSpeedY() {
		return 0f;
	}

	@Override
	public float getSpriteWidth() {
		return 190;
	}

	@Override
	public float getSpriteHeight() {
		return 140;
	}

	@Override
	public IProjectile getProjectile() {
		//todo this needs to be abstracted when I get more shooters
		BolaShot bolaShot = new BolaShot(x, y);
		bolaShot.y = y + getSpriteHeight()/2 - bolaShot.getSpriteHeight()/2;
		bolaShot.x = x + getHitAreaWidth()/2;
		CvaModel.thingsCheerborgsInteractWith.add(bolaShot);
		return bolaShot;
	}

	@Override
	public String getShootingRegionName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getShootingNumFrames() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getHitAreaWidth() {
		return 200;
	}

	@Override
	public float getHitAreaHeight() {
		return 100;
	}

	@Override
	public Rectangle getHitArea() {
		return hitArea;
	}

	@Override
	public ArrayList<IHitAreaObject> getInteractables() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void handleCollision(IHitAreaObject o) {
		// TODO Auto-generated method stub

	}

	@Override
	public AtlasRegion getShootingFrame() {
		return CvaModel.spiderShootingFrame;
	}
}
