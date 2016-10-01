package com.thompson.spectrumshooter.spawning;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.thompson.spectrumshooter.gameobject.GameObject;
import com.thompson.spectrumshooter.gameobject.GameObjectFactory;

/**
 * NormalProjectileSpawn.java
 * 
 * Implements and algorithm for spawning new projectiles to match
 * the direction of the mouse.
 * 
 * @author Christopher Boyer
 *
 */
public class NormalProjectileSpawn implements ProjectileSpawn
{
	private GameObjectFactory gameObjectFactory;

	public NormalProjectileSpawn()
	{
		this.gameObjectFactory = new GameObjectFactory();
	}

	/**
	 * If it's time to spawn than and a new projecetile. If there are any dead projectiles
	 * in the group remove them.
	 */
	@Override
	public Array<GameObject> update(Array<GameObject> group, boolean spawn, Color color, World world, float mouseX, float mouseY)
	{
		
		if (spawn)
		{
			group.add(gameObjectFactory.makeProjectile(world, color, mouseX, mouseY));
		}
		
		for (GameObject projectile: group)
		{
			if (!projectile.isAlive)
			{
				world.destroyBody(projectile.getFixture().getBody());
				projectile.dispose();
				// false indicates using .equals; true indicated using ==
				group.removeValue(projectile, false);
			}
		}

		return group;
	}

}
