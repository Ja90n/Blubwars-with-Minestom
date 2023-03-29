package com.Ja90n.events;

import net.minestom.server.entity.GameMode;
import net.minestom.server.entity.ItemEntity;
import net.minestom.server.entity.Player;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.item.ItemDropEvent;
import net.minestom.server.event.item.PickupItemEvent;
import net.minestom.server.item.ItemStack;
import net.minestom.server.utils.time.TimeUnit;

public class ItemEvents {

    public ItemEvents(GlobalEventHandler globalEventHandler){
        globalEventHandler.addListener(ItemDropEvent.class, event -> {

            if (event.getPlayer().getGameMode().equals(GameMode.SPECTATOR)) {
                return;
            }

            ItemStack item = event.getItemStack();

            ItemEntity itemEntity = new ItemEntity(item);
            itemEntity.setPickupDelay(40, TimeUnit.SERVER_TICK);
            itemEntity.setInstance(event.getInstance(), event.getPlayer().getPosition().add(0, 1.5, 0));
            itemEntity.setVelocity(event.getPlayer().getPosition().direction().mul(6));
        });

        globalEventHandler.addListener(PickupItemEvent.class, event -> {
           if (event.getEntity() instanceof Player player) {
               player.getInventory().addItemStack(event.getItemStack());
           }
        });
    }
}
