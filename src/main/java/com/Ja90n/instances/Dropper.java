package com.Ja90n.instances;

import com.Ja90n.enums.Cost;
import net.kyori.adventure.text.Component;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.Entity;
import net.minestom.server.entity.EntityType;
import net.minestom.server.entity.ItemEntity;
import net.minestom.server.entity.metadata.other.ArmorStandMeta;
import net.minestom.server.instance.Instance;
import net.minestom.server.item.ItemStack;
import net.minestom.server.utils.time.TimeUnit;

public class Dropper {

    private Pos pos;
    private Instance instance;
    private Cost type;
    private int ticksToDrop;
    private Entity entity;
    private ArmorStandMeta armorStandMeta;

    public Dropper(Pos pos, Instance instance, Cost type) {
        this.pos = pos;
        this.type = type;
        this.instance = instance;
        ticksToDrop = type.getDropDelay();

        if (type.equals(Cost.TROPICAL_FISH) || type.equals(Cost.PUFFERFISH)) {
            entity = new Entity(EntityType.ARMOR_STAND);
            entity.setInstance(instance,pos);
            armorStandMeta = (ArmorStandMeta) entity.getEntityMeta();
            armorStandMeta.setCustomNameVisible(true);
            armorStandMeta.setHasNoGravity(true);
            armorStandMeta.setInvisible(true);
            armorStandMeta.setHasNoBasePlate(true);
            armorStandMeta.setCustomName(type.getComponent().append(Component.text(" dropping in: ").append(Component.text(ticksToDrop))));
        }
    }

    public void tick() {
        ticksToDrop--;
        if (entity != null) {
            armorStandMeta.setCustomName(type.getComponent().append(Component.text(" dropping in: ").append(Component.text(ticksToDrop))));
        }
        if (ticksToDrop <= 0) {
            ItemStack item = ItemStack.builder(type.getMaterial()).customName(type.getComponent()).build();
            ItemEntity itemEntity = new ItemEntity(item);
            itemEntity.setPickupDelay(40, TimeUnit.SERVER_TICK);
            itemEntity.setInstance(instance, pos);

            ticksToDrop = type.getDropDelay();
        }
    }
}
