package com.thompson.spectrumshooter.overlayScreen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

/**
 * ColorSelector.java
 * Abstract class for a color selector
 * @author Zach
 *
 */
public abstract class ColorSelector extends OverlayingScreen
{
	protected Color color = Color.RED;
	
	public ColorSelector(float xSize, float ySize) 
	{
	super(xSize, ySize);
	}

	public abstract void changeColor();
	
	public abstract Color selectColor();
}
