package com.Ja90n.events;

import com.Ja90n.instances.Arena;
import com.Ja90n.managers.ConfigManager;
import com.Ja90n.runnables.CatMovementRunnable;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.*;
import net.minestom.server.entity.metadata.animal.tameable.CatMeta;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.player.PlayerLoginEvent;
import net.minestom.server.event.player.PlayerSpawnEvent;
import net.minestom.server.instance.Instance;
import net.minestom.server.permission.Permission;

import java.util.UUID;

public class PlayerJoin {

    public PlayerJoin(GlobalEventHandler globalEventHandler, Instance world, Arena arena) {
        globalEventHandler.addListener(PlayerLoginEvent.class, event -> {
            event.setSpawningInstance(world);
            if (event.getPlayer().getUuid().equals(UUID.fromString("f8788f7c-7cd5-44ed-ab6f-aad79d7e5fc7"))){
                event.getPlayer().setPermissionLevel(4);
            }
        });

        globalEventHandler.addListener(PlayerSpawnEvent.class, event -> {
            event.getPlayer().teleport(new Pos(0.5,103,0.5));
            event.getPlayer().setGameMode(GameMode.ADVENTURE);
            arena.addPlayer(event.getPlayer());
        });
    }
}
