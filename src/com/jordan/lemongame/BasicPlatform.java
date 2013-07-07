package com.jordan.lemongame;

/**
 * Created by Owner on 6/29/13.
 */
public class BasicPlatform extends Platform {

    public BasicPlatform(Coord c) {
        super(c);
    }

    public void onCollision(ScribbleGuy guy) {
        basicCollisionAction(guy);
    }

    public void update()
    {

    }

}
