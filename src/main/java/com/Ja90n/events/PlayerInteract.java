package com.Ja90n.events;

import com.Ja90n.instances.Arena;
import net.minestom.server.entity.GameMode;
import net.minestom.server.entity.Player;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.player.PlayerUseItemEvent;
import net.minestom.server.item.Material;

public class PlayerInteract {

    public PlayerInteract(GlobalEventHandler globalEventHandler, Arena arena) {
        globalEventHandler.addListener(PlayerUseItemEvent.class, event -> {

            if (!event.getPlayer().getGameMode().equals(GameMode.ADVENTURE)) {
                return;
            }

            if (!event.getHand().equals(Player.Hand.MAIN)) {
                return;
            }

            if (event.getItemStack().material().equals(Material.OAK_DOOR)) {
                arena.getGame().getPlayerManager().setRespawnPreference(event.getPlayer(),0);
            } else if (event.getItemStack().material().equals(Material.CAT_SPAWN_EGG)) {
                arena.getGame().getPlayerManager().setRespawnPreference(event.getPlayer(),1);
            }

            event.setCancelled(true);
        });
    }
}
