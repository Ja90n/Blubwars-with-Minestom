package com.Ja90n.events;

import com.Ja90n.instances.Arena;
import net.kyori.adventure.sound.Sound;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.minestom.server.entity.GameMode;
import net.minestom.server.entity.Player;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.player.PlayerUseItemEvent;
import net.minestom.server.item.Material;
import net.minestom.server.sound.SoundEvent;

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
                event.getPlayer().sendMessage(Component.text("You are now respawning at your base!", NamedTextColor.BLUE));
                event.getPlayer().playSound(Sound.sound(SoundEvent.BLOCK_LEVER_CLICK, Sound.Source.MASTER,1f,1f));
            } else if (event.getItemStack().material().equals(Material.CAT_SPAWN_EGG)) {
                arena.getGame().getPlayerManager().setRespawnPreference(event.getPlayer(),1);
                event.getPlayer().sendMessage(Component.text("You are now respawning at your cat!", NamedTextColor.GREEN));
                event.getPlayer().playSound(Sound.sound(SoundEvent.BLOCK_LEVER_CLICK, Sound.Source.MASTER,1f,1f));
            }

            event.setCancelled(true);
        });
    }
}
