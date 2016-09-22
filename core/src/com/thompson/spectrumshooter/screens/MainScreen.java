package com.thompson.spectrumshooter.screens;

import java.sql.Blob;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.thompson.spectrumshooter.color.ColorWheel;
import com.thompson.spectrumshooter.gameobject.Enemy;
import com.thompson.spectrumshooter.gameobject.GameObject;
import com.thompson.spectrumshooter.gameobject.GameObjectFactory;
import com.thompson.spectrumshooter.spawning.NormalSpawn;
import com.thompson.spectrumshooter.spawning.SpawningAlgorithm;
import com.thompson.spectrumshooter.util.CollisionThing;
import com.thompson.spectrumshooter.util.Constants;

public class MainScreen implements Screen {

	private int currentColorCode;

	Array<GameObject> enemyHorde;
	private GameObject hero;

	private ColorWheel colorWheel;
	private SpriteBatch spriteBatch;

	private OrthographicCamera camera;

	private World world;

	Box2DDebugRenderer debugRendere = new Box2DDebugRenderer();

	private SpawningAlgorithm spawningAlgorithm;

	public MainScreen() {
		init();
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float deltaTime) {
		updateBackgroundColor();

		world.step(1 / 30f, 9, 2);

		this.spawningAlgorithm.update(enemyHorde, world, deltaTime);

		for (GameObject enemy: enemyHorde)
		{
			enemy.update();
		}
		hero.update();
		
		spriteBatch.setProjectionMatrix(camera.combined);
		spriteBatch.begin();
		hero.draw(spriteBatch);
		
		for (GameObject enemy: enemyHorde)
		{
			enemy.draw(spriteBatch);
		}
		
		spriteBatch.end();

		this.debugRendere.render(world, camera.combined);

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
		enemyHorde = new Array<GameObject>();

		world = new World(new Vector2(0,0), true);
		world.setContactListener(new CollisionThing());
		
		GameObjectFactory gameObjectFactory = new GameObjectFactory();

		hero = gameObjectFactory.makeHero(world);
		
		camera = new OrthographicCamera(Constants.VIEWPORT_WIDTH,
										Constants.VIEWPORT_HEIGHT);
		camera.position.set(0, 0, 0);
		camera.update();

		currentColorCode = 0;
		colorWheel = new ColorWheel();
		spriteBatch  = new SpriteBatch();

		spawningAlgorithm = new NormalSpawn();

	}

}
