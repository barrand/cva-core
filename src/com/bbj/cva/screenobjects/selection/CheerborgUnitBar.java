package com.bbj.cva.screenobjects.selection;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bbj.cva.events.PlaceUnitEvent;
import com.bbj.cva.events.QueryCheerUnitAvailableEvent;
import com.bbj.cva.events.UnitTypeSelectEvent;
import com.bbj.cva.model.CvaModel;
import com.bbj.cva.screenobjects.ScreenObjectSimple;
import com.bbj.cva.screenobjects.interfaces.IScreenObject;
import com.squareup.otto.Subscribe;

public class CheerborgUnitBar implements IScreenObject
{
	private ArrayList<ScreenObjectSimple> unitbar;
	
	public CheerborgUnitBar()
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
		so.x = (pos*CvaModel.TILE_WIDTH) + (CvaModel.TILE_WIDTH*7);
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
	public void onUnitSelected(UnitTypeSelectEvent event) {
		if (event.selection.x < (CvaModel.TILE_WIDTH*7))
			return;
		for (ScreenObjectSimple so : unitbar)
		{
			if (event.selection.contains(so.x+5, so.y+5))
			{
				so.selected = true;
			}
			else
			{
				so.selected = false;
			}
		}
	}

	@Subscribe
	public void onQueryAvailability(QueryCheerUnitAvailableEvent event) {
		for (ScreenObjectSimple so : unitbar)
		{
			if (so.selected)
			{
				if (so.isAvailable())
				{
					CvaModel.eventBus.post(new PlaceUnitEvent(event.x, event.y, so.type));
				}
			}
		}
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
