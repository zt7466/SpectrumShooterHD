package com.thompson.spectrumshooter.gameobject;

import com.badlogic.gdx.graphics.Color;
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
import com.thompson.spectrumshooter.util.Constants;


/**
 * GameObjectFactory.java
 * 
 * A collection of methods that create different GameObjects.
 *
 * @author Christopher Boyer
 */
public class GameObjectFactory
{

	private static final int PIXMAP_RADIUS = 150;

	private static final short CATEGORY_ENEMY = 0x0002;
	private static final short CATEGORY_HERO_PROJECTILE = 0x0001;

	private static final int OUTWARDS = 1;
	private static final int INWARDS = -1;
	private static final int STATIONARY = 1;

	private static final float SIZE_ENEMY_MIN = 0.25f;
	private static final float SIZE__ENEMY_MAX = 0.75f;
	private static final float SIZE_HERO = 0.5f;
	private static final float SIZE_PROJECTILE = 0.2f;

	private static final float HEALTH_ENEMY = 6.0f;
	private static final float HEALTH_HERO = 10.0f;
	private static final float HEALTH_PROJECTILE = 1.0f;

	private static final float DAMAGE_ENEMY = 1.0f;
	private static final float DAMAGE_HERO = 0.0f;
	private static final float DAMAGE_PROJECTILE = 3.0f;

	/**
	 * Make a new Enemy that moves linearly from it's spawn location towards
	 * the center of the screen.
	 * @param world		the world where the Enemy exists
	 * @return			the new basic Enemy
	 */
	public Enemy makeBasicEnemy(World world)
	{
		Color color = createCreatableColor();

		Texture texture = new Texture(createPixmap(color));

		float spriteSize =  MathUtils.random(SIZE_ENEMY_MIN, SIZE__ENEMY_MAX);


		Fixture fixture = createDynamicFixture(world,
				createRandomSpawnLocation(Constants.ENEMY_RADIUS), spriteSize, INWARDS, 0.1f);
		Filter filter = new Filter();
		filter.categoryBits = CATEGORY_ENEMY;
		filter.maskBits = ~CATEGORY_ENEMY;
		fixture.setFilterData(filter);

		Enemy enemy = new Enemy(color, HEALTH_ENEMY, fixture, texture,spriteSize, DAMAGE_ENEMY);
		enemy.setOrigin(enemy.getWidth() / 2.0f, enemy.getHeight() / 2.0f);

		return enemy;
	}

	/**
	 * Create a new Hero that remains in the center of the game.
	 * @param world		the World in which the Hero will exist
	 * @return			a new Hero
	 */
	public Hero makeHero(World world)
	{
		Color color = new Color(Color.WHITE);
		Texture texture = new Texture(createPixmap(color));
		float spriteSize = SIZE_HERO;
		Fixture fixture = createStaticFixture(world, new Vector2(0, 0), spriteSize, STATIONARY, DAMAGE_HERO);
		Filter filter = new Filter();
		filter.categoryBits = CATEGORY_HERO_PROJECTILE;
		filter.maskBits = ~CATEGORY_HERO_PROJECTILE;
		fixture.setFilterData(filter);

		Hero hero = new Hero(color, HEALTH_HERO, fixture, texture, spriteSize, 0f);

		hero.setOrigin(hero.getWidth() / 2.0f, hero.getHeight() / 2.0f);

		// hero does this when enemy/projectile don't becuase the enemies call their update
		// method which does this.
		hero.setPosition(fixture.getBody().getPosition().x - spriteSize/2.0f,
 		  		 		 fixture.getBody().getPosition().y - spriteSize/2.0f);

		return hero;
	}

	/**
	 * Make a projectile that moves towards the position of the mouse.
	 * @param world		the World where the projectile will exist
	 * @param mouseX	the X coordinate of the mouse
	 * @param mouseY	the Y coordinate of the mouse
	 * @return			a new Projectile
	 */
	public Projectile makeProjectile(World world, Color color, float mouseX, float mouseY)
	{
		Texture texture = new Texture(createPixmap(color));
		float spriteSize = SIZE_PROJECTILE;
		Fixture fixture = createDynamicFixture(world, createProjectilePosition(Constants.PROJECTILE_REDIUS,
											   mouseX, mouseY), spriteSize, OUTWARDS, DAMAGE_PROJECTILE);
		Filter filter = new Filter();
		filter.categoryBits = CATEGORY_HERO_PROJECTILE;
		filter.maskBits = ~CATEGORY_HERO_PROJECTILE;
		fixture.setFilterData(filter);

		Projectile projectile = new Projectile(color, HEALTH_PROJECTILE, fixture, texture, spriteSize, 1f);
		projectile.setOrigin(projectile.getWidth() / 2.0f, projectile.getHeight() / 2.0f);

		return projectile;
	}


	/**
	 * Create the pixmaps used for our GameObjects.
	 * @param colorCode		the color code from the color wheel of this Pixmap
	 * @return 				a new pixmap
	 */
	private Pixmap createPixmap(Color color)
	{
		Pixmap pixmap = new Pixmap(300, 300, Format.RGBA8888);
		pixmap.setColor(color.r, color.g, color.b, 1);
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
	private Fixture createDynamicFixture(World world, Vector2 spawnPosition, float spriteSize, int direction, float speed)
	{
		BodyDef bodyDef = new BodyDef();
		// Dynamic implies that things can move.
		bodyDef.type = BodyType.DynamicBody;

		return createFixture(world, spawnPosition, spriteSize, bodyDef, direction, speed);
	}

	/**
	 * Creat a new static Fixture at the given spawn position and sprite size.
	 * @param world				the world the fixture will exist in
	 * @param spawnPosition		the location the fixture will spawn at
	 * @param spriteSize		the size of the sprite corresponding to the fixture
	 * @return					a new static Fixture
	 */
	private Fixture createStaticFixture(World world, Vector2 spawnPosition, float spriteSize, int direction, float speed)
	{
		BodyDef bodyDef = new BodyDef();
		// Static implies that things cannot move
		bodyDef.type = BodyType.StaticBody;

		return createFixture(world, spawnPosition, spriteSize, bodyDef, direction, speed);
	}

	/**
	 * Create a new Fixture for a game object based on the given body definition.
	 * @param world		game world where the Enemy exists
	 * @return			new Fixture
	 */
	private Fixture createFixture(World world, Vector2 spawnPosition, float spriteSize, BodyDef bodyDef, int direction, float speed)
	{
		bodyDef.position.set(spawnPosition);

		Body body = world.createBody(bodyDef);
		body.setLinearVelocity((direction *spawnPosition.x) * speed, (direction * spawnPosition.y * speed));

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
	private Vector2 createRandomSpawnLocation(float spawnRadius)
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
	private Vector2 createProjectilePosition(float spawnRadius, float mouseX, float mouseY)
	{
		float theta = MathUtils.radiansToDegrees * MathUtils.atan2(mouseY, mouseX);
		if (mouseY < 0)
		{
			theta = 180 + (180 - (theta * -1));
		}
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
		return new Vector2(spawnRadius * MathUtils.cosDeg(theta),
						   spawnRadius * MathUtils.sinDeg(theta));
	}
	
	/**
	 * Create a color that is reachable by the basic color selector.
	 * @return a random reachable Color
	 */
	private Color createCreatableColor()
	{
		float maxValue = Constants.MAX_COMBINATION / 100;
		
		float redValue = (float)Math.random();
		float greenValue;
		float blueValue;
		maxValue = maxValue - redValue;
		
		if(maxValue > 1)
		{
			greenValue = (float)Math.random();
		}
		else
		{
			greenValue = (float)Math.random() * maxValue;
		}
		maxValue = maxValue - greenValue;
		
		if(maxValue > 1)
		{
			blueValue = (float)Math.random();
		}
		else
		{
			blueValue = maxValue;
		}
		
		return new Color(redValue, greenValue, blueValue, 1);
	}

}



















