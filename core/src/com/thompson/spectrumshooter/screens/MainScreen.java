package com.thompson.spectrumshooter.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.thompson.spectrumshooter.assets.Assets;
import com.thompson.spectrumshooter.color.ColorWheel;
import com.thompson.spectrumshooter.gameobject.Enemy;
import com.thompson.spectrumshooter.gameobject.GameObject;
import com.thompson.spectrumshooter.gameobject.GameObjectFactory;
import com.thompson.spectrumshooter.gameobject.Hero;
import com.thompson.spectrumshooter.overlayScreen.ColorSelector;
import com.thompson.spectrumshooter.overlayScreen.HealthBar;
import com.thompson.spectrumshooter.overlayScreen.RGBBarSelector;
import com.thompson.spectrumshooter.sound.AudioManager;
import com.thompson.spectrumshooter.spawning.EnemySpawning;
import com.thompson.spectrumshooter.spawning.ExponentialEnemySpawn;
import com.thompson.spectrumshooter.spawning.LinearEnemySpawn;
import com.thompson.spectrumshooter.spawning.NormalProjectileSpawn;
import com.thompson.spectrumshooter.spawning.ProjectileSpawn;
import com.thompson.spectrumshooter.util.CollisionThing;
import com.thompson.spectrumshooter.util.Constants;

/**
 * MainScreen.java
 *
 * @author Christopher Boyer
 */
public class MainScreen implements Screen
{


	public static final String TAG = MainScreen.class.getName();
	private int currentColorCode;

	Array<GameObject> enemyHorde;
	Array<GameObject> projectiles;
	private Hero hero;

	private ColorWheel colorWheel;
	private SpriteBatch spriteBatch;

	private OrthographicCamera camera;

	private World world;

	// TODO this
	private Stage backgroundStage;

	private ColorSelector colorSelector;
	private HealthBar healthBar;

	// TODO remove
	//Box2DDebugRenderer debugRenderer = new Box2DDebugRenderer();

	private EnemySpawning enemySpawning;
	private ProjectileSpawn projectileSpawning;

	private float shootDelay = 0.2f;
	private float currentDelay;

	private boolean spawn;
	public boolean gameOver = false;

	public int enemiesKilled;

	private CollisionThing collision;

	private static final float SPAWN_SPEED = 1.5f;

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
		colorSelector.updateColor(); // Tells the colorSelector to updateItSelf
		backgroundStage.draw();

		world.step(1 / 30f, 9, 2);

		if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && currentDelay >= shootDelay)
		{
			spawn = true;
			currentDelay = 0;
		}

		hero.setColor(colorSelector.selectColor().r,
					  colorSelector.selectColor().g,
					  colorSelector.selectColor().b,
					  1);

		this.enemySpawning.update(enemyHorde, world, deltaTime);
		projectileSpawning.update(projectiles, spawn, colorSelector.selectColor(), world,
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

		enemiesKilled = collision.enemiesKilled;

		// draw everything in the game
		spriteBatch.setProjectionMatrix(camera.combined);

		spriteBatch.begin();

		if (!gameOver)
		{
			hero.draw(spriteBatch);
			healthBar.setValue((int)hero.getHealth());
		}

		for (GameObject enemy: enemyHorde)
		{
			enemy.draw(spriteBatch);
			((Enemy) enemy).getCenterSprite().draw(spriteBatch);
		}

		for (GameObject projectile: projectiles)
		{
			projectile.draw(spriteBatch);
		}
		spriteBatch.end();

		//debugRenderer.render(world, camera.combined);

	}

	private void updateBackgroundColor() {
		Color backgroundColor = new Color(colorWheel.getRedValue(currentColorCode) / 255.0f,
				colorWheel.getBlueValue(currentColorCode) / 255.0f, colorWheel.getGreenValue(currentColorCode) / 255.0f,
				1);

		Gdx.gl.glClearColor(backgroundColor.r,backgroundColor.g,backgroundColor.b,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		currentColorCode = colorWheel.incrementColorCode(currentColorCode);

		healthBar.changeColor(backgroundColor);
		colorSelector.changeColor(backgroundColor);
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

	public Color getColorSelectorColor()
	{
		return colorSelector.selectColor();
	}

	private void init()
	{
		AudioManager.instance.play(Assets.instance.gameMusic);


		enemiesKilled = 0;

		currentDelay = 0;

		spawn = false;

		enemyHorde = new Array<GameObject>();
		projectiles = new Array<GameObject>();

		collision = new CollisionThing();

		world = new World(new Vector2(0,0), true);
		world.setContactListener(collision);

		GameObjectFactory gameObjectFactory = new GameObjectFactory();

		hero = gameObjectFactory.makeHero(world);

		camera = new OrthographicCamera(Constants.VIEWPORT_WIDTH,
										Constants.VIEWPORT_HEIGHT);
		camera.position.set(0, 0, 0);
		camera.update();

		currentColorCode = 0;
		colorWheel = new ColorWheel();
		spriteBatch  = new SpriteBatch();

		enemySpawning = new LinearEnemySpawn();
		projectileSpawning = new NormalProjectileSpawn();

		backgroundStage = new Stage(new FitViewport(Constants.GAME_WIDTH, Constants.GAME_HEIGHT), spriteBatch);

		// TODO fix this to not be shitty
		Table backgroundTable = new Table();
		backgroundTable.setFillParent(true);
		Sprite circleBackground = new Sprite(new Texture(Gdx.files.local("whiteCircleFaded.png")));
		circleBackground.setSize(550, 550);
		backgroundTable.add(new Image(new SpriteDrawable(circleBackground)));
		backgroundStage.addActor(backgroundTable);

		healthBar = new HealthBar((int)hero.getHealth());
		Table healthBarTable = new Table();
		healthBarTable.setFillParent(true);
		healthBarTable.add(healthBar.getTable()).padTop(Constants.GAME_HEIGHT *.85f);
		backgroundStage.addActor(healthBarTable);

		colorSelector = new RGBBarSelector();
		Table colorSelectorTable = new Table();
		colorSelectorTable.setFillParent(true);
		colorSelectorTable.add(colorSelector.getTable()).padRight(Constants.GAME_WIDTH * .75f);
		backgroundStage.addActor(colorSelectorTable);
	}

}
