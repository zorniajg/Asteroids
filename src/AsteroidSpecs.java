/**
 * This class is responsible for holding values associated with each type of
 * asteroid based on size.
 * 
 * @author Jacob Zorniak
 * @version 3/25/18
 * 
 */

public enum AsteroidSpecs
{
    SMALL(10, 200), MEDIUM(20, 100), LARGE(30, 50);

    int radius;
    int pointsWorth;

    /**
     * Constructs an AsteroidSpecs.
     * 
     * @param radius The asteroid's radius
     * @param pointsWorth The amount of points the asteroid is worth based on
     *            size
     */
    AsteroidSpecs(int radius, int pointsWorth)
    {
        this.radius = radius;
        this.pointsWorth = pointsWorth;
    }

    /**
     * Returns the asteroid's radius.
     * 
     * @return The radius
     */
    public int getRadius()
    {
        return radius;
    }

    /**
     * Returns the amount of points the asteroid is worth.
     * 
     * @return The points the asteroid is worth
     */
    public int getPointsWorth()
    {
        return pointsWorth;
    }
}
