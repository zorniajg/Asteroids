/**
 * This class is responsible for creating an asteroid and provide methods to
 * work with it. This method extends the Sprite class.
 * 
 * @author Jacob Zorniak
 * @version 3/25/18
 * 
 */

public class Asteroid extends Sprite
{
    private AsteroidSpecs asteroidSize;
    private boolean deleted;

    /**
     * Constructs an asteroid.
     * 
     * @param velocity The asteroid's velocity
     * @param heading The asteroid's heading
     * @param deleted The state of the asteroid (deleted or not deleted)
     */
    public Asteroid(Vector2D velocity, double heading, boolean deleted)
    {
        super(velocity, Point.generateRandomLocation(GameConstants.SCREEN_WIDTH), Point
                .generateRandomLocation(GameConstants.SCREEN_HEIGHT), heading);
        asteroidSize = generateSize();
        this.deleted = deleted;
        setCollisionRadius();
    }

    /*
     * (non-Javadoc)
     * 
     * @see Sprite#move()
     */
    @Override
    public void move()
    {
        super.move(this.getVelocity());
    }

    /**
     * Returns the asteroid's size.
     * 
     * @return The asteroid's size
     */
    public double getSize()
    {
        return asteroidSize.getRadius();
    }

    /**
     * Returns the amount of points the asteroid is worth.
     * 
     * @return The amount of points
     */
    public int getPointsWorth()
    {
        return asteroidSize.getPointsWorth();
    }

    /**
     * Generates the size of an asteroid when it is created based on the size
     * probabilities.
     * 
     * @return The asteroid's generated size
     */
    private AsteroidSpecs generateSize()
    {
        double probability = GameConstants.GENERATOR.nextDouble();

        if (probability <= .50)
        {
            return AsteroidSpecs.LARGE;
        }
        else if (probability > .50 && probability <= .75)
        {
            return AsteroidSpecs.MEDIUM;
        }
        else
        {
            return AsteroidSpecs.SMALL;
        }
    }

    /**
     * Sets the asteroid's state (deleted or not deleted).
     * 
     * @param isDeleted If the asteroid is deleted or not
     */
    public void setDeleted(boolean isDeleted)
    {
        deleted = isDeleted;
    }

    /**
     * Returns the asteroid's state (deleted or not deleted).
     * 
     * @return The asteroid's state
     */
    public boolean getDeleted()
    {
        return deleted;
    }

    /**
     * Provides the necessary functions on an asteroid when it is deleted.
     */
    public void deleteAsteroid()
    {
        this.setX(-100);
        this.setVelocity(new Vector2D(0.0, 0.0));
        deleted = true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see Sprite#setCollisionRadius()
     */
    @Override
    void setCollisionRadius()
    {
        this.collisionRadius = this.getSize();
    }
}
