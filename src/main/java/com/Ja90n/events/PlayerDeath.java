package com.Ja90n.events;

import com.Ja90n.enums.GameState;
import com.Ja90n.instances.Arena;
import com.Ja90n.managers.ConfigManager;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.item.ItemDropEvent;
import net.minestom.server.event.player.PlayerDeathEvent;
import net.minestom.server.event.player.PlayerRespawnEvent;

public class PlayerDeath {

    public PlayerDeath(GlobalEventHandler globalEventHandler, Arena arena, ConfigManager configManager) {
        globalEventHandler.addListener(PlayerDeathEvent.class, event -> {

        });

        globalEventHandler.addListener(PlayerRespawnEvent.class, event -> {
            if (arena.getGameState().equals(GameState.LIVE)){
                event.setRespawnPosition(configManager.getTeamSpawn(arena.getGame().getTeamManager().getTeam(event.getPlayer())));
            } else {
                event.setRespawnPosition(configManager.getLobbyLocation());
            }
        });
    }
}
