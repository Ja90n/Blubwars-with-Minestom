package com.Ja90n.events;

import net.minestom.server.entity.LivingEntity;
import net.minestom.server.entity.damage.DamageType;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.entity.EntityAttackEvent;
import net.minestom.server.event.player.PlayerBlockBreakEvent;

public class BlockBreak {

    public BlockBreak(GlobalEventHandler globalEventHandler) {
        globalEventHandler.addListener(PlayerBlockBreakEvent.class, event -> {

        });
    }
}
