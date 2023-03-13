package com.Ja90n.events;

import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.item.ItemDropEvent;
import net.minestom.server.event.player.PlayerDeathEvent;

public class PlayerDeath {

    public PlayerDeath(GlobalEventHandler globalEventHandler) {
        globalEventHandler.addListener(PlayerDeathEvent.class, event -> {
            event.getPlayer().getInstance().saveChunksToStorage();
        });
    }
}
