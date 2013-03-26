package com.bbj.cva.screenobjects.selection;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bbj.cva.events.PlaceUnitEvent;
import com.bbj.cva.events.QuerySpiderUnitAvailableEvent;
import com.bbj.cva.events.UnitTypeSelectEvent;
import com.bbj.cva.model.CvaModel;
import com.bbj.cva.screenobjects.ScreenObject;
import com.bbj.cva.screenobjects.ScreenObjectFactory;
import com.bbj.cva.screenobjects.ScreenObjectSimple;
import com.bbj.cva.screenobjects.interfaces.IHitAreaObject;
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
		so.x = pos*CvaModel.TILE_WIDTH;
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
		if (event.selection.x >= (CvaModel.TILE_WIDTH*7))
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
	public void onQueryAvailability(QuerySpiderUnitAvailableEvent event) {
		for (ScreenObjectSimple so : unitbar)
		{
			if (so.selected)
			{
				if (so.isAvailable())
				{
					ScreenObject screenObject = ScreenObjectFactory.createUnit(event.x, event.y, so.type);
					CvaModel.eventBus.post(new PlaceUnitEvent(screenObject));
					CvaModel.thingsCheerborgsInteractWith.add((IHitAreaObject) screenObject);
				}
			}
		}
	}
}
