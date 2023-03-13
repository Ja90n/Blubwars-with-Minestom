package com.Ja90n.events;

import net.minestom.server.entity.ItemEntity;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.item.ItemDropEvent;
import net.minestom.server.item.ItemStack;
import net.minestom.server.utils.time.TimeUnit;

public class ItemDrop {

    public ItemDrop(GlobalEventHandler globalEventHandler){
        globalEventHandler.addListener(ItemDropEvent.class, event -> {
            ItemStack item = event.getItemStack();

            ItemEntity itemEntity = new ItemEntity(item);
            itemEntity.setPickupDelay(40, TimeUnit.SERVER_TICK);
            itemEntity.setInstance(event.getInstance(), event.getPlayer().getPosition().add(0, 1.5, 0));
            itemEntity.setVelocity(event.getPlayer().getPosition().direction().mul(6));
        });
    }
}
