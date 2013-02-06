package com.bbj.cva.screenobjects;

import java.awt.Point;

import org.bushe.swing.event.EventBus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.bbj.cva.events.PlaceUnitEvent;
import com.bbj.cva.model.CvaModel;

public class Selection implements IScreenObject
{
	protected Rectangle selectionRect;
	protected Texture selectionImage;
	protected Texture selectedImage;
	private long lastMoveSelectionTime;
	Point startSpace = new Point(11, 5);
	private static final long SELECTION_WAIT = 100000000;
	
	@Override
	public void create()
	{
		selectionImage = new Texture(Gdx.files.internal("data/selection.png"));

		// TODO Auto-generated method stub
		selectionRect = new Rectangle();
		selectionRect.width = CvaModel.TILE_WIDTH;
		selectionRect.height = CvaModel.TILE_HEIGHT;
		selectionRect.x = startSpace.x * CvaModel.TILE_WIDTH;
		selectionRect.y = startSpace.y * CvaModel.TILE_HEIGHT;
	}

	protected boolean rightWasDownLastFrame = false;
	protected boolean leftWasDownLastFrame = false;
	protected boolean upWasDownLastFrame = false;
	protected boolean downWasDownLastFrame = false;
	protected boolean enterWasDownLastFrame = false;

	@Override
	public void render(SpriteBatch spriteBatch)
	{
	}

	@Override
	public void setX(int x) {
	}

	@Override
	public void setY(int y) {
	}
	
	@Override
	public void loadTextureIfNeeded() {
		
	}
	@Override
	public float getSpeedX() {
		return 0;
	}
	@Override
	public float getSpeedY() {
		return 0;
	}
}