package com.jordan.lemongame;

import com.jordan.framework.Game;
import com.jordan.framework.Graphics;
import com.jordan.framework.Screen;
import com.jordan.framework.Graphics.ImageFormat;

//it loads the splash to display on the actual loading screen
//it also loads the music
public class SplashLoadingScreen extends Screen {
	public SplashLoadingScreen(Game game) {
		super(game);
	}

	@Override
	public void update(float deltaTime) {

		Graphics g = game.getGraphics();
        Assets.loadSound(game);
		Assets.splash= g.newImage("splash.png", ImageFormat.RGB565);
        Assets.loadedMusic = true;
        Assets.theme.play();
		game.setScreen(new LoadingScreen(game));

    }

	@Override
	public void paint(float deltaTime) {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {

	}

	@Override
	public void backButton() {

	}
}