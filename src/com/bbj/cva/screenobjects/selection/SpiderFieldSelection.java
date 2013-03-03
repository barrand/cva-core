package com.bbj.cva.screenobjects.selection;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.bbj.cva.events.PlaceUnitEvent;
import com.bbj.cva.events.SpiderUnitTypeEvent;
import com.bbj.cva.model.CvaModel;
import com.bbj.cva.util.PlayerInput;
import com.squareup.otto.Subscribe;

public class SpiderFieldSelection extends Selection {
	private CvaModel.Unit unitType;

	public SpiderFieldSelection() {
		CvaModel.eventBus.register(this);
	}

	@Override
	public void create() {
		playerNum = 1;
		selectionImage = new Texture(Gdx.files.internal("data/selection.png"));

		// TODO Auto-generated method stub
		selectionRect = new Rectangle();
		selectionRect.width = CvaModel.TILE_WIDTH;
		selectionRect.height = CvaModel.TILE_HEIGHT;
		selectionRect.x = CvaModel.TILE_WIDTH * 3;
		selectionRect.y = CvaModel.TILE_HEIGHT * 4;
	}

	@Override
	public void render(SpriteBatch spriteBatch) {
		System.nanoTime();

		/**
		 * Detect requested motion.
		 */

		if (PlayerInput.moveRightPressed(playerNum)) {
			rightWasDownLastFrame = true;
		} else if (rightWasDownLastFrame == true) {
			selectionRect.x += CvaModel.TILE_WIDTH;
			rightWasDownLastFrame = false;
		}

		if (PlayerInput.moveLeftPressed(playerNum)) {
			leftWasDownLastFrame = true;
		} else if (leftWasDownLastFrame == true) {
			selectionRect.x -= CvaModel.TILE_WIDTH;
			leftWasDownLastFrame = false;
		}

		if (PlayerInput.moveUpPressed(playerNum)) {
			upWasDownLastFrame = true;
		} else if (upWasDownLastFrame == true) {
			selectionRect.y += CvaModel.TILE_HEIGHT;
			upWasDownLastFrame = false;
		}
		if (PlayerInput.moveDownPressed(playerNum)) {
			downWasDownLastFrame = true;
		} else if (downWasDownLastFrame == true) {
			selectionRect.y -= CvaModel.TILE_HEIGHT;
			downWasDownLastFrame = false;
		}

		if (PlayerInput.actionButtonPressed(playerNum)) {
			enterWasDownLastFrame = true;
		} else if (enterWasDownLastFrame == true) {
//			if (unitType != null) {
				CvaModel.eventBus.post(new PlaceUnitEvent(selectionRect.x
						+ getSpriteWidth() / 2, selectionRect.y,
						CvaModel.Unit.SPIDER));
//			}
			enterWasDownLastFrame = false;
		}

		spriteBatch.draw(selectionImage, selectionRect.x, selectionRect.y);
	}

	@Subscribe
	public void onSpiderUnitType(SpiderUnitTypeEvent event) {
		unitType = event.unitType;

	}

	@Override
	public float getSpriteWidth() {
		return CvaModel.TILE_WIDTH;
	}

	@Override
	public float getSpriteHeight() {
		return CvaModel.TILE_HEIGHT;
	}
}
