package com.Ja90n.instances;

import com.Ja90n.Blubwars;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.EntityCreature;
import net.minestom.server.entity.EntityType;
import net.minestom.server.entity.metadata.villager.VillagerMeta;

public class ShopVillager {

    public ShopVillager(Pos pos) {
        EntityCreature entityCreature = new EntityCreature(EntityType.VILLAGER);
        VillagerMeta villagerMeta = (VillagerMeta) entityCreature.getEntityMeta();
        villagerMeta.setVillagerData(new VillagerMeta.VillagerData(VillagerMeta.Type.SNOW, VillagerMeta.Profession.NONE, VillagerMeta.Level.MASTER));
        entityCreature.setInvulnerable(true);
        entityCreature.setInstance(Blubwars.getWorld().getInstance(),pos);
    }
}
