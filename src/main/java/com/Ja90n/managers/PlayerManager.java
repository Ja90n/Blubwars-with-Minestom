package com.Ja90n.managers;

import com.Ja90n.enums.TeamType;
import com.Ja90n.shopitems.IShopItem;
import net.minestom.server.entity.Player;
import net.minestom.server.item.ItemMeta;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;
import net.minestom.server.item.metadata.LeatherArmorMeta;

import java.util.HashMap;
import java.util.UUID;

public class PlayerManager {

    public HashMap<UUID, IShopItem> armor;

    public PlayerManager() {
        armor = new HashMap<>();
    }

    public void addPlayer(Player player, IShopItem shopItem) {
        armor.put(player.getUuid(),shopItem);
    }

    public void setInventory(Player player, TeamType teamType) {
        setTools(player);
        setArmor(player,teamType);
    }

    private void setTools(Player player) {
        player.getInventory().addItemStack(ItemStack.builder(Material.WOODEN_SWORD).build());
    }

    private void setArmor(Player player, TeamType teamType) {
        ItemMeta leatherArmorMeta = new LeatherArmorMeta.Builder().color(teamType.getColor()).build();

        ItemStack helmet = ItemStack.builder(Material.LEATHER_HELMET)
                .meta(leatherArmorMeta)
                .build();

        ItemStack chestplate = ItemStack.builder(Material.LEATHER_CHESTPLATE)
                .meta(leatherArmorMeta)
                .build();

        ItemStack leggings = ItemStack.builder(Material.LEATHER_LEGGINGS)
                .meta(leatherArmorMeta)
                .build();

        ItemStack boots = ItemStack.builder(Material.LEATHER_BOOTS)
                .meta(leatherArmorMeta)
                .build();

        player.getInventory().setHelmet(helmet);
        player.getInventory().setChestplate(chestplate);
        player.getInventory().setLeggings(leggings);
        player.getInventory().setBoots(boots);

        if (armor.containsKey(player.getUuid())) {
            armor.get(player.getUuid()).setArmor(player);
        }
    }
}
