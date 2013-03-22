package com.bbj.cva.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import com.badlogic.gdx.utils.Scaling;

import com.bbj.cva.CheerVArachnids;

public class SplashScreen extends AbstractScreen
{
    private Texture splashTexture;
    private Image splashImage;

    public SplashScreen(CheerVArachnids game)
    {
        super(game);
    }

    @Override
    public void show()
    {
        super.show();

        // load the splash image and create the texture region
        splashTexture = new Texture(Gdx.files.internal("data/splash.png"));

        // we set the linear texture filter to improve the stretching
        splashTexture.setFilter( TextureFilter.Linear, TextureFilter.Linear );
    }

    @Override
    public void resize(int width, int height )
    {
        super.resize(width, height);

        // let's make sure the stage is clear
        stage.clear();

        // in the image atlas, our splash image begins at (0,0) of the
        // upper-left corner and has a dimension of 512x301
        TextureRegion splashRegion = new TextureRegion(splashTexture, 0, 0, 512, 382);
        Drawable splashRegionDraw = new TextureRegionDrawable(splashRegion);
        
        // here we create the splash image actor and set its size
        splashImage = new Image(splashRegionDraw, Scaling.stretch);
        splashImage.setFillParent( true );
        
        // this is needed for the fade-in effect to work correctly; we're just
        // making the image completely transparent
        splashImage.getColor().a = 0f;

        // configure the fade-in/out effect on the splash image
        splashImage.addAction( Actions.sequence( Actions.fadeIn( 0.75f ), Actions.delay( 1.75f ), Actions.fadeOut( 0.75f ),
        		 new Action() {
        		  public boolean act( float delta ) {
        		   game.setScreen( new GameScreen( game ) );
        		   return true; // returning true consumes the event
        		  }
        		 } )
        		);
        // and finally we add the actors to the stage, on the correct order
        stage.addActor( splashImage );
    }
    
    @Override
    public void dispose()
    {
        super.dispose();
        splashTexture.dispose();
    }
}