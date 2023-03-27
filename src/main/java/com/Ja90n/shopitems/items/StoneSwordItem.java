package com.Ja90n.shopitems.items;

import com.Ja90n.enums.Cost;
import com.Ja90n.shopitems.IShopItem;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;

public class StoneSwordItem implements IShopItem {

    @Override
    public Cost getCost() {
        return Cost.COD;
    }

    @Override
    public ItemStack getItemStack() {
        return ItemStack.builder(Material.STONE_SWORD)
                .displayName(Component.text("Stone sword", NamedTextColor.DARK_GRAY))
                .build();
    }

    @Override
    public int getCostAmount() {
        return 5;
    }

    @Override
    public int inventoySlot() {
        return 10;
    }
}
