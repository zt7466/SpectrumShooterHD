package com.thompson.spectrumshooter.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.Array;
import com.thompson.spectrumshooter.color.ColorWheel;
import com.thompson.spectrumshooter.gameobject.GameObject;
import com.thompson.spectrumshooter.gameobject.GameObjectFactory;
import com.thompson.spectrumshooter.spawning.EnemySpawning;
import com.thompson.spectrumshooter.spawning.LinearEnemySpawn;
import com.thompson.spectrumshooter.spawning.NormalProjectileSpawn;
import com.thompson.spectrumshooter.spawning.ProjectileSpawn;
import com.thompson.spectrumshooter.util.CollisionThing;
import com.thompson.spectrumshooter.util.Constants;

public class MainScreen implements Screen {

	private int currentColorCode;

	Array<GameObject> enemyHorde;
	Array<GameObject> projectiles;
	private GameObject hero;

	private ColorWheel colorWheel;
	private SpriteBatch spriteBatch;

	private OrthographicCamera camera;

	private World world;

	// TODO remove
	Box2DDebugRenderer debugRenderer = new Box2DDebugRenderer();

	private EnemySpawning enemySpawning;
	private ProjectileSpawn projectileSpawning;
	
	private float shootDelay = 0.075f;
	private float currentDelay;
	
	LabelStyle currentHealthStyle;
	Label currentHealth;
	
	private boolean spawn;
	private boolean gameOver = false;
	
	private int enemiesKilled;

	public MainScreen() {
		init();
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float deltaTime) {

		currentDelay = currentDelay + deltaTime;
		
		// update information about the game
		updateBackgroundColor();
		world.step(1 / 30f, 9, 2);
		
		if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && currentDelay >= shootDelay)
		{
			spawn = true;
			currentDelay = 0;
		}

		this.enemySpawning.update(enemyHorde, world, deltaTime);
		projectileSpawning.update(projectiles, spawn, world,
				  (Gdx.input.getX() - Gdx.graphics.getWidth() / 2),
				  (Gdx.input.getY() - Gdx.graphics.getHeight() / 2) * -1);

		spawn = false;
		
		for (GameObject enemy: enemyHorde)
		{
			enemy.update();
		}
		
		for (GameObject projectile: projectiles)
		{
			projectile.update();
		}
		
		if (!gameOver)
		{
			if (!hero.isAlive)
			{
				hero.dispose();
				world.destroyBody(hero.getFixture().getBody());
				gameOver = true;
			}
		}

		// draw everything in the game
		spriteBatch.setProjectionMatrix(camera.combined);

		spriteBatch.begin();
		
		if (!gameOver)
		{
			hero.draw(spriteBatch);
		}
			
		for (GameObject enemy: enemyHorde)
		{
			enemy.draw(spriteBatch);
		}
		
		for (GameObject projectile: projectiles)
		{
			projectile.draw(spriteBatch);
		}
		currentHealth.draw(spriteBatch, 1);
		spriteBatch.end();

		debugRenderer.render(world, camera.combined);

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
		enemiesKilled = 0;
		
		currentDelay = 0;
		
		spawn = false;
		
		enemyHorde = new Array<GameObject>();
		projectiles = new Array<GameObject>();

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

		enemySpawning = new LinearEnemySpawn(2.0f);
		projectileSpawning = new NormalProjectileSpawn();
		currentHealthStyle = new LabelStyle(new BitmapFont(), new Color(.255f, .255f, .255f, 1f));
		currentHealth = new Label("Current Health: ", currentHealthStyle);

	}

}
