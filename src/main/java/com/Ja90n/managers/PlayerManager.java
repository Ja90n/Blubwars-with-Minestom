package com.Ja90n.managers;

import com.Ja90n.enums.TeamType;
import com.Ja90n.shopitems.IShopItem;
import net.minestom.server.entity.EquipmentSlot;
import net.minestom.server.entity.Player;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;

import java.util.HashMap;
import java.util.UUID;

public class PlayerManager {

    public HashMap<UUID, IShopItem> armor;

    // 0 means base and 1 means cat
    public HashMap<UUID, Integer> respawnPreference;

    public PlayerManager() {
        armor = new HashMap<>();
        respawnPreference = new HashMap<>();
    }

    public void addPlayer(Player player, IShopItem shopItem) {
        armor.put(player.getUuid(),shopItem);
    }

    public void setInventory(Player player, TeamType teamType) {
        setTools(player);
        setArmor(player,teamType);
    }

    public void setRespawnPreference(Player player, int preference) {
        respawnPreference.put(player.getUuid(),preference);
    }

    public int getPreference(Player player) {
        if (!respawnPreference.containsKey(player.getUuid())) {
            respawnPreference.put(player.getUuid(),0);
        }
        return respawnPreference.get(player.getUuid());
    }

    private void setTools(Player player) {
        player.getInventory().addItemStack(ItemStack.builder(Material.WOODEN_SWORD).build());
    }

    private void setArmor(Player player, TeamType teamType) {

        ItemStack helmet = ItemStack.builder(Material.LEATHER_HELMET)
                .build();

        ItemStack chestplate = ItemStack.builder(Material.LEATHER_CHESTPLATE)
                .build();

        ItemStack leggings = ItemStack.builder(Material.LEATHER_LEGGINGS)
                .build();

        ItemStack boots = ItemStack.builder(Material.LEATHER_BOOTS)
                .build();

        player.getInventory().setEquipment(EquipmentSlot.BOOTS, (byte) 0,helmet);
        player.getInventory().setEquipment(EquipmentSlot.CHESTPLATE, (byte) 0,chestplate);
        player.getInventory().setEquipment(EquipmentSlot.LEGGINGS, (byte) 0,leggings);
        player.getInventory().setEquipment(EquipmentSlot.BOOTS, (byte) 0,boots);


        if (armor.containsKey(player.getUuid())) {
            armor.get(player.getUuid()).setArmor(player);
        }
    }
}
