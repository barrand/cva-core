package com.bbj.cva.screenobjects.selection;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.controllers.mappings.Ouya;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.bbj.cva.events.PlaceUnitEvent;
//import com.bbj.cva.events.QueryCheerUnitAvailableEvent;
import com.bbj.cva.model.CvaModel;
import com.bbj.cva.screenobjects.Pom;
import com.bbj.cva.util.PlayerInput;

public class CheerborgFieldSelection extends Selection {
	public CheerborgFieldSelection() {
		CvaModel.eventBus.register(this);
	}

	@Override
	public void create() {
		playerNum = 2;
		selectionImage = new Texture(Gdx.files.internal("data/selection.png"));

		selectionRect = new Rectangle();
		selectionRect.width = CvaModel.TILE_WIDTH;
		selectionRect.height = CvaModel.TILE_HEIGHT;
		selectionRect.x = CvaModel.TILE_WIDTH * 11;
		selectionRect.y = CvaModel.TILE_HEIGHT * 4;

		// setup the listener that prints events to the console
		Controllers.addListener(new ControllerListener() {
			public int indexOf(Controller controller) {
				return Controllers.getControllers().indexOf(controller, true);
			}

			@Override
			public void connected(Controller controller) {
				Gdx.app.log("cva", "connected " + controller.getName());
				int i = 0;
				for (Controller c : Controllers.getControllers()) {
					Gdx.app.log("cva", "#" + i++ + ": " + c.getName());
				}
			}

			@Override
			public void disconnected(Controller controller) {
				Gdx.app.log("cva", "disconnected " + controller.getName());
				int i = 0;
				for (Controller c : Controllers.getControllers()) {
					Gdx.app.log("cva", "#" + i++ + ": " + c.getName());
				}
				if (Controllers.getControllers().size == 0)
					Gdx.app.log("cva", "No controllers attached");
			}

			@Override
			public boolean buttonDown(Controller controller, int buttonIndex) {
				Gdx.app.log("cva", "#" + indexOf(controller) + ", button "
						+ buttonIndex + " down");
				if(buttonIndex == Ouya.BUTTON_DPAD_LEFT){
					selectionRect.x -= CvaModel.TILE_WIDTH;
				}else if(buttonIndex == Ouya.BUTTON_DPAD_DOWN){
					selectionRect.y -= CvaModel.TILE_WIDTH;
				}else if(buttonIndex == Ouya.BUTTON_DPAD_RIGHT){
					selectionRect.x += CvaModel.TILE_WIDTH;
					
				}else if(buttonIndex == Ouya.BUTTON_DPAD_UP){
					selectionRect.y += CvaModel.TILE_WIDTH;
				}
				return false;
			}

			@Override
			public boolean buttonUp(Controller controller, int buttonIndex) {
				Gdx.app.log("cva", "#" + indexOf(controller) + ", button "
						+ buttonIndex + " up");
				return false;
			}

			@Override
			public boolean axisMoved(Controller controller, int axisIndex,
					float value) {
				return false;
			}

			@Override
			public boolean povMoved(Controller controller, int povIndex,
					PovDirection value) {
				return false;
			}

			@Override
			public boolean xSliderMoved(Controller controller, int sliderIndex,
					boolean value) {
				return false;
			}

			@Override
			public boolean ySliderMoved(Controller controller, int sliderIndex,
					boolean value) {
				return false;
			}

			@Override
			public boolean accelerometerMoved(Controller controller,
					int accelerometerIndex, Vector3 value) {
				// not printing this as we get to many values
				return false;
			}
		});

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
			//todo brad
//			CvaModel.eventBus.post(new QueryCheerUnitAvailableEvent(selectionRect.x, selectionRect.y));
//			if (unitType != null) {
//				switch (unitType) {
//				case POM:
					Pom screenObject = new Pom(selectionRect.x
							+ getSpriteWidth() / 2, selectionRect.y);
					CvaModel.eventBus.post(new PlaceUnitEvent(screenObject));
//					break;
//				case SPIDER:
//					break;
//				case BOLA:
//					break;
//				}
//			}
			enterWasDownLastFrame = false;
		}

		spriteBatch.draw(selectionImage, selectionRect.x, selectionRect.y);
	}

}
