package com.bbj.cva.screenobjects.selection;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bbj.cva.events.SpiderUnitTypeEvent;
import com.bbj.cva.events.UnitTypeSelectEvent;
import com.bbj.cva.model.CvaModel;
import com.bbj.cva.screenobjects.IScreenObject;
import com.bbj.cva.screenobjects.ScreenObject;
import com.squareup.otto.Subscribe;

// FIXME: This class is SO SIMILAR to the Cheerborg Unit Bar.  Only like 3 things are different.  Consider creating parent class...

public class SpiderUnitBar implements IScreenObject
{
	private ArrayList<ScreenObject> unitbar;
	
	public SpiderUnitBar()
	{
		CvaModel.eventBus.register(this);
	}
	
	@Override
	public void create()
	{
		unitbar = new ArrayList<ScreenObject>();
		
	}

	public boolean addUnitToBar(ScreenObject so)
	{
		int pos = unitbar.size();
		if (pos >= 5) return false;
		so.setX(pos*CvaModel.TILE_WIDTH + so.getWidth()/2);  // FIXME: This is too hard-codedy for my taste...
		so.setY(CvaModel.TILE_HEIGHT*7);
		unitbar.add(so);
		return true;
	}
	
	@Override
	public void destroy()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(SpriteBatch spriteBatch)
	{
		for (ScreenObject unit : unitbar)
		{
			unit.render(spriteBatch);
		}
	}
	
	@Subscribe
	public void onUnitSelect(UnitTypeSelectEvent event) {
		boolean fireEvent = false;
		for (ScreenObject so : unitbar)
		{
			if (Math.abs(so.getX() - event.x) < 0.01)
			{
				// FIXME: Eventually the screenobjects here should have a state of being 'ready' to deploy, which we will check...
				fireEvent = true;
				CvaModel.eventBus.post(new SpiderUnitTypeEvent(so)); // FIXME: Should change this to transmit only the Unit enum of the selection
			}
		}
		// FIXME: ...and then we won't have to do this nonsense.
		if (!fireEvent && (event.x > getX()) && (event.x < (getX()+getWidth())))
		{
			CvaModel.eventBus.post(new SpiderUnitTypeEvent(null));
		}

	}

	@Override
	public float getWidth()
	{
		return CvaModel.TILE_WIDTH*5;
	}

	@Override
	public float getHeight()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getSpeedX()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getSpeedY()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getX()
	{
		return 0f;
	}

	@Override
	public float getY()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setX(float x)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setY(float y)
	{
		// TODO Auto-generated method stub
		
	}
}
