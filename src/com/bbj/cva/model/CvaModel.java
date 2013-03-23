package com.bbj.cva.model;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.bbj.cva.screenobjects.interfaces.IHitAreaObject;
import com.squareup.otto.Bus;

public class CvaModel {
	/**
	 * The screen's width and height. This may not match that computed by
	 * libgdx's gdx.graphics.getWidth() / getHeight() on devices that make use
	 * of on-screen menu buttons.
	 */
	public static int screenWidth;
	public static int screenHeight;

	public static final float TILE_WIDTH = 128;
	public static final float TILE_HEIGHT = 135;

	public static ArrayList<IHitAreaObject> thingsCheerborgsInteractWith = new ArrayList<IHitAreaObject>();

	public static Animation pomWalk;
	public static Animation pomDie;
	public static Animation pomAttack;
	public static AtlasRegion pomAttackingFrame;
	public static Animation spiderShoot;
	public static AtlasRegion spiderShootingFrame;
	public static Animation spiderDie;
	public static Texture bolaShot;
	public static Texture pomSelect;
	public static Texture spiderSelect;
	public static Texture blue;

	public static Color defaultColor;// the normal color of the sprite batch. we
										// keep track so we can set things back
										// to normal after tinting something

	public static Bus eventBus;
	public static boolean DEBUG = false;

	public static enum Unit {
		POM, SPIDER, BOLA_SHOT
	}

	public static enum ActionState {
		WALKING, ATTACKING, DYING, SHOOTING, NOTHING
	}
}
