package com.bbj.cva;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.FPSLogger;
import com.bbj.cva.screens.GameScreen;
import com.bbj.cva.screens.SplashScreen;

public class CheerVArachnids extends Game {
    // constant useful for logging
	public static final String LOG = CheerVArachnids.class.getSimpleName();
	
	// a libgdx helper class that logs the current FPS each second
    private FPSLogger fpsLogger;
	
	public CheerVArachnids()
	{
	}

	public SplashScreen getSplashScreen()
    {
        return new SplashScreen( this );
    }
	
	public GameScreen getGameScreen()
    {
        return new GameScreen( this );
    }

	@Override
	public void create() {
		Gdx.app.log(CheerVArachnids.LOG, "Creating Game");
		fpsLogger = new FPSLogger();
		setScreen(getSplashScreen());
	}

	@Override
	public void dispose() 
	{
		super.dispose();
		Gdx.app.log(CheerVArachnids.LOG, "Disposing Game");
	}

	@Override
	public void render() {
		super.render();
		
		// output the current FPS
		fpsLogger.log();
	}

	@Override
	public void resize(int width, int height)
	{
		super.resize(width, height);
		Gdx.app.log(CheerVArachnids.LOG, "Resizing game to: " + width + " x " + height);
	}

	@Override
	public void pause()
	{
		super.pause();
		Gdx.app.log(CheerVArachnids.LOG,  "Pausing game");
	}

	@Override
	public void resume()
	{
		super.resume();
		Gdx.app.log(CheerVArachnids.LOG,  "Resuming game");
	}
	
	@Override
	public void setScreen(Screen screen)
	{
		super.setScreen(screen);
		Gdx.app.log(CheerVArachnids.LOG, "Setting screen: " + screen.getClass().getSimpleName());
	}
}