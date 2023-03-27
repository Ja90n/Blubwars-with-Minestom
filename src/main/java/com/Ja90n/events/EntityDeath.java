package com.Ja90n.events;

import com.Ja90n.instances.Arena;
import com.Ja90n.instances.TeamCat;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
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
            arena.sendMessage(teamCat.getTeam().getTeamType().getDisplay().append(Component.text(" 's cat died!")));
            arena.sendMessage(Component.text("It has ").append(Component.text(teamCat.getLives()).append(Component.text(" lives left!"))));
            teamCat.died();
        });
    }
}
