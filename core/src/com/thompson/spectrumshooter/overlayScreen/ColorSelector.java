package com.thompson.spectrumshooter.overlayScreen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * ColorSelector.java
 * Abstract class for a color selector
 * @author Zach
 *
 */
public abstract class ColorSelector extends OverlayingScreen
{
	Color color = Color.RED;
	
	public ColorSelector(Stage stage, float xSize, float ySize) 
	{
	super(stage, xSize, ySize);
	
	}

	public abstract void changeColor();
	
	public abstract Color selectColor();
}
