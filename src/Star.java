/**
 * This class is responsible for creating stars.
 * 
 * @author Jacob Zorniak
 * @version 3/25/18
 * 
 */
public class Star extends Point
{
    /**
     * Constructs a star by assigning its x and y positions to random locations
     * on the screen.
     */
    public Star()
    {
        super(Point.generateRandomLocation(GameConstants.SCREEN_WIDTH), Point
                .generateRandomLocation(GameConstants.SCREEN_HEIGHT));
    }

}
