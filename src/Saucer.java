/**
 * This class is responsible for creating saucers and providing methods to
 * edit/manipulate them.
 * 
 * @author Jacob Zorniak
 * @version 3/25/18
 * 
 */

public class Saucer extends Sprite
{
    /**
     * Constructs a saucer.
     * 
     * @param velocity The saucer's velocity
     * @param heading The saucer's heading
     */
    public Saucer(Vector2D velocity, double heading)
    {
        super(velocity, Point.generateRandomLocation(GameConstants.SCREEN_WIDTH),
                Point.generateRandomLocation(GameConstants.SCREEN_HEIGHT), heading);
        setCollisionRadius();
    }

    /*
     * (non-Javadoc)
     * 
     * @see Sprite#move()
     */
    @Override
    void move()
    {
        super.move(this.getVelocity());
    }

    /**
     * Changes the saucer's direction based on a direction-change probability.
     */
    public void changeDirection()
    {
        double changeDirectionProbability = GameConstants.GENERATOR.nextDouble();
        if (changeDirectionProbability <= GameConstants.SAUCER_DIRECTION_PROBABILITY)
        {
            this.setVelocity(new Vector2D(Pose.generateHeading(), 2.0));
        }
    }

    /**
     * Checks to see if the saucer is off-screen.
     * 
     * @return If the saucer is off the screen
     */
    public boolean isOffScreen()
    {
        if (this.getX() <= 0 - GameConstants.SAUCER_WIDTH)
        {
            return true;
        }
        else if (this.getX() >= GameConstants.SCREEN_WIDTH + GameConstants.SAUCER_WIDTH)
        {
            return true;
        }

        if (this.getY() <= 0 - GameConstants.SAUCER_HEIGHT)
        {
            return true;
        }
        else if (this.getY() >= GameConstants.SCREEN_HEIGHT + GameConstants.SAUCER_HEIGHT)
        {
            return true;
        }
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see Sprite#setCollisionRadius()
     */
    @Override
    void setCollisionRadius()
    {
        this.collisionRadius = 10.0;
    }
}
