package com.Ja90n.shopitems.items;

import com.Ja90n.enums.Cost;
import com.Ja90n.shopitems.IShopItem;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;

public class PickaxeItem implements IShopItem {

    @Override
    public Cost getCost() {
        return Cost.COD;
    }

    @Override
    public ItemStack getItemStack() {
        return ItemStack.builder(Material.STONE_PICKAXE).build();
    }

    @Override
    public int getCostAmount() {
        return 10;
    }

    @Override
    public int inventoySlot() {
        return 22;
    }
}
