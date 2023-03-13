package com.Ja90n.events;

import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.GameMode;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.player.PlayerLoginEvent;
import net.minestom.server.instance.Instance;

public class PlayerJoin {

    public PlayerJoin(GlobalEventHandler globalEventHandler, Instance world) {
        globalEventHandler.addListener(PlayerLoginEvent.class, event -> {
            event.setSpawningInstance(world);
            event.getPlayer().setRespawnPoint(new Pos(0.5,103,0.5));
            event.getPlayer().setGameMode(GameMode.CREATIVE);
        });
    }
}
