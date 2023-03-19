package com.Ja90n.events;

import com.Ja90n.enums.GameState;
import com.Ja90n.instances.Arena;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.player.PlayerBlockBreakEvent;
import net.minestom.server.event.player.PlayerBlockPlaceEvent;

public class PlayerBlockEvents {

    public PlayerBlockEvents(GlobalEventHandler globalEventHandler, Arena arena) {
        globalEventHandler.addListener(PlayerBlockBreakEvent.class, event -> {
            if (arena.getGameState().equals(GameState.LIVE)) {
                if (arena.getGame().getBlockManager().containsBlock(event.getBlockPosition())){
                    arena.getGame().getBlockManager().removeBlock(event.getBlockPosition());
                } else {
                    event.setCancelled(true);
                    event.getPlayer().sendMessage(Component.text("You can't break this block!", NamedTextColor.RED));
                }
            } else {
                event.setCancelled(true);
            }
        });

        globalEventHandler.addListener(PlayerBlockPlaceEvent.class, event -> {
            if (arena.getGameState().equals(GameState.LIVE)){
                arena.getGame().getBlockManager().addBlock(event.getBlockPosition());
            } else {
                event.setCancelled(true);
            }
        });
    }
}
