package com.jordan.lemongame;

/**
 * Created by Owner on 7/7/13.
 */
public class RisePlatform extends Platform {

    public int diff, end;

    public RisePlatform(Coord c, int diff, int end) {
        super(c);
        this.diff = diff;
        this.end = end;
    }

    @Override
    public void onCollision(ScribbleGuy guy) {
        basicCollisionAction(guy);
        c.y += diff - 25;
        c.x += Math.random() * 250 - 125;
        if (c.y > end) c.y = 0;
        if (c.x > 440 || c.x < 40) c.x = Math.random() * 200 + 140;
    }

    @Override
    public void update() {

    }
}
