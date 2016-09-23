package com.thompson.spectrumshooter.spawning;

import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.thompson.spectrumshooter.gameobject.GameObject;

/**
 * The way in which Enemies spawn into the world
 * @author Christopher Boyer
 */
public interface SpawningAlgorithm
{
	/**
	 * Update the given array with the given implementation of the algorithm.
	 * @param enemies		the current array of enemies
	 * @param deltaTime		the amount of time since the last update
	 * @return				the new array of enemies
	 */
	public Array<GameObject> update(Array<GameObject> group, World world, float deltaTime);
}
