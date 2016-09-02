package com.thompson.spectrumshooter;

import com.badlogic.gdx.Game;

/**
 * SpectrumShooter.java
 * @author Zach
 * Main class for the game that manages the games screens
 *
 */
public class SpectrumShooter extends Game 
{
	private static SpectrumShooter instance = null;
	
	@Override
	public void create() 
	{
		instance = this;
		
		instance.setScreen(new MainScreen());
	}

	@Override
	public void render()
	{
		super.render();
	}
	
	
	
	public static  SpectrumShooter getInstance()
	{
		return instance;
	}
}
