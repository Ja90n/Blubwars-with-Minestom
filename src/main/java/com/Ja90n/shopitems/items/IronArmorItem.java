package com.Ja90n.shopitems.items;

import com.Ja90n.enums.Cost;
import com.Ja90n.shopitems.IShopItem;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.minestom.server.entity.Player;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;

public class IronArmorItem implements IShopItem {

    @Override
    public Cost getCost() {
        return Cost.SALMON;
    }

    @Override
    public ItemStack getItemStack() {
        return ItemStack.builder(Material.IRON_BOOTS)
                .displayName(Component.text("Iron armor", NamedTextColor.GRAY))
                .build();
    }

    @Override
    public int getCostAmount() {
        return 10;
    }

    @Override
    public int inventoySlot() {
        return 21;
    }

    @Override
    public boolean isArmor() {
        return true;
    }

    @Override
    public void setArmor(Player player) {
        player.getInventory().setBoots(ItemStack.builder(Material.IRON_BOOTS).build());
        player.getInventory().setLeggings(ItemStack.builder(Material.IRON_LEGGINGS).build());
    }
}
