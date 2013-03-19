package com.bbj.cva.screenobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bbj.cva.model.CvaModel;

public class PomSelect extends ScreenObjectSimple 
{
	public PomSelect(float x, float y)
	{
		super(x,y);
		type = CvaModel.Unit.POM;
		texture = CvaModel.pomSelect;
	}

	@Override
	public void render(SpriteBatch spriteBatch) 
	{
		super.render(spriteBatch);
	}

	@Override
	public void create() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
}