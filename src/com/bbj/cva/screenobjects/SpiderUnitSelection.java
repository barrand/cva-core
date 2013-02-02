package com.bbj.cva.screenobjects;

import org.bushe.swing.event.EventBus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.bbj.cva.events.UnitTypeEvent;
import com.bbj.cva.model.CvaModel;

public class SpiderUnitSelection extends Selection
{
	private boolean selected;
	private Texture select;

	@Override
	public void create()
	{
		selectionImage = new Texture(Gdx.files.internal("data/selection.png"));
		selectedImage = new Texture(Gdx.files.internal("data/selected.png"));
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

		/**
		 * Detect requested motion.
		 */
		if (!selected)
		{
			if (Gdx.input.isKeyPressed(Input.Keys.E))
			{
				rightWasDownLastFrame = true;
			}
			else if (rightWasDownLastFrame == true)
			{
				if (selectionRect.x + CvaModel.TILE_WIDTH <= CvaModel.TILE_WIDTH*7)
				{
					selectionRect.x += CvaModel.TILE_WIDTH;
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
			}
		}
		if (Gdx.input.isKeyPressed(Input.Keys.SPACE))
		{
			enterWasDownLastFrame = true;
		}
		else if (enterWasDownLastFrame == true)
		{
			if (!selected)
			{
				EventBus.publish(new UnitTypeEvent());
				select = selectedImage;
				selected = true;
			}
			else
			{
				select = selectionImage;
				selected = false;
			}
			enterWasDownLastFrame = false;
		}

		spriteBatch.draw(select, selectionRect.x, selectionRect.y);
	}
}

