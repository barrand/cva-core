package com.bbj.cva.screenobjects.selection;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bbj.cva.events.CheerborgUnitTypeEvent;
import com.bbj.cva.events.UnitTypeSelectEvent;
import com.bbj.cva.model.CvaModel;
import com.bbj.cva.screenobjects.IScreenObject;
import com.bbj.cva.screenobjects.ScreenObject;
import com.squareup.otto.Subscribe;

public class CheerborgUnitBar implements IScreenObject
{
	private ArrayList<ScreenObject> unitbar;
	
	public CheerborgUnitBar()
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
		so.x = (pos*CvaModel.TILE_WIDTH) + (CvaModel.TILE_WIDTH*7) + so.getSpriteWidth()/2;
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
		for (ScreenObject unit : unitbar)
		{
			unit.render(spriteBatch);
		}
	}
	
	@Subscribe
	public void onUnitSelected(UnitTypeSelectEvent event) {
		boolean fireEvent = false;
		for (ScreenObject so : unitbar)
		{
			if (Math.abs(so.x - event.x) < 0.01)
			{
				// FIXME: Eventually the screenobjects here should have a state of being 'ready' to deploy, which we will check...
				fireEvent = true;
				CvaModel.eventBus.post(new CheerborgUnitTypeEvent(so)); // FIXME: Should change this to transmit only the Unit enum of the selection
			}
		}
		// FIXME: ...and then we won't have to do this nonsense.
		
		//todo, I'm not sure what this is supposed to do
//		if (!fireEvent && (event.x > getX()) && (event.x < (getX()+getSpriteWidth())))
//		{
//			CvaModel.eventBus.post(new CheerborgUnitTypeEvent(null));
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
