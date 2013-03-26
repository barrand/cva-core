package com.bbj.cva.screenobjects;

import com.bbj.cva.model.CvaModel;
import com.bbj.cva.screenobjects.projectiles.BolaShot;

public class ScreenObjectFactory
{
	public ScreenObjectFactory()
	{
	}
	
	static public ScreenObject createUnit(float x, float y, CvaModel.Unit type)
	{
		ScreenObject so = null;
		switch (type)
		{
		case POM:
			so = new Pom(x, y);
			break;
		case SPIDER:
			so = new SpiderUnit(x, y);
			break;
		case BOLA_SHOT:
			so = new BolaShot(x, y);
			break;
		default:
			break;
		}
		
		return so;
	}
}
