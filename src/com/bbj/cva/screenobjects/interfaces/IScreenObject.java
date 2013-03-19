package com.bbj.cva.screenobjects.interfaces;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface IScreenObject {

	public void create();
	public void destroy();
	public void render(SpriteBatch spriteBatch);
}
