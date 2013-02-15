package com.bbj.cva.screenobjects.selection;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.bbj.cva.events.PlaceUnitEvent;
import com.bbj.cva.events.SpiderUnitTypeEvent;
import com.bbj.cva.model.CvaModel;
import com.bbj.cva.screenobjects.ScreenObject;
import com.squareup.otto.Subscribe;

public class SpiderFieldSelection extends Selection
{
	private ScreenObject unitType;
	
	public SpiderFieldSelection()
	{
		CvaModel.eventBus.register(this);
	}
	
	@Override
	public void create()
	{
		selectionImage = new Texture(Gdx.files.internal("data/selection.png"));

		// TODO Auto-generated method stub
		selectionRect = new Rectangle();
		selectionRect.width = CvaModel.TILE_WIDTH;
		selectionRect.height = CvaModel.TILE_HEIGHT;
		selectionRect.x = CvaModel.TILE_WIDTH*3;
		selectionRect.y = CvaModel.TILE_HEIGHT*4;
	}
	
	@Override
	public void render(SpriteBatch spriteBatch)
	{
		System.nanoTime();
	
		/**
		 * Detect requested motion.
		 */
	
		if (Gdx.input.isKeyPressed(Input.Keys.D))
		{
			rightWasDownLastFrame = true;
		}
		else if (rightWasDownLastFrame == true)
		{
			selectionRect.x += CvaModel.TILE_WIDTH;
			rightWasDownLastFrame = false;
		}
	
		if (Gdx.input.isKeyPressed(Input.Keys.A))
		{
			leftWasDownLastFrame = true;
		}
		else if (leftWasDownLastFrame == true)
		{
			selectionRect.x -= CvaModel.TILE_WIDTH;
			leftWasDownLastFrame = false;
		}
	
		if (Gdx.input.isKeyPressed(Input.Keys.W))
		{
			upWasDownLastFrame = true;
		}
		else if (upWasDownLastFrame == true)
		{
			selectionRect.y += CvaModel.TILE_HEIGHT;
			upWasDownLastFrame = false;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.S))
		{
			downWasDownLastFrame = true;
		}
		else if (downWasDownLastFrame == true)
		{
			selectionRect.y -= CvaModel.TILE_HEIGHT;
			downWasDownLastFrame = false;
		}
	
		if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT))
		{
			enterWasDownLastFrame = true;
		}
		else if (enterWasDownLastFrame == true)
		{

			//SpiderUnit spider = new SpiderUnit(selectionRect.x,selectionRect.y);
			//CvaModel.eventBus.post(new PlaceUnitEvent(spider));
			if (unitType != null)
			{
				CvaModel.eventBus.post(new PlaceUnitEvent(selectionRect.x + getWidth()/2, selectionRect.y, unitType));
			}
			enterWasDownLastFrame = false;
		}
	
		spriteBatch.draw(selectionImage, selectionRect.x, selectionRect.y);
	}
	
	@Subscribe
	public void onSelect(SpiderUnitTypeEvent event) {
		unitType = event.screenObject;

	}
	
	@Override
	public float getWidth() {
		return CvaModel.TILE_WIDTH;
	}

	@Override
	public float getHeight() {
		return CvaModel.TILE_HEIGHT;
	}
	
	@Override
	public void setX(float x) {
	}

	@Override
	public void setY(float y) {
	}
	@Override
	public float getX()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getY()
	{
		// TODO Auto-generated method stub
		return 0;
	}
}
