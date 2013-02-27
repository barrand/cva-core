package com.bbj.cva.screenobjects;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bbj.cva.events.RemoveScreenObjectEvent;
import com.bbj.cva.model.CvaModel;
import com.bbj.cva.screenobjects.interfaces.IAnimatedAttacker;
import com.bbj.cva.screenobjects.interfaces.IAnimatedDier;
import com.bbj.cva.screenobjects.interfaces.IAnimatedWalker;
import com.bbj.cva.screenobjects.interfaces.IHitAreaObject;
import com.bbj.cva.screenobjects.projectiles.IProjectile;

public class Pom extends AttackingHitableAnimated implements IAnimatedWalker, IAnimatedDier, IAnimatedAttacker {

	private float speedX = -1.2f;
	
	public Pom(float x, float y) {
		super(x, y);
		type = CvaModel.Unit.POM;
	}
	
	@Override
	public void create(){
		super.create();
		currentAnim = walkAnim;
	}

	@Override
	public void render(SpriteBatch spriteBatch) {
		super.render(spriteBatch);
	}

	@Override
	public void onAnimationEnd(){
		CvaModel.eventBus.post(new RemoveScreenObjectEvent(this));
	}
	
	// smaller is faster
	protected float getAnimationSpeed() {
		return 0.03f;
	}

	// larger is faster
	@Override
	public float getSpeedX() {
		return speedX ;
	}

	@Override
	public float getSpeedY() {
		return 0f;
	}

	@Override
	public float getSpriteWidth() {
		return 300;
	}

	@Override
	public float getSpriteHeight() {
		return 300;
	}

	@Override
	public float getHitAreaWidth() {
		return 80;
	}

	@Override
	public float getHitAreaHeight() {
		return 130;
	}

	@Override
	protected float getAttackAreaWidth() {
		return 100;
	}

	@Override
	protected float getAttackAreaHeight() {
		// TODO Auto-generated method stub
		return 130;
	}

	@Override
	protected boolean attacksToTheLeft() {
		return true;
	}

	@Override
	protected ArrayList<IHitAreaObject> getInteractables() {
		return CvaModel.thingsCheerborgsInteractWith;
	}

	@Override
	protected void handleCollision(IHitAreaObject o) {
		if(o instanceof IProjectile){
			Gdx.app.log("cva", this.toString());
			stateTime = 0f;
			currentAnim = dieAnim;
			speedX = 0f;
			checkForInteractions = false;
			loop = false;
			CvaModel.eventBus.post(new RemoveScreenObjectEvent(
					(ScreenObject) o));
		}
	}

	@Override
	public String getWalkRegionName() {
		return "render-grunt-walk/CB-grunt-walking";
	}
	@Override
	public String getDieRegionName() {
		return "render-grunt-die/CB-grunt-death";
	}

	@Override
	public String getAttackRegionName() {
		return "render-grunt-attack/CB-grunt-attack";
	}

	@Override
	public int getAttackNumFrames() {
		return 45;
	}

	@Override
	public int getDieNumFrames() {
		return 38;
	}

	@Override
	public int getWalkNumFrames() {
		return 40;
	}

	@Override
	protected String getTextureAtlasUrl() {
		return "data/spriteSheets/pomAll.txt";
	}
}