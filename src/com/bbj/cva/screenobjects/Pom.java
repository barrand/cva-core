package com.bbj.cva.screenobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bbj.cva.events.PlaceUnitEvent;
import com.bbj.cva.events.RemoveScreenObjectEvent;
import com.bbj.cva.model.CvaModel;

public class Pom extends AnimatedScreenObject {

	public Pom(float x, float y)
	{
		super(x,y);
		type = CvaModel.Unit.POM;
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
	public void loadTextureIfNeeded() {
		if(CvaModel.pomWalk == null){
			CvaModel.pomWalk = new Texture(Gdx.files.internal("data/spriteSheets/pomWalk.png")); // #9
		}
		texture = CvaModel.pomWalk;
	}

	@Override
	public int getFrameCols() {
		return 10;
	}

	@Override
	public int getFrameRows() {
		return 14;
	}

	@Override
	public float getAnimationSpeed() {
		return 0.05f;
	}

	@Override
	public float getSpeedX() {
		return -4f;
	}

	@Override
	public float getSpeedY() {
		return 0f;
	}
	
	@Override
	public float getWidth() {
		return 50;
	}

	@Override
	public float getHeight() {
		return 125;
	}

}