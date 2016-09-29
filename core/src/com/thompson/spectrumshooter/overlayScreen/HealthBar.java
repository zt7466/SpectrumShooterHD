package com.thompson.spectrumshooter.overlayScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar.ProgressBarStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

/**
 * HealthBar.java 
 * class for main character's healthbar
 * @author Zachary
 *
 */
public class HealthBar extends OverlayingScreen 
{
	protected ProgressBar healthBar;
	protected Sprite fillSprite;
	public HealthBar(int MaxHealthBar)
	{
		super(400, 40);
		
		Texture backgroundTexture = new Texture(Gdx.files.local("colorBar.png"));
		
		Sprite backgroundSprite = new Sprite(backgroundTexture);
		backgroundSprite.setSize(350, 20);
		SpriteDrawable background = new SpriteDrawable(backgroundSprite);
		
		fillSprite = new Sprite(backgroundTexture);
		fillSprite.setColor(Color.BLACK);
		fillSprite.setSize(20, 20);
		SpriteDrawable fill = new SpriteDrawable(fillSprite);
		
		ProgressBarStyle style = new ProgressBarStyle(background, fill);
		style.knobBefore = fill;
		
		healthBar = new ProgressBar(0,MaxHealthBar,1,false,style);
		
		healthBar.setValue(100);
		inerdTable.add(healthBar).minWidth(375);
	}

	public void setValue(int value)
	{
		healthBar.setValue(value);
	}
	
	@Override
	public void changeColor(Color c)
	{
		for(int i = 0; i < sprites.size(); i++)
		{
			sprites.get(i).setColor(new Color(.5f - c.r, .5f - c.g, .5f - c.b, 1));
		}
		
		if(fillSprite != null)
		{
			fillSprite.setColor(new Color(c.r, c.g, c.b, 1));
		}
	}
}
