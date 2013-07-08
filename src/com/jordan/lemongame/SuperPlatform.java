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
        guy.velocity = guy.getMV() * 2.98;
    }

    @Override
    public void update() {

    }
}
