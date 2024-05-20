package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;


public class EnemyPlugin implements IGamePluginService{
    private Entity enemy;
    
    public EnemyPlugin(){
    }

    @Override
    public void start(GameData gameData, World world){
        enemy = createEnemy(gameData);
        world.addEntity(enemy);
    }

    private Entity createEnemy(GameData gameData){
        Entity enemyShip = new Enemy();
        // enemyShip.setPolygonCoordinates(-5,-5,5,-5,10,5,-10,5);
        enemyShip.setPolygonCoordinates(-5,-5,10,0,-5,5);
        enemyShip.setX(gameData.getDisplayHeight()/3);
        enemyShip.setY(gameData.getDisplayWidth()/3);
        return enemyShip;
        
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(enemy);
    }

}
