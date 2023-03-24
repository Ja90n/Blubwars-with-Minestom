package com.Ja90n.runnables;

import net.minestom.server.MinecraftServer;
import net.minestom.server.entity.Entity;
import net.minestom.server.entity.EntityCreature;
import net.minestom.server.entity.EntityType;
import net.minestom.server.entity.metadata.animal.tameable.TameableAnimalMeta;
import net.minestom.server.timer.Task;
import net.minestom.server.timer.TaskSchedule;

public class CatMovementRunnable {

    private EntityCreature entityCreature;
    private Entity target;
    private Task task;

    public CatMovementRunnable(EntityCreature entityCreature, Entity target) {
        this.entityCreature = entityCreature;
        this.target = target;
    }

    public void start() {
        task = MinecraftServer.getSchedulerManager().scheduleTask(() -> {
            if (entityCreature == null) {
                return;
            }

            // If it is a cat
            if (!entityCreature.getEntityType().equals(EntityType.CAT)){
                return;
            }

            // If it is not sitting
            TameableAnimalMeta tameableAnimalMeta = (TameableAnimalMeta) entityCreature.getEntityMeta();
            if (tameableAnimalMeta.isSitting()) {
                return;
            }

            // If it is not in a 1 block range
            if (entityCreature.getDistance(target.getPosition()) < 3) {
                return;
            }

            entityCreature.getNavigator().setPathTo(target.getPosition());

        }, TaskSchedule.tick(20),TaskSchedule.tick(20));
    }

    public void setEntityCreature(EntityCreature entityCreature) {
        this.entityCreature = entityCreature;
    }

    public void setTarget(Entity target) {
        this.target = target;
    }

    public void stop() {
        task.cancel();
    }
}
