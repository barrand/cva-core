package com.bbj.cva.screenobjects;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.bbj.cva.events.RemoveScreenObjectEvent;
import com.bbj.cva.model.CvaModel;
import com.bbj.cva.screenobjects.interfaces.IHitAreaObject;

public abstract class HitableAnimatedScreenObject extends AnimatedScreenObject
		implements IHitAreaObject {

	protected Rectangle hitArea;
	protected boolean checkForInteractions = true;

	public HitableAnimatedScreenObject(float x, float y) {
		super(x, y);
		// TODO Auto-generated constructor stub
		hitArea = new Rectangle();
		hitArea.width = getHitAreaWidth();
		hitArea.height = getHitAreaHeight();
		hitArea.x = x;
		hitArea.y = y;
	}

	@Override
	public void render(SpriteBatch spriteBatch) {
		super.render(spriteBatch);
		hitArea.x = x - hitArea.width / 2;
		hitArea.y = y + hitArea.height / 2;
		if (CvaModel.DEBUG) {
			spriteBatch.setColor(200, 200, 200, 240);
			spriteBatch.draw(CvaModel.blue, hitArea.x, hitArea.y,
					hitArea.width, hitArea.height, 0, 0, 0, 0, false, false);
			spriteBatch.setColor(CvaModel.defaultColor);
		}

		if (checkForInteractions) {

			for (IHitAreaObject o : getInteractables()) {
				if (o.getHitArea().overlaps(hitArea)) {
					handleCollision(o);
				}
			}
		}
	}
	
	abstract protected ArrayList<IHitAreaObject> getInteractables();
	abstract protected void handleCollision(IHitAreaObject o);

	@Override
	public Rectangle getHitArea() {
		return hitArea;
	}
}
