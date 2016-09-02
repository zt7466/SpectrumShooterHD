package com.thompson.spectrumshooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.thompson.spectrumshooter.color.ColorWheel;

public class MainScreen implements Screen
{

	private int currentColorCode;
	private ColorWheel colorWheel;

	public MainScreen()
	{
		currentColorCode = 0;
		colorWheel = new ColorWheel();
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float delta)
	{
		Gdx.gl.glClearColor(colorWheel.getRedValue(currentColorCode)/255.0f,
							colorWheel.getBlueValue(currentColorCode)/255.0f,
							colorWheel.getGreenValue(currentColorCode)/255.0f,
							1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		currentColorCode++;
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
