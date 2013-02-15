package com.bbj.cva.screenobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bbj.cva.events.PlaceUnitEvent;
import com.bbj.cva.events.RemoveScreenObjectEvent;
import com.bbj.cva.model.CvaModel;

public class Pom extends AnimatedScreenObject {

	public Pom(float x, float y)
	{
		super(x,y);
		type = CvaModel.Unit.POM;
		texture = CvaModel.pomWalk;
	}

	@Override
	public void render(SpriteBatch spriteBatch) {
		for(ScreenObject o:CvaModel.thingsCheerborgsInteractWith){
			
			//if she got hit, then kill her and put in the die animation
			if(o.unitRect.overlaps(unitRect)){
				Gdx.app.log("cva", this.toString());
				CvaModel.eventBus.post(new RemoveScreenObjectEvent(this));
				CvaModel.eventBus.post(new RemoveScreenObjectEvent(o));
				
				PomDie pomDie = new PomDie(unitRect.x, unitRect.y);
				CvaModel.eventBus.post(new PlaceUnitEvent(pomDie));
				
			}
		}
		super.render(spriteBatch);
	}

	@Override
	public int getFrameCols() {
		return 10;
	}

	@Override
	public int getFrameRows() {
		return 8;
	}

	//smaller is faster
	@Override
	public float getAnimationSpeed() {
		return 0.017f;
	}

	//larger is faster
	@Override
	public float getSpeedX() {
		return -1.2f;
	}

	@Override
	public float getSpeedY() {
		return 0f;
	}
	
	@Override
	public float getWidth() {
		return 300;
	}

	@Override
	public float getHeight() {
		return 300;
	}

}