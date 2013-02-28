package com.bbj.cva.screenobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bbj.cva.model.CvaModel;
import com.bbj.cva.screenobjects.interfaces.IScreenObject;

public abstract class ScreenObjectSimple implements IScreenObject {
	protected Texture texture;
	public CvaModel.Unit type;
	public float x,y;

	public ScreenObjectSimple(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public void render(SpriteBatch spriteBatch) {
		spriteBatch.draw(texture, x, y);
	}
}
