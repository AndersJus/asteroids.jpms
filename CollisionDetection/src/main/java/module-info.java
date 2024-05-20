import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

module CollisionDetection {
    requires Common;
    provides IPostEntityProcessingService with dk.sdu.mmmi.cbse.collisionDetectionSystem.CollisionDetector;
}