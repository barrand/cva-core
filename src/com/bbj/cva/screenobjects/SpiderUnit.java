package com.bbj.cva.screenobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.bbj.cva.model.CvaModel;
import com.bbj.cva.screenobjectsdata.SpiderUnitData;

public class SpiderUnit implements ScreenObjectBase {
	Rectangle unitRect;
	Texture unitImage;
	float dx, dy;
		
	public SpiderUnit(SpiderUnitData spd)
	{
		dx = spd.dx;
		dy = spd.dy;
	}
	
	@Override
	public void create() {
		unitImage = new Texture(Gdx.files.internal("data/sprite.png"));
		
		unitRect = new Rectangle();
		unitRect.width = CvaModel.TILE_WIDTH;
		unitRect.height = CvaModel.TILE_HEIGHT;	
		unitRect.x = dx;
		unitRect.y = dy;
	}
	@Override
	public void render(SpriteBatch spriteBatch) 
	{
		spriteBatch.draw(unitImage, unitRect.x, unitRect.y);
	}
}
