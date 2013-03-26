package com.bbj.cva.screens;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.tiled.TileMapRenderer;
import com.badlogic.gdx.graphics.g2d.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.bbj.cva.CheerVArachnids;
import com.bbj.cva.TiledMapHelper;
import com.bbj.cva.events.PlaceUnitEvent;
import com.bbj.cva.events.RemoveScreenObjectEvent;
import com.bbj.cva.model.CvaModel;
import com.bbj.cva.screenobjects.PomSelect;
import com.bbj.cva.screenobjects.ScreenObject;
import com.bbj.cva.screenobjects.SpiderSelect;
import com.bbj.cva.screenobjects.interfaces.IScreenObject;
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

public class GameScreen extends AbstractScreen
{
	OrthographicCamera camera;
	TiledMap tiledMap;
	TileMapRenderer tileMapRenderer;
	Selection selection;
	Vector2 v;

	// this holds all the objects that are going to be on screen
	// when we render the whole world, we'll call render on each
	// of these objects so the logic will be in the classes
	private ArrayList<IScreenObject> screenObjects;

	// I keep track of these in an array, so that we don't get concurrent
	// problems coming from the event
	private ArrayList<IScreenObject> objectsToDelete = new ArrayList<IScreenObject>();

	private ArrayList<ScreenObject> createObjectsQueue;

	private TiledMapHelper tiledMapHelper;
	public GameScreen(CheerVArachnids game)
	{
		super(game);
		
		CvaModel.screenWidth = -1;
		CvaModel.screenHeight = -1;
		CvaModel.eventBus = new Bus(ThreadEnforcer.ANY);
		CvaModel.eventBus.register(this);
	}

	
	@Override
	public void show() {
		v = new Vector2(0,0);
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

//		TiledMap map = tiledMapHelper.getMap();

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
		
		CvaModel.defaultColor = batch.getColor();
		
		Gdx.app.debug("cva","Controllers: " + Controllers.getControllers().size);
		int i = 0;
		for(Controller controller: Controllers.getControllers()) {
			Gdx.app.debug("cva","#" + i++ + ": " + controller.getName());
		}
		if(Controllers.getControllers().size == 0) Gdx.app.debug("cva","No controllers attached");
	}
	
	@Override
    public void dispose()
    {
        super.dispose();
    }
	
	@Override
	public void render(float delta) {
		super.render(delta);
		
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

		batch.setProjectionMatrix(tiledMapHelper.getCamera().combined);
		batch.begin();

		for (IScreenObject o : screenObjects) {
			o.render(batch);
		}

		/**
		 * "Flush" the sprites to screen.
		 */
		batch.end();
	}

	@Subscribe public void placeUnitListener (PlaceUnitEvent event) {
		createObjectsQueue.add(event.screenObject);
	}

	@Subscribe
	public void onRemoveScreenObject(RemoveScreenObjectEvent event) {
		objectsToDelete.add(event.screenObject);
		v.x = event.screenObject.x;
		v.y = event.screenObject.y;
		if (CvaModel.occupancyList.contains(v))
		{
			CvaModel.occupancyList.remove(v);
		}
	}

	@Override
	public void resize(int width, int height)
	{
		super.resize(width, height);
	}

	@Override
	public void pause()
	{
		super.pause();
	}

	@Override
	public void resume()
	{
		super.resume();
	}
}
