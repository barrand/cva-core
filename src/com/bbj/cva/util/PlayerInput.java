package com.bbj.cva.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.bbj.cva.model.CvaModel;

public class PlayerInput
{
	static public boolean moveSelectLeftPressed(int playernum)
	{
		return false;
	}
	
	static public boolean moveSelectRightPressed(int playernum)
	{
		return false;
	}
	
	static public boolean moveLeftPressed(int playernum)
	{
		switch (playernum)
		{
		case 1:
			if (Gdx.input.isKeyPressed(Input.Keys.A))
			{
				return true;
			}
			break;
		case 2:
			if (Gdx.input.isKeyPressed(Input.Keys.DPAD_LEFT))
			{
				return true;
			}
			break;
		default:
			break;
		}
		return false;	
	}
	
	static public boolean moveRightPressed(int playernum)
	{
		switch (playernum)
		{
		case 1:
			if (Gdx.input.isKeyPressed(Input.Keys.D))
			{
				return true;
			}
			break;
		case 2:
			if (Gdx.input.isKeyPressed(Input.Keys.DPAD_RIGHT))
			{
				return true;
			}
			break;
		default:
			break;
		}
		return false;
	}
	
	static public boolean moveUpPressed(int playernum)
	{
		switch (playernum)
		{
		case 1:
			if (Gdx.input.isKeyPressed(Input.Keys.W))
			{
				return true;
			}
			break;
		case 2:
			if (Gdx.input.isKeyPressed(Input.Keys.DPAD_UP))
			{
				return true;
			}
			break;
		default:
			break;
		}
		return false;
	}
	
	static public boolean moveDownPressed(int playernum)
	{
		switch (playernum)
		{
		case 1:
			if (Gdx.input.isKeyPressed(Input.Keys.S))
			{
				return true;
			}
			break;
		case 2:
			if (Gdx.input.isKeyPressed(Input.Keys.DPAD_DOWN))
			{
				return true;
			}
			break;
		default:
			break;
		}
		return false;
	}
	
	static public boolean actionButtonPressed(int playernum)
	{
		switch (playernum)
		{
		case 1:
			if (Gdx.input.isKeyPressed(Input.Keys.SPACE))
			{
				return true;
			}
			if (Gdx.input.isTouched() && (Gdx.input.getX() < CvaModel.screenWidth/2))
			{
				return true;
			}
			break;
		case 2:
			if (Gdx.input.isKeyPressed(Input.Keys.ENTER))
			{
				return true;
			}
			if (Gdx.input.isTouched() && (Gdx.input.getX() > CvaModel.screenWidth/2))
			{
				return true;
			}
			break;
		default:
			break;
		}
		return false;
	}
}
