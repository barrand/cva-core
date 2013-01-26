package com.bbj.cva;

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

public class CheerVArachnids implements ApplicationListener {
	private static final float TILE_WIDTH = 128;
	private static final float TILE_HEIGHT = 135;
	private static final long SELECTION_WAIT = 100000000;
	OrthographicCamera camera;
	SpriteBatch spriteBatch;
	TiledMap tiledMap;
	TileMapRenderer tileMapRenderer;
	Rectangle selectionRect;
	Texture selectionImage;

	private long lastMoveSelectionTime;

	private TiledMapHelper tiledMapHelper;
	private Texture overallTexture;

	private Sprite jumperSprite;
	private boolean jumperFacingRight;

	/**
	 * This is the player character. It will be created as a dynamic object.
	 */
	private Body jumper;

	/**
	 * The screen coordinates of where a drag event began, used when updating
	 * the camera position.
	 */
	private int lastTouchedX;
	private int lastTouchedY;

	/**
	 * The screen's width and height. This may not match that computed by
	 * libgdx's gdx.graphics.getWidth() / getHeight() on devices that make use
	 * of on-screen menu buttons.
	 */
	private int screenWidth;
	private int screenHeight;

	public CheerVArachnids() {
		super();

		// Defer until create() when Gdx is initialized.
		screenWidth = -1;
		screenHeight = -1;
	}

	public CheerVArachnids(int width, int height) {
		super();

		screenWidth = width;
		screenHeight = height;
	}

	@Override
	public void create() {
		Texture.setEnforcePotImages(false);

		/**
		 * If the viewport's size is not yet known, determine it here.
		 */
		if (screenWidth == -1) {
			screenWidth = Gdx.graphics.getWidth();
			screenHeight = Gdx.graphics.getHeight();
		}

		selectionImage = new Texture(Gdx.files.internal("data/selection.png"));

		tiledMapHelper = new TiledMapHelper();

		tiledMapHelper.setPackerDirectory("data/packer");

		tiledMapHelper.loadMap("data/world/level1/level.tmx");

		tiledMapHelper.prepareCamera(tiledMapHelper.getWidth(),
				tiledMapHelper.getHeight());

		TiledMap map = tiledMapHelper.getMap();

		selectionRect = new Rectangle();
		selectionRect.width = TILE_WIDTH;
		selectionRect.height = TILE_HEIGHT;
		selectionRect.x = 0;
		selectionRect.y = 0;

		/**
		 * Load up the overall texture and chop it in to pieces. In this case,
		 * piece.
		 */
		overallTexture = new Texture(Gdx.files.internal("data/sprite.png"));
		overallTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		jumperSprite = new Sprite(overallTexture, 0, 0, 21, 37);

		spriteBatch = new SpriteBatch(); // #12
	}

	@Override
	public void dispose() {
		spriteBatch.dispose();
	}

	@Override
	public void render() {
		long now = System.nanoTime();

		/**
		 * Detect requested motion.
		 */

		if (now - lastMoveSelectionTime > SELECTION_WAIT) {
			if (Gdx.input.isKeyPressed(Input.Keys.DPAD_RIGHT)) {
				selectionRect.x += TILE_WIDTH;
			} else if (Gdx.input.isKeyPressed(Input.Keys.DPAD_LEFT)) {
				selectionRect.x -= TILE_WIDTH;
			}
			if (Gdx.input.isKeyPressed(Input.Keys.DPAD_UP)) {
				selectionRect.y += TILE_HEIGHT;
			} else if (Gdx.input.isKeyPressed(Input.Keys.DPAD_DOWN)) {
				selectionRect.y -= TILE_HEIGHT;
			}
			lastMoveSelectionTime = now;
		}

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

		// jumperSprite.setPosition(
		// PIXELS_PER_METER * jumper.getPosition().x
		// - jumperSprite.getWidth() / 2,
		// PIXELS_PER_METER * jumper.getPosition().y
		// - jumperSprite.getHeight() / 2);
		// jumperSprite.draw(spriteBatch);
		spriteBatch.draw(selectionImage, selectionRect.x, selectionRect.y);

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
}