package com.thompson.spectrumshooter;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.thompson.spectrumshooter.assets.Assets;
import com.thompson.spectrumshooter.screens.GameOverScreen;
import com.thompson.spectrumshooter.screens.MainScreen;
import com.thompson.spectrumshooter.screens.MenuScreen;
import com.thompson.spectrumshooter.sound.AudioManager;

/**
 * SpectrumShooter.java
 *
 * @author Zachary Thompson
 *
 * Main class for runninng the game and managing the current game screen.
 */
public class SpectrumShooter extends Game
{
	private static SpectrumShooter instance = null;
	private boolean testMode;	// discerns whether or not we're in test mode

	public SpectrumShooter(boolean testMode)
	{
		this.testMode = testMode;
	}

	/**
	 * Create a new instance of the game.
	 */
	@Override
	public void create()
	{
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		instance = this;
		if(!testMode)
		{
			AudioManager.instance.play(Assets.instance.music.mainMenuMusic);
			instance.setScreen(new MenuScreen());
		}
		else
		{
			AudioManager.instance.play(Assets.instance.music.gameMusic);
			instance.setScreen(new MainScreen());
		}
	}

	/**
	 * Main game loop.
	 */
	@Override
	public void render()
	{
		// if the current screen is MainScreen and the game is curently over, then switch to
		// the game over screen.
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
	 * Get the only instance of SpectrumShooter.
	 * @return only instance of SpectrumShooter
	 */
	public static SpectrumShooter getInstance()
	{
		return instance;
	}

}
