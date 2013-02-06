package com.bbj.cva.screenobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.bbj.cva.model.CvaModel;

public abstract class AnimatedScreenObject extends ScreenObject implements IAnimatedObject {
	Animation animation; // #3
	TextureRegion[] textureFrames; // #5
	SpriteBatch spriteBatch; // #6
	TextureRegion currentFrame; // #7
	public boolean loop = true;
	

	float stateTime; // #8

	public AnimatedScreenObject(float x, float y) {
		super(x,y);
		unitRect = new Rectangle();
		unitRect.width = CvaModel.TILE_WIDTH;
		unitRect.height = CvaModel.TILE_HEIGHT;
		unitRect.x = x;
		unitRect.y = y;
	}

	@Override
	public void create() {
		super.create();
		TextureRegion[][] tmp = TextureRegion.split(texture,
				texture.getWidth() / getFrameCols(), texture.getHeight()
						/ getFrameRows()); // #10
		textureFrames = new TextureRegion[getFrameCols() * getFrameRows()];
		int index = 0;
		for (int i = 0; i < getFrameRows(); i++) {
			for (int j = 0; j < getFrameCols(); j++) {
				textureFrames[index++] = tmp[i][j];
			}
		}
		animation = new Animation(getAnimationSpeed(), textureFrames); // #11
		stateTime = 0f; // #13
	}

	@Override
	public void render(SpriteBatch spriteBatch) {
		unitRect.x += getSpeedX() + speedXModifier;
		unitRect.y += getSpeedY() + speedYModifier;
		stateTime += Gdx.graphics.getDeltaTime();
        currentFrame = animation.getKeyFrame(stateTime, true);
        if(loop == false && currentFrame == textureFrames[textureFrames.length-1]){
        	onAnimationEnd();
        }
        spriteBatch.draw(currentFrame, unitRect.x - unitRect.getWidth()/2, unitRect.y);
	}

	protected void onAnimationEnd() {
		//override me if needed
	}
}
