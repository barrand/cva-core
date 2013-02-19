package com.bbj.cva;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.tiled.TileMapRenderer;
import com.badlogic.gdx.graphics.g2d.tiled.TiledMap;
import com.bbj.cva.events.PlaceUnitEvent;
import com.bbj.cva.events.RemoveScreenObjectEvent;
import com.bbj.cva.model.CvaModel;
import com.bbj.cva.screenobjects.IHitAreaObject;
import com.bbj.cva.screenobjects.IScreenObject;
import com.bbj.cva.screenobjects.Pom;
import com.bbj.cva.screenobjects.PomSelect;
import com.bbj.cva.screenobjects.ScreenObject;
import com.bbj.cva.screenobjects.SpiderSelect;
import com.bbj.cva.screenobjects.SpiderUnit;
import com.bbj.cva.screenobjects.projectiles.IProjectile;
import com.bbj.cva.screenobjects.selection.CheerborgFieldSelection;
import com.bbj.cva.screenobjects.selection.CheerborgUnitBar;
import com.bbj.cva.screenobjects.selection.CheerborgUnitSelection;
import com.bbj.cva.screenobjects.selection.Selection;
import com.bbj.cva.screenobjects.selection.SpiderFieldSelection;
import com.bbj.cva.screenobjects.selection.SpiderUnitBar;
import com.bbj.cva.screenobjects.selection.SpiderUnitSelection;
import com.bbj.cva.util.TextureUtils;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import com.squareup.otto.ThreadEnforcer;

public class CheerVArachnids implements ApplicationListener {
	OrthographicCamera camera;
	SpriteBatch spriteBatch;
	TiledMap tiledMap;
	TileMapRenderer tileMapRenderer;
	Selection selection;

	// this holds all the objects that are going to be on screen
	// when we render the whole world, we'll call render on each
	// of these objects so the logic will be in the classes
	private ArrayList<IScreenObject> screenObjects;

	// I keep track of these in an array, so that we don't get concurrent
	// problems coming from the event
	private ArrayList<IScreenObject> objectsToDelete = new ArrayList<IScreenObject>();

	private ArrayList<ScreenObject> createObjectsQueue;

	private TiledMapHelper tiledMapHelper;
	public CheerVArachnids() {
		super();

		// Defer until create() when Gdx is initialized.
		CvaModel.screenWidth = -1;
		CvaModel.screenHeight = -1;

	}

	public CheerVArachnids(int width, int height) {
		super();

		CvaModel.screenWidth = width;
		CvaModel.screenHeight = height;
	}

	@Override
	public void create() {
		CvaModel.eventBus = new Bus(ThreadEnforcer.ANY);
		CvaModel.eventBus.register(this);

		Texture.setEnforcePotImages(false);

		TextureUtils.loadTextures();
		
		/**
		 * If the viewport's size is not yet known, determine it here.
		 */
		if (CvaModel.screenWidth == -1) {
			CvaModel.screenWidth = Gdx.graphics.getWidth();
			CvaModel.screenHeight = Gdx.graphics.getHeight();
		}

		tiledMapHelper = new TiledMapHelper();

		tiledMapHelper.setPackerDirectory("data/packer");

		tiledMapHelper.loadMap("data/world/level1/level.tmx");

		tiledMapHelper.prepareCamera(tiledMapHelper.getWidth(),
				tiledMapHelper.getHeight());

		TiledMap map = tiledMapHelper.getMap();

		createObjectsQueue = new ArrayList<ScreenObject>();
		// add all the objects that are going to be on screen here
		screenObjects = new ArrayList<IScreenObject>();
		screenObjects.add(new CheerborgUnitSelection());
		screenObjects.add(new SpiderUnitSelection());
		screenObjects.add(new CheerborgFieldSelection());
		screenObjects.add(new SpiderFieldSelection());
		
		
		// FIXME: Eventually we need another screen/mechanism to choose units for the unitbar
		CheerborgUnitBar cbunitbar = new CheerborgUnitBar();
		cbunitbar.create();
		PomSelect so = new PomSelect(0,0);
		cbunitbar.addUnitToBar(so);

		SpiderUnitBar spunitbar = new SpiderUnitBar();
		spunitbar.create();
		SpiderSelect spo = new SpiderSelect(0,0);
		spunitbar.addUnitToBar(spo);
		
		for (IScreenObject o : screenObjects) {
			o.create();
		}
		screenObjects.add(0, cbunitbar);
		screenObjects.add(0, spunitbar);
		
		spriteBatch = new SpriteBatch();
		CvaModel.defaultColor = spriteBatch.getColor();
	}

	@Override
	public void dispose() {
		spriteBatch.dispose();
	}

	@Override
	public void render() {
		for (ScreenObject o : createObjectsQueue) {
			o.create();
			screenObjects.add(o);
		}
		
		for(IScreenObject o : objectsToDelete){
			o.destroy();
			screenObjects.remove(o);
			if(CvaModel.thingsCheerborgsInteractWith.contains(o)){
				CvaModel.thingsCheerborgsInteractWith.remove(o);
			}
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

		for (IScreenObject o : screenObjects) {
			o.render(spriteBatch);
		}

		/**
		 * "Flush" the sprites to screen.
		 */
		spriteBatch.end();

	}

	@Subscribe public void placeUnitListener (PlaceUnitEvent event) {

		switch (event.screenObject.type) {
		case POM:
			Pom screenObject = new Pom(event.x, event.y);
			createObjectsQueue.add(screenObject);
			break;
		case POM_DIE:
			createObjectsQueue.add(event.screenObject);
			break;
		case SPIDER:
			SpiderUnit spi = new SpiderUnit(event.x, event.y);
			createObjectsQueue.add(spi);
			break;
		case BOLA:
			createObjectsQueue.add(event.screenObject);
			break;
		}
		if (event.screenObject instanceof IProjectile) {
			CvaModel.thingsCheerborgsInteractWith.add((IHitAreaObject) event.screenObject);
		}
	}

	@Subscribe
	public void onRemoveScreenObject(RemoveScreenObjectEvent event) {
		objectsToDelete.add(event.screenObject);

	}

	@Override
	public void resize(int width, int height)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume()
	{
		// TODO Auto-generated method stub
		
	}
}