/**
 * This class is responsible for creating bullets and providing methods to
 * edit/manipulate them.
 * 
 * @author Jacob Zorniak
 * @version 3/25/18
 * 
 */

public class Bullet extends Sprite
{
    private int timeStepCounter;

    /**
     * Constructs a bullet.
     * 
     * @param velocity The bullet's velocity
     * @param xPosition The bullet's x-position
     * @param yPosition The bullet's y-position
     * @param heading The bullet's heading
     */
    public Bullet(Vector2D velocity, double xPosition,
            double yPosition, double heading)
    {
        super(velocity, xPosition, yPosition, heading);
        timeStepCounter = 0;
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
     * Returns if the bullet should be deleted (after 20 ttime steps).
     * 
     * @return If the bullet should be deleted
     */
    public boolean deleteBullet()
    {
        timeStepCounter++;
        if (timeStepCounter >= 20)
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
        this.collisionRadius = GameConstants.BULLET_RADIUS;
    }
}
