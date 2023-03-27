package com.Ja90n.shopitems.items;

import com.Ja90n.enums.Cost;
import com.Ja90n.shopitems.IShopItem;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;

public class WoodItem implements IShopItem {

    @Override
    public Cost getCost() {
        return Cost.SALMON;
    }

    @Override
    public ItemStack getItemStack() {
        return ItemStack.builder(Material.SPRUCE_PLANKS)
                .displayName(Component.text("Wood", NamedTextColor.GOLD))
                .amount(3)
                .build();
    }

    @Override
    public int getCostAmount() {
        return 2;
    }

    @Override
    public int inventoySlot() {
        return 29;
    }
}
