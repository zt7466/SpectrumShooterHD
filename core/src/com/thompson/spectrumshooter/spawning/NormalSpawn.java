package com.thompson.spectrumshooter.spawning;

import com.badlogic.gdx.utils.Array;
import com.thompson.spectrumshooter.enemy.Enemy;

/**
 * A normal spawning algorithm where every time an enemy dies, a new enemy is
 * is spawned. Additionally every given so often another enemy spawns.
 *
 * @author Christopher Boyer
 */
public class NormalSpawn implements SpawningAlgorithim
{
	// the number of seconds between spawning a new enemy
	private int spawnTime;
	private int previousEnemyCount;

	/**
	 * Construct the spawning algorithm with the default spawn time of ten
	 * seconds.
	 */
	public NormalSpawn()
	{
		spawnTime = 10;
	}

	@Override
	public Array<Enemy> update(Array<Enemy> enemies, float deltaTime)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
