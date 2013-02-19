package com.bbj.cva.model;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.bbj.cva.screenobjects.IHitAreaObject;
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

	public static Texture pomWalk;
	public static Texture pomDie;
	public static Texture spider;
	public static Texture bolaShot;
	public static Texture pomSelect;
	public static Texture spiderSelect;
	public static Texture blue;

	public static Color defaultColor;// the normal color of the sprite batch. we
										// keep track so we can set things back
										// to normal after tinting something

	public static Bus eventBus;
	public static boolean DEBUG = true;

	public static enum Unit {
		POM, POM_DIE, SPIDER, BOLA
	}
}
