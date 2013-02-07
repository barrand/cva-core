package com.bbj.cva.screenobjects.selection;

import java.util.ArrayList;

import org.bushe.swing.event.EventBus;
import org.bushe.swing.event.EventSubscriber;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bbj.cva.events.CheerborgUnitTypeEvent;
import com.bbj.cva.events.UnitTypeSelectEvent;
import com.bbj.cva.model.CvaModel;
import com.bbj.cva.screenobjects.IScreenObject;
import com.bbj.cva.screenobjects.ScreenObject;

public class CheerborgUnitBar implements IScreenObject
{
	private ArrayList<ScreenObject> unitbar;
	
	public CheerborgUnitBar()
	{
		EventBus.subscribe(UnitTypeSelectEvent.class, new UnitTypeSelectListener());
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
		so.setX((int)(pos*CvaModel.TILE_WIDTH + CvaModel.TILE_WIDTH*7));
		so.setY((int)(CvaModel.TILE_HEIGHT*7));
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
	
	class UnitTypeSelectListener implements EventSubscriber<UnitTypeSelectEvent> 
	{
		@Override
		public void onEvent(UnitTypeSelectEvent unitSelect)
		{
			boolean fireEvent = false;
			for (ScreenObject so : unitbar)
			{
				if (so.getX() == unitSelect.x)
				{
					// FIXME: Eventually the screenobjects here should have a state of being 'ready' to deploy, which we will check...
					fireEvent = true;
					EventBus.publish(new CheerborgUnitTypeEvent(so)); // FIXME: Should change this to transmit only the Unit enum of the selection
				}
			}
			// FIXME: ...and then we won't have to do this nonsense.
			if (!fireEvent && (unitSelect.x > getX()) && (unitSelect.x < (getX()+getWidth())))
			{
				EventBus.publish(new CheerborgUnitTypeEvent(null));
			}
		}
		
	}

	@Override
	public void setX(int x)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setY(int y)
	{
		// TODO Auto-generated method stub
		
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
	public void loadTextureIfNeeded()
	{
		// TODO Auto-generated method stub
		
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
	public int getX()
	{
		return (int)(CvaModel.TILE_WIDTH * 7);
	}

	@Override
	public int getY()
	{
		// TODO Auto-generated method stub
		return 0;
	}
}
