package com.jordan.lemongame;

import java.util.List;

import android.graphics.Color;
import android.graphics.Paint;

import com.jordan.framework.Game;
import com.jordan.framework.Graphics;
import com.jordan.framework.Image;
import com.jordan.framework.Input.TouchEvent;
import com.jordan.framework.Screen;

public class GameScreen extends Screen {
	enum GameState {
		Ready, Running, Paused, GameOver
	}

	GameState state = GameState.Ready;

	private Animation anim, hanim;
    private int ticks = 0;
    private final int OFFSET = 30;
    private int pauseticks;
	Paint paintc, paintl;

	public GameScreen(Game game) {
		super(game);

		// Initialize game objects here

        //how to animate
		/*hanim = new Animation();
		hanim.addFrame(heliboy, 100);
		hanim.addFrame(heliboy2, 100);
		hanim.addFrame(heliboy3, 100);
		hanim.addFrame(heliboy4, 100);
		hanim.addFrame(heliboy5, 100);
		hanim.addFrame(heliboy4, 100);
		hanim.addFrame(heliboy3, 100);
		hanim.addFrame(heliboy2, 100);*/

		// Defining a paint object
		paintc = new Paint();
		paintc.setTextSize(30);
		paintc.setTextAlign(Paint.Align.CENTER);
		paintc.setAntiAlias(true);
		paintc.setColor(Color.YELLOW);

        paintl = new Paint();
        paintl.setTextSize(30);
        paintl.setTextAlign(Paint.Align.LEFT);
        paintl.setAntiAlias(true);
        paintl.setColor(Color.GRAY);

	}

	@Override
	public void update(float deltaTime) {
        ticks++;
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();

		// We have four separate update methods in this example.
		// Depending on the state of the game, we call different update methods.
		// Refer to Unit 3's code. We did a similar thing without separating the
		// update methods.

		if (state == GameState.Ready)
			updateReady(touchEvents);
		if (state == GameState.Running)
			updateRunning(touchEvents, deltaTime);
		if (state == GameState.Paused)
			updatePaused(touchEvents);
		if (state == GameState.GameOver)
			updateGameOver(touchEvents);
	}

	private void updateReady(List<TouchEvent> touchEvents) {

		// This example starts with a "Ready" screen.
		// When the user touches the screen, the game begins.
		// state now becomes GameState.Running.
		// Now the updateRunning() method will be called!

		if (touchEvents.size() > 0)
			state = GameState.Running;
	}

    private boolean right = false, left = false;

	private void updateRunning(List<TouchEvent> touchEvents, float deltaTime) {

		// 1. All touch input is handled here:
		for (TouchEvent event: touchEvents) {
            if (ticks < 15) break;
            switch(event.type)
            {
                case TouchEvent.TOUCH_DOWN:
                case TouchEvent.TOUCH_DRAGGED:
                    right = false;
                    left = false;

                    if (event.x < 50 && event.y < 50)
                        pause();
                    if (event.x >= 240)
                        right = true;
                    if (event.x < 240)
                        left = true;
                    break;
                case TouchEvent.TOUCH_UP:
                    right = false;
                    left = false;
            }

		}

		//2. update
        GameDisplay.update(right,left);

        //3. check for death
		if (!GameDisplay.guy.checkDeath())
        {
			state = GameState.GameOver;
            pauseticks = ticks;
        }

        //anim.update(10);
	}

	private void updatePaused(List<TouchEvent> touchEvents) {
		if (touchEvents.size() > 0 && pauseticks + 15 < ticks)
				resume();
	}

	private void updateGameOver(List<TouchEvent> touchEvents) {
        if (touchEvents.size() > 0 && pauseticks + 15 < ticks)
        {
            nullify();
			game.setScreen(new GameScreen(game));
        }
	}

	@Override
	public void paint(float deltaTime) {
		if (state == GameState.Ready)
			drawReadyUI();
		if (state == GameState.Running)
			drawRunningUI();
		if (state == GameState.Paused)
			drawPausedUI();
		if (state == GameState.GameOver)
			drawGameOverUI();
	}

	private void nullify() {

		// Set all variables to null, recreate them in the constructor.
		paintc = null;
        paintl = null;
		anim = null;
		hanim = null;

        GameDisplay.reset();

		// Call garbage collector to clean up memory.
		System.gc();
	}

	private void drawReadyUI() {
		Graphics g = game.getGraphics();
        g.drawImage(Assets.background, 0,0);

        g.drawARGB(200, 0, 0, 0);
		g.drawString("Tap to Start", 240, 400, paintc);
	}

	private void drawRunningUI() {
        Graphics g = game.getGraphics();
        g.clearScreen(Color.BLACK);
        g.drawImage(Assets.background, 0,0);

        int x = (int) GameDisplay.guyCoord.x;
        int y = (int) GameDisplay.guyCoord.y;
        Image theguy = Assets.guyr;
        if(GameDisplay.guy.velocity > GameDisplay.guy.maxVelocity)
            theguy = Assets.guye;
        else if (GameDisplay.guy.c.y - GameDisplay.guy.deathHeight < 300 && GameDisplay.guy.velocity < -4)
            theguy = Assets.guys;
        else if (GameDisplay.guy.movement < 0)
            theguy = Assets.guyl;
        g.drawImage(theguy, x - Assets.guyr.getWidth()/2,y - Assets.guyr.getHeight()/2);

        for(Platform p: GameDisplay.platforms)
        {
            if (p instanceof VanishPlatform)
                g.drawImage(Assets.vanishplat, (int)p.c.x - Assets.basicplat.getWidth()/2, (int)(800 - (p.c.y - GameDisplay.guy.deathHeight)) - Assets.basicplat.getHeight()+OFFSET);
            else if (p instanceof SuperPlatform)
                g.drawImage(Assets.superplat, (int)p.c.x - Assets.basicplat.getWidth()/2, (int)(800 - (p.c.y - GameDisplay.guy.deathHeight)) - Assets.basicplat.getHeight()+OFFSET);
            else if (p instanceof MovingPlatform)
                g.drawImage(Assets.movingplat, (int)p.c.x - Assets.basicplat.getWidth()/2, (int)(800 - (p.c.y - GameDisplay.guy.deathHeight)) - Assets.basicplat.getHeight()+OFFSET);
            else
                g.drawImage(Assets.basicplat, (int)p.c.x - Assets.basicplat.getWidth()/2, (int)(800 - (p.c.y - GameDisplay.guy.deathHeight)) - Assets.basicplat.getHeight()+OFFSET);
        }

        g.drawString((int)GameDisplay.guy.currentScore + "", 140, 37, paintl);
	}

	private void drawPausedUI() {
		Graphics g = game.getGraphics();
        drawRunningUI();
		// Darken the entire screen so you can display the Paused screen.
		g.drawARGB(200, 0, 0, 0);
		g.drawString("Tap to Resume", 240, 400, paintc);
	}

	private void drawGameOverUI() {
		Graphics g = game.getGraphics();
        drawRunningUI();
        g.drawARGB(200, 0, 0, 0);
        g.drawString("GAME OVER", 240, 325, paintc);
        g.drawString("SCORE: " + (int)GameDisplay.guy.currentScore, 240, 400, paintc);
		g.drawString("Tap to Retry", 240, 475, paintc);
	}

	@Override
	public void pause() {
		if (state == GameState.Running)
        {
			state = GameState.Paused;
            pauseticks = ticks;
        }

	}

	@Override
	public void resume() {
		if (state == GameState.Paused)
			state = GameState.Running;
	}

	@Override
	public void dispose() {

	}

	@Override
	public void backButton() {
		pause();
	}

}