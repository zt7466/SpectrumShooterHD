package com.thompson.spectrumshooter.damage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;

/**
 * BasicDamage.java
 * 
 * Calculate damage by averaging the differences between the instigators RGB color values.
 * 
 * @author Christopher Boyer
 */
public class BasicDamage implements Damage
{
	
	public static final String TAG = BasicDamage.class.getName();

	/**
	 * Fine the square root of the sum of each color factor squared. Each color factor comprises
	 * of the instigator's value minus the target's value clamped at the instigator's max value.
	 */
	@Override
	public float calculateDamge(float damagePower, Color instigatorColor, Color targetColor)
	{
		float redValue = calculateColorValue(instigatorColor.r, targetColor.r);
		float greenValue = calculateColorValue(instigatorColor.g, targetColor.g);
		float blueValue = calculateColorValue(instigatorColor.b, targetColor.b);
		float damage = (float) (damagePower + 1 * (1 - Math.sqrt((redValue * redValue) +
					(greenValue * greenValue) +
					(blueValue * blueValue))));
		
		Gdx.app.debug(TAG, "damagePower: " + damagePower + 
				"|r: " + redValue + 
				"| g: " + greenValue + 
				"| b: " + blueValue + 
				"| damage: " + damage);

		return damage;
	}
	
	/**
	 * Subtract the target's color value from the instigator's color value if the target's
	 * color value is higher than the instigator's. Otherwise, return the instigator's color
	 * value.
	 * @param instigatorValue	the color value of the instigator
	 * @param targetValue		the color value of the target
	 * @return					the difference in color value
	 */
	private float calculateColorValue(float instigatorValue, float targetValue)
	{
		return (instigatorValue < targetValue) ? instigatorValue : instigatorValue - targetValue;
	}
}
