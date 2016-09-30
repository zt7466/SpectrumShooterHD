package com.thompson.spectrumshooter.spawning;

import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.thompson.spectrumshooter.assets.Assets;
import com.thompson.spectrumshooter.gameobject.GameObject;
import com.thompson.spectrumshooter.gameobject.GameObjectFactory;
import com.thompson.spectrumshooter.sound.AudioManager;

/**
 * A normal spawning algorithm where every time an enemy dies, a new enemy is
 * is spawned. Additionally every given so often another enemy spawns.
 *
 * @author Christopher Boyer
 */
public class LinearEnemySpawn implements EnemySpawning
{
	// the number of seconds between spawning a new enemy
	private float spawnTime;
	private int previousEnemyCount;
	private GameObjectFactory gameObjectFactory;
	private float previousTime;

	private int deadEnemies;

	/**
	 * Construct the spawning algorithm with the default spawn time of ten
	 * seconds.
	 */
	public LinearEnemySpawn()
	{
		this(10);
		deadEnemies = 0;
	}

	public LinearEnemySpawn(float spawnTime)
	{
		this.spawnTime = spawnTime;
		this.gameObjectFactory = new GameObjectFactory();
		this.previousTime = 0f;
	}

	@Override
	public Array<GameObject> update(Array<GameObject> enemies, World world, float deltaTime)
	{

		previousTime = previousTime + deltaTime;

		if (previousTime > spawnTime)
		{
			enemies.add(gameObjectFactory.makeBasicEnemy(world));
			previousTime = previousTime - spawnTime;
		}

		if (enemies.size < previousEnemyCount)
		{
			enemies.add(gameObjectFactory.makeBasicEnemy(world));
		}

		if (enemies.size == 0)
		{
			enemies.add(gameObjectFactory.makeBasicEnemy(world));
		}

		for (GameObject enemy : enemies)
		{
			if (!enemy.isAlive)
			{
				deadEnemies++;
				world.destroyBody(enemy.getFixture().getBody());
				enemy.dispose();
				// false indicates using .equals; true indicated using ==
				AudioManager.instance.play(Assets.instance.enemyDeathSound);
				enemies.removeValue(enemy, false);
			}
		}

		previousEnemyCount = enemies.size;

		return enemies;
	}

}
