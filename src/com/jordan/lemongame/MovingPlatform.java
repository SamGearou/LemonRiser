package com.jordan.lemongame;

/**
 * Created by Owner on 7/1/13.
 */
public class MovingPlatform extends Platform {

    public int s;
    public int r = (int)(Math.random() + .5);
    public boolean right = r == 1;

    public MovingPlatform(Coord c) {
        super(c);
        s = (int)c.x;

    }

    @Override
    public void onCollision(ScribbleGuy guy) {
        basicCollisionAction(guy);
    }

    @Override
    public void update() {
        if (right)
        {
            c.x += 2;
        }
        else
        {
            c.x -= 2;
        }
        if (c.x >= s + 100)
            right = false;
        else if (c.x <= s - 100)
            right = true;
    }
}
