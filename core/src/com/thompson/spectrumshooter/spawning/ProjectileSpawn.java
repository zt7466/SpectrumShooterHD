package com.thompson.spectrumshooter.spawning;

import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.thompson.spectrumshooter.gameobject.GameObject;

public interface ProjectileSpawn
{
	public Array<GameObject> update(Array<GameObject> group, World world, float mouseX, float mouseY);
}
