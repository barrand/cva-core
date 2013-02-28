package com.bbj.cva.screenobjects.selection;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bbj.cva.events.PlaceUnitEvent;
import com.bbj.cva.events.SpiderUnitTypeEvent;
import com.bbj.cva.events.UnitTypeSelectEvent;
import com.bbj.cva.model.CvaModel;
import com.bbj.cva.screenobjects.Pom;
import com.bbj.cva.screenobjects.ScreenObject;
import com.bbj.cva.screenobjects.ScreenObjectSimple;
import com.bbj.cva.screenobjects.SpiderUnit;
import com.bbj.cva.screenobjects.interfaces.IScreenObject;
import com.squareup.otto.Subscribe;

// FIXME: This class is SO SIMILAR to the Cheerborg Unit Bar.  Only like 3 things are different.  Consider creating parent class...

public class SpiderUnitBar implements IScreenObject
{
	private ArrayList<ScreenObjectSimple> unitbar;
	
	public SpiderUnitBar()
	{
		CvaModel.eventBus.register(this);
	}
	
	@Override
	public void create()
	{
		unitbar = new ArrayList<ScreenObjectSimple>();
		
	}

	public boolean addUnitToBar(ScreenObjectSimple so)
	{
		int pos = unitbar.size();
		if (pos >= 5) return false;
		so.x = pos*CvaModel.TILE_WIDTH + so.getSpriteWidth()/2;  // FIXME: This is too hard-codedy for my taste...
		so.y = CvaModel.TILE_HEIGHT*7;
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
		for (ScreenObjectSimple unit : unitbar)
		{
			unit.render(spriteBatch);
		}
	}
	
	@Subscribe
	public void onUnitSelect(UnitTypeSelectEvent event) {
		boolean fireEvent = false;
		for (ScreenObjectSimple so : unitbar)
		{
			if (Math.abs(so.x - event.x) < 0.01)
			{
				// FIXME: Eventually the screenobjects here should have a state of being 'ready' to deploy, which we will check...
				fireEvent = true;
//				SpiderUnit spider = new SpiderUnit(so.x, so.y);
//				CvaModel.eventBus.post(new PlaceUnitEvent(spider)); // FIXME: Should change this to transmit only the Unit enum of the selection
			}
		}
		// FIXME: ...and then we won't have to do this nonsense.
		//not sure on this
//		if (!fireEvent && (event.x > x) && (event.x < (x+getSpriteWidth())))
//		{
//			CvaModel.eventBus.post(new SpiderUnitTypeEvent(null));
//		}

	}

	@Override
	public float getSpriteWidth()
	{
		return CvaModel.TILE_WIDTH*5;
	}

	@Override
	public float getSpriteHeight()
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
}
