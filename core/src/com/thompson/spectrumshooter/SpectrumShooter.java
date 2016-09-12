package com.thompson.spectrumshooter;

import com.badlogic.gdx.Game;
import com.thompson.spectrumshooter.screens.MainScreen;
import com.thompson.spectrumshooter.util.Constants;
import com.badlogic.gdx.Gdx;

/**
 * SpectrumShooter.java
 * @author Zach
 * Main class for the game that manages the games screens
 *
 */
public class SpectrumShooter extends Game
{
	private static SpectrumShooter instance = null;

	/**
	 * Class run where the game is started
	 */
	@Override
	public void create()
	{
		
		instance = this;
		
		instance.setScreen(new MainScreen());
	}

	/**
	 * Class run on repeat
	 */
	@Override
	public void render()
	{
		super.render();
	}
	
	@Override
	public void resize(int width, int height)
	{
		instance.getScreen().resize(width, height);
	}

	/**
	 * Singleton method call
	 * @return
	 */
	public static SpectrumShooter getInstance()
	{
		return instance;
	}

}
