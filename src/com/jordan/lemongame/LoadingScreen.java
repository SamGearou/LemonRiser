package com.jordan.lemongame;

import com.jordan.framework.Game;
import com.jordan.framework.Graphics;
import com.jordan.framework.Graphics.ImageFormat;
import com.jordan.framework.Screen;

//where all the images are loaded
public class LoadingScreen extends Screen {
	public LoadingScreen(Game game) {
		super(game);
	}

	@Override
	public void update(float deltaTime) {

        //makes sure the screen lasts 3 seconds
        long startTime = System.currentTimeMillis();

        Assets.loadImages(game);

        long t = (System.currentTimeMillis() - startTime);
        try {
            if (t < 3000)
                Thread.sleep(3000 - t);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

		game.setScreen(new GameScreen(game));
	}

	@Override
	public void paint(float deltaTime) {
		Graphics g = game.getGraphics();
		g.drawImage(Assets.splash, 0, 0);
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