package com.bbj.cva.screenobjects.selection;

import org.bushe.swing.event.EventBus;
import org.bushe.swing.event.EventSubscriber;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.bbj.cva.events.PlaceUnitEvent;
import com.bbj.cva.events.SpiderUnitTypeEvent;
import com.bbj.cva.model.CvaModel;
import com.bbj.cva.screenobjects.ScreenObject;

public class SpiderFieldSelection extends Selection
{
	private ScreenObject unitType;
	
	public SpiderFieldSelection()
	{
		EventBus.subscribe(SpiderUnitTypeEvent.class,  new UnitTypeListener());
	}
	
	@Override
	public void create()
	{
		selectionImage = new Texture(Gdx.files.internal("data/selection.png"));

		// TODO Auto-generated method stub
		selectionRect = new Rectangle();
		selectionRect.width = CvaModel.TILE_WIDTH;
		selectionRect.height = CvaModel.TILE_HEIGHT;
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
			//EventBus.publish(new PlaceUnitEvent(spider));
			if (unitType != null)
			{
				EventBus.publish(new PlaceUnitEvent(selectionRect.x, selectionRect.y, unitType));
			}
			enterWasDownLastFrame = false;
		}
	
		spriteBatch.draw(selectionImage, selectionRect.x, selectionRect.y);
	}
	
	class UnitTypeListener implements EventSubscriber<SpiderUnitTypeEvent>
	{
		@Override
		public void onEvent(SpiderUnitTypeEvent type)
		{
			unitType = type.screenObject;
		}
		
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
