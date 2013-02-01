package com.bbj.cva;

import java.util.ArrayList;

import org.bushe.swing.event.EventBus;
import org.bushe.swing.event.EventServiceExistsException;
import org.bushe.swing.event.EventServiceLocator;
import org.bushe.swing.event.EventSubscriber;
import org.bushe.swing.event.ThreadSafeEventService;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.tiled.TileAtlas;
import com.badlogic.gdx.graphics.g2d.tiled.TileMapRenderer;
import com.badlogic.gdx.graphics.g2d.tiled.TiledLoader;
import com.badlogic.gdx.graphics.g2d.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.bbj.cva.events.PlaceUnitEvent;
import com.bbj.cva.model.CvaModel;
import com.bbj.cva.screenobjects.ScreenObjectBase;
import com.bbj.cva.screenobjects.Selection;
import com.bbj.cva.screenobjects.SpiderUnit;
import com.bbj.cva.screenobjectsdata.SpiderUnitData;

public class CheerVArachnids implements ApplicationListener, EventSubscriber<PlaceUnitEvent>{
	OrthographicCamera camera;
	SpriteBatch spriteBatch;
	TiledMap tiledMap;
	TileMapRenderer tileMapRenderer;
	Selection selection;
	
	//this holds all the objects that are going to be on screen
	//when we render the whole world, we'll call render on each
	//of these objects so the logic will be in the classes
	private ArrayList<ScreenObjectBase> screenObjects;
	
	private ArrayList<SpiderUnitData> createObjectsQueue;

	private TiledMapHelper tiledMapHelper;
	private Texture overallTexture;

	public CheerVArachnids() {
		super();

		// Defer until create() when Gdx is initialized.
		CvaModel.screenWidth = -1;
		CvaModel.screenHeight = -1;

		EventBus.subscribe(PlaceUnitEvent.class, this);
	}

	public CheerVArachnids(int width, int height) {
		super();

		CvaModel.screenWidth = width;
		CvaModel.screenHeight = height;

		EventBus.subscribe(PlaceUnitEvent.class, this);
	}

	@Override
	public void create() {
		
		Texture.setEnforcePotImages(false);

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

		createObjectsQueue = new ArrayList<SpiderUnitData>();
		//add all the objects that are going to be on screen here
		screenObjects = new ArrayList<ScreenObjectBase>();
		selection = new Selection();
		screenObjects.add(selection);


		for (ScreenObjectBase o : screenObjects) {
			o.create();
		}

		/**
		 * Load up the overall texture and chop it in to pieces. In this case,
		 * piece.
		 */
		overallTexture = new Texture(Gdx.files.internal("data/sprite.png"));
		overallTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		spriteBatch = new SpriteBatch(); // #12
	}

	@Override
	public void dispose() {
		spriteBatch.dispose();
	}

	@Override
	public void render() 
	{
		for (SpiderUnitData o : createObjectsQueue)
		{
			SpiderUnit su = new SpiderUnit(o);
			su.create();
			screenObjects.add(su);
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

		for (ScreenObjectBase o : screenObjects) {
			o.render(spriteBatch);
		}

		/**
		 * "Flush" the sprites to screen.
		 */
		spriteBatch.end();

	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void onEvent(PlaceUnitEvent event) 
	{
		SpiderUnitData spd = new SpiderUnitData(event.x, event.y);
		createObjectsQueue.add(spd);
	}
}