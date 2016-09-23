package com.thompson.spectrumshooter.util;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.thompson.spectrumshooter.gameobject.Enemy;
import com.thompson.spectrumshooter.gameobject.Hero;

public class CollisionThing implements ContactListener
{

	@Override
	public void beginContact(Contact contact) {}

	@Override
	public void endContact(Contact contact) {}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold)
	{
		Fixture hero = (contact.getFixtureA().getUserData().getClass() == Hero.class) ? 
				contact.getFixtureA() : contact.getFixtureB();
		Fixture enemy = (contact.getFixtureA().getUserData().getClass() == Enemy.class) ?
				contact.getFixtureA() : contact.getFixtureB();
				
		if (hero.getUserData().getClass() == Hero.class && enemy.getUserData().getClass() == Enemy.class)
		{
			((Enemy) enemy.getUserData()).isAlive = false;
		}
			
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {}
	
}
