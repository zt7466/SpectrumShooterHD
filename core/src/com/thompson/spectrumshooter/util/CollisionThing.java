package com.thompson.spectrumshooter.util;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.thompson.spectrumshooter.gameobject.Enemy;
import com.thompson.spectrumshooter.gameobject.Hero;
import com.thompson.spectrumshooter.gameobject.Projectile;


public class CollisionThing implements ContactListener
{

	@Override
	public void beginContact(Contact contact) {}

	@Override
	public void endContact(Contact contact) {}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold)
	{
		this.enemyHeroResolve(contact);
		this.projectileEnemyResolve(contact);
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {}
	
	private void projectileEnemyResolve(Contact contact)
	{
		if (contact.getFixtureA().getUserData().getClass() == Projectile.class &&
			contact.getFixtureB().getUserData().getClass() == Enemy.class)
		{
			this.projectileEnemyCollision(contact.getFixtureB(), contact.getFixtureA());
		}
		else if (contact.getFixtureA().getUserData().getClass() == Enemy.class &&
				 contact.getFixtureB().getUserData().getClass() == Projectile.class)
		{
			this.projectileEnemyCollision(contact.getFixtureA(), contact.getFixtureB());
		}
	}
	
	private void projectileEnemyCollision(Fixture enemy, Fixture projectile)
	{
		((Enemy) enemy.getUserData()).takeHit();
		((Projectile) projectile.getUserData()).isAlive = false;
	}
	
	
	/**
	 * Determine if the collision is the result of a Hero/Enemy collision,
	 * and take the appropriate action if it is.
	 * @param contact	the instacne of contact
	 */
	private void enemyHeroResolve(Contact contact)
	{
		
		if (contact.getFixtureA().getUserData().getClass() == Hero.class &&
			contact.getFixtureB().getUserData().getClass() == Enemy.class)
		{
			this.enemyHeroCollision(contact.getFixtureB(), contact.getFixtureA());
		}
		else if (contact.getFixtureA().getUserData().getClass() == Enemy.class &&
				 contact.getFixtureB().getUserData().getClass() == Hero.class)
		{
			this.enemyHeroCollision(contact.getFixtureA(), contact.getFixtureB());
		}
	}
	
	/**
	 * Complete necisary action for an Enemy/Hero collision.
	 * @param enemy		the Enemy in the collision
	 * @param hero		the Hero in the collision
	 */
	private void enemyHeroCollision(Fixture enemy, Fixture hero)
	{
		((Enemy) enemy.getUserData()).isAlive = false;
	}
	

}
