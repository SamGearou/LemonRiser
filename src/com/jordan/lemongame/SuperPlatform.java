package com.jordan.lemongame;

/**
 * Created by Owner on 7/1/13.
 */
public class SuperPlatform extends Platform {
    public SuperPlatform(Coord c) {
        super(c);
    }

    @Override
    public void onCollision(ScribbleGuy guy) {
        if (Math.random() < 0.04)
            guy.velocity = guy.getMV() * 7.01;
        else
            guy.velocity = guy.getMV() * 2.98;

        if (Assets.theme.isPlaying())
            Assets.superBounce.play(0.85f);
    }

    @Override
    public void update() {

    }
}
