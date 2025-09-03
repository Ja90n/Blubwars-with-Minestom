package com.Ja90n.shopitems.items;

import com.Ja90n.enums.Cost;
import com.Ja90n.shopitems.IShopItem;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.minestom.server.entity.EquipmentSlot;
import net.minestom.server.entity.Player;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;

public class DiamondArmorItem implements IShopItem {

    @Override
    public Cost getCost() {
        return Cost.PUFFERFISH;
    }

    @Override
    public ItemStack getItemStack() {
        return ItemStack.builder(Material.DIAMOND_BOOTS)
                .customName(Component.text("Diamond armor", NamedTextColor.BLUE))
                .build();
    }

    @Override
    public int getCostAmount() {
        return 6;
    }

    @Override
    public int inventoySlot() {
        return 30;
    }

    @Override
    public boolean isArmor() {
        return true;
    }

    @Override
    public void setArmor(Player player) {
        player.getInventory().setEquipment(EquipmentSlot.BOOTS, (byte) 0,ItemStack.builder(Material.DIAMOND_BOOTS).build());
        player.getInventory().setEquipment(EquipmentSlot.LEGGINGS, (byte) 0,ItemStack.builder(Material.DIAMOND_LEGGINGS).build());
    }
}