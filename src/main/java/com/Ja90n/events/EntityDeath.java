package com.Ja90n.events;

import com.Ja90n.instances.Arena;
import com.Ja90n.instances.TeamCat;
import net.minestom.server.entity.EntityCreature;
import net.minestom.server.entity.EntityType;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.entity.EntityDeathEvent;

public class EntityDeath {

    public EntityDeath(GlobalEventHandler globalEventHandler, Arena arena) {
        globalEventHandler.addListener(EntityDeathEvent.class, event -> {
            if (!event.getEntity().getEntityType().equals(EntityType.CAT)) {
                return;
            }

            EntityCreature entityCreature = (EntityCreature) event.getEntity();
            TeamCat teamCat = arena.getGame().getTeamManager().getCat(entityCreature);
            if (teamCat == null) {
                return;
            }
            teamCat.removeLife();
            if (teamCat.getLives() <= -1) {
                teamCat.getTeam().setCatAlive(false);
                return;
            }
            teamCat.respawnCat();
        });
    }
}
