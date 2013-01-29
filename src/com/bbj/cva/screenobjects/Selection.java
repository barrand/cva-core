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
	private boolean rightWasDownLastFrame = false;
	private boolean leftWasDownLastFrame = false;
	private boolean upWasDownLastFrame = false;
	private boolean downWasDownLastFrame = false;
	@Override
	public void render(SpriteBatch spriteBatch) {
		long now = System.nanoTime();

		/**
		 * Detect requested motion.
		 */

		if(Gdx.input.isKeyPressed(Input.Keys.DPAD_RIGHT)){
			rightWasDownLastFrame = true;
		}else if(rightWasDownLastFrame == true){
			selectionRect.x += CvaModel.TILE_WIDTH;
			rightWasDownLastFrame = false;
		}
		
		if(Gdx.input.isKeyPressed(Input.Keys.DPAD_LEFT)){
			leftWasDownLastFrame = true;
		}else if(leftWasDownLastFrame == true){
			selectionRect.x -= CvaModel.TILE_WIDTH;
			leftWasDownLastFrame = false;
		}
		
		if(Gdx.input.isKeyPressed(Input.Keys.DPAD_UP)){
			upWasDownLastFrame = true;
		}else if(upWasDownLastFrame == true){
			selectionRect.y += CvaModel.TILE_HEIGHT;
			upWasDownLastFrame = false;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.DPAD_DOWN)){
			downWasDownLastFrame = true;
		}else if(downWasDownLastFrame == true){
			selectionRect.y -= CvaModel.TILE_HEIGHT;
			downWasDownLastFrame = false;
		}
		
//		if (now - lastMoveSelectionTime > SELECTION_WAIT) {
//			if (Gdx.input.isKeyPressed(Input.Keys.DPAD_RIGHT)) {
//				selectionRect.x += CvaModel.TILE_WIDTH;
//			} else if (Gdx.input.isKeyPressed(Input.Keys.DPAD_LEFT)) {
//				selectionRect.x -= CvaModel.TILE_WIDTH;
//			}
//			if (Gdx.input.isKeyPressed(Input.Keys.DPAD_UP)) {
//				selectionRect.y += CvaModel.TILE_HEIGHT;
//			} else if (Gdx.input.isKeyPressed(Input.Keys.DPAD_DOWN)) {
//				selectionRect.y -= CvaModel.TILE_HEIGHT;
//			}
//			lastMoveSelectionTime = now;
//		}
		
		spriteBatch.draw(selectionImage, selectionRect.x, selectionRect.y);
	}
}
