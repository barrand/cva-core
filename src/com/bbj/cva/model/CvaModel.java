package com.bbj.cva.model;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.bbj.cva.screenobjects.ScreenObject;

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

	public static ArrayList<ScreenObject> thingsCheerborgsInteractWith = new ArrayList<ScreenObject>();

	public static Texture pomWalk;
	public static Texture spider;
	public static Texture bolaShot;

}
