package com.thompson.spectrumshooter.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.math.MathUtils;
import com.thompson.spectrumshooter.SpectrumShooter;
import com.thompson.spectrumshooter.util.Constants;

public class DesktopLauncher
{
	public static void main(String[] arg)
	{
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		
		float x = -10;
		float y = -10;
		System.out.println(MathUtils.sin(MathUtils.atan2(y, x)));
		System.out.println(MathUtils.sinDeg(MathUtils.radiansToDegrees * MathUtils.atan2(y, x)));
		
		config.title = Constants.GAME_NAME;
		config.height = Constants.GAME_HEIGHT;
		config.width = Constants.GAME_WIDTH;

		new LwjglApplication(new SpectrumShooter(), config);
	}
}
