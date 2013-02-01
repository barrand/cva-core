package com.bbj.cva.screenobjects;

import org.bushe.swing.event.EventBus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.bbj.cva.events.PlaceUnitEvent;
import com.bbj.cva.model.CvaModel;

public class Selection implements ScreenObjectBase {
	private long lastMoveSelectionTime;
	Rectangle selectionRect;
	Texture selectionImage;
	private static final long SELECTION_WAIT = 100000000;
	
	@Override
	public void create() {
		selectionImage = new Texture(Gdx.files.internal("data/selection.png"));
		
		// TODO Auto-generated method stub
		selectionRect = new Rectangle();
		selectionRect.width = CvaModel.TILE_WIDTH;
		selectionRect.height = CvaModel.TILE_HEIGHT;
		selectionRect.x = 0;
		selectionRect.y = 0;
		
	}
	@Override
	public void render(SpriteBatch spriteBatch) {
		long now = System.nanoTime();

		/**
		 * Detect requested motion.
		 */

		if (now - lastMoveSelectionTime > SELECTION_WAIT) 
		{
			if (Gdx.input.isKeyPressed(Input.Keys.DPAD_RIGHT)) 
			{
				if (selectionRect.x + CvaModel.TILE_WIDTH < CvaModel.screenWidth)
					selectionRect.x += CvaModel.TILE_WIDTH;
			} 
			else if (Gdx.input.isKeyPressed(Input.Keys.DPAD_LEFT)) 
			{
				if (selectionRect.x - CvaModel.TILE_WIDTH >= 0)
					selectionRect.x -= CvaModel.TILE_WIDTH;
			}
			if (Gdx.input.isKeyPressed(Input.Keys.DPAD_UP)) 
			{
				// FIXME: This has an issue because the tile height is not a multiple of the ScreenHeight
				if (selectionRect.y + CvaModel.TILE_HEIGHT < CvaModel.screenHeight)
					selectionRect.y += CvaModel.TILE_HEIGHT;
			}
			else if (Gdx.input.isKeyPressed(Input.Keys.DPAD_DOWN)) 
			{
				if (selectionRect.y - CvaModel.TILE_HEIGHT >= 0)
					selectionRect.y -= CvaModel.TILE_HEIGHT;
			}
			if (Gdx.input.isKeyPressed(Input.Keys.ENTER))
			{
				// TODO: This should call a Factory to create a new object
				//        ** Instead of "creating" a new object...should it just grab an already created one?
				//        ** What are our memory limitations?  Standard on dynamic memory allocation?
				//       Then it needs to get added to the ScreenObject Array for the CVA::render() call.
				EventBus.publish(new PlaceUnitEvent(selectionRect.x, selectionRect.y));
			}
			lastMoveSelectionTime = now;
		}
		
		spriteBatch.draw(selectionImage, selectionRect.x, selectionRect.y);
	}
}
