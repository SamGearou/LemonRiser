package com.jordan.lemongame;

//the black super high platform
public class SuperPlatform extends Platform {
    public SuperPlatform(Coord c) {
        super(c);
    }

    @Override
    public void onCollision(ScribbleGuy guy) {
        if (Math.random() < 0.04)
            guy.velocity = guy.getMV() * 7.02;
        else
            guy.velocity = guy.getMV() * 2.98;

        if (Assets.theme.isPlaying())
        {
            Assets.bounce.play(Assets.VOLUME);
        }
    }

    @Override
    public void update(float deltaTime) {

    }
}
