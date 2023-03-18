package com.Ja90n.events;

import com.Ja90n.enums.GameState;
import com.Ja90n.instances.Arena;
import net.minestom.server.entity.Entity;
import net.minestom.server.entity.LivingEntity;
import net.minestom.server.entity.damage.DamageType;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.entity.EntityAttackEvent;

public class EntityAttack {

    public EntityAttack(GlobalEventHandler globalEventHandler, Arena arena) {
        globalEventHandler.addListener(EntityAttackEvent.class, event -> {
            if (!(event.getTarget() instanceof LivingEntity target)) return;

            if (arena.getGameState().equals(GameState.LIVE)){
                target.damage(DamageType.fromEntity(event.getEntity()),1);
                addKnockback(target,event.getEntity());
            }
        });
    }

    private void addKnockback(Entity target, Entity source){
        target.takeKnockback(
                0.3f,
                Math.sin(source.getPosition().yaw() * (Math.PI / 180)),
                -Math.cos(source.getPosition().yaw() * (Math.PI / 180))
        );
    }
}
