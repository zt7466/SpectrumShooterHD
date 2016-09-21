package com.thompson.spectrumshooter.gameobject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.graphics.Texture;
import com.thompson.spectrumshooter.color.ColorWheel;
import com.thompson.spectrumshooter.util.Constants;


/**
 * A factory that allows for the creation of enemies.
 *
 * @author cb9619
 */
public class GameObjectFactory
{

	private static final int PIXMAP_RADIUS = 150;
	private ColorWheel colorWheel;

	public GameObjectFactory()
	{
		this.colorWheel = new ColorWheel();
	}

	/**
	 * Make a new Enemy that moves linearly from it's spawn location towards
	 * the center of the screen.
	 * @param world		the world where the Enemy exists
	 * @return			the new basic Enemy
	 */
	public Enemy makeBasicEnemy(World world)
	{
		int colorCode = colorWheel.random();
		Texture texture = new Texture(createPixmap(colorCode));

		float spriteSize =  MathUtils.random(0.25f, 0.75f);

		Fixture fixture = createFixture(world, generateRandomSpawnLocation(), spriteSize);

		Enemy enemy = new Enemy(colorCode, fixture, texture,spriteSize);

		enemy.setOrigin(enemy.getWidth() / 2.0f, enemy.getHeight() / 2.0f);

		return enemy;
	}

	public Hero makeHero(World world)
	{
		int colorCode = 0;

		Texture texture = new Texture(createPixmap(colorCode));
		float spriteSize = 0.75f;
		Fixture fixture = createFixture(world, new Vector2(0, 0), spriteSize);

		Hero hero = new Hero(colorCode, fixture, texture, 0.0f);

		hero.setOrigin(hero.getWidth() / 2.0f, hero.getHeight() / 2.0f);

		hero.setSize(spriteSize, spriteSize);
		hero.setPosition(fixture.getBody().getPosition().x,
				  		 fixture.getBody().getPosition().y);

		return hero;
	}

	/**
	 * Create the pixmaps used for our GameObjects.
	 * @param colorCode the color code from the color wheel of this Pixmap
	 * @return a new pixmap
	 */
	private Pixmap createPixmap(int colorCode)
	{
		Pixmap pixmap = new Pixmap(300, 300, Format.RGBA8888);
		pixmap.setColor(colorWheel.getRedValue(colorCode),
						colorWheel.getGreenValue(colorCode),
						colorWheel.getBlueValue(colorCode),
						1);
		pixmap.fillCircle(150, 150, PIXMAP_RADIUS);
		return pixmap;
	}

	/**
	 * Create a new Fixture for an Enemy. Add that fixture to the game world.
	 * @param world		game world where the Enemy exists
	 * @return			new Fixture
	 */
	private Fixture createFixture(World world, Vector2 spawnPosition, float spriteSize)
	{
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;

		bodyDef.position.set(spawnPosition);

		Body body = world.createBody(bodyDef);
		body.setLinearVelocity(-spawnPosition.x * 0.1f, -spawnPosition.y * 0.1f);

		CircleShape circle = new CircleShape();
		circle.setRadius(spriteSize * Constants.BOX2D_CONVERSION);

		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = circle;

		Fixture fixture = body.createFixture(fixtureDef);
		// remove the CircleShape resources for some reason
		circle.dispose();
		return fixture;
	}

	/**
	 * Generate a random spawn location for an Enemy.
	 * @return	a random spawn location for an Enemy
	 */
	private Vector2 generateRandomSpawnLocation()
	{
		int QUAD_I_START = 0;
		int QUAD_II_START = 90;
		int QUAD_III_START = 180;
		int QUAD_IV_START = 270;
		int QUAD_IV_END = 360;

		float x = 0;
		float y = 0;

		float theta = MathUtils.random(0f, 360.0f);
		if (theta > QUAD_I_START && theta < QUAD_II_START) {
			x =  Constants.SPAWN_CIRCLE_RADIUS * MathUtils.cosDeg(theta);
			y =  Constants.SPAWN_CIRCLE_RADIUS * MathUtils.sinDeg(theta);
		} else if (theta > QUAD_II_START && theta < QUAD_III_START) {
			x = -Constants.SPAWN_CIRCLE_RADIUS * MathUtils.cosDeg(theta);
			y =  Constants.SPAWN_CIRCLE_RADIUS * MathUtils.sinDeg(theta);
		} else if (theta > QUAD_III_START && theta < QUAD_IV_START) {
			x = -Constants.SPAWN_CIRCLE_RADIUS * MathUtils.cosDeg(theta);
			y =  Constants.SPAWN_CIRCLE_RADIUS * MathUtils.sinDeg(theta);
		} else if (theta > QUAD_IV_START && theta < QUAD_IV_END) {
			x = -Constants.SPAWN_CIRCLE_RADIUS * MathUtils.cosDeg(theta);
			y =  Constants.SPAWN_CIRCLE_RADIUS * MathUtils.sinDeg(theta);
		} else if (theta == QUAD_I_START) {
			x = Constants.SPAWN_CIRCLE_RADIUS;
			y = 0;
		} else if (theta == QUAD_II_START) {
			x = 0;
			y = -Constants.SPAWN_CIRCLE_RADIUS;
		} else if (theta == QUAD_III_START) {
			x = -Constants.SPAWN_CIRCLE_RADIUS;
			y = 0;
		} else if (theta == QUAD_IV_START) {
			x = 0;
			y = -Constants.SPAWN_CIRCLE_RADIUS;
		} else if (theta == QUAD_IV_END) {
			x = Constants.SPAWN_CIRCLE_RADIUS;
			y = 0;
		}

		Vector2 randomLocation = new Vector2(x, y);

		return randomLocation;
	}
}



















