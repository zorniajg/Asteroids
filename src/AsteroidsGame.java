import java.util.ArrayList;

/**
 * This class is responsible for creating all of the game elements, updating
 * them as necessary, and drawing them onto the screen.
 * 
 * @author Jacob Zorniak
 * @version 3/25/18
 * 
 */
public class AsteroidsGame
{
    private Ship ship;
    private ArrayList<Saucer> saucers;
    private ArrayList<Bullet> bullets;
    private Asteroid[] asteroids;
    private Star[] stars;
    private int lives;
    private int score;
    private boolean gameOver;

    /**
     * Constructor, which takes in the number of asteroids that should appear at
     * the start of the game and initializes all of the necessary attributes.
     * 
     * @param numAsteroids The number of asteroids
     */
    public AsteroidsGame(int numAsteroids)
    {
        ship = new Ship(new Vector2D(Math.PI / 2, 0.0), GameConstants.SCREEN_WIDTH / 2,
                GameConstants.SCREEN_HEIGHT / 2, Math.PI / 2, 10, 20);
        saucers = new ArrayList<Saucer>();
        bullets = new ArrayList<Bullet>();
        asteroids = new Asteroid[numAsteroids];
        for (int i = 0; i < asteroids.length; i++)
        {
            double randomHeading = Pose.generateHeading();
            asteroids[i] = new Asteroid(new Vector2D(randomHeading, 1.0), randomHeading, false);
        }
        stars = new Star[100];
        for (int i = 0; i < stars.length; i++)
        {
            stars[i] = new Star();
        }
        lives = 3;
        score = 0;
        gameOver = false;
    }

    /**
     * Updates the position/state of each game element. This includes checking
     * for screen wrapping, collisions, point management, and checking to see if
     * the game is over.
     */
    public void update()
    {
        // Check to see if the game is over (no more lives)
        gameOver();
        if (!gameOver)
        {
            // Update ship location
            ship.move();
            // Check if a bullet should be fired
            checkFire();
            // Update bullets
            for (int i = 0; i < bullets.size(); i++)
            {
                bullets.get(i).move();
                if (bullets.get(i).deleteBullet())
                {
                    bullets.remove(i);
                }
            }
            // Update asteroids locations
            for (int i = 0; i < asteroids.length; i++)
            {
                asteroids[i].move();
            }
            // Update saucers
            double spawnSaucer = GameConstants.GENERATOR.nextDouble();
            if (spawnSaucer <= GameConstants.SAUCER_PORBABILITY)
            {
                double randomHeading = Pose.generateHeading();
                saucers.add(new Saucer(new Vector2D(randomHeading, 2.0), randomHeading));
            }
            for (int i = 0; i < saucers.size(); i++)
            {
                saucers.get(i).move();
                saucers.get(i).changeDirection();
            }
            // Check if a new level should start
            if (noAsteroidsOrSaucersLeft())
            {
                newLevel();
            }
            // Check if objects should wrap the screen
            checkScreenWraps();
            // Check for collisions
            checkCollisions();
        }
    }

    /**
     * Draws each game element onto the screen after it has been updated.
     */
    public void draw()
    {
        // Set color
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.setPenRadius();
        // Draw stars
        for (int i = 0; i < stars.length; i++)
        {
            StdDraw.filledCircle(stars[i].getX(), stars[i].getY(), GameConstants.STAR_RADIUS);
        }
        // Draw score
        StdDraw.text(60.0, GameConstants.SCREEN_HEIGHT - 20.0, "Score: " + score);
        // Draw lives remaining
        StdDraw.text(60.0, GameConstants.SCREEN_HEIGHT - 60.0, "Lives: " + lives);
        // Draw ship
        GameUtils.drawPoseAsTriangle(ship, ship.getBase(), ship.getHeight());
        // Draw bullets
        for (int i = 0; i < bullets.size(); i++)
        {
            StdDraw.filledCircle(bullets.get(i).getX(), bullets.get(i).getY(),
                    GameConstants.BULLET_RADIUS);
        }
        // Draw asteroids
        for (int i = 0; i < asteroids.length; i++)
        {
            StdDraw.circle(asteroids[i].getX(), asteroids[i].getY(), asteroids[i].getSize());
        }
        // Draw saucers
        for (int i = 0; i < saucers.size(); i++)
        {
            StdDraw.rectangle(saucers.get(i).getX(), saucers.get(i).getY(),
                    GameConstants.SAUCER_WIDTH / 2, GameConstants.SAUCER_HEIGHT / 2);
        }
    }

    /**
     * Checks each collidable game element for collisions.
     */
    private void checkCollisions()
    {
        // Check collisions with ship
        for (int i = 0; i < asteroids.length; i++)
        {
            if (asteroids[i].collide(ship))
            {
                asteroids[i].deleteAsteroid();
                setScore(asteroids[i].getPointsWorth());
                loseLife();
            }
        }
        for (int i = 0; i < saucers.size(); i++)
        {
            if (saucers.get(i).collide(ship))
            {
                saucers.remove(i);
                setScore(GameConstants.SAUCER_POINTS);
                loseLife();
            }
        }

        // Check collisions with bullets
        for (int i = 0; i < bullets.size(); i++)
        {
            boolean deleteBullet = false;
            for (int j = 0; j < asteroids.length; j++)
            {
                if (bullets.get(i).collide(asteroids[j]))
                {
                    asteroids[j].deleteAsteroid();
                    setScore(asteroids[j].getPointsWorth());
                    deleteBullet = true;
                }
            }
            if (deleteBullet)
            {
                bullets.remove(i);
            }
        }

        for (int i = 0; i < bullets.size(); i++)
        {
            boolean deleteBullet = false;
            for (int j = 0; j < saucers.size(); j++)
            {
                if (bullets.get(i).collide(saucers.get(j)))
                {
                    saucers.remove(j);
                    setScore(GameConstants.SAUCER_POINTS);
                    deleteBullet = true;
                }
            }
            if (deleteBullet)
            {
                bullets.remove(i);
            }
        }
    }

    /**
     * Checks if an element should wrap the screen if it has that ability.
     */
    private void checkScreenWraps()
    {
        Sprite.checkScreenWrap(ship, ship.getHeight());
        for (int i = 0; i < asteroids.length; i++)
        {
            if (!asteroids[i].getDeleted())
            {
                Sprite.checkScreenWrap(asteroids[i], asteroids[i].getSize());
            }
        }
        for (int i = 0; i < bullets.size(); i++)
        {
            Sprite.checkScreenWrap(bullets.get(i), GameConstants.BULLET_RADIUS);
        }
        for (int i = 0; i < saucers.size(); i++)
        {
            if (saucers.get(i).isOffScreen())
            {
                saucers.remove(i);
            }
        }
    }

    /**
     * Adjusts the score based on the amount of new points earned.
     * 
     * @param newPointsEarned The amount of new points earned
     */
    private void setScore(int newPointsEarned)
    {
        score += newPointsEarned;
    }

    /**
     * Updates the amount of lives and ship position whenever the ship collides
     * with an asteroid or a saucer.
     */
    private void loseLife()
    {
        lives--;

        if (lives > 0)
        {
            ship.setX(GameConstants.SCREEN_WIDTH / 2);
            ship.setY(GameConstants.SCREEN_HEIGHT / 2);
            ship.setHeading(Math.PI / 2);
            ship.setVelocity(new Vector2D(Math.PI / 2, 0.0));
        }

    }

    /**
     * Resets the asteroids if there are no asteroids or saucers left on the
     * screen (starts a new level).
     */
    private void newLevel()
    {
        for (int i = 0; i < asteroids.length; i++)
        {
            double randomHeading = Pose.generateHeading();
            asteroids[i].setX(Pose.generateRandomLocation(GameConstants.SCREEN_WIDTH));
            asteroids[i].setY(Pose.generateRandomLocation(GameConstants.SCREEN_HEIGHT));
            asteroids[i].setHeading(randomHeading);
            asteroids[i].setVelocity(new Vector2D(randomHeading, 1.0));
            asteroids[i].setDeleted(false);
        }
    }

    /**
     * Checks to see if there are any asteroids or saucers left on the screen.
     * 
     * @return If there are no asteroids or saucers left
     */
    private boolean noAsteroidsOrSaucersLeft()
    {
        boolean noAsteroidsLeft = true;
        for (int i = 0; i < asteroids.length; i++)
        {
            if (asteroids[i].getX() != -100)
            {
                noAsteroidsLeft = false;
            }
        }
        if (noAsteroidsLeft && saucers.size() == 0)
        {
            return true;
        }
        return false;
    }

    /**
     * Checks to see if the game should end and adjusts the game elements
     * accordingly.
     */
    private void gameOver()
    {
        if (lives <= 0)
        {
            gameOver = true;
            Vector2D gameOverVelocity = new Vector2D(0.0, 0.0);

            ship.setX(-100);
            ship.setVelocity(new Vector2D(ship.getHeading(), 0.0));
            for (int i = 0; i < asteroids.length; i++)
            {
                asteroids[i].setVelocity(gameOverVelocity);
            }
            for (int i = 0; i < saucers.size(); i++)
            {
                saucers.get(i).setVelocity(gameOverVelocity);
            }
        }
    }

    /**
     * Checks if the user has entered the space bar (this action triggers a
     * bullet to be fired).
     */
    private void checkFire()
    {
        if (StdDraw.hasNextKeyTyped() && StdDraw.nextKeyTyped() == ' ')
        {
            bullets.add(new Bullet(new Vector2D(ship.getHeading(), 20.0), ship.getX(), ship
                    .getY(), ship.getHeading()));
        }
    }

}