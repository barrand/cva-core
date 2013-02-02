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
	private boolean rightWasDownLastFrame = false;
	private boolean leftWasDownLastFrame = false;
	private boolean upWasDownLastFrame = false;
	private boolean downWasDownLastFrame = false;
	private boolean enterWasDownLastFrame = false;
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
		
		if(Gdx.input.isKeyPressed(Input.Keys.ENTER)){
			enterWasDownLastFrame = true;
		}else if(enterWasDownLastFrame == true){
			// TODO: This should call a Factory to create a new object
			//        ** Instead of "creating" a new object...should it just grab an already created one?
			//        ** What are our memory limitations?  Standard on dynamic memory allocation?
			//       Then it needs to get added to the ScreenObject Array for the CVA::render() call.
			EventBus.publish(new PlaceUnitEvent(selectionRect.x, selectionRect.y));
			enterWasDownLastFrame = false;
		}
		
		spriteBatch.draw(selectionImage, selectionRect.x, selectionRect.y);
	}
	@Override
	public void setX(int x) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setY(int y) {
		// TODO Auto-generated method stub
		
	}
}