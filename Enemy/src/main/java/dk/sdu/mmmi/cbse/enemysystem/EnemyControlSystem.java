package dk.sdu.mmmi.cbse.enemysystem;

import java.util.Collection;
import java.util.ServiceLoader;

import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameKeys;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import static java.util.stream.Collectors.toList;

public class EnemyControlSystem implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {
        // UnsupportedOperationException("Unimplemented method 'process'");

        double minX = 0;
        double minY = 0;
        double maxX = gameData.getDisplayWidth();
        double maxY = gameData.getDisplayHeight();

        for (Entity enemy : world.getEntities(Enemy.class)) {
            double changeX = Math.random();
            double changeY = Math.random();
            //Angle of movement
            // double angle = Math.random() * 2 * Math.PI;
            // double directionMoveX = Math.cos(angle);
            // double directionMoveY = Math.sin(angle);
            // enemy.setRotation(enemy.getX() + directionMoveX);
            // enemy.setY(enemy.getY() + directionMoveY);

            if (changeX > 0.5) {
                enemy.setRotation(enemy.getRotation() - 15);
            }
            if (changeX < 0.5) {
                enemy.setRotation(enemy.getRotation() + 15);
            }


            // Forward 
            double directionX = Math.cos(Math.toRadians(enemy.getRotation()));
            double directionY = Math.sin(Math.toRadians(enemy.getRotation()));
            enemy.setX(enemy.getX() + directionX);
            enemy.setY(enemy.getY() + directionY);
            
            // Make the enemy stay inside the screen
            double newX = enemy.getX() + directionX;
            double newY = enemy.getY() + directionY;

            newX = Math.min(Math.max(newX, minX), maxX);
            newY = Math.min(Math.max(newY, minY), maxY);

            enemy.setX(newX);
            enemy.setY(newY);

            //Enemy shoots
            double shouldEnemyShoot = Math.random();

            if (shouldEnemyShoot > 0.9){
                getBulletSPIs().stream().findFirst().ifPresent(
                    spi -> {world.addEntity(spi.createBullet(enemy, gameData));}
                );
            }

    }
}

    private Collection<? extends BulletSPI> getBulletSPIs() {
        return ServiceLoader.load(BulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
