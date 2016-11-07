package com.thompson.spectrumshooter.spawning;

import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.thompson.spectrumshooter.assets.Assets;
import com.thompson.spectrumshooter.gameobject.GameObject;
import com.thompson.spectrumshooter.gameobject.GameObjectFactory;
import com.thompson.spectrumshooter.sound.AudioManager;

/**
 * LinearEnemySpawn.java
 *
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

	/**
	 * Construct the spawning algorithm with the default spawn time of ten
	 * seconds.
	 */
	public LinearEnemySpawn()
	{
		this(10);
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
		previousTime += deltaTime;

		// if it's the start of the game, then an initial enemy needs to spawned
		if (enemies.size == 0)
		{
			enemies.add(gameObjectFactory.makeBasicEnemy(world));
		}

		// if it's been enough time since the last enemy died, make another
		if (previousTime > spawnTime)
		{
			enemies.add(gameObjectFactory.makeBasicEnemy(world));
			previousTime = 0f;
		}

		// all the dead enemies need to be removed, their bodies destroyed, and a bell tolled
		// commemorating their death; a new enemy is added
		for (GameObject enemy : enemies)
		{
			if (!enemy.isAlive)
			{
				// add a new enemy
				enemies.add(gameObjectFactory.makeBasicEnemy(world));

				// remove the body
				world.destroyBody(enemy.getFixture().getBody());
				enemy.dispose();
				enemies.removeValue(enemy, false); // false indicates .equal; true indicated ==
				AudioManager.instance.play(Assets.instance.sound.enemyDeathSound);
			}
		}



		return enemies;
	}

}
