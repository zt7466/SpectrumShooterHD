package com.thompson.spectrumshooter;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.thompson.spectrumshooter.screens.GameOverScreen;
import com.thompson.spectrumshooter.screens.MainScreen;
import com.thompson.spectrumshooter.screens.MenuScreen;

/**
 * SpectrumShooter.java
 * @author Zach
 * Main class for the game that manages the games screens
 */
public class SpectrumShooter extends Game
{
	private static SpectrumShooter instance = null;
	private boolean testMode;

	public SpectrumShooter(boolean testMode)
	{
		this.testMode = testMode;
	}

	/**
	 * Class run where the game is started
	 */
	@Override
	public void create()
	{
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		instance = this;
		if(!testMode)
		{
			instance.setScreen(new MenuScreen());
		}
		else
		{
			instance.setScreen(new MainScreen());
		}
	}

	/**
	 * Class run on repeat
	 */
	@Override
	public void render()
	{
		if (instance.getScreen().getClass() == MainScreen.class &&
				((MainScreen) instance.getScreen()).gameOver)
		{
			this.setScreen(new GameOverScreen(((MainScreen) instance.getScreen()).enemiesKilled));
		}
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
