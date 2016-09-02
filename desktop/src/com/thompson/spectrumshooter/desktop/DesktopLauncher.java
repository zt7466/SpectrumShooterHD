package com.thompson.spectrumshooter.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.thompson.spectrumshooter.SpectrumShooter;
import com.thompson.spectrumshooter.util.Constants;

public class DesktopLauncher
{
	public static void main(String[] arg)
	{
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.title = Constants.GAME_NAME;
		config.height = Constants.GAME_HEIGHT;
		config.width = Constants.GAME_WIDTH;

		new LwjglApplication(new SpectrumShooter(), config);
	}
}
