package com.thompson.spectrumshooter.gameobject;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Fixture;

/**
 * Implementation of the GameObject: Projectile.
 * @author Christopher Boyer
 */
public class Projectile extends GameObject
{

	public Projectile(Color color, float health, Fixture fixture, Texture texture, float spriteSize, float damage)
	{
		super(color, health, fixture, texture, spriteSize, damage);
		fixture.setUserData(this);
		this.update();
	}

	/**
	 * Update the location of the porjectiles's position to match that of its fixture.
	 */
	@Override
	public void update()
	{
		this.setPosition(fixture.getBody().getPosition().x - spriteSize/2.0f,
 		  		 		 fixture.getBody().getPosition().y - spriteSize/2.0f);
		
		// TODO set to is dead if the projectile goes off the screen
	}

	@Override
	public void takeHit(float damage) {}

}
