package com.Ja90n.events;

import net.minestom.server.entity.EntityType  ;
import net.minestom.server.entity.Player;
import net.minestom.server.entity.metadata.animal.tameable.CatMeta;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.player.PlayerEntityInteractEvent;

public class InteractEntity {

    public InteractEntity(GlobalEventHandler globalEventHandler){
        globalEventHandler.addListener(PlayerEntityInteractEvent.class, event -> {
            if (event.getTarget().getEntityType().equals(EntityType.CAT)) {
                if (event.getHand().equals(Player.Hand.MAIN)) {
                    CatMeta catMeta = (CatMeta) event.getTarget().getEntityMeta();
                    catMeta.setSitting(!catMeta.isSitting());
                    catMeta.setTamed(true);
                }
            }
        });

    }
}
