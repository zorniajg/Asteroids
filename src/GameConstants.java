import java.awt.Color;
import java.util.Random;

/**
 * This class stores constants related to the Asteroids application.
 * 
 * @author Nathan Sprague
 * @version V1.0, 3/18
 */
public class GameConstants
{
    public static final Color SCREEN_COLOR = new Color(0, 0, 0);
    public static final int DRAW_DELAY = 10; // In milliseconds.
    public static final int SCREEN_WIDTH = 480;
    public static final int SCREEN_HEIGHT = 480;
    public static final int DEFAULT_ASTEROIDS_PER_LEVEL = 10;
    
    public static final Random GENERATOR = new Random();
    public static final double SAUCER_PORBABILITY = .002;
    public static final double SAUCER_DIRECTION_PROBABILITY = .05;
    public static final int SAUCER_POINTS = 400;
    public static final double BULLET_RADIUS = 1.5;
    public static final double STAR_RADIUS = 1.0;
    public static final double SAUCER_WIDTH = 20.0;
    public static final double SAUCER_HEIGHT = 10.0;

}
