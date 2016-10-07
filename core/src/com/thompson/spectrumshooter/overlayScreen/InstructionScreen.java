package com.thompson.spectrumshooter.overlayScreen;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class InstructionScreen extends OverlayingScreen{

	private boolean visibleStatus = false;
	public InstructionScreen(TextButtonStyle textStyle) 
	{
		super(550, 350);
		
		LabelStyle labelStyle = new LabelStyle(textStyle.font, Color.WHITE);
		inerdTable.add(new Label("Instructions", labelStyle)).padBottom(13f).row();
		
		LabelStyle instructionStyle = new LabelStyle(new BitmapFont(), Color.WHITE);
		
		inerdTable.add(new Label("Shoot the incoming enemies using the mouse", instructionStyle)).padBottom(7).row();
		inerdTable.add(new Label("Change your color to change your shoot's color", instructionStyle)).padBottom(7).row();
		inerdTable.add(new Label("The closer your shot's color is to the targets color the more damage you will cause", instructionStyle)).padBottom(7).row();
		inerdTable.add(new Label("Press 'A' to increase your RED value", instructionStyle)).padBottom(7).row();
		inerdTable.add(new Label("Press 'S' to increase your GREEN value", instructionStyle)).padBottom(7).row();
		inerdTable.add(new Label("Press 'D' to increase your BLUE value", instructionStyle)).padBottom(7).row();
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
