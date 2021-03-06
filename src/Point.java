/**
 * Encapsulation of a two-dimensional point.
 * 
 * @author Nathan Sprague
 * @version V2.0 10/15
 */
public class Point
{
    protected double xPosition;
    protected double yPosition;

    /**
     * Construct a point object.
     * 
     * @param xPosition - position on the X-axis
     * @param yPosition - position on the Y-axis
     */
    public Point(double xPosition, double yPosition)
    {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    /**
     * Copy constructor.
     * 
     * @param other The point to copy
     */
    public Point(Point other)
    {
        this(other.getX(), other.getY());
    }

    /**
     * @return the X coordinate
     */
    public double getX()
    {
        return xPosition;
    }

    /**
     * @return the Y coordinate
     */
    public double getY()
    {
        return yPosition;
    }

    /**
     * Set the x position of the point.
     * 
     * @param xPosition The new X position
     */
    public void setX(double xPosition)
    {
        this.xPosition = xPosition;
    }

    /**
     * Set the y position of the point.
     * 
     * @param yPosition The new y position.
     */
    public void setY(double yPosition)
    {
        this.yPosition = yPosition;
    }

    /**
     * Move the point according the the provided vector.
     * 
     * @param vector The motion vector (probably encoding a velocity)
     */
    public void move(Vector2D vector)
    {
        setX(getX() + vector.getX());
        setY(getY() + vector.getY());
    }

    /**
     * @return String representation of this point as
     *         "Point[xPosition=x, yPosition=y]"
     */
    public String toString()
    {
        return "Point[xPosition=" + xPosition + ", yPosition=" + yPosition + "]";
    }
    
    /**
     * Returns a random location on the screen base on the bound (width or height).
     * 
     * @param upperBound The bound to use
     * @return A random location based on the axis/bound (width(x), height(y))
     */
    public static double generateRandomLocation(double upperBound)
    {
        double randomLocation = GameConstants.GENERATOR.nextDouble() * upperBound;
        return randomLocation;
    }

}
