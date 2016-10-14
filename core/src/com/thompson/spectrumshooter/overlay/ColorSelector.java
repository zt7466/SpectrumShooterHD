package com.thompson.spectrumshooter.overlayScreen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

/**
 * ColorSelector.java
 * 
 * Abstract class for a color selector
 * 
 * @author Zachary Thompson
 *
 */
public abstract class ColorSelector extends OverlayingScreen
{
	protected Color color = Color.RED;
	
	public ColorSelector(float xSize, float ySize) 
	{
	super(xSize, ySize);
	}

	/**
	 * Update the current color of the color selector.
	 */
	public abstract void updateColor();
	
	/**
	 * Get the current color of this ColorSelector.
	 * @return current color of this ColorSelector
	 */
	public abstract Color selectColor();
}
