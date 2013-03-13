package com.bbj.cva.screenobjects.selection;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.bbj.cva.events.UnitTypeSelectEvent;
import com.bbj.cva.model.CvaModel;
import com.bbj.cva.util.PlayerInput;

public class CheerborgUnitSelection extends Selection
{
	private Texture select; 
	private boolean init = true;
	@Override
	public void create()
	{
		playerNum = 2;
		selectionImage = new Texture(Gdx.files.internal("data/selection.png"));
		
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
			CvaModel.eventBus.post(new UnitTypeSelectEvent(selectionRect));
		}
		/**
		 * Detect requested motion.
		 */
		if (PlayerInput.moveSelectRightPressed(playerNum))
		{
			rightWasDownLastFrame = true;
		}
		else if (rightWasDownLastFrame == true)
		{
			selectionRect.x += CvaModel.TILE_WIDTH;
			rightWasDownLastFrame = false;
			CvaModel.eventBus.post(new UnitTypeSelectEvent(selectionRect));
		}

		if (PlayerInput.moveSelectLeftPressed(playerNum))
		{
			leftWasDownLastFrame = true;
		}
		else if (leftWasDownLastFrame == true)
		{
			if (selectionRect.x - CvaModel.TILE_WIDTH >= CvaModel.TILE_WIDTH*7)
			{
				selectionRect.x -= CvaModel.TILE_WIDTH;
				CvaModel.eventBus.post(new UnitTypeSelectEvent(selectionRect));
			}
			leftWasDownLastFrame = false;
			
		}
		
		spriteBatch.draw(select, selectionRect.x, selectionRect.y);
	}
}
