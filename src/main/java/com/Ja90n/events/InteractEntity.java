package com.Ja90n.events;

import com.Ja90n.instances.Arena;
import com.Ja90n.instances.TeamCat;
import net.minestom.server.entity.EntityCreature;
import net.minestom.server.entity.EntityType  ;
import net.minestom.server.entity.Player;
import net.minestom.server.entity.metadata.animal.tameable.CatMeta;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.player.PlayerEntityInteractEvent;

public class InteractEntity {

    public InteractEntity(GlobalEventHandler globalEventHandler, Arena arena){
        globalEventHandler.addListener(PlayerEntityInteractEvent.class, event -> {
            if (!event.getTarget().getEntityType().equals(EntityType.CAT)) {
                return;
            }

            if (!event.getHand().equals(Player.Hand.MAIN)) {
                return;
            }

            EntityCreature entityCreature = (EntityCreature) event.getTarget();
            TeamCat teamCat = arena.getGame().getTeamManager().getCat(entityCreature);
            if (teamCat == null) {
                return;
            }

            if (!teamCat.getTeam().containsPlayer(event.getPlayer())) {
                return;
            }

            CatMeta catMeta = (CatMeta) event.getTarget().getEntityMeta();
            catMeta.setSitting(!catMeta.isSitting());
            catMeta.setTamed(true);
        });

    }
}
