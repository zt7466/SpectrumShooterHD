package com.thompson.spectrumshooter.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.thompson.spectrumshooter.color.ColorWheel;
import com.thompson.spectrumshooter.enemy.Enemy;
import com.thompson.spectrumshooter.enemy.EnemyFactory;
import com.thompson.spectrumshooter.util.Constants;

public class MainScreen implements Screen {
	private Array<Enemy> enemyHorde;

	private int currentColorCode;
	private ColorWheel colorWheel;
	private SpriteBatch spriteBatch;

	private OrthographicCamera camera;

	private EnemyFactory enemyFactory;

	private World world;

	public MainScreen() {
		init();
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float delta) {
		updateBackgroundColor();
		world.step(1 / 30f, 9, 2);

		for (Enemy enemy : enemyHorde)
		{
			enemy.update();
		}

		for (Enemy enemy : enemyHorde)
		{
			if (!enemy.isAlive)
			{
				world.destroyBody(enemy.getFixture().getBody());
				enemy.dispose();
				enemyHorde.removeValue(enemy, false);
			}
		}

		spriteBatch.setProjectionMatrix(camera.combined);
		spriteBatch.begin();
		for (Enemy enemy : enemyHorde)
		{
			enemy.draw(spriteBatch);
		}
		spriteBatch.end();

	}

	private void updateBackgroundColor() {
		Gdx.gl.glClearColor(colorWheel.getRedValue(currentColorCode) / 255.0f,
				colorWheel.getBlueValue(currentColorCode) / 255.0f, colorWheel.getGreenValue(currentColorCode) / 255.0f,
				1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		currentColorCode = colorWheel.incrementColorCode(currentColorCode);
	}

	@Override
	public void resize(int width, int height) {
		camera.viewportWidth = (Constants.VIEWPORT_HEIGHT / height) * width;
		camera.update();
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

	private void init()
	{
		world = new World(new Vector2(0,0), true);

		enemyHorde = new Array<Enemy>();

		camera = new OrthographicCamera(Constants.VIEWPORT_WIDTH,
										Constants.VIEWPORT_HEIGHT);
		camera.position.set(0, 0, 0);
		camera.update();

		currentColorCode = 0;
		colorWheel = new ColorWheel();
		spriteBatch  = new SpriteBatch();
		enemyFactory = new EnemyFactory();

		enemyHorde.add(enemyFactory.makeBasicEnemy(world));

	}

}
