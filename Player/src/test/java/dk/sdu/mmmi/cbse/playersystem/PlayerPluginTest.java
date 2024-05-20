package dk.sdu.mmmi.cbse.playersystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class PlayerPluginTest {

    @Test
    public void testPlayerShipCreation() {
        // Create mock GameData and World objects
        GameData gameData = new GameDataMock();
        World world = new World();

        // Create an instance of PlayerPlugin
        PlayerPlugin playerPlugin = new PlayerPlugin();

        // Call the start method of PlayerPlugin
        playerPlugin.start(gameData, world);

        Entity playerShip = new Player();
        playerShip.setPolygonCoordinates(-5,-5,10,0,-5,5);
        playerShip.setX(gameData.getDisplayHeight()/2);
        playerShip.setY(gameData.getDisplayWidth()/2);
        playerShip.setRadius(8);

        world.addEntity(playerShip);

        // Get the list of entities in the world
        Entity[] entities = world.getEntities().toArray(new Entity[0]);

        // Check if there is at least one entity in the world
        assertTrue(world.getEntities().contains(playerShip), "Player ship should have been added to the world");

        // Check if the added entity is a player ship
        assertTrue(entities[0] instanceof Player, "Added entity should be a Player");

        //Saves the initial value for the first coordinate of the player ship
        double initialPolygonCoordinate = entities[0].getPolygonCoordinates()[0];

        // Alters the polygon coordinate of the player ship
        entities[0].setPolygonCoordinates(-15,-5,10,0,-5,5);

        // Check if the player ship's coordinates are changed from the initial
        assertTrue(entities[0].getPolygonCoordinates()[0] != initialPolygonCoordinate,"Error with polygon coordinate" );

        // Clean up by stopping the PlayerPlugin
        playerPlugin.stop(gameData, world);
    }


    // Mock class for GameData
    private static class GameDataMock extends GameData {
        @Override
        public int getDisplayWidth() {
            return 800; // Example value
        }

        @Override
        public int getDisplayHeight() {
            return 600; // Example value
        }
    }
}