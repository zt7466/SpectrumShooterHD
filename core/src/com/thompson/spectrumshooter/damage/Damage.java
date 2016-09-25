package com.thompson.spectrumshooter.damage;

import com.badlogic.gdx.graphics.Color;

public interface Damage
{
	/**
	 * Calculate the damage done when the first target is of the given color and
	 * target 2 is of the given color with the given maximum possible damage
	 * @param damagePower		the maximum possible damage of the instigator
	 * @param instigatorColor	the color of the instigator
	 * @param targetColor		the color of the target
	 * @return					the damage done
	 */
	public float calculateDamge(float damagePower, Color instigatorColor, Color targetColor);
}
