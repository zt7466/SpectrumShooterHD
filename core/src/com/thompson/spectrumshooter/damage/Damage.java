package com.thompson.spectrumshooter.damage;

import com.badlogic.gdx.graphics.Color;

/**
 * Damage.java
 * 
 * Interface for implementing a Damage algorithm between an instigator and a target where the
 * target is of a certain color, and the instigator is of a certain color and damage power.
 * 
 * @author Christopher Boyer
 */
public interface Damage
{
	/**
	 * Calculate the damage done when the instigator has the given power and the given color,
	 * and the target has the given color.
	 * @param damagePower		the base damage of the instigator
	 * @param instigatorColor	the color of the instigator
	 * @param targetColor		the color of the target
	 * @return					the damage done
	 */
	public float calculateDamge(float damagePower, Color instigatorColor, Color targetColor);
}
