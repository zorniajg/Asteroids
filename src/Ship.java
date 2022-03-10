import java.awt.event.KeyEvent;

/**
 * This class is responsible for creating the ship and providing methods to
 * edit/manipulate it.
 * 
 * @author Jacob Zorniak
 * @version 3/25/18
 * 
 */

public class Ship extends Sprite
{
    private int base;
    private int height;

    /**
     * Constructs a ship.
     * 
     * @param velocity The ship's velocity
     * @param xPosition The ship's x-position
     * @param yPosition The ship's y-position
     * @param heading The ship's heading
     * @param base The ship's base
     * @param height The ship's height
     */
    public Ship(Vector2D velocity, double xPosition,
            double yPosition, double heading, int base, int height)
    {
        super(velocity, xPosition, yPosition, heading);
        this.base = base;
        this.height = height;
        setCollisionRadius();
    }

    /**
     * Moves the ship based on user input from the keyboard, namely the arrow
     * keys.
     */
    public void move()
    {
        if (StdDraw.isKeyPressed(KeyEvent.VK_LEFT))
        {
            this.setHeading(this.getHeading() + .1);
        }
        if (StdDraw.isKeyPressed(KeyEvent.VK_RIGHT))
        {
            this.setHeading(this.getHeading() - .1);
        }
        if (StdDraw.isKeyPressed(KeyEvent.VK_DOWN))
        {
            GameUtils.applyThrust(this.getVelocity(), new Vector2D(this.getHeading(), .1));
            this.move(this.getVelocity());
        }
        else
        {
            this.getVelocity().setMagnitude(this.getVelocity().getMagnitude() * .99);
            super.move(this.getVelocity());
        }
    }

    /**
     * Returns the ship's base.
     * 
     * @return The ship's base
     */
    public int getBase()
    {
        return base;
    }

    /**
     * Returns the ship's height.
     * 
     * @return The ship's height
     */
    public int getHeight()
    {
        return height;
    }

    /*
     * (non-Javadoc)
     * @see Sprite#setCollisionRadius()
     */
    @Override
    void setCollisionRadius()
    {
        this.collisionRadius = 10.0;
    }

}
