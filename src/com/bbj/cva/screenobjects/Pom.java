package com.bbj.cva.screenobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.bbj.cva.model.CvaModel;

public class Pom implements ScreenObjectBase
{

	private static final int FRAME_COLS = 5; // #1
	private static final int FRAME_ROWS = 4; // #2

	Animation walkAnimation; // #3
	TextureRegion[] walkFrames; // #5
	SpriteBatch spriteBatch; // #6
	TextureRegion currentFrame; // #7

	int speed = 3;

	float stateTime; // #8

	private int x;
	private int y;

	@Override
	public void create()
	{
		if (CvaModel.pomWalk == null)
		{
			CvaModel.pomWalk = new Texture(
					Gdx.files.internal("data/spriteSheets/pomWalk.png")); // #9
		}
		TextureRegion[][] tmp = TextureRegion.split(CvaModel.pomWalk,
				CvaModel.pomWalk.getWidth() / FRAME_COLS,
				CvaModel.pomWalk.getHeight() / FRAME_ROWS); // #10
		walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
		int index = 0;
		for (int i = 0; i < FRAME_ROWS; i++)
		{
			for (int j = 0; j < FRAME_COLS; j++)
			{
				walkFrames[index++] = tmp[i][j];
			}
		}
		walkAnimation = new Animation(0.05f, walkFrames); // #11
		stateTime = 0f; // #13

	}

	@Override
	public void render(SpriteBatch spriteBatch)
	{
		x -= speed;
		stateTime += Gdx.graphics.getDeltaTime();
		currentFrame = walkAnimation.getKeyFrame(stateTime, true);
		spriteBatch.draw(currentFrame, x, y);
	}

	@Override
	public void setX(int x)
	{
		this.x = x;
	}

	@Override
	public void setY(int y)
	{
		this.y = y;
	}

}