package com.bbj.cva.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.bbj.cva.model.CvaModel;

public class TextureUtils {

	public static void loadTextures(){
		CvaModel.bolaShot = new Texture(Gdx.files.internal("data/spriteSheets/webshot.png"));
		CvaModel.pomWalk = new Texture(Gdx.files.internal("data/spriteSheets/pomWalk.png"));
		CvaModel.pomDie = new Texture(Gdx.files.internal("data/spriteSheets/pomDie.png"));
		CvaModel.pomSelect = new Texture(Gdx.files.internal("data/spriteSheets/pomSelect.png"));
		CvaModel.spiderSelect = new Texture(Gdx.files.internal("data/spriteSheets/spiderSelect.png"));
		CvaModel.spider = new Texture(Gdx.files.internal("data/spriteSheets/spiderShot.png"));
		CvaModel.blue = new Texture(Gdx.files.internal("data/spriteSheets/blue.png"));
	}
}
