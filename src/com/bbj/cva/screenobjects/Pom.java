package com.bbj.cva.screenobjects;

import org.bushe.swing.event.EventBus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bbj.cva.events.RemoveScreenObjectEvent;
import com.bbj.cva.model.CvaModel;

public class Pom extends AnimatedScreenObject {

	public Pom()
	{
		super();
		
	}

	@Override
	public void render(SpriteBatch spriteBatch) {
		for(ScreenObject o:CvaModel.thingsCheerborgsInteractWith){
			if(o.unitRect.overlaps(unitRect)){
				Gdx.app.log("cva", this.toString());
				EventBus.publish(new RemoveScreenObjectEvent(this));
				EventBus.publish(new RemoveScreenObjectEvent(o));
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
		return 5;
	}

	@Override
	public int getFrameRows() {
		return 4;
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