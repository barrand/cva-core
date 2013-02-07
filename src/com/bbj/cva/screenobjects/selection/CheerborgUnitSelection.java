package com.bbj.cva.screenobjects.selection;

import org.bushe.swing.event.EventBus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.bbj.cva.events.UnitTypeSelectEvent;
import com.bbj.cva.model.CvaModel;

public class CheerborgUnitSelection extends Selection
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

		selectionRect.x = CvaModel.TILE_WIDTH*7;
		selectionRect.y = CvaModel.TILE_HEIGHT*7;
	}

	@Override
	public void render(SpriteBatch spriteBatch)
	{
		System.nanoTime();
		if (init)
		{
			init = false;
			EventBus.publish(new UnitTypeSelectEvent(selectionRect.x+getWidth()/2));
		}
		/**
		 * Detect requested motion.
		 */
		if (Gdx.input.isKeyPressed(Input.Keys.L))
		{
			rightWasDownLastFrame = true;
		}
		else if (rightWasDownLastFrame == true)
		{
			selectionRect.x += CvaModel.TILE_WIDTH;
			rightWasDownLastFrame = false;
			EventBus.publish(new UnitTypeSelectEvent(selectionRect.x+getWidth()/2));
		}

		if (Gdx.input.isKeyPressed(Input.Keys.J))
		{
			leftWasDownLastFrame = true;
		}
		else if (leftWasDownLastFrame == true)
		{
			if (selectionRect.x - CvaModel.TILE_WIDTH >= CvaModel.TILE_WIDTH*7)
			{
				selectionRect.x -= CvaModel.TILE_WIDTH;
				EventBus.publish(new UnitTypeSelectEvent(selectionRect.x+getWidth()/2));
			}
			leftWasDownLastFrame = false;
			
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
