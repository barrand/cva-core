package com.bbj.cva.screenobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.bbj.cva.events.PlaceUnitEvent;
import com.bbj.cva.events.RemoveScreenObjectEvent;
import com.bbj.cva.model.CvaModel;

public class Pom extends HitableAnimatedScreenObject {

	public Pom(float x, float y) {
		super(x, y);
		type = CvaModel.Unit.POM;
		texture = CvaModel.pomWalk;
	}

	@Override
	public void render(SpriteBatch spriteBatch) {
		for (IHitAreaObject o : CvaModel.thingsCheerborgsInteractWith) {

			// if she got hit, then kill her and put in the die animation
			if (o.getHitArea().overlaps(hitArea)) {
				Gdx.app.log("cva", this.toString());
				CvaModel.eventBus.post(new RemoveScreenObjectEvent(this));
				CvaModel.eventBus.post(new RemoveScreenObjectEvent((ScreenObject) o));

				PomDie pomDie = new PomDie(hitArea.x - hitArea.getWidth() / 2,
						hitArea.y);
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

	// smaller is faster
	@Override
	public float getAnimationSpeed() {
		return 0.017f;
	}

	// larger is faster
	@Override
	public float getSpeedX() {
		return -1.2f;
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
}