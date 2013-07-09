package com.jordan.lemongame;

import com.jordan.framework.Game;
import com.jordan.framework.Graphics;
import com.jordan.framework.Graphics.ImageFormat;
import com.jordan.framework.Screen;

public class LoadingScreen extends Screen {
	public LoadingScreen(Game game) {
		super(game);
	}

	@Override
	public void update(float deltaTime) {
		Graphics g = game.getGraphics();
        long startTime = System.currentTimeMillis();

        Assets.background = g.newImage("gamebgike.png", ImageFormat.RGB565);
        Assets.guye = g.newImage("guyeike.png", ImageFormat.RGB565);
        Assets.guys = g.newImage("guysike.png", ImageFormat.RGB565);
        Assets.guyr = g.newImage("guyrike.png", ImageFormat.RGB565);
        Assets.guyl = g.newImage("guylike.png", ImageFormat.RGB565);
        Assets.basicplat = g.newImage("basicplatike.png", ImageFormat.RGB565);
        Assets.movingplat = g.newImage("movingplatike.png", ImageFormat.RGB565);
        Assets.vanishplat = g.newImage("vanishplatike.png", ImageFormat.RGB565);
        Assets.superplat = g.newImage("superplatike.png", ImageFormat.RGB565);
        Assets.riseplat = g.newImage("riserplatike.png", ImageFormat.RGB565);

        long t = (System.currentTimeMillis() - startTime);
        try {
            if (t < 3000)
                Thread.sleep(3000 - t);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assets.theme.play();
		
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