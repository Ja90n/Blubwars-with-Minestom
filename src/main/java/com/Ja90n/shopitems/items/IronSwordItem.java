package com.Ja90n.shopitems.items;

import com.Ja90n.enums.Cost;
import com.Ja90n.shopitems.IShopItem;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;

public class IronSwordItem implements IShopItem {

    @Override
    public Cost getCost() {
        return Cost.SALMON;
    }

    @Override
    public ItemStack getItemStack() {
        return ItemStack.builder(Material.IRON_SWORD)
                .customName(Component.text("Iron sword", NamedTextColor.GRAY))
                .build();
    }

    @Override
    public int getCostAmount() {
        return 7;
    }

    @Override
    public int inventoySlot() {
        return 19;
    }
}
