package com.bbj.cva.screenobjects;

import org.bushe.swing.event.EventBus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.bbj.cva.events.PlaceUnitEvent;
import com.bbj.cva.model.CvaModel;
import com.bbj.cva.screenobjects.projectiles.BolaShot;
import com.bbj.cva.screenobjectsdata.SpawnUnitData;

public class CheerborgFieldSelection extends Selection
{
	@Override
	public void create()
	{
		selectionImage = new Texture(Gdx.files.internal("data/selection.png"));

		selectionRect = new Rectangle();
		selectionRect.width = CvaModel.TILE_WIDTH;
		selectionRect.height = CvaModel.TILE_HEIGHT;
		selectionRect.x = CvaModel.TILE_WIDTH*11;
		selectionRect.y = CvaModel.TILE_HEIGHT*4;
	}
	
	@Override
	public void render(SpriteBatch spriteBatch)
	{
		System.nanoTime();
	
		/**
		 * Detect requested motion.
		 */
	
		if (Gdx.input.isKeyPressed(Input.Keys.DPAD_RIGHT))
		{
			rightWasDownLastFrame = true;
		}
		else if (rightWasDownLastFrame == true)
		{
			selectionRect.x += CvaModel.TILE_WIDTH;
			rightWasDownLastFrame = false;
		}
	
		if (Gdx.input.isKeyPressed(Input.Keys.DPAD_LEFT))
		{
			leftWasDownLastFrame = true;
		}
		else if (leftWasDownLastFrame == true)
		{
			selectionRect.x -= CvaModel.TILE_WIDTH;
			leftWasDownLastFrame = false;
		}
	
		if (Gdx.input.isKeyPressed(Input.Keys.DPAD_UP))
		{
			upWasDownLastFrame = true;
		}
		else if (upWasDownLastFrame == true)
		{
			selectionRect.y += CvaModel.TILE_HEIGHT;
			upWasDownLastFrame = false;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.DPAD_DOWN))
		{
			downWasDownLastFrame = true;
		}
		else if (downWasDownLastFrame == true)
		{
			selectionRect.y -= CvaModel.TILE_HEIGHT;
			downWasDownLastFrame = false;
		}
	
		if (Gdx.input.isKeyPressed(Input.Keys.ENTER))
		{
			enterWasDownLastFrame = true;
		}
		else if (enterWasDownLastFrame == true)
		{
			Pom pom = new Pom(selectionRect.x, selectionRect.y);
			EventBus.publish(new PlaceUnitEvent(pom));

			BolaShot shot = new BolaShot(0f, selectionRect.y + CvaModel.TILE_HEIGHT/2);
			EventBus.publish(new PlaceUnitEvent(shot));

			enterWasDownLastFrame = false;
		}

		spriteBatch.draw(selectionImage, selectionRect.x, selectionRect.y);
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
}
