package dk.sdu.mmmi.cbse.asteroidsystem;

import dk.sdu.mmmi.cbse.common.asteroid.Asteroid;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

public class AsteroidPlugin implements IGamePluginService {

    private Entity asteroid;

    @Override
    public void start(GameData gameData, World world) {
        asteroid = createAsteroid(gameData);
        world.addEntity(asteroid);
    }

    @Override
    public void stop(GameData gameData, World world) {
        for (Entity e : world.getEntities()){
            if (e.getClass() == Asteroid.class){
                world.removeEntity(e);
            }
        }
    }

    public Entity createAsteroid(GameData gameData) {
        Entity asteroid = new Asteroid();
        asteroid.setPolygonCoordinates(-20,-20,20,-20,20,20,-20,20);
        asteroid.setX(gameData.getDisplayHeight()/4);
        asteroid.setY(gameData.getDisplayWidth()/4);
        return asteroid;
    }
}
