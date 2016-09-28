package com.thompson.spectrumshooter.spawning;

import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.thompson.spectrumshooter.gameobject.GameObject;
import com.thompson.spectrumshooter.gameobject.GameObjectFactory;

public class NormalProjectileSpawn implements ProjectileSpawn
{
	private GameObjectFactory gameObjectFactory;

	public NormalProjectileSpawn()
	{
		this.gameObjectFactory = new GameObjectFactory();
	}

	@Override
	public Array<GameObject> update(Array<GameObject> group, boolean spawn, World world, float mouseX, float mouseY)
	{
		
		if (spawn)
		{
			group.add(gameObjectFactory.makeProjectile(world, mouseX, mouseY));
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
