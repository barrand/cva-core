package com.bbj.cva.screenobjects.interfaces;

import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

public interface IAnimatedShooter {
	String getShootingRegionName();
	int getShootingNumFrames();
	AtlasRegion getShootingFrame();
}
