package com.bbj.cva;

import java.util.ArrayList;

import org.bushe.swing.event.EventBus;
import org.bushe.swing.event.EventSubscriber;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.tiled.TileMapRenderer;
import com.badlogic.gdx.graphics.g2d.tiled.TiledMap;
import com.bbj.cva.events.PlaceUnitEvent;
import com.bbj.cva.model.CvaModel;
import com.bbj.cva.screenobjects.CheerborgFieldSelection;
import com.bbj.cva.screenobjects.CheerborgUnitSelection;
import com.bbj.cva.screenobjects.Pom;
import com.bbj.cva.screenobjects.IScreenObject;
import com.bbj.cva.screenobjects.Selection;
import com.bbj.cva.screenobjects.SpiderFieldSelection;
import com.bbj.cva.screenobjects.SpiderUnit;
import com.bbj.cva.screenobjects.SpiderUnitSelection;
import com.bbj.cva.screenobjectsdata.SpawnUnitData;

public class CheerVArachnids implements ApplicationListener,
		EventSubscriber<PlaceUnitEvent>
{
	OrthographicCamera camera;
	SpriteBatch spriteBatch;
	TiledMap tiledMap;
	TileMapRenderer tileMapRenderer;
	Selection selection;
	
	//this holds all the objects that are going to be on screen
	//when we render the whole world, we'll call render on each
	//of these objects so the logic will be in the classes
	private ArrayList<IScreenObject> screenObjects;
	
	private ArrayList<SpawnUnitData> createObjectsQueue;

	private TiledMapHelper tiledMapHelper;

	public CheerVArachnids()
	{
		super();

		// Defer until create() when Gdx is initialized.
		CvaModel.screenWidth = -1;
		CvaModel.screenHeight = -1;

		EventBus.subscribe(PlaceUnitEvent.class, this);
	}

	public CheerVArachnids(int width, int height)
	{
		super();

		CvaModel.screenWidth = width;
		CvaModel.screenHeight = height;

		EventBus.subscribe(PlaceUnitEvent.class, this);
	}

	@Override
	public void create()
	{

		Texture.setEnforcePotImages(false);

		/**
		 * If the viewport's size is not yet known, determine it here.
		 */
		if (CvaModel.screenWidth == -1)
		{
			CvaModel.screenWidth = Gdx.graphics.getWidth();
			CvaModel.screenHeight = Gdx.graphics.getHeight();
		}

		tiledMapHelper = new TiledMapHelper();

		tiledMapHelper.setPackerDirectory("data/packer");

		tiledMapHelper.loadMap("data/world/level1/level.tmx");

		tiledMapHelper.prepareCamera(tiledMapHelper.getWidth(),
				tiledMapHelper.getHeight());

		TiledMap map = tiledMapHelper.getMap();

		createObjectsQueue = new ArrayList<SpawnUnitData>();
		// add all the objects that are going to be on screen here
		screenObjects = new ArrayList<IScreenObject>();
		screenObjects.add(new CheerborgUnitSelection());
		screenObjects.add(new SpiderUnitSelection());
		screenObjects.add(new CheerborgFieldSelection());
		screenObjects.add(new SpiderFieldSelection());

		for (IScreenObject o : screenObjects)
		{
			o.create();
		}

		spriteBatch = new SpriteBatch(); // #12
	}

	@Override
	public void dispose()
	{
		spriteBatch.dispose();
	}

	@Override
	public void render()
	{
		for (SpawnUnitData o : createObjectsQueue)
		{
			o.screenObject.create();
			screenObjects.add(o.screenObject);
		}
		createObjectsQueue.clear();

		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		/**
		 * A nice(?), blue backdrop.
		 */
		Gdx.gl.glClearColor(0, 0.5f, 0.9f, 0);

		tiledMapHelper.getCamera().update();
		tiledMapHelper.render();

		/**
		 * Prepare the SpriteBatch for drawing.
		 */
		spriteBatch.setProjectionMatrix(tiledMapHelper.getCamera().combined);
		spriteBatch.begin();

		for (IScreenObject o : screenObjects)
		{
			o.render(spriteBatch);
		}

		/**
		 * "Flush" the sprites to screen.
		 */
		spriteBatch.end();

	}

	@Override
	public void resize(int width, int height)
	{
	}

	@Override
	public void pause()
	{
	}

	@Override
	public void resume()
	{
	}

	@Override
	public void onEvent(PlaceUnitEvent event)
	{
		// todo look at the selected class type at the top of the UI
		Pom screenObject = new Pom();
		screenObject.setX((int) event.x);
		screenObject.setY((int) event.y);
		SpawnUnitData spd = new SpawnUnitData(screenObject, event.x, event.y);
		createObjectsQueue.add(spd);
	}
}