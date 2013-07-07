package com.jordan.lemongame;

/**
 * Created by Owner on 7/1/13.
 */
public class VanishPlatform extends Platform {
    public VanishPlatform(Coord c) {
        super(c);
    }

    @Override
    public void onCollision(ScribbleGuy guy) {
        basicCollisionAction(guy);

        c.y = -100;
    }

    @Override
    public void update() {

    }
}
