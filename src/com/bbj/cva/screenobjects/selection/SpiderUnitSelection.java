package com.bbj.cva.screenobjects.selection;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.bbj.cva.events.UnitTypeSelectEvent;
import com.bbj.cva.model.CvaModel;

public class SpiderUnitSelection extends Selection
{
	private Texture select;
	private boolean init = true;

	@Override
	public void create()
	{
		selectionImage = new Texture(Gdx.files.internal("data/selection.png"));
		//selectedImage = new Texture(Gdx.files.internal("data/selected.png"));
		select = selectionImage;
		
		selectionRect = new Rectangle();
		selectionRect.width = CvaModel.TILE_WIDTH;
		selectionRect.height = CvaModel.TILE_HEIGHT;

		selectionRect.x = 0;
		selectionRect.y = CvaModel.TILE_HEIGHT*7;
	}

	@Override
	public void render(SpriteBatch spriteBatch)
	{
		System.nanoTime();
		if (init)
		{
			init = false;
			CvaModel.eventBus.post(new UnitTypeSelectEvent(selectionRect.x+getWidth()/2));
		}
		/**
		 * Detect requested motion.
		 */
		
		if (Gdx.input.isKeyPressed(Input.Keys.E))
		{
			rightWasDownLastFrame = true;
		}
		else if (rightWasDownLastFrame == true)
		{
			if (selectionRect.x + CvaModel.TILE_WIDTH <= CvaModel.TILE_WIDTH*7)
			{
				selectionRect.x += CvaModel.TILE_WIDTH;
				CvaModel.eventBus.post(new UnitTypeSelectEvent(selectionRect.x+getWidth()/2));
			}
			rightWasDownLastFrame = false;
		}

		if (Gdx.input.isKeyPressed(Input.Keys.Q))
		{
			leftWasDownLastFrame = true;
		}
		else if (leftWasDownLastFrame == true)
		{
			selectionRect.x -= CvaModel.TILE_WIDTH;
			leftWasDownLastFrame = false;
			CvaModel.eventBus.post(new UnitTypeSelectEvent(selectionRect.x+getWidth()/2));
		}

		spriteBatch.draw(select, selectionRect.x, selectionRect.y);
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

	@Override
	public void setX(float x)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setY(float y)
	{
		// TODO Auto-generated method stub
		
	}
}

