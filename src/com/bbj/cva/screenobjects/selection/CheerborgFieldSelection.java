package com.bbj.cva.screenobjects.selection;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.bbj.cva.events.CheerborgUnitTypeEvent;
import com.bbj.cva.events.PlaceUnitEvent;
import com.bbj.cva.model.CvaModel;
import com.bbj.cva.screenobjects.ScreenObject;
import com.squareup.otto.Subscribe;

public class CheerborgFieldSelection extends Selection {
	private ScreenObject unitType;

	public CheerborgFieldSelection() {
		CvaModel.eventBus.register(this);
	}

	@Override
	public void create() {
		selectionImage = new Texture(Gdx.files.internal("data/selection.png"));

		selectionRect = new Rectangle();
		selectionRect.width = CvaModel.TILE_WIDTH;
		selectionRect.height = CvaModel.TILE_HEIGHT;
		selectionRect.x = CvaModel.TILE_WIDTH * 11;
		selectionRect.y = CvaModel.TILE_HEIGHT * 4;
	}

	@Override
	public void render(SpriteBatch spriteBatch) {
		System.nanoTime();

		/**
		 * Detect requested motion.
		 */

		if (Gdx.input.isKeyPressed(Input.Keys.DPAD_RIGHT)) {
			rightWasDownLastFrame = true;
		} else if (rightWasDownLastFrame == true) {
			selectionRect.x += CvaModel.TILE_WIDTH;
			rightWasDownLastFrame = false;
		}

		if (Gdx.input.isKeyPressed(Input.Keys.DPAD_LEFT)) {
			leftWasDownLastFrame = true;
		} else if (leftWasDownLastFrame == true) {
			selectionRect.x -= CvaModel.TILE_WIDTH;
			leftWasDownLastFrame = false;
		}

		if (Gdx.input.isKeyPressed(Input.Keys.DPAD_UP)) {
			upWasDownLastFrame = true;
		} else if (upWasDownLastFrame == true) {
			selectionRect.y += CvaModel.TILE_HEIGHT;
			upWasDownLastFrame = false;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.DPAD_DOWN)) {
			downWasDownLastFrame = true;
		} else if (downWasDownLastFrame == true) {
			selectionRect.y -= CvaModel.TILE_HEIGHT;
			downWasDownLastFrame = false;
		}

		if (Gdx.input.isKeyPressed(Input.Keys.ENTER) || Gdx.input.isTouched()) {
			enterWasDownLastFrame = true;
		} else if (enterWasDownLastFrame == true) {

			// Pom pom = new Pom(selectionRect.x, selectionRect.y);
			// CvaModel.eventBus.post(new PlaceUnitEvent(pom));

			// BolaShot shot = new BolaShot(0f, selectionRect.y +
			// CvaModel.TILE_HEIGHT/2);
			// CvaModel.eventBus.post(new PlaceUnitEvent(shot));

			if (unitType != null) {
				CvaModel.eventBus.post(new PlaceUnitEvent(selectionRect.x
						+ getWidth() / 2, selectionRect.y, unitType));
			}
			enterWasDownLastFrame = false;
		}

		spriteBatch.draw(selectionImage, selectionRect.x, selectionRect.y);
	}

	@Subscribe
	public void onCheerborgUnitType(CheerborgUnitTypeEvent event) {
		unitType = event.screenObject;

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
		// TODO Auto-generated method stub

	}

	@Override
	public void setY(float y) {
		// TODO Auto-generated method stub

	}

	@Override
	public float getX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getY() {
		// TODO Auto-generated method stub
		return 0;
	}

}
