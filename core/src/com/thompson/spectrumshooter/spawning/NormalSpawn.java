package com.thompson.spectrumshooter.spawning;

import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.thompson.spectrumshooter.enemy.Enemy;
import com.thompson.spectrumshooter.enemy.EnemyFactory;

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
	private EnemyFactory enemyFactory;

	/**
	 * Construct the spawning algorithm with the default spawn time of ten
	 * seconds.
	 */
	public NormalSpawn()
	{
		this.spawnTime = 10;
		this.enemyFactory = new EnemyFactory();
	}

	@Override
	public Array<Enemy> update(Array<Enemy> enemies, World world)
	{
		if (enemies.size < previousEnemyCount)
		{
			enemies.add(enemyFactory.makeBasicEnemy(world));
		}

		if (enemies.size == 0)
		{
			enemies.add(enemyFactory.makeBasicEnemy(world));
		}
		
		for (Enemy enemy : enemies)
		{
			if (!enemy.isAlive)
			{
				world.destroyBody(enemy.getFixture().getBody());
				enemy.dispose();
				// false indicates using .equals; true indicated using ==
				enemies.removeValue(enemy, false);
			}
		}

		return enemies;
	}

}
