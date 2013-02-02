package com.bbj.cva.screenobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface ScreenObjectBase {

	public void create();;
	public void render(SpriteBatch spriteBatch);
	public void setX(int x);
	public void setY(int y);
}
