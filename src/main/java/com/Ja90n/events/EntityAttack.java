package com.Ja90n.events;

import com.Ja90n.enums.GameState;
import com.Ja90n.instances.Arena;
import com.Ja90n.instances.Team;
import com.Ja90n.instances.TeamCat;
import io.github.bloepiloepi.pvp.events.FinalAttackEvent;
import net.kyori.adventure.sound.Sound;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.minestom.server.entity.*;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.sound.SoundEvent;

public class EntityAttack {

    public EntityAttack(GlobalEventHandler globalEventHandler, Arena arena) {
        globalEventHandler.addListener(FinalAttackEvent.class, event -> {

            if (event.getTarget().getEntityType().equals(EntityType.CAT)) {
                EntityCreature entityCreature = (EntityCreature) event.getTarget();
                TeamCat teamCat = arena.getGame().getTeamManager().getCat(entityCreature);

                if (teamCat == null){
                    return;
                }

                if (!(event.getEntity() instanceof Player player)){
                    return;
                }

                if (teamCat.getTeam().getTeamType().equals(arena.getGame().getTeamManager().getTeam(player).getTeamType())) {
                    event.setCancelled(true);
                    player.openInventory(teamCat.getCatChest());
                }
            } else if (event.getTarget() instanceof Player target) {
                if (!(event.getEntity() instanceof Player player)){
                    return;
                }

                if (!arena.getGameState().equals(GameState.LIVE)) {
                    event.setCancelled(true);
                    return;
                }

                Team team = arena.getGame().getTeamManager().getTeam(player);
                Team targetTeam = arena.getGame().getTeamManager().getTeam(target);
                if (team.equals(targetTeam)) {
                    event.setCancelled(true);
                }
            }
        });
    }
}
