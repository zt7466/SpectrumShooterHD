package com.thompson.spectrumshooter.gameobject;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
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

	private final short CATEGORY_ENEMY = 0x0002;
	private final short CATEGORY_HERO_PROJECTILE = 0x0003;

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

		Fixture fixture = createDynamicFixture(world, generateRandomSpawnLocation(Constants.ENEMY_SPAWN_CIRCLE_RADIUS), spriteSize);
		Filter filter = new Filter();
		filter.categoryBits = CATEGORY_ENEMY;
		filter.maskBits = ~CATEGORY_ENEMY;
		fixture.setFilterData(filter);

		Enemy enemy = new Enemy(colorCode, 3, fixture, texture,spriteSize);

		enemy.setOrigin(enemy.getWidth() / 2.0f, enemy.getHeight() / 2.0f);

		return enemy;
	}

	public Hero makeHero(World world)
	{
		int colorCode = colorWheel.random();

		Texture texture = new Texture(createPixmap(colorCode));
		float spriteSize = 0.75f;
		Fixture fixture = createStaticFixture(world, new Vector2(0, 0), spriteSize);
//		Filter filter = new Filter();
//		filter.categoryBits = CATEGORY_HERO_PROJECTILE;
//		filter.maskBits = ~CATEGORY_HERO_PROJECTILE;
//		fixture.setFilterData(filter);

		Hero hero = new Hero(colorCode, 15, fixture, texture, 0.0f);

		hero.setPosition(fixture.getBody().getPosition().x - 3,
 		  		 		 fixture.getBody().getPosition().y - 3);

		hero.setOrigin(hero.getWidth() / 2.0f, hero.getHeight() / 2.0f);

		hero.setSize(spriteSize, spriteSize);

		// hero does this when enemy/projectile don't becuase the enemies call their update
		// method which does this.
		hero.setPosition(fixture.getBody().getPosition().x - spriteSize/2.0f,
 		  		 		 fixture.getBody().getPosition().y - spriteSize/2.0f);

		return hero;
	}

	public Projectile makeProjectile(World world, float mouseX, float mouseY)
	{
		int colorCode = colorWheel.random();
		Texture texture = new Texture(createPixmap(colorCode));
		float spriteSize = 0.2f;
		Fixture fixture = createDynamicFixture(world, generateProjectilePosition(Constants.PROJECTILE_SPAWN_CIRCLE_REDIUS, mouseX, mouseY), spriteSize);
//		Filter filter = new Filter();
//		filter.categoryBits = CATEGORY_HERO_PROJECTILE;
//		filter.maskBits = ~CATEGORY_HERO_PROJECTILE;
//		fixture.setFilterData(filter);

		Projectile projectile = new Projectile(colorCode, 1, fixture, texture, spriteSize);

		projectile.setOrigin(projectile.getWidth() / 2.0f, projectile.getHeight() / 2.0f);

		return projectile;
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
	 * Create a Dynamic Fixture at the given position and size.
	 * @param world				the world the fixture will exist in
	 * @param spawnPosition		the location the fixture will spawn at
	 * @param spriteSize		the size of the sprite corresponding to the fixture
	 * @return					a new dynaic Fixture
	 */
	private Fixture createDynamicFixture(World world, Vector2 spawnPosition, float spriteSize)
	{
		BodyDef bodyDef = new BodyDef();
		// Dynamic implies that things can move.
		bodyDef.type = BodyType.DynamicBody;

		return createFixture(world, spawnPosition, spriteSize, bodyDef);
	}

	/**
	 * Creat a new static Fixture at the given spawn position and sprite size.
	 * @param world				the world the fixture will exist in
	 * @param spawnPosition		the location the fixture will spawn at
	 * @param spriteSize		the size of the sprite corresponding to the fixture
	 * @return					a new static Fixture
	 */
	private Fixture createStaticFixture(World world, Vector2 spawnPosition, float spriteSize)
	{
		BodyDef bodyDef = new BodyDef();
		// Static implies that things cannot move
		bodyDef.type = BodyType.StaticBody;

		return createFixture(world, spawnPosition, spriteSize, bodyDef);
	}

	/**
	 * Create a new Fixture for a game object based on the given body definition.
	 * @param world		game world where the Enemy exists
	 * @return			new Fixture
	 */
	private Fixture createFixture(World world, Vector2 spawnPosition, float spriteSize, BodyDef bodyDef)
	{
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
	private Vector2 generateRandomSpawnLocation(float spawnRadius)
	{
		float theta = MathUtils.random(0f, 360.0f);

		Vector2 randomLocation = createPosition(spawnRadius, theta);

		return randomLocation;
	}

	/**
	 * Create a position for a projectile.
	 * @param spawnRadius	the distance the projectile should be from the origin
	 * @param mouseX		the x location of the mouse when pressed
	 * @param mouseY		the y location of the mouse when pressed
	 * @return				the Vector2 location of the projectile
	 */
	private Vector2 generateProjectilePosition(float spawnRadius, float mouseX, float mouseY)
	{
		// get the degree at which the project is spawning based on where the mouse was clicked.
		float theta = MathUtils.radiansToDegrees * MathUtils.atan2(mouseX, mouseY);

		return createPosition(spawnRadius, theta);
	}

	/**
	 * Create a Vector2 position at the given radius and given degree around the origin.
	 * @param spawnRadius	the distance from the center the position will be at
	 * @param theta			the rotation around the origin the position will be at
	 * @return				a Vector2 for the given position
	 */
	private Vector2 createPosition(float spawnRadius, float theta)
	{
		float x = 0;
		float y = 0;

		final int QUAD_I_START = 0;
		final int QUAD_II_START = 90;
		final int QUAD_III_START = 180;
		final int QUAD_IV_START = 270;
		final int QUAD_IV_END = 360;

		if (theta > QUAD_I_START && theta < QUAD_II_START) {
			x =  spawnRadius * MathUtils.cosDeg(theta);
			y =  spawnRadius * MathUtils.sinDeg(theta);
		} else if (theta > QUAD_II_START && theta < QUAD_III_START) {
			x = -spawnRadius * MathUtils.cosDeg(theta);
			y =  spawnRadius * MathUtils.sinDeg(theta);
		} else if (theta > QUAD_III_START && theta < QUAD_IV_START) {
			x = -spawnRadius * MathUtils.cosDeg(theta);
			y =  spawnRadius * MathUtils.sinDeg(theta);
		} else if (theta > QUAD_IV_START && theta < QUAD_IV_END) {
			x = -spawnRadius * MathUtils.cosDeg(theta);
			y =  spawnRadius * MathUtils.sinDeg(theta);
		} else if (theta == QUAD_I_START) {
			x = spawnRadius;
			y = 0;
		} else if (theta == QUAD_II_START) {
			x = 0;
			y = -spawnRadius;
		} else if (theta == QUAD_III_START) {
			x = -spawnRadius;
			y = 0;
		} else if (theta == QUAD_IV_START) {
			x = 0;
			y = -spawnRadius;
		} else if (theta == QUAD_IV_END) {
			x = spawnRadius;
			y = 0;
		}

		return new Vector2(x, y);
	}

}



















