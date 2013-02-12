package com.bbj.cva.screenobjects.selection;

import java.awt.Point;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.bbj.cva.model.CvaModel;
import com.bbj.cva.screenobjects.IScreenObject;

public abstract class Selection implements IScreenObject
{
	protected Rectangle selectionRect;
	protected Texture selectionImage;
	//protected Texture selectedImage;
	int startSpaceX = 11;
	int startSpaceY = 11;
	
	@Override
	public void create()
	{
		selectionImage = new Texture(Gdx.files.internal("data/selection.png"));

		selectionRect = new Rectangle();
		selectionRect.width = CvaModel.TILE_WIDTH;
		selectionRect.height = CvaModel.TILE_HEIGHT;
		selectionRect.x = startSpaceX * CvaModel.TILE_WIDTH;
		selectionRect.y = startSpaceY * CvaModel.TILE_HEIGHT;
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
	
	@Override
	public void destroy() {
		//
	}
}