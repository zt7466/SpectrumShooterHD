package com.thompson.spectrumshooter.spawning;

import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.thompson.spectrumshooter.gameobject.GameObject;
import com.thompson.spectrumshooter.gameobject.GameObjectFactory;

/**
 * A normal spawning algorithm where every time an enemy dies, a new enemy is
 * is spawned. Additionally every given so often another enemy spawns.
 *
 * @author Christopher Boyer
 */
public class NormalSpawn implements SpawningAlgorithm
{
	// the number of seconds between spawning a new enemy
	private int spawnTime;
	private int previousEnemyCount;
	private GameObjectFactory gameObjectFactory;
	private float previousTime;

	/**
	 * Construct the spawning algorithm with the default spawn time of ten
	 * seconds.
	 */
	public NormalSpawn()
	{
		this(10);
	}

	public NormalSpawn(int spawnTime)
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
				world.destroyBody(enemy.getFixture().getBody());
				enemy.dispose();
				// false indicates using .equals; true indicated using ==
				enemies.removeValue(enemy, false);
			}
		}
		
		previousEnemyCount = enemies.size;

		return enemies;
	}

}
