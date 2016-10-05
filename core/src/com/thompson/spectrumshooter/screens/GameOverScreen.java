package com.thompson.spectrumshooter.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.thompson.spectrumshooter.SpectrumShooter;
import com.thompson.spectrumshooter.assets.Assets;
import com.thompson.spectrumshooter.color.ColorWheel;
import com.thompson.spectrumshooter.sound.AudioManager;
import com.thompson.spectrumshooter.util.Constants;

/**
 * GameOverScreen.java
 * Screen to be placed after the game ends
 *
 * @author Zachary Thompson
 */
public class GameOverScreen implements Screen
{
	private OrthographicCamera camera;
	private Viewport viewport;
	private SpriteBatch batch;
	private Stage stage;
	private LabelStyle titleLabelStyle;
	private TextButton playAgain;
	private TextButton mainMenu;

	private ColorWheel colorWheel;
	private int currentColorCode;

	/**
	 * Constructor, builds the screen
	 * @param numberKilled the number of enemies killed in a given time span
	 */
	public GameOverScreen(int numberKilled)
	{
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Constants.GAME_WIDTH, Constants.GAME_HEIGHT);

		viewport = new FitViewport(Constants.GAME_WIDTH, Constants.GAME_HEIGHT);
		batch = new SpriteBatch();

		stage = new Stage(viewport, batch);
		Gdx.input.setInputProcessor(stage);

		colorWheel = new ColorWheel();
		currentColorCode = colorWheel.incrementColorCode(currentColorCode);

		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = Constants.GAME_HEIGHT/6;
		parameter.borderWidth = 3;
		parameter.borderColor = Color.BLACK;
		parameter.shadowOffsetX = 2;
		parameter.shadowOffsetY = 2;

		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.local("GosmickSansBold.ttf"));
		titleLabelStyle = new LabelStyle(generator.generateFont(parameter), new Color(0.255f, 0.412f, 0.882f, 1f));

		Table mainTable = new Table();
		mainTable.setFillParent(true);
		mainTable.add(new Label("GAME OVER", titleLabelStyle)).row();

		mainTable.add(new Label( numberKilled + " Kills", titleLabelStyle)).row();

		parameter.size = Constants.GAME_HEIGHT/20;
		TextButtonStyle style = new TextButtonStyle();
		style.font = generator.generateFont(parameter);
		style.fontColor = Color.WHITE;
		style.downFontColor = Color.BLACK;

		playAgain = new TextButton("Play Again", style);
		playAgain.addListener(new ChangeListener()
		{
			@Override
			public void changed(ChangeEvent event, Actor actor)
			{
				SpectrumShooter.getInstance().setScreen(new MainScreen());
				dispose();
			}
		});

		mainMenu = new TextButton("Main Menu", style);
		mainMenu.addListener(new ChangeListener()
		{
			@Override
			public void changed(ChangeEvent event, Actor actor)
			{
				AudioManager.instance.stop(Assets.instance.gameMusic);
				AudioManager.instance.play(Assets.instance.mainMenuMusic);
				SpectrumShooter.getInstance().setScreen(new MenuScreen());
				dispose();
			}
		});

		mainTable.add(playAgain).row();
		mainTable.add(mainMenu);

		stage.addActor(mainTable);
	}

	/**
	 * Renders the screen
	 */
	@Override
	public void render(float delta)
	{
		updateColors();
		camera.update();
		stage.draw();
	}

	/**
	 * Updates the background color
	 */
	private void updateColors()
	{
		Color newColor = new Color(
				colorWheel.getRedValue(currentColorCode)/255.0f,
				colorWheel.getGreenValue(currentColorCode)/255.0f,
				colorWheel.getBlueValue(currentColorCode)/255.0f,
				1);;
		Gdx.gl.glClearColor(newColor.r, newColor.g, newColor.b, newColor.a);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		titleLabelStyle.fontColor = new Color(1 - newColor.r,1 - newColor.g,1 - newColor.b, 1);
		currentColorCode = colorWheel.incrementColorCode(currentColorCode);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
