package com.bbj.cva.screenobjects;

import org.bushe.swing.event.EventBus;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.bbj.cva.events.PlaceUnitEvent;

public abstract class Shooter extends AnimatedScreenObject {

	protected boolean alreadyShot = false;

	public Shooter(float x, float y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(SpriteBatch spriteBatch) {
		super.render(spriteBatch);

		// only shoot once per animation cycle and reset when going to a
		// different animation
		if (currentFrame == getShootingFrame() && !alreadyShot) {
			EventBus.publish(new PlaceUnitEvent(getProjectile()));
			alreadyShot = true;
		} else if (alreadyShot && currentFrame != getShootingFrame()) {
			alreadyShot = false;
		}
	}

	protected abstract ScreenObject getProjectile();

	protected abstract TextureRegion getShootingFrame();

}
