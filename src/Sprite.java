/**
 * This class is used to create a Sprite and provides methods for
 * editing/manipulating Sprites.
 * 
 * @author Jacob Zorniak
 * @version 3/25/18
 * 
 */

public abstract class Sprite extends Pose
{
    protected double collisionRadius;
    private Vector2D velocity;

    /**
     * Constructs a Sprite.
     * 
     * @param velocity The Sprite's velocity
     * @param xPosition The Sprite's x-position
     * @param yPosition The Sprite's y-position
     * @param heading The Sprite's heading
     */
    public Sprite(Vector2D velocity, double xPosition, double yPosition, double heading)
    {
        super(xPosition, yPosition, heading);
        this.velocity = velocity;
    }

    /**
     * Abstract method that all subclasses of Sprite must contain. Controls the
     * Sprite's movement.
     */
    abstract void move();

    /**
     * Abstract method that all subclasses of Sprite must contain. Sets the
     * Sprite's collision radius.
     */
    abstract void setCollisionRadius();

    /**
     * Returns the Sprite's velocity.
     * 
     * @return The velocity
     */
    public Vector2D getVelocity()
    {
        return velocity;
    }

    /**
     * Sets the Sprite's velocity to a new velocity.
     * 
     * @param newVelocity The new velocity
     */
    public void setVelocity(Vector2D newVelocity)
    {
        velocity = newVelocity;
    }

    /**
     * Returns the Sprite's collision radius.
     * 
     * @return The collision radius
     */
    public double getCollisionRadius()
    {
        return collisionRadius;
    }

    /**
     * Checks the Sprite for collisions with a different Sprite.
     * 
     * @param collidingSprite The Sprite to check for collisions with the
     *            current Sprite.
     * @return If the two Sprites are colliding
     */
    public boolean collide(Sprite collidingSprite)
    {
        double xDif = this.getX() - collidingSprite.getX();
        double yDif = this.getY() - collidingSprite.getY();
        double distanceSquared = xDif * xDif + yDif * yDif;
        return distanceSquared < (this.getCollisionRadius() + collidingSprite
                .getCollisionRadius())
                * (this.getCollisionRadius() + collidingSprite.getCollisionRadius());
    }

    /**
     * Checks to see if a Sprite should wrap the screen.
     * 
     * @param spriteToCheck The Sprite to check
     * @param dimensionToCheck The dimension of the Sprite that should be
     *            accounted for when checking.
     */
    public static void checkScreenWrap(Sprite spriteToCheck, double dimensionToCheck)
    {
        if (spriteToCheck.getX() <= 0 - dimensionToCheck)
        {
            spriteToCheck.setX(GameConstants.SCREEN_WIDTH + dimensionToCheck);
        }
        else if (spriteToCheck.getX() >= GameConstants.SCREEN_WIDTH + dimensionToCheck)
        {
            spriteToCheck.setX(0 - dimensionToCheck);
        }

        if (spriteToCheck.getY() <= 0 - dimensionToCheck)
        {
            spriteToCheck.setY(GameConstants.SCREEN_HEIGHT + dimensionToCheck);
        }
        else if (spriteToCheck.getY() >= GameConstants.SCREEN_HEIGHT + dimensionToCheck)
        {
            spriteToCheck.setY(0 - dimensionToCheck);
        }
    }

}
