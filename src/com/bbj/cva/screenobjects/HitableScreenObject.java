package com.bbj.cva.screenobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.bbj.cva.model.CvaModel;

public abstract class HitableScreenObject extends ScreenObject implements IHitAreaObject {

protected Rectangle hitArea;
	
	public HitableScreenObject(float x, float y) {
		super(x, y);
		// TODO Auto-generated constructor stub
		hitArea = new Rectangle();
		hitArea.width = getSpriteWidth();
		hitArea.height = getSpriteHeight();
		hitArea.x = x;
		hitArea.y = y;
	}
	
	@Override
	public void render(SpriteBatch spriteBatch){
		super.render(spriteBatch);
		hitArea.x = x - hitArea.width/2;
    	hitArea.y = y - hitArea.height/2;
    	spriteBatch.draw(CvaModel.blue, hitArea.x, hitArea.y, hitArea.width, hitArea.height, 0, 0, 0, 0, false, false);
	}

	@Override
	public Rectangle getHitArea() {
		return hitArea;
	}
	
}
