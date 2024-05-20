package dk.sdu.mmmi.cbse.asteroidsystem;

import dk.sdu.mmmi.cbse.common.asteroid.Asteroid;
import dk.sdu.mmmi.cbse.common.asteroid.AsteroidSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import java.util.Random;


public class AsteroidControlSystem implements IEntityProcessingService, AsteroidSPI {
    private Random random = new Random();


    @Override
    public void process(GameData gameData, World world) {

        for (Entity asteroid : world.getEntities(Asteroid.class)){
            // Make asteroid turn around
            asteroid.setRotation(asteroid.getRotation() +2);

            double asteroidX = asteroid.getX();
            double asteroidY = asteroid.getY();
            float asteroidSpeed = 2;
            double asteroidDirection = asteroid.getRotation();

            // Calculate new position based on current direction
            double newX = asteroidX + asteroidSpeed * (float)Math.cos(Math.toRadians(asteroidDirection));
            double newY = asteroidY + asteroidSpeed * (float)Math.sin(Math.toRadians(asteroidDirection));

            // Check if the asteroid hits the border of the screen
            if (newX < 0 || newX > gameData.getDisplayWidth() || newY < 0 || newY > gameData.getDisplayHeight()) {
                // If it does, remove the asteroid from the world
                world.removeEntity(asteroid);
            } else {
                // Update asteroid position
                asteroid.setX(newX);
                asteroid.setY(newY);
            }
        }
    }

    @Override
    public Entity createAsteroid(Entity e, GameData gameData) {
        Entity asteroid = new Asteroid();
        asteroid.setPolygonCoordinates(-20,0,-10,-20,10,-20,20,0,20,20,-10,20);
        asteroid.setX(gameData.getDisplayHeight()/4);
        asteroid.setY(gameData.getDisplayWidth()/4);
        return asteroid;
    }
}
