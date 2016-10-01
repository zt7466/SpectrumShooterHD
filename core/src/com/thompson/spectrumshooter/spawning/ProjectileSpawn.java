package com.thompson.spectrumshooter.spawning;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.thompson.spectrumshooter.gameobject.GameObject;

/**
 * ProjectileSpawn.java
 * 
 * Defines an interface for spawing projectiles.
 * 
 * @author Christopher Boyer
 */
public interface ProjectileSpawn
{
	/**
	 * Update the given group of projectiles and game world based on the given conditions.
	 * @param group		the current group of projectiles
	 * @param spawn		boolean discerning if a new projectile will be spawned
	 * @param color		the current color of the color selector
	 * @param world		the world all GameObjects exist in
	 * @param mouseX	the X coordinate of the mouse
	 * @param mouseY	the Y coordinate of the mouse
	 * @return			the updated game group of projectiles and game world
	 */
	public Array<GameObject> update(Array<GameObject> group, boolean spawn, Color color, World world, float mouseX, float mouseY);
}
