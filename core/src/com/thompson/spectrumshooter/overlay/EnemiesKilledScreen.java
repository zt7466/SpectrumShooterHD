package com.thompson.spectrumshooter.overlay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.thompson.spectrumshooter.util.Constants;

public class EnemiesKilledScreen extends OverlayingScreen 
{
	private Label label;
	public EnemiesKilledScreen() 
	{
		super(150, 50);
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = Constants.GAME_HEIGHT/24;
		parameter.borderWidth = 3;
		parameter.borderColor = Color.BLACK;
		parameter.shadowOffsetX = 2;
		parameter.shadowOffsetY = 2;

		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.local("GosmickSansBold.ttf"));
		
		LabelStyle titleLabelStyle = new LabelStyle(generator.generateFont(parameter), Color.WHITE);
		label = new Label("0 Killed", titleLabelStyle);
		inerdTable.add(label);
	}

	public void setText(int value)
	{
		label.setText(value + " Killed");
	}
}
