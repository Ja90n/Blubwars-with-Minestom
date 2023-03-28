package com.Ja90n.events;

import com.Ja90n.instances.Arena;
import com.Ja90n.instances.TeamCat;
import com.Ja90n.inventories.ShopGUI;
import net.minestom.server.entity.EntityCreature;
import net.minestom.server.entity.EntityType;
import net.minestom.server.entity.Player;
import net.minestom.server.entity.metadata.animal.tameable.CatMeta;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.player.PlayerEntityInteractEvent;
import net.minestom.server.item.Material;
import net.minestom.server.potion.Potion;
import net.minestom.server.potion.PotionEffect;

public class InteractEntity {

    public InteractEntity(GlobalEventHandler globalEventHandler, Arena arena){
        globalEventHandler.addListener(PlayerEntityInteractEvent.class, event -> {
            if (!event.getTarget().getEntityType().equals(EntityType.CAT)) {
                if (event.getTarget().getEntityType().equals(EntityType.VILLAGER)){
                    event.getPlayer().openInventory(new ShopGUI(arena.getGame().getTeamManager().getTeam(event.getPlayer()).getTeamType()).getInventory());
                }
                return;
            }

            if (!event.getHand().equals(Player.Hand.MAIN)) {
                return;
            }

            EntityCreature entityCreature = (EntityCreature) event.getTarget();
            TeamCat teamCat = arena.getGame().getTeamManager().getCat(entityCreature);
            if (teamCat == null) {
                return;
            }

            if (!teamCat.getTeam().containsPlayer(event.getPlayer())) {
                if (event.getPlayer().getItemInMainHand().material().equals(Material.PUFFERFISH)) {
                    event.getTarget().addEffect(new Potion(PotionEffect.POISON, (byte) 10,5));
                }
                return;
            }

            CatMeta catMeta = (CatMeta) event.getTarget().getEntityMeta();
            catMeta.setSitting(!catMeta.isSitting());
            catMeta.setTamed(true);
        });

    }
}
