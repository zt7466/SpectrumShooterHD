package com.thompson.spectrumshooter.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.thompson.spectrumshooter.assets.Assets;
import com.thompson.spectrumshooter.color.ColorWheel;
import com.thompson.spectrumshooter.enemy.Enemy;
import com.thompson.spectrumshooter.sound.AudioManager;
import com.thompson.spectrumshooter.util.Constants;

public class MainScreen implements Screen
{
	private Array<Enemy> enemyHorde;

	private int currentColorCode;
	private ColorWheel colorWheel;
	private SpriteBatch spriteBatch;

	private OrthographicCamera camera;

	public MainScreen()
	{
		init();
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float delta)
	{
		updateBackgroundColor();

		spriteBatch.setProjectionMatrix(camera.combined);
		spriteBatch.begin();
		// render here
		spriteBatch.end();
	}

	private void updateBackgroundColor()
	{
		Gdx.gl.glClearColor(colorWheel.getRedValue(currentColorCode)/255.0f,
							colorWheel.getBlueValue(currentColorCode)/255.0f,
							colorWheel.getGreenValue(currentColorCode)/255.0f,
							1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		currentColorCode = colorWheel.incrementColorCode(currentColorCode);
	}

	@Override
	public void resize(int width, int height)
	{
		camera.viewportWidth = (Constants.VIEWPORT_HEIGHT / height) * width;
		camera.update();
	}

	@Override
	public void pause()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void resume()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void hide()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose()
	{
		// TODO Auto-generated method stub

	}

	private void init()
	{
		enemyHorde = new Array<Enemy>();

		camera = new OrthographicCamera(Constants.VIEWPORT_WIDTH,
										Constants.VIEWPORT_HEIGHT);
		camera.position.set(0, 0, 0);
		camera.update();

		currentColorCode = 0;
		colorWheel = new ColorWheel();
		spriteBatch  = new SpriteBatch();
		Assets.instance.init();
		AudioManager.instance.play(Assets.instance.deathSound);
	}
}















