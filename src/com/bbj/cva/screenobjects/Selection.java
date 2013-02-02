package com.bbj.cva.screenobjects;

import org.bushe.swing.event.EventBus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.bbj.cva.events.PlaceUnitEvent;
import com.bbj.cva.model.CvaModel;

public class Selection implements ScreenObjectBase
{
	protected Rectangle selectionRect;
	protected Texture selectionImage;
	protected Texture selectedImage;
	
	@Override
	public void create()
	{
		selectionImage = new Texture(Gdx.files.internal("data/selection.png"));

		// TODO Auto-generated method stub
		selectionRect = new Rectangle();
		selectionRect.width = CvaModel.TILE_WIDTH;
		selectionRect.height = CvaModel.TILE_HEIGHT;
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
	public void setX(int x)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void setY(int y)
	{
		// TODO Auto-generated method stub

	}
}