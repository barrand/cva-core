package com.bbj.cva.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.bbj.cva.model.CvaModel;

public class TextureUtils {

	public static void loadTextures() {
		TextureAtlas pomAtlas = new TextureAtlas(
				Gdx.files.internal("data/spriteSheets/pomAll.txt"));
		CvaModel.pomWalk = createAnimation(40,
				"render-grunt-walk/CB-grunt-walking", pomAtlas, 0.03f);
		CvaModel.pomWalk.setPlayMode(Animation.LOOP);
		CvaModel.pomDie = createAnimation(38,
				"render-grunt-die/CB-grunt-death", pomAtlas, 0.03f);
		CvaModel.pomDie.setPlayMode(Animation.NORMAL);
		AnimationInfo ai = createAnimationWithSpecialFrame(44,
				"render-grunt-attack/CB-grunt-attack", pomAtlas, 0.04f, 28);
		CvaModel.pomAttack = ai.animation;
		CvaModel.pomAttackingFrame = ai.specialFrame;
		CvaModel.pomAttack.setPlayMode(Animation.LOOP);

		TextureAtlas spiderAtlas = new TextureAtlas(
				Gdx.files.internal("data/spriteSheets/spiderAll.txt"));
		ai = createAnimationWithSpecialFrame(3, "spiderShoot",
				spiderAtlas, 1f, 2);
		CvaModel.spiderShoot = ai.animation;
		CvaModel.spiderShootingFrame = ai.specialFrame;
		CvaModel.spiderShoot.setPlayMode(Animation.LOOP);
		CvaModel.spiderDie = createAnimation(3, "spiderDie", spiderAtlas, 1f);
		CvaModel.spiderDie.setPlayMode(Animation.NORMAL);

		CvaModel.bolaShot = new Texture(
				Gdx.files.internal("data/spriteSheets/webshot.png"));
		CvaModel.pomSelect = new Texture(
				Gdx.files.internal("data/spriteSheets/pomSelect.png"));
		CvaModel.spiderSelect = new Texture(
				Gdx.files.internal("data/spriteSheets/spiderSelect.png"));
		CvaModel.blue = new Texture(
				Gdx.files.internal("data/spriteSheets/blue.png"));
	}

	private static Animation createAnimation(int numberOfFrames,
			String fileNamePrefix, TextureAtlas textureAtlas, float animSpeed) {
		AtlasRegion[] walkAtlases = new AtlasRegion[numberOfFrames];
		for (int ct = 1; ct < numberOfFrames + 1; ct++) {
			String name = fileNamePrefix + String.format("%04d", ct);
			Gdx.app.log("cva", "name " + name);
			walkAtlases[ct - 1] = textureAtlas.findRegion(name);
		}
		return new Animation(animSpeed, walkAtlases);
	}

	private static AnimationInfo createAnimationWithSpecialFrame(int numberOfFrames,
			String fileNamePrefix, TextureAtlas textureAtlas, float animSpeed, int specialFrameNum) {
		AnimationInfo toReturn = new AnimationInfo();
		AtlasRegion[] walkAtlases = new AtlasRegion[numberOfFrames];
		for (int ct = 1; ct < numberOfFrames + 1; ct++) {
			String name = fileNamePrefix + String.format("%04d", ct);
			Gdx.app.log("cva", "shooter name " + name);
			AtlasRegion ar = textureAtlas.findRegion(name);
			walkAtlases[ct - 1] = ar;
			if (ct == specialFrameNum) {
				toReturn.specialFrame = ar;
			}
		}
		toReturn.animation = new Animation(animSpeed, walkAtlases);
		return toReturn;
	}
}

class AnimationInfo{
	Animation animation;
	AtlasRegion specialFrame;
}
