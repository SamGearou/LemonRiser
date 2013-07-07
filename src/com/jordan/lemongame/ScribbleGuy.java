package com.jordan.lemongame;

/**
 * Created by Owner on 6/29/13.
 */
import java.util.ArrayList;

public class ScribbleGuy {

    public Coord c;

    //j- stores when he dies,
    public double deathHeight, currentScore;

    //j- velocity is upward momentum
    public double velocity;
    public double movement;

    //j- gravity is how fast he falls
    //movement is right/left
    public final double gravity = -0.5;
    public final double resistance = 0.75;
    public final double maxMovement = 8;
    public final double maxVelocity = 17;
    public final double COLLISION_HEIGHT = 20;
    public final double COLLISION_WIDTH = -10 + Assets.basicplat.getWidth()/2 + Assets.guyr.getWidth();
    public final double DEATH_BUFFER = 400;

    public ScribbleGuy()
    {
        c = new Coord();
        c.x = 480 / 2;
        c.y = 100;
        deathHeight = 0;
        currentScore = 0;
        velocity = maxVelocity;
    }

    //j- called every tick to update coords
    public void updatePosition(boolean right, boolean left)
    {
        if (right)
            movement = maxMovement;
        if (left)
            movement = -maxMovement;

        c.x += movement;
        movement *= resistance;

        if (c.x > 479)
            c.x = 1;
        if (c.x < 1)
            c.x = 479;

        c.y += velocity;
        velocity += gravity;

        if (currentScore < c.y)
            currentScore = c.y;

        if (deathHeight < c.y - DEATH_BUFFER)
            deathHeight = c.y - DEATH_BUFFER;
    }

    public boolean checkDeath() {
        return c.y >= deathHeight;
    }

    public void checkCollision(ArrayList<Platform> platforms)
    {
        for(Platform p: platforms)
        {
            if ( Math.abs(p.c.x - c.x) < COLLISION_WIDTH && Math.abs(p.c.y - c.y) < COLLISION_HEIGHT && velocity <= 0)
                p.onCollision(this);
            p.update();
        }
    }

    public double getMV()
    {
        return maxVelocity;
    }

}
