package com.thompson.spectrumshooter.overlayScreen;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.thompson.spectrumshooter.util.Constants;

public class InstructionScreen extends OverlayingScreen{

	private boolean visibleStatus = false;
	public InstructionScreen(TextButtonStyle textStyle) 
	{
		super(550, 450);
		
		LabelStyle labelStyle = new LabelStyle(textStyle.font, Color.WHITE);
		inerdTable.add(new Label("Instructions", labelStyle)).padBottom(13f).row();
		
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = Constants.GAME_HEIGHT/36;
		parameter.borderWidth = 1;
		parameter.borderColor = Color.BLACK;

		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.local("GosmickSansBold.ttf"));
		
		LabelStyle instructionStyle = new LabelStyle(generator.generateFont(parameter), Color.WHITE);
		
		inerdTable.add(new Label("Shoot the incoming enemies using the mouse", instructionStyle)).padBottom(7).row();
		inerdTable.add(new Label("Mix your colors to change your projectile's color", instructionStyle)).padBottom(7).row();
		inerdTable.add(new Label("The closer your projectiles color is to the enemy's,", instructionStyle)).padBottom(4).row();
		inerdTable.add(new Label("the more damage you will cause", instructionStyle)).padBottom(7).row();
		inerdTable.add(new Label("Press/Hold 'A' to increase your RED value", instructionStyle)).padBottom(7).row();
		inerdTable.add(new Label("Press/Hold 'S' to increase your GREEN value", instructionStyle)).padBottom(7).row();
		inerdTable.add(new Label("Press/Hold 'D' to increase your BLUE value", instructionStyle)).padBottom(7).row();
		inerdTable.add(new Label("(Give it a try!)", instructionStyle)).padBottom(7).row();
		inerdTable.add(new Label("Survive for as long as possible!", instructionStyle)).row();
		
		TextButton closeButton = new TextButton("Close", textStyle);
		closeButton.addListener(new ChangeListener()
		{
			@Override
			public void changed(ChangeEvent event, Actor actor)
			{
				toggleVisibility();
				visibleStatus = false;
			}
		});
		
		inerdTable.add(closeButton).bottom();
		
		changeColor(Color.GRAY);
		this.toggleVisibility();
	}
	
	/**
	 * Change against regular Overlay screen so this does not change
	 */
	@Override
	public void changeColor(Color c)
	{
		for(int i = 0; i < sprites.size(); i++)
		{
			sprites.get(i).setColor(new Color(c.r,c.g, c.b, c.a));
		}
	}

	public void makeVisibile()
	{
		toggleVisibility();
		visibleStatus = true;
	}
	
	public boolean getVisbleStatus()
	{
		return visibleStatus;
	}
}
