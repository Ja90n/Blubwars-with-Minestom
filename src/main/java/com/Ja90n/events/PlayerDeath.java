package com.Ja90n.events;

import com.Ja90n.Blubwars;
import com.Ja90n.enums.GameState;
import com.Ja90n.instances.Arena;
import com.Ja90n.managers.ConfigManager;
import com.Ja90n.runnables.PlayerRespawn;
import net.kyori.adventure.text.Component;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.player.PlayerDeathEvent;
import net.minestom.server.event.player.PlayerRespawnEvent;

public class PlayerDeath {

    public PlayerDeath(GlobalEventHandler globalEventHandler, Arena arena) {

        ConfigManager configManager = Blubwars.getConfigManager();

        globalEventHandler.addListener(PlayerDeathEvent.class, event -> {
            event.setDeathText(Component.text(""));
            event.getPlayer().getInventory().clear();
            event.getPlayer().respawn();
            event.getPlayer().teleport(Blubwars.getConfigManager().getRespawnLocation());
        });

        globalEventHandler.addListener(PlayerRespawnEvent.class, event -> {
            event.setRespawnPosition(Blubwars.getConfigManager().getRespawnLocation());
            event.getPlayer().teleport(Blubwars.getConfigManager().getRespawnLocation());
            new PlayerRespawn(event.getPlayer(), arena);
        });
    }
}
