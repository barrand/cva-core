package com.bbj.cva.screenobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
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

		if (now - lastMoveSelectionTime > SELECTION_WAIT) {
			if (Gdx.input.isKeyPressed(Input.Keys.DPAD_RIGHT)) {
				selectionRect.x += CvaModel.TILE_WIDTH;
			} else if (Gdx.input.isKeyPressed(Input.Keys.DPAD_LEFT)) {
				selectionRect.x -= CvaModel.TILE_WIDTH;
			}
			if (Gdx.input.isKeyPressed(Input.Keys.DPAD_UP)) {
				selectionRect.y += CvaModel.TILE_HEIGHT;
			} else if (Gdx.input.isKeyPressed(Input.Keys.DPAD_DOWN)) {
				selectionRect.y -= CvaModel.TILE_HEIGHT;
			}
			lastMoveSelectionTime = now;
		}
		
		spriteBatch.draw(selectionImage, selectionRect.x, selectionRect.y);
	}
}
