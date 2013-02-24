package com.bbj.cva.screenobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.bbj.cva.events.PlaceUnitEvent;
import com.bbj.cva.events.RemoveScreenObjectEvent;
import com.bbj.cva.model.CvaModel;

public class Pom extends AttackingHitableAnimated {

	Animation walkAnim;
	Animation dieAnim;
	protected Animation currentAnim;
	private boolean checkForInteractions = true;
	private float speedX = -1.2f;
	
	public Pom(float x, float y) {
		super(x, y);
		type = CvaModel.Unit.POM;
		texture = CvaModel.pomWalk;
		
		TextureAtlas ta = new TextureAtlas(Gdx.files.internal("data/spriteSheets/pomAll.txt"));
//		ta.findRegion(spriteName);
		
		int numberOfFrames = 40;
		AtlasRegion[] walkAtlases = new AtlasRegion[numberOfFrames];
		for(int ct = 1; ct < numberOfFrames; ct++)
		{
			String name = "render-grunt-walk/CB-grunt-walking"+String.format("%04d", ct);
			Gdx.app.log("cva", "name " + name);
			walkAtlases[ct] =ta.findRegion(name);
		}
		walkAnim = new Animation(getAnimationSpeed(), walkAtlases);
		walkAnim.setPlayMode(Animation.LOOP);
		
		numberOfFrames = 38;
		AtlasRegion[] dieAtlases = new AtlasRegion[numberOfFrames];
		for(int ct = 1; ct < numberOfFrames; ct++)
		{
			String name = "render-grunt-die/CB-grunt-death"+String.format("%04d", ct);
			Gdx.app.log("cva", "name " + name);
			dieAtlases[ct] =ta.findRegion(name);
		}
		dieAnim = new Animation(getAnimationSpeed(), dieAtlases);
		dieAnim.setPlayMode(Animation.NORMAL);
		
		currentAnim = walkAnim;
		
	}

	@Override
	public void render(SpriteBatch spriteBatch) {
		
		if(checkForInteractions){
			
			for (IHitAreaObject o : CvaModel.thingsCheerborgsInteractWith) {
				
				// if she got hit, then kill her and put in the die animation
				if (o.getHitArea().overlaps(hitArea)) {
					Gdx.app.log("cva", this.toString());
					stateTime = 0f;
					currentAnim = dieAnim;
					speedX = 0f;
					checkForInteractions = false;
					loop = false;
					CvaModel.eventBus.post(new RemoveScreenObjectEvent((ScreenObject)o));
				}
			}
		}
		
		
		x += getSpeedX() + speedXModifier;
		y += getSpeedY() + speedYModifier;
		hitArea.x = x;
		hitArea.y = y;
		stateTime += Gdx.graphics.getDeltaTime();
        AtlasRegion currentFrame = (AtlasRegion) currentAnim.getKeyFrame(stateTime, loop);
        if(CvaModel.DEBUG){
        	spriteBatch.setColor(255, 0, 0, 240);
        	attackArea.x = x - attackArea.width;
        	spriteBatch.draw(CvaModel.blue, attackArea.x, attackArea.y, attackArea.width, attackArea.height, 0, 0, 0, 0, false, false);
        	spriteBatch.setColor(CvaModel.defaultColor);
        }
        if(!loop && currentAnim.isAnimationFinished(stateTime)){
        	onAnimationEnd();
        }
        if(currentFrame != null){
//        	Gdx.app.log("cva", Float.toString(stateTime));
        	spriteBatch.draw(currentFrame, x + currentFrame.offsetX, y + currentFrame.offsetY);
        }
		
//		super.render(spriteBatch);
	}

	@Override
	public void onAnimationEnd(){
		CvaModel.eventBus.post(new RemoveScreenObjectEvent(this));
	}
	
	@Override
	public int getFrameCols() {
		return 10;
	}

	@Override
	public int getFrameRows() {
		return 8;
	}

	// smaller is faster
	@Override
	public float getAnimationSpeed() {
		return 0.03f;
	}

	// larger is faster
	@Override
	public float getSpeedX() {
		return speedX ;
	}

	@Override
	public float getSpeedY() {
		return 0f;
	}

	@Override
	public float getSpriteWidth() {
		return 300;
	}

	@Override
	public float getSpriteHeight() {
		return 300;
	}

	@Override
	public float getHitAreaWidth() {
		return 80;
	}

	@Override
	public float getHitAreaHeight() {
		return 130;
	}

	@Override
	protected float getAttackAreaWidth() {
		return 100;
	}

	@Override
	protected float getAttackAreaHeight() {
		// TODO Auto-generated method stub
		return 130;
	}

	@Override
	protected boolean attacksToTheLeft() {
		return true;
	}
}