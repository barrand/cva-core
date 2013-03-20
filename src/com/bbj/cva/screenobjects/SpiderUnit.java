package com.bbj.cva.screenobjects;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.bbj.cva.events.RemoveScreenObjectEvent;
import com.bbj.cva.model.CvaModel;
import com.bbj.cva.screenobjects.interfaces.IAnimated;
import com.bbj.cva.screenobjects.interfaces.IAttackable;
import com.bbj.cva.screenobjects.interfaces.IHitAreaObject;
import com.bbj.cva.screenobjects.interfaces.IShooter;
import com.bbj.cva.screenobjects.projectiles.BolaShot;
import com.bbj.cva.screenobjects.projectiles.IProjectile;

public class SpiderUnit extends ScreenObject implements IShooter,
		IAttackable, IAnimated {
	
	public SpiderUnit(float x, float y) {
		super(x, y, CvaModel.Unit.SPIDER);
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


	@Override
	public IProjectile getProjectile() {
		//todo this needs to be abstracted when I get more shooters
		BolaShot bolaShot = new BolaShot(x, y);
		bolaShot.y = y + stats.hitAreaHeight/2 - bolaShot.stats.spriteHeight/2;
		bolaShot.x = x + stats.hitAreaWidth/2;
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
